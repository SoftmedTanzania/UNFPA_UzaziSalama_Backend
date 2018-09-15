package org.opensrp.web.controller;

import com.google.gson.Gson;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opensrp.common.AllConstants;
import org.opensrp.domain.*;
import org.opensrp.dto.*;
import org.opensrp.form.domain.FormData;
import org.opensrp.form.domain.FormInstance;
import org.opensrp.form.domain.FormSubmission;
import org.opensrp.form.service.FormSubmissionService;
import org.opensrp.repository.*;
import org.opensrp.scheduler.SystemEvent;
import org.opensrp.scheduler.TaskSchedulerService;
import org.opensrp.service.ClientConverter;
import org.opensrp.service.GoogleFCMService;
import org.opensrp.service.RapidProServiceImpl;
import org.opensrp.service.ReferralPatientsService;
import org.opensrp.service.formSubmission.FormEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ReferralPatientsController {
	private static Logger logger = LoggerFactory.getLogger(ReferralPatientsController.class.toString());
	private ReferralPatientsService patientsService;
	private ANCClientsRepository ancClientsRepository;
	private HealthFacilityRepository healthFacilityRepository;
	private HealthFacilitiesClientsRepository healthFacilitiesClientsRepository;
	private ANCRoutineVisitsRepository ANCRoutineVisitsRepository;
	private PatientsAppointmentsRepository patientsAppointmentsRepository;
	private PatientReferralRepository patientReferralRepository;
	private PatientReferralIndicatorRepository patientReferralIndicatorRepository;
	private GooglePushNotificationsUsersRepository googlePushNotificationsUsersRepository;
	private PNCClientsRepository pncClientsRepository;
	private FormSubmissionService formSubmissionService;
	private FormEntityConverter formEntityConverter;
	private TaskSchedulerService scheduler;
	private GoogleFCMService googleFCMService;
	private ReferralPatientsService referralClientService;
	private RapidProServiceImpl rapidProService;
	@Autowired
	public ReferralPatientsController(ReferralPatientsService patientsService, ANCClientsRepository ancClientsRepository, TaskSchedulerService scheduler,
	                                  HealthFacilityRepository healthFacilityRepository, HealthFacilitiesClientsRepository healthFacilitiesClientsRepository, PatientsAppointmentsRepository patientsAppointmentsRepository,
	                                  ANCRoutineVisitsRepository ANCRoutineVisitsRepository, PatientReferralRepository patientReferralRepository, PNCClientsRepository pncClientsRepository, FormSubmissionService formSubmissionService,
	                                  FormEntityConverter formEntityConverter, GooglePushNotificationsUsersRepository googlePushNotificationsUsersRepository, GoogleFCMService googleFCMService,
	                                  PatientReferralIndicatorRepository patientReferralIndicatorRepository, ReferralPatientsService referralClientService, RapidProServiceImpl rapidProService) {
		this.patientsService = patientsService;
		this.ancClientsRepository = ancClientsRepository;
		this.scheduler = scheduler;
		this.healthFacilityRepository = healthFacilityRepository;
		this.healthFacilitiesClientsRepository = healthFacilitiesClientsRepository;
		this.patientsAppointmentsRepository = patientsAppointmentsRepository;
		this.ANCRoutineVisitsRepository = ANCRoutineVisitsRepository;
		this.patientReferralRepository = patientReferralRepository;
		this.pncClientsRepository = pncClientsRepository;
		this.formSubmissionService = formSubmissionService;
		this.formEntityConverter = formEntityConverter;
		this.googlePushNotificationsUsersRepository = googlePushNotificationsUsersRepository;
		this.googleFCMService = googleFCMService;
		this.patientReferralIndicatorRepository = patientReferralIndicatorRepository;
		this.referralClientService = referralClientService;
		this.rapidProService = rapidProService;
	}

	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/save-anc-client")
	public ResponseEntity<AncClientReferralsDTO> saveAncClient(@RequestBody String json) {
		AncClientDTO ancClientDTO = new Gson().fromJson(json, AncClientDTO.class);
		try {
			if (ancClientDTO ==null) {
				return new ResponseEntity<>(BAD_REQUEST);
			}
			scheduler.notifyEvent(new SystemEvent<>(AllConstants.OpenSRPEvent.REFERRED_PATIENTS_SUBMISSION, ancClientDTO));

			ANCClients patient = ClientConverter.toANCClient(ancClientDTO);
			final long healthfacilityPatientId = referralClientService.saveClient(patient, ancClientDTO.getHealthFacilityCode(), "");

			ancClientDTO.setClientId(healthfacilityPatientId);

			Long[] healthFacilityPatientArg = new Long[1];
			healthFacilityPatientArg[0] =  healthfacilityPatientId;

			Object[] facilityParams = new Object[]{ancClientDTO.getHealthFacilityCode(), 1};
			List<GooglePushNotificationsUsers> googlePushNotificationsUsers = googlePushNotificationsUsersRepository.getGooglePushNotificationsUsers("SELECT * FROM " + GooglePushNotificationsUsers.tbName + " WHERE " + GooglePushNotificationsUsers.COL_FACILITY_UIID + " = ? AND " + GooglePushNotificationsUsers.COL_USER_TYPE + " = ?", facilityParams);
			JSONArray tokens = new JSONArray();
			for (GooglePushNotificationsUsers googlePushNotificationsUsers1 : googlePushNotificationsUsers) {
				tokens.put(googlePushNotificationsUsers1.getGooglePushNotificationToken());
			}

			if(tokens.length()>0) {
				String jsonData = new Gson().toJson(ancClientDTO);
				JSONObject msg = new JSONObject(jsonData);
				msg.put("type","ClientRegistration");

				googleFCMService.SendPushNotification(msg, tokens, true);
			}

			String phoneNumber = ancClientDTO.getPhoneNumber();
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			try {
				System.out.println("Coze: registered phone number : "+phoneNumber);
				Phonenumber.PhoneNumber tzPhoneNumber = phoneUtil.parse(phoneNumber, "TZ");
				phoneNumber = phoneUtil.format(tzPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);

			} catch (NumberParseException e) {
				System.err.println("NumberParseException was thrown: " + e.toString());
			}

			List<String> urns;
			urns = new ArrayList<String>();
			urns.add("tel:"+phoneNumber);

			try {
				System.out.println("Coze: sending phone number to rapidpro : "+phoneNumber);
				rapidProService.startFlow(urns, "251c1c0c-a082-474b-826b-a0ab233013e3");
			}catch (Exception e){
				e.printStackTrace();
			}

			List<AncClientReferralsDTO> ancClientReferralsDTOS = referralClientService.getClients("SELECT * FROM " + HealthFacilitiesClients.tbName +
					" WHERE " + HealthFacilitiesClients.COL_HEALTH_FACILITY_CLIENT_ID + "=?",healthFacilityPatientArg);

			return new ResponseEntity<AncClientReferralsDTO>(ancClientReferralsDTOS.get(0),OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(format("Patients processing failed with exception {0}.\nSubmissions: {1}", e, json));
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	//separate endpoint for saving the first client Appointment.
	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/save-first-appointment")
	public ResponseEntity<PatientsAppointmentsDTO> saveFirstAppointment(@RequestBody String json) {
		PatientsAppointmentsDTO appointmentsDTO = new Gson().fromJson(json, PatientsAppointmentsDTO.class);
		try {
			if (appointmentsDTO ==null) {
				return new ResponseEntity<>(BAD_REQUEST);
			}

			Object[] healthFacilityPatientArg = new Object[]{ appointmentsDTO.getHealthFacilityClientId()};
			List<AncClientReferralsDTO> ancClientReferralsDTOS = referralClientService.getClients("SELECT * FROM " + HealthFacilitiesClients.tbName +
					" WHERE " + HealthFacilitiesClients.COL_HEALTH_FACILITY_CLIENT_ID + "=?",healthFacilityPatientArg);

			//checking if the client has already been saved in the server
			if(ancClientReferralsDTOS.size()==0){
				return new ResponseEntity<>(PRECONDITION_FAILED);
			}

			AncClientDTO ancClientDTO = ancClientReferralsDTOS.get(0).getAncClientDTO();
			createNextAppointments(appointmentsDTO.getHealthFacilityClientId(),ancClientDTO.getLmnpDate(),true,0);

			Object[] facilityParams = new Object[]{ancClientDTO.getHealthFacilityCode(), 1};
			List<GooglePushNotificationsUsers> googlePushNotificationsUsers = googlePushNotificationsUsersRepository.getGooglePushNotificationsUsers("SELECT * FROM " + GooglePushNotificationsUsers.tbName + " WHERE " + GooglePushNotificationsUsers.COL_FACILITY_UIID + " = ? AND " + GooglePushNotificationsUsers.COL_USER_TYPE + " = ?", facilityParams);
			JSONArray tokens = new JSONArray();
			for (GooglePushNotificationsUsers googlePushNotificationsUsers1 : googlePushNotificationsUsers) {
				tokens.put(googlePushNotificationsUsers1.getGooglePushNotificationToken());
			}

			if(tokens.length()>0) {
				String jsonData = new Gson().toJson(ancClientDTO);
				JSONObject msg = new JSONObject(jsonData);
				msg.put("type","FirstAppointmentRegistration");

				googleFCMService.SendPushNotification(msg, tokens, true);
			}
			String getPatientsAppointmentsSQL = "SELECT * from " + PatientAppointments.tbName+" WHERE "+PatientAppointments.COL_HEALTH_FACILITY_CLIENT_ID +" =?";
			List<PatientAppointments> patientAppointments = patientsAppointmentsRepository.getAppointments(getPatientsAppointmentsSQL,healthFacilityPatientArg);

			PatientsAppointmentsDTO patientsAppointmentsDTO =  ClientConverter.toPatientAppointmentsDTO(patientAppointments.get(0));
			return new ResponseEntity<>(patientsAppointmentsDTO,OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(format("First Appointment Processing failed with exception {0}.\nSubmissions: {1}", e, json));
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/save-pnc-client")
	public ResponseEntity<PNCCompleteCLientDataDTO> savePncClient(@RequestBody String json) {
		PNCClientDTO PNCClientDTO = new Gson().fromJson(json,PNCClientDTO.class);
		try {
			scheduler.notifyEvent(new SystemEvent<>(AllConstants.OpenSRPEvent.REFERRED_PATIENTS_SUBMISSION, PNCClientDTO));


			PNCClients pncClient = ClientConverter.toPNCClient(PNCClientDTO);


			System.out.println("Coze:PNC Client data = "+new Gson().toJson(pncClient));


			pncClientsRepository.save(pncClient);


			PNCCompleteCLientDataDTO PNCCompleteCLientDataDTO = new PNCCompleteCLientDataDTO();
			List<HealthFacilitiesClients> healthFacilitiesPatients = healthFacilitiesClientsRepository.getHealthFacilityPatients("SELECT * FROM " + HealthFacilitiesClients.tbName + " WHERE " + HealthFacilitiesClients.COL_HEALTH_FACILITY_CLIENT_ID + "=?",
					new Object[]{pncClient.getHealthFacilityClientId()});

			HealthFacilitiesClients healthFacilitiesPatient = healthFacilitiesPatients.get(0);
			List<ANCClients> patients = ancClientsRepository.getPatients("SELECT * FROM " + ANCClients.tbName + " WHERE " + ANCClients.COL_CLIENTS_ID + "=?",
					new Object[]{healthFacilitiesPatient.getAncClient().getClientId()});

			PNCCompleteCLientDataDTO.setAncClientDTO(ClientConverter.toPatientsDTO(patients.get(0)));

			List<PNCClients> pncClientsList = pncClientsRepository.getPncClients("SELECT * FROM " + PNCClients.tbName + " WHERE " + PNCClients.COL_HEALTH_FACILITY_CLIENT_ID + "=?",
					new Object[]{healthFacilitiesPatient.getAncClient().getClientId()});

			PNCCompleteCLientDataDTO.setPncClientDTO(ClientConverter.toPNCClientDTO(pncClientsList.get(0)));

			return new ResponseEntity<PNCCompleteCLientDataDTO>(PNCCompleteCLientDataDTO,HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(format("PNC Client processing failed with exception {0}.\nSubmissions: {1}", e, PNCClientDTO));

		}
		return null;
	}

	@RequestMapping("get-facility-pnc-clients/{facilityUUID}")
	@ResponseBody
	public ResponseEntity<List<PNCCompleteCLientDataDTO>> getFacilityPNCClients(@PathVariable("facilityUUID") String facilityUUID) {
		try {
			List<PNCCompleteCLientDataDTO> pncCompleteCLientDataDTOS = new ArrayList<>();
			List<HealthFacilitiesClients> healthFacilitiesClients = healthFacilitiesClientsRepository.getHealthFacilityPatients("SELECT * FROM " + HealthFacilitiesClients.tbName +
							" INNER JOIN "+HealthFacilities.tbName+" ON "+ HealthFacilitiesClients.tbName+"."+ HealthFacilitiesClients.COL_FACILITY_ID +" = "+HealthFacilities.tbName+"._id WHERE " + HealthFacilities.COL_OPENMRS_UIID + "=?",
					new Object[]{facilityUUID});

			for(HealthFacilitiesClients healthFacilitiesPatient:healthFacilitiesClients){
				try {
					PNCCompleteCLientDataDTO pncCompleteCLientDataDTO = new PNCCompleteCLientDataDTO();
					List<PNCClients> pncClientsList = pncClientsRepository.getPncClients("SELECT * FROM " + PNCClients.tbName + " WHERE " + PNCClients.COL_HEALTH_FACILITY_CLIENT_ID + "=?",
							new Object[]{healthFacilitiesPatient.getAncClient().getClientId()});
					pncCompleteCLientDataDTO.setPncClientDTO(ClientConverter.toPNCClientDTO(pncClientsList.get(0)));

					List<ANCClients> patients = ancClientsRepository.getPatients("SELECT * FROM " + ANCClients.tbName + " WHERE " + ANCClients.COL_CLIENTS_ID + "=?",
							new Object[]{healthFacilitiesPatient.getAncClient().getClientId()});

					pncCompleteCLientDataDTO.setAncClientDTO(ClientConverter.toPatientsDTO(patients.get(0)));


					pncCompleteCLientDataDTOS.add(pncCompleteCLientDataDTO);
				}catch (Exception e){
					e.printStackTrace();
				}
			}


			return new ResponseEntity<List<PNCCompleteCLientDataDTO>>(pncCompleteCLientDataDTOS,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(format("Obtaining PNC Clients failed with exception {0}.\nfacility id: {1}", e, facilityUUID));

		}
		return null;
	}

	@RequestMapping("get-facility-anc-clients/{facilityUUID}")
	@ResponseBody
	public ResponseEntity<List<AncClientDTO>> getFacilityANCClients(@PathVariable("facilityUUID") String facilityUUID) {
		try {
			List<AncClientDTO> ancClientsList = new ArrayList<>();
			List<HealthFacilitiesClients> healthFacilitiesClients = healthFacilitiesClientsRepository.getHealthFacilityPatients("SELECT * FROM " + HealthFacilitiesClients.tbName +
							" INNER JOIN "+HealthFacilities.tbName+" ON "+ HealthFacilitiesClients.tbName+"."+ HealthFacilitiesClients.COL_FACILITY_ID +" = "+HealthFacilities.tbName+"._id WHERE " + HealthFacilities.COL_OPENMRS_UIID + "=?",
					new Object[]{facilityUUID});

			for(HealthFacilitiesClients healthFacilitiesPatient:healthFacilitiesClients){
				try {

					List<ANCClients> patients = ancClientsRepository.getPatients("SELECT * FROM " + ANCClients.tbName + " WHERE " + ANCClients.COL_CLIENTS_ID + "=?",
							new Object[]{healthFacilitiesPatient.getAncClient().getClientId()});

					ancClientsList.add(ClientConverter.toPatientsDTO(patients.get(0)));
				}catch (Exception e){
					e.printStackTrace();
				}
			}

			return new ResponseEntity<List<AncClientDTO>>(ancClientsList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(format("Obtaining ANC Clients failed with exception {0}.\nfacility id: {1}", e, facilityUUID));

		}
		return null;
	}

	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/save-visit")
	public ResponseEntity<EncounterVisitFeedbackDTO> saveVisit(@RequestBody String json) {
		System.out.println("saveVisit : "+json);
		RoutineVisitDTO routineVisitDTOS = new Gson().fromJson(json,RoutineVisitDTO.class);
		try {
			RoutineVisits encounter = ClientConverter.toRoutineVisit(routineVisitDTOS);

			if(encounter.getAppointmentId()==0)
				return new ResponseEntity<>(BAD_REQUEST);

			ANCRoutineVisitsRepository.save(encounter);

			createNextAppointments(routineVisitDTOS.getHealthFacilityClientId(),routineVisitDTOS.getVisitDate(),false,routineVisitDTOS.getVisitNumber());
			List<PatientAppointments> patientAppointments = patientsAppointmentsRepository.getAppointments("SELECT * FROM " + PatientAppointments.tbName + " WHERE " + PatientAppointments.COL_APPOINTMENT_ID + "=?",
					new Object[]{encounter.getAppointmentId()});

			String encounterQuery = "SELECT * FROM " + RoutineVisits.tbName + " WHERE " +
					RoutineVisits.COL_HEALTH_FACILITY_CLIENT_ID + " = ?    AND " +
					RoutineVisits.COL_APPOINTMENT_ID + " = ?  ";

			Object[] tbEncountersParams = new Object[]{
					encounter.getHealthFacilityClientId(),
					encounter.getAppointmentId()};

			List<RoutineVisits> routineVisits = ANCRoutineVisitsRepository.getTBEncounters(encounterQuery, tbEncountersParams);
			RoutineVisits routineVisit = routineVisits.get(0);

			RoutineVisitDTO routineVisitDTO = new RoutineVisitDTO();
			routineVisitDTO.setId(routineVisit.getId());
			routineVisitDTO.setHealthFacilityClientId(routineVisit.getHealthFacilityClientId());
			routineVisitDTO.setAppointmentId(routineVisit.getAppointmentId());

			routineVisitDTO.setAppointmentDate(routineVisit.getAppointmentDate().getTime());
			routineVisitDTO.setVisitNumber(routineVisit.getVisitNumber());
			routineVisitDTO.setVisitDate(routineVisit.getVisitDate().getTime());
			routineVisitDTO.setAppointmentDate(routineVisit.getAppointmentDate().getTime());
			routineVisitDTO.setAnaemia(routineVisit.isAnaemia());
			routineVisitDTO.setOedema(routineVisit.isOedema());
			routineVisitDTO.setProtenuria(routineVisit.isProtenuria());
			routineVisitDTO.setHighBloodPressure(routineVisit.isHighBloodPressure());
			routineVisitDTO.setWeightStagnation(routineVisit.isWeightStagnation());
			routineVisitDTO.setAntepartumHaemorrhage(routineVisit.isAntepartumHaemorrhage());
			routineVisitDTO.setSugarInTheUrine(routineVisit.isSugarInTheUrine());
			routineVisitDTO.setFetusLie(routineVisit.isFetusLie());

			EncounterVisitFeedbackDTO encounterVisitFeedbackDTO = new EncounterVisitFeedbackDTO();
			encounterVisitFeedbackDTO.setRoutineVisitDTO(routineVisitDTO);

			try {
				List<PatientAppointments> appointments = patientsAppointmentsRepository.getAppointments("SELECT * FROM " + PatientAppointments.tbName + " WHERE " + PatientAppointments.COL_HEALTH_FACILITY_CLIENT_ID + "=?",
						new Object[]{patientAppointments.get(0).getHealthFacilityClientId()});
				encounterVisitFeedbackDTO.setPatientsAppointmentsDTOS(ClientConverter.toPatientAppointmentDTOsList(appointments));
			}catch (Exception e){
				e.printStackTrace();
			}


			logger.debug(format("Added  Visit Submissions: {0}", routineVisitDTOS));

			return new ResponseEntity<EncounterVisitFeedbackDTO>(encounterVisitFeedbackDTO,HttpStatus.OK);

		} catch (Exception e) {
			logger.error(format("TB Encounters processing failed with exception {0}.\nSubmissions: {1}", e, routineVisitDTOS));
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = GET, value = "/all-clients-referrals")
	@ResponseBody
	private List<AncClientReferralsDTO> getAllPatientsReferrals() {
		List<AncClientReferralsDTO> ancClientReferralsDTOS = patientsService.getAllPatientReferrals();

		return ancClientReferralsDTOS;
	}

	@RequestMapping("get-facility-referrals/{facilityUUID}")
	@ResponseBody
	private List<AncClientReferralsDTO> getHealthFacilityReferrals(@PathVariable("facilityUUID") String facilityUuid) {
		List<AncClientReferralsDTO> ancClientReferralsDTOS = patientsService.getHealthFacilityReferrals(facilityUuid);
		return ancClientReferralsDTOS;
	}

	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/save-facility-referral")
	public ResponseEntity<ReferralsDTO> saveFacilityReferral(@RequestBody String jsonData) {
		ReferralsDTO referralsDTO = new Gson().fromJson(jsonData,ReferralsDTO.class);
		try {
			scheduler.notifyEvent(new SystemEvent<>(AllConstants.OpenSRPEvent.REFERRED_PATIENTS_SUBMISSION, referralsDTO));

			ClientReferral clientReferral = ClientConverter.toPatientReferral(referralsDTO);
			Long referralId = patientReferralRepository.save(clientReferral);


			logger.debug(format("Added  ReferralsDTO Submissions: {0}", referralsDTO));

			referralsDTO.setId(referralId);


			Object[] patientParams = new Object[]{
					clientReferral.getAncClientId()};
			List<ANCClients> patients = ancClientsRepository.getPatients("SELECT * FROM "+ ANCClients.tbName+" WHERE "+ ANCClients.COL_CLIENTS_ID +" =?",patientParams);

			AncClientReferralsDTO ancClientReferralsDTO = new AncClientReferralsDTO();
			ancClientReferralsDTO.setAncClientDTO(ClientConverter.toPatientsDTO(patients.get(0)));

			List<ReferralsDTO> patientReferrals = new ArrayList<>();
			patientReferrals.add(ClientConverter.toPatientDTO(clientReferral));


			ancClientReferralsDTO.setPatientReferralsList(patientReferrals);

			if(referralsDTO.getReferralType()==4) {
				System.out.println("chwreferral service provider uuid : "+ clientReferral.getServiceProviderUUID());
				Object[] facilityParams = new Object[]{clientReferral.getServiceProviderUUID()};

				List<GooglePushNotificationsUsers> googlePushNotificationsUsers = googlePushNotificationsUsersRepository.getGooglePushNotificationsUsers("SELECT * FROM " + GooglePushNotificationsUsers.tbName + " WHERE " + GooglePushNotificationsUsers.COL_USER_UIID + " = ?", facilityParams);
				JSONArray tokens = new JSONArray();
				for (GooglePushNotificationsUsers googlePushNotificationsUsers1 : googlePushNotificationsUsers) {
					tokens.put(googlePushNotificationsUsers1.getGooglePushNotificationToken());
				}

				System.out.println("tokens : "+tokens.toString());

				String json = new Gson().toJson(ancClientReferralsDTO);

				JSONObject msg = new JSONObject(json);
				msg.put("type","ClientReferral");

				googleFCMService.SendPushNotification(msg, tokens, false);
				String healthFacilitySql = "SELECT * FROM " + HealthFacilities.tbName + " WHERE " + HealthFacilities.COL_OPENMRS_UIID + " = ?";
				Object[] healthFacilityParams = new Object[]{
						clientReferral.getFacilityId(), clientReferral.getFacilityId()};

				List<HealthFacilities> healthFacilities = null;
				try {
					healthFacilities = healthFacilityRepository.getHealthFacility(healthFacilitySql, healthFacilityParams);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					if (healthFacilities != null)
						saveReferralFollowup(clientReferral, healthFacilities.get(0).getId() + "");
				}catch (Exception e){
					e.printStackTrace();
				}

			}

			return new ResponseEntity<ReferralsDTO>(referralsDTO,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(format("ReferralsDTO processing failed with exception {0}.\nSubmissions: {1}", e, jsonData));
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	public void saveReferralFollowup(ClientReferral clientReferral, String facilityId){
		System.out.println("Coze : saving referral Form data for followup");
		List<HealthFacilitiesClients> healthFacilitiesPatients = null;
		try {
			healthFacilitiesPatients = healthFacilitiesClientsRepository.getHealthFacilityPatients("SELECT * FROM "+ HealthFacilitiesClients.tbName+" WHERE "+ HealthFacilitiesClients.COL_CLIENT_ID + " = "+ clientReferral.getAncClientId()+" AND "+ HealthFacilitiesClients.COL_FACILITY_ID+ " = '"+facilityId+"'",null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<ANCClients> patients = null;
		try {
			patients = ancClientsRepository.getPatients("SELECT * FROM "+ ANCClients.tbName+" WHERE "+ ANCClients.COL_CLIENTS_ID +" = "+ clientReferral.getAncClientId(),null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ANCClients patient = patients.get(0);

			HealthFacilitiesClients healthFacilitiesPatient = healthFacilitiesPatients.get(0);

			String uuid = UUID.randomUUID().toString();

			List<org.opensrp.form.domain.FormField> formFields = new ArrayList<>();
			try {
				formFields.add(new org.opensrp.form.domain.FormField("first_name", patient.getFirstName() == null ? "" : patient.getFirstName(), "followup_client.first_name"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
			formFields.add(new org.opensrp.form.domain.FormField("middle_name", patient.getMiddleName()==null?"": patient.getMiddleName(), "followup_client.middlename"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
			formFields.add(new org.opensrp.form.domain.FormField("surname", patient.getSurname()==null?"":patient.getSurname(), "followup_client.surname"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
			formFields.add(new org.opensrp.form.domain.FormField("date_of_birth", patient.getDateOfBirth().getTime()+"", "followup_client.date_of_birth"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
			formFields.add(new org.opensrp.form.domain.FormField("edd", patient.getEdd().getTime()+"", "followup_client.edd"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("spouse_name", patient.getSpouseName()==null?"":patient.getSpouseName(), "followup_client.spouse_name"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("map_cue", patient.getMapCue()==null?"":patient.getMapCue(), "followup_client.map_cue"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("facility_id", clientReferral.getFromFacilityId()+ "", "followup_client.facility_id"));

			}catch (Exception e){
				e.printStackTrace();
			}
			try {formFields.add(new org.opensrp.form.domain.FormField("referral_reason", clientReferral.getReferralReason()==null?"": clientReferral.getReferralReason(), "followup_client.referral_reason"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("phone_number", patient.getPhoneNumber()==null?"":patient.getPhoneNumber(), "followup_client.phone_number"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("comment",  "", "followup_client.comment"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("referral_status",  "0", "followup_client.referral_status"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("service_provider_uiid",  "", "followup_client.service_provider_uiid"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("visit_date",  "", "followup_client.visit_date"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("referral_date",  clientReferral.getReferralDate().getTime()+"", "followup_client.referral_date"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("village",  patient.getVillage()==null?"":patient.getVillage(), "followup_client.village"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("relationalid",  uuid, "followup_client.relationalid"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("is_valid",  "true", "followup_client.is_valid"));
			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				formFields.add(new org.opensrp.form.domain.FormField("id",  uuid, "followup_client.id"));
			}catch (Exception e){
				e.printStackTrace();
			}

			FormData formData = new FormData("followup_client", "/model/instance/anc_client_follow_up_form/", formFields, null);
			FormInstance formInstance = new FormInstance(formData);
			FormSubmission formSubmission = new FormSubmission(clientReferral.getFromFacilityId()+"", uuid+"", "client_follow_up_form", clientReferral.getReferralUUID() + "", "1", 4, formInstance);


			System.out.println("Coze : saving referral form submission");
			formSubmissionService.submit(formSubmission);
		}catch (Exception e){
			e.printStackTrace();
		}

	}

	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/receive-feedback")
	public ResponseEntity<String> saveReferralFeedback(@RequestBody String json) {
		try {
			System.out.println("Coze: receive feedback");
			ReferralsDTO referralsDTO = new Gson().fromJson(json,ReferralsDTO.class);
			scheduler.notifyEvent(new SystemEvent<>(AllConstants.OpenSRPEvent.REFERRED_PATIENTS_SUBMISSION, referralsDTO));

			List<ClientReferral> referrals = patientReferralRepository.getReferrals("SELECT * FROM " + ClientReferral.tbName + " WHERE " + ClientReferral.COL_REFERRAL_ID + "=?",
					new Object[]{referralsDTO.getId()});

			ClientReferral referral=null;
			try {
				referral = referrals.get(0);
				referral.setReferralStatus(referralsDTO.getReferralStatus());
				referral.setOtherNotes(referralsDTO.getOtherClinicalInformation());
				referral.setReferralFeedback(referralsDTO.getReferralFeedback());
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("Coze: referral not found");
				return new ResponseEntity<String>("referral not found",PRECONDITION_FAILED);
			}


			if(referral!=null) {
				String sql ="UPDATE " + ClientReferral.tbName + " SET " +
						ClientReferral.COL_REFERRAL_STATUS + " = '" + referral.getReferralStatus() + "' , " +
						ClientReferral.COL_OTHER_NOTES + " = '" + referral.getOtherNotes() + "' , " +
						ClientReferral.COL_REFERRAL_FEEDBACK + " = '" + referral.getReferralFeedback() + "' WHERE  " + ClientReferral.COL_REFERRAL_ID + " = " + referral.getId();
				patientReferralRepository.executeQuery(sql);
				System.out.println("Coze: updated referral feedback : "+sql);

				if (referral.getReferralType() == 1) {
					try {
						FormSubmission formSubmission = formSubmissionService.findByInstanceId(referral.getInstanceId());
						System.out.println("Coze: formsubmission to be updated = "+new Gson().toJson(formSubmission));

						formSubmission = formEntityConverter.updateFormSUbmissionField(formSubmission, ClientReferral.COL_OTHER_NOTES, referral.getOtherNotes());
						formSubmission = formEntityConverter.updateFormSUbmissionField(formSubmission, ClientReferral.COL_REFERRAL_FEEDBACK, referral.getReferralFeedback());
						formSubmission = formEntityConverter.updateFormSUbmissionField(formSubmission, ClientReferral.COL_REFERRAL_STATUS, referral.getReferralStatus() + "");


						System.out.println("Coze: updated formsubmission = "+new Gson().toJson(formSubmission));
						formSubmissionService.update(formSubmission);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Object[] facilityParams = new Object[]{referralsDTO.getServiceProviderUUID()};
				List<GooglePushNotificationsUsers> googlePushNotificationsUsers = googlePushNotificationsUsersRepository.getGooglePushNotificationsUsers("SELECT * FROM "+GooglePushNotificationsUsers.tbName+" WHERE "+GooglePushNotificationsUsers.COL_USER_UIID+" = ? ",facilityParams);
				JSONArray tokens = new JSONArray();
				for(GooglePushNotificationsUsers googlePushNotificationsUsers1:googlePushNotificationsUsers){
					tokens.put(googlePushNotificationsUsers1.getGooglePushNotificationToken());
				}

				String referralDTOJson = new Gson().toJson(referralsDTO);

				JSONObject msg = new JSONObject(referralDTOJson);
				msg.put("type","ReferralFeedback");


				try {
					if(referral.getReferralType()==1)
						googleFCMService.SendPushNotification(msg, tokens, false);
					else{
						googleFCMService.SendPushNotification(msg, tokens, true);
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}else{
				return new ResponseEntity<String>("Referral Not found",BAD_REQUEST);
			}


			logger.debug(format("updated  ReferralsFeedbackDTO Submissions: {0}", referralsDTO));
		} catch (Exception e) {
			logger.error(format("ReferralsFeedbackDTO processing failed with exception {0}.\nSubmissions: {1}", e, json));
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>("success",OK);
	}

	@RequestMapping(method = GET, value = "/check-status-of-referrals")
	@ResponseBody
	public ResponseEntity<HttpStatus> checkStatusOfReferrals() {
		try {

			List<ClientReferral> clientReferrals = patientReferralRepository.getReferrals("SELECT * FROM "+ ClientReferral.tbName+" WHERE "+ ClientReferral.COL_REFERRAL_STATUS+" = 0 ",null);

			Date now = Calendar.getInstance().getTime();

			for (ClientReferral clientReferral : clientReferrals) {
				long diff = now.getTime() - clientReferral.getReferralDate().getTime();
				System.out.println ("hours since referrals Isued: " + TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS));

				//Failed referrals
				if( (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)>3)){

					System.out.println ("failed referral " + clientReferral.getId());
					clientReferral.setReferralStatus(-1);
					String sql ="UPDATE " + ClientReferral.tbName + " SET " +
							ClientReferral.COL_REFERRAL_STATUS + " = '" + clientReferral.getReferralStatus() + "' WHERE  " + ClientReferral.COL_REFERRAL_ID + " = " + clientReferral.getId();
					patientReferralRepository.executeQuery(sql);


					if (clientReferral.getReferralType() == 1) {
						try {
							FormSubmission formSubmission = formSubmissionService.findByInstanceId(clientReferral.getInstanceId());
							formSubmission = formEntityConverter.updateFormSUbmissionField(formSubmission, ClientReferral.COL_REFERRAL_STATUS, clientReferral.getReferralStatus() + "");
							System.out.println("Coze: updated formsubmission = "+new Gson().toJson(formSubmission));
							formSubmissionService.update(formSubmission);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}


					List <ANCClients> patients = ancClientsRepository.getPatients("SELECT * FROM "+ ANCClients.tbName+" WHERE "+ ANCClients.COL_CLIENTS_ID +" = "+ clientReferral.getAncClientId(),null);
					System.out.println("Coze: Send notification sms to user "+patients.get(0).getPhoneNumber());

					//TODO send notification to the user

					ReferralsDTO referralsDTO = ClientConverter.toPatientDTO(clientReferral);
					JSONObject body = new JSONObject();
					body.put("type", "FailedReferrals");

					Object[] facilityParams = new Object[]{clientReferral.getFacilityId(), clientReferral.getFromFacilityId()};
					List<GooglePushNotificationsUsers> googlePushNotificationsUsers = googlePushNotificationsUsersRepository.getGooglePushNotificationsUsers("SELECT * FROM " + GooglePushNotificationsUsers.tbName +
							" WHERE " +
							GooglePushNotificationsUsers.COL_FACILITY_UIID + " = ? OR " +
							GooglePushNotificationsUsers.COL_FACILITY_UIID + " = ? ", facilityParams);

					JSONArray tokens = new JSONArray();
					for (GooglePushNotificationsUsers googlePushNotificationsUsers1 : googlePushNotificationsUsers) {
						tokens.put(googlePushNotificationsUsers1.getGooglePushNotificationToken());
					}

					if(tokens.length()>0) {
						String jsonData = new Gson().toJson(referralsDTO);
						JSONObject msg = new JSONObject(jsonData);
						googleFCMService.SendPushNotification(msg, tokens, false);
					}
				}

			}


		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(CREATED);
	}

	@RequestMapping(method = GET, value = "/check-appointments")
	@ResponseBody
	public ResponseEntity<String> checkUpcomingAppointments() {
		try {

			JSONArray phoneNumbers = new JSONArray();
			Date d = Calendar.getInstance().getTime();
			List <PatientAppointments> patientAppointments  = patientsAppointmentsRepository.getAppointments("SELECT * FROM "+PatientAppointments.tbName+" WHERE "+PatientAppointments.COL_APPOINTMENT_DATE+" > '"+d.getTime()+"'",null);

			System.out.println("Coze: checking appointment ");
			Date now = Calendar.getInstance().getTime();


			List<String> threeDaysToAppointmentUrns= new ArrayList<String>();
			List<String> aDayToAppointmentUrns= new ArrayList<String>();
			for(PatientAppointments appointments : patientAppointments) {
				System.out.println("Coze: checking appointment " + appointments.getAppointmentDate());


				long diff = appointments.getAppointmentDate().getTime() - now.getTime();

				System.out.println("Coze: Days to appointment : "+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
				if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) == 3) {

					List<HealthFacilitiesClients> healthFacilitiesPatients = healthFacilitiesClientsRepository.getHealthFacilityPatients("SELECT * FROM "+ HealthFacilitiesClients.tbName+" WHERE "+ HealthFacilitiesClients.COL_HEALTH_FACILITY_CLIENT_ID +" = "+appointments.getHealthFacilitiesClients().getHealthFacilityClientId(),null);
					List <ANCClients> patients = ancClientsRepository.getPatients("SELECT * FROM "+ ANCClients.tbName+" WHERE "+ ANCClients.COL_CLIENTS_ID +" = "+healthFacilitiesPatients.get(0).getAncClient().getClientId(),null);
					System.out.println("Coze: Send 3 days to Appointment notification to user "+patients.get(0).getPhoneNumber());

					if(patients.get(0).getPhoneNumber().equals("")) {
						phoneNumbers.put(patients.get(0).getPhoneNumber());



						PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
						try {
							System.out.println("Coze: registered phone number : "+patients.get(0).getPhoneNumber());
							Phonenumber.PhoneNumber tzPhoneNumber = phoneUtil.parse(patients.get(0).getPhoneNumber(), "TZ");
							String formatedPhoneNumber = phoneUtil.format(tzPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);

							System.out.println("Coze:formatted phone number : "+formatedPhoneNumber);


							threeDaysToAppointmentUrns.add("tel:"+formatedPhoneNumber);
						} catch (NumberParseException e) {
							System.err.println("NumberParseException was thrown: " + e.toString());
						}

					}

				}else if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) == 1) {

					List<HealthFacilitiesClients> healthFacilitiesPatients = healthFacilitiesClientsRepository.getHealthFacilityPatients("SELECT * FROM "+ HealthFacilitiesClients.tbName+" WHERE "+ HealthFacilitiesClients.COL_HEALTH_FACILITY_CLIENT_ID +" = "+appointments.getHealthFacilitiesClients().getHealthFacilityClientId(),null);
					List <ANCClients> patients = ancClientsRepository.getPatients("SELECT * FROM "+ ANCClients.tbName+" WHERE "+ ANCClients.COL_CLIENTS_ID +" = "+healthFacilitiesPatients.get(0).getAncClient().getClientId(),null);
					System.out.println("Coze: Send 1 days to Appointment notification to user "+patients.get(0).getPhoneNumber());

					if(patients.get(0).getPhoneNumber().equals("")) {

						phoneNumbers.put(patients.get(0).getPhoneNumber());
						PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
						try {
							System.out.println("Coze: registered phone number : "+patients.get(0).getPhoneNumber());
							Phonenumber.PhoneNumber tzPhoneNumber = phoneUtil.parse(patients.get(0).getPhoneNumber(), "TZ");
							String formatedPhoneNumber = phoneUtil.format(tzPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);

							System.out.println("Coze:formatted a day to appointment phone number : "+formatedPhoneNumber);


							aDayToAppointmentUrns.add("tel:"+formatedPhoneNumber);
						} catch (NumberParseException e) {
							System.err.println("NumberParseException was thrown: " + e.toString());
						}

					}

				}
			}

			try {
				String response = rapidProService.startFlow(threeDaysToAppointmentUrns, "a5421259-c67b-4a41-967d-e9560170ecc1");
				System.out.println("Coze: received rapidpro response for 3 days to appointment notifications : "+response);

				String response2 = rapidProService.startFlow(aDayToAppointmentUrns, "2f09f8a5-6bef-4e1b-8d53-607ce3230cee");
				System.out.println("Coze: received rapidpro response for a day to appointment notifications : "+response2);


			}catch (Exception e){
				e.printStackTrace();
			}


			return new ResponseEntity<String>(phoneNumbers.toString(),HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/get-chw-referrals-summary")
	@ResponseBody
	public ResponseEntity<List<CHWReferralsSummaryDTO>> getCHWReferralsSummaryLists(@RequestBody String json) {
		JSONObject object = null;
		try {
			object = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONArray array = null;
		try {
			array = object.getJSONArray("chw_uuid");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		//Default dates if the date range is not passed
		String fromDate = "2017-01-01";
		String toDate = "2020-01-01";
		try {
			toDate = object.getString("to_date");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			fromDate = object.getString("from_date");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		int size = array.length();
		String chwUIIDs = "";
		for(int i=0;i<size;i++){
			try {
				chwUIIDs+="'"+array.getString(i)+"',";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}


		if ( chwUIIDs.length() > 0 && chwUIIDs.charAt(chwUIIDs.length() - 1) == ',') {
			chwUIIDs = chwUIIDs.substring(0, chwUIIDs.length() - 1);
		}

		try {
			List<CHWReferralsSummaryDTO> chwReferralsSummaryDTOS = patientReferralRepository.getCHWReferrals(
					"SELECT COUNT("+ ClientReferral.tbName+"."+ ClientReferral.COL_REFERRAL_ID+") as count ,"+ClientReferral.COL_SERVICE_PROVIDER_UUID+" as service_name FROM "+ ClientReferral.tbName +
					" WHERE "+ ClientReferral.COL_REFERRAL_TYPE+"=1 AND " +
							ClientReferral.COL_SERVICE_PROVIDER_UUID+" IN ("+chwUIIDs+") AND "+
							ClientReferral.COL_REFERRAL_DATE+" > '"+fromDate+"' AND "+
							ClientReferral.COL_REFERRAL_DATE+" <= '"+toDate+"' "+
					" GROUP BY "+ClientReferral.COL_SERVICE_PROVIDER_UUID,null);


			return new ResponseEntity<List<CHWReferralsSummaryDTO>>(chwReferralsSummaryDTOS,HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<List<CHWReferralsSummaryDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/get-chw-referrals-list")
	@ResponseBody
	public ResponseEntity<List<CHWReferralsListDTO>> getCHWANCReferralsLists(@RequestBody String json) {
		JSONObject object = null;
		try {
			object = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		JSONArray array = null;
		try {
			array = object.getJSONArray("chw_uuid");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		//Default dates if the date range is not passed
		String fromDate = "2017-01-01";
		String toDate = "2020-01-01";
		try {
			toDate = object.getString("to_date");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			fromDate = object.getString("from_date");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		int size = array.length();
		String chwUIIDs = "";
		for(int i=0;i<size;i++){
			try {
				chwUIIDs+="'"+array.getString(i)+"',";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}


		if ( chwUIIDs.length() > 0 && chwUIIDs.charAt(chwUIIDs.length() - 1) == ',') {
			chwUIIDs = chwUIIDs.substring(0, chwUIIDs.length() - 1);
		}

		try {
			List<CHWReferralsListDTO> chwReferralsSummaryDTOS = patientReferralRepository.getCHWReferralsList(
					"  SELECT "
							+ ClientReferral.tbName+"."+ ClientReferral.COL_SERVICE_PROVIDER_UUID+", "
							+ ANCClients.tbName+"."+ ANCClients.COL_PATIENT_FIRST_NAME+", "
							+ ANCClients.tbName+"."+ ANCClients.COL_PATIENT_SURNAME+", "
							+ HealthFacilities.tbName+"."+ HealthFacilities.COL_FACILITY_NAME+", "
							+ ClientReferral.tbName+"."+ ClientReferral.COL_REFERRAL_STATUS+
							" FROM "+ ClientReferral.tbName +
							" INNER JOIN "+ANCClients.tbName+" ON "+ClientReferral.tbName+"."+ClientReferral.COL_ANC_CLIENT_ID+" = "+ANCClients.tbName+"."+ANCClients.COL_CLIENTS_ID+
							" INNER JOIN "+HealthFacilities.tbName+" ON "+ClientReferral.tbName+"."+ClientReferral.COL_FACILITY_ID+" = "+HealthFacilities.tbName+"."+HealthFacilities.COL_OPENMRS_UIID+
							" WHERE "+ ClientReferral.tbName+"."+ClientReferral.COL_REFERRAL_TYPE+"=1 AND " +
							ClientReferral.tbName+"."+ClientReferral.COL_SERVICE_PROVIDER_UUID+" IN ("+chwUIIDs+") AND "+
							ClientReferral.tbName+"."+ClientReferral.COL_REFERRAL_DATE+" > '"+fromDate+"' AND "+
							ClientReferral.tbName+"."+ClientReferral.COL_REFERRAL_DATE+" <= '"+toDate+"' "+
							" GROUP BY "+ClientReferral.tbName+"."+ClientReferral.COL_SERVICE_PROVIDER_UUID+","+ANCClients.tbName+"."+ANCClients.COL_CLIENTS_ID+","+HealthFacilities.tbName+"._id, "+ClientReferral.tbName+"."+ ClientReferral.COL_REFERRAL_STATUS,null);


			System.out.println("SQL QUERRY = "+"  SELECT "
					+ ClientReferral.tbName+"."+ ClientReferral.COL_SERVICE_PROVIDER_UUID+", "
					+ ANCClients.tbName+"."+ ANCClients.COL_PATIENT_FIRST_NAME+", "
					+ ANCClients.tbName+"."+ ANCClients.COL_PATIENT_SURNAME+", "
					+ HealthFacilities.tbName+"."+ HealthFacilities.COL_FACILITY_NAME+", "
					+ ClientReferral.tbName+"."+ ClientReferral.COL_REFERRAL_STATUS+
					" FROM "+ ClientReferral.tbName +
					" INNER JOIN "+ANCClients.tbName+" ON "+ClientReferral.tbName+"."+ClientReferral.COL_REFERRAL_ID+" = "+ANCClients.tbName+"."+ANCClients.COL_CLIENTS_ID+
					" INNER JOIN "+HealthFacilities.tbName+" ON "+ClientReferral.tbName+"."+ClientReferral.COL_FACILITY_ID+" = "+HealthFacilities.tbName+"."+HealthFacilities.COL_OPENMRS_UIID+
					" WHERE "+ ClientReferral.tbName+"."+ClientReferral.COL_REFERRAL_TYPE+"=1 AND " +
					ClientReferral.tbName+"."+ClientReferral.COL_SERVICE_PROVIDER_UUID+" IN ("+chwUIIDs+") AND "+
					ClientReferral.tbName+"."+ClientReferral.COL_REFERRAL_DATE+" > '"+fromDate+"' AND "+
					ClientReferral.tbName+"."+ClientReferral.COL_REFERRAL_DATE+" <= '"+toDate+"' "+
					" GROUP BY "+ClientReferral.tbName+"."+ClientReferral.COL_SERVICE_PROVIDER_UUID+","+ANCClients.tbName+"."+ANCClients.COL_CLIENTS_ID+","+HealthFacilities.tbName+"._id");

			return new ResponseEntity<List<CHWReferralsListDTO>>(chwReferralsSummaryDTOS,HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<List<CHWReferralsListDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void createNextAppointments(long healthfacilityPatientId, long time, boolean isFIrstAppointment, int visitNumber) {
		long today = Calendar.getInstance().getTime().getTime();

		PatientAppointments appointments = new PatientAppointments();
		appointments.setHealthFacilityClientId(healthfacilityPatientId);
		appointments.setAppointmentType(2);
		appointments.setIsCancelled(false);
		appointments.setVisitNumber(++visitNumber);

		if(isFIrstAppointment){
			long diff = today - time;
			System.out.println ("hours since lmnp Isued: " + TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS));
			if(TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS)<(4*4*7*24)){
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(time);
				c.add(Calendar.MONTH, +4);
				c.add(Calendar.DAY_OF_MONTH, +checkIfWeekend(c.getTime()));
				appointments.setAppointmentDate(c.getTime());

			}else{
				appointments.setAppointmentDate(Calendar.getInstance().getTime());
			}
		}else{
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(time);
			c.add(Calendar.MONTH, +1);
			c.add(Calendar.DAY_OF_MONTH, +checkIfWeekend(c.getTime()));
			appointments.setAppointmentDate(c.getTime());

		}

		try {
			System.out.println("Coze:save appointment");
			patientsAppointmentsRepository.save(appointments);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int checkIfWeekend(Date d1) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		System.out.println(c1.get(Calendar.DAY_OF_WEEK));
		if ((c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
			return 2;
		} else if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return 1;
		} else {
			return 0;
		}
	}
}
