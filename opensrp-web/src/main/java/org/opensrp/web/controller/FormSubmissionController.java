package org.opensrp.web.controller;

import ch.lambdaj.function.convert.Converter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opensrp.common.AllConstants;
import org.opensrp.connector.openmrs.constants.OpenmrsHouseHold;
import org.opensrp.connector.openmrs.service.EncounterService;
import org.opensrp.connector.openmrs.service.HouseholdService;
import org.opensrp.connector.openmrs.service.PatientService;
import org.opensrp.domain.*;
import org.opensrp.dto.AncClientReferralsDTO;
import org.opensrp.dto.AncClientDTO;
import org.opensrp.dto.ReferralsDTO;
import org.opensrp.dto.form.FormSubmissionDTO;
import org.opensrp.dto.form.MultimediaDTO;
import org.opensrp.form.domain.FormSubmission;
import org.opensrp.form.service.FormSubmissionConverter;
import org.opensrp.form.service.FormSubmissionService;
import org.opensrp.repository.*;
import org.opensrp.scheduler.SystemEvent;
import org.opensrp.scheduler.TaskSchedulerService;
import org.opensrp.service.*;
import org.opensrp.service.formSubmission.FormEntityConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static ch.lambdaj.collection.LambdaCollections.with;
import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class FormSubmissionController {
    private static Logger logger = LoggerFactory.getLogger(FormSubmissionController.class.toString());
    private FormSubmissionService formSubmissionService;
    private GoogleFCMService googleFCMService;
    private TaskSchedulerService scheduler;
    private EncounterService encounterService;
    private FormEntityConverter formEntityConverter;
    private PatientService patientService;
    private ReferralPatientsService referralPatientService;
    private HouseholdService householdService;
    private ErrorTraceService errorTraceService;
    private MultimediaService multimediaService;
    private MultimediaRepository multimediaRepository;
    private HealthFacilitiesClientsRepository healthFacilitiesClientsRepository;
    private PatientReferralRepository patientReferralRepository;
    private PatientReferralIndicatorRepository patientReferralIndicatorRepository;
    private GooglePushNotificationsUsersRepository googlePushNotificationsUsersRepository;
	private RapidProServiceImpl rapidProService;
	private PatientsAppointmentsRepository patientsAppointmentsRepository;

    @Autowired
    public FormSubmissionController(FormSubmissionService formSubmissionService, TaskSchedulerService scheduler,
                                    EncounterService encounterService, FormEntityConverter formEntityConverter, PatientService patientService,
                                    HouseholdService householdService, MultimediaService multimediaService, MultimediaRepository multimediaRepository,
                                    ErrorTraceService errorTraceService, HealthFacilitiesClientsRepository healthFacilitiesClientsRepository, PatientReferralRepository patientReferralRepository,
                                    GooglePushNotificationsUsersRepository googlePushNotificationsUsersRepository, GoogleFCMService googleFCMService,
                                    PatientReferralIndicatorRepository patientReferralIndicatorRepository,
                                    ReferralPatientsService referralPatientService, RapidProServiceImpl rapidProService, PatientsAppointmentsRepository patientsAppointmentsRepository) {
        this.formSubmissionService = formSubmissionService;
        this.scheduler = scheduler;
        this.errorTraceService=errorTraceService;
        this.encounterService = encounterService;
        this.formEntityConverter = formEntityConverter;
        this.patientService = patientService;
        this.householdService = householdService;
        this.multimediaService = multimediaService;
        this.multimediaRepository = multimediaRepository;
        this.healthFacilitiesClientsRepository = healthFacilitiesClientsRepository;
        this.patientReferralRepository = patientReferralRepository;
        this.googlePushNotificationsUsersRepository = googlePushNotificationsUsersRepository;
	    this.googleFCMService =googleFCMService;
	    this.patientReferralIndicatorRepository = patientReferralIndicatorRepository;
	    this.referralPatientService = referralPatientService;
	    this.rapidProService=rapidProService;
	    this.patientsAppointmentsRepository=patientsAppointmentsRepository;
    }

    @RequestMapping(method = GET, value = "/form-submissions")
    @ResponseBody
    private List<FormSubmissionDTO> getNewSubmissionsForANM(@RequestParam("anm-id") String anmIdentifier,
                                                            @RequestParam("timestamp") Long timeStamp,
                                                            @RequestParam(value = "batch-size", required = false)
                                                            Integer batchSize) {
        List<FormSubmission> newSubmissionsForANM = formSubmissionService
                .getNewSubmissionsForANM(anmIdentifier, timeStamp, batchSize);
        return with(newSubmissionsForANM).convert(new Converter<FormSubmission, FormSubmissionDTO>() {
            @Override
            public FormSubmissionDTO convert(FormSubmission submission) {
                return FormSubmissionConverter.from(submission);
            }
        });
    }

    @RequestMapping(method = GET, value="/all-form-submissions")
    @ResponseBody
    private List<FormSubmissionDTO> getAllFormSubmissions(@RequestParam("timestamp") Long timeStamp,
                                                          @RequestParam(value = "batch-size", required = false)
                                                          Integer batchSize) {
        List<FormSubmission> allSubmissions = formSubmissionService
                .getAllSubmissions(timeStamp, batchSize);
        return with(allSubmissions).convert(new Converter<FormSubmission, FormSubmissionDTO>() {
            @Override
            public FormSubmissionDTO convert(FormSubmission submission) {
                return FormSubmissionConverter.from(submission);
            }
        });
    }
    
 

    @RequestMapping(headers = {"Accept=application/json"}, method = POST, value = "/form-submissions")
    public ResponseEntity<HttpStatus> submitForms(@RequestBody List<FormSubmissionDTO> formSubmissionsDTO) {
        try {
            if (formSubmissionsDTO.isEmpty()) {
                return new ResponseEntity<>(BAD_REQUEST);
            }
            scheduler.notifyEvent(new SystemEvent<>(AllConstants.OpenSRPEvent.FORM_SUBMISSION, formSubmissionsDTO));
            
            try{

            String json = new Gson().toJson(formSubmissionsDTO);

            List<FormSubmissionDTO> formSubmissions = new Gson().fromJson(json, new TypeToken<List<FormSubmissionDTO>>() {
            }.getType());
            List<FormSubmission> fsl = with(formSubmissions).convert(new Converter<FormSubmissionDTO, FormSubmission>() {
                @Override
                public FormSubmission convert(FormSubmissionDTO submission) {
                    return FormSubmissionConverter.toFormSubmission(submission);
                }
            });
	            for (FormSubmission formSubmission : fsl) {
	            	try{
			            saveFormToOpenSRP(formSubmission);
//	            		addFormToOpenMRS(formSubmission);
	            	}
	            	catch(Exception e){
	            		e.printStackTrace();
	            		ErrorTrace errorTrace=new ErrorTrace(new DateTime(), "Parse Exception", "", e.getStackTrace().toString(), "Unsolved", formSubmission.formName());
						errorTrace.setRecordId(formSubmission.instanceId());
						errorTraceService.addError(errorTrace);
	            	}
	    		}
            }
            catch(Exception e){
            	e.printStackTrace();
            }
            logger.debug(format("Added Form submissions to queue.\nSubmissions: {0}", formSubmissionsDTO));
        } catch (Exception e) {
            logger.error(format("Form submissions processing failed with exception {0}.\nSubmissions: {1}", e, formSubmissionsDTO));
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(CREATED);
    }
    
    private void addFormToOpenMRS(FormSubmission formSubmission) throws ParseException, IllegalStateException, JSONException{
//    	if(formEntityConverter.isOpenmrsForm(formSubmission)){
    		Client c = formEntityConverter.getClientFromFormSubmission(formSubmission);
			Event e = formEntityConverter.getEventFromFormSubmission(formSubmission);
			Map<String, Map<String, Object>> dep = formEntityConverter.getDependentClientsFromFormSubmission(formSubmission);

    		// TODO temporary because not necessarily we register inner entity for Household only
    		if(formSubmission.formName().toLowerCase().contains("household") 
    				|| formSubmission.formName().toLowerCase().contains("census") ){
    			OpenmrsHouseHold hh = new OpenmrsHouseHold(c, e);
    			for (Map<String, Object> cm : dep.values()) {
    				hh.addHHMember((Client)cm.get("client"), (Event)cm.get("event"));
    			}
    			
    			householdService.saveHH(hh, true);
    		}
    		else {
    			JSONObject p = patientService.getPatientByIdentifier(c.getBaseEntityId());
    			if(p == null){
    				System.out.println(patientService.createPatient(c));
    			}
        	
    			System.out.println(encounterService.createEncounter(e));
    			
    			for (Map<String, Object> cm : dep.values()) {
    				Client cin = (Client)cm.get("client");
    				Event evin = (Event)cm.get("event");
    				JSONObject pin = patientService.getPatientByIdentifier(cin.getBaseEntityId());
        			if(pin == null){
        				System.out.println(patientService.createPatient(cin));
        			}
            	
        			System.out.println(encounterService.createEncounter(evin));
    			}
    		}
    	//}
    }

	private void saveFormToOpenSRP(FormSubmission formSubmission) throws ParseException, IllegalStateException, JSONException{
        ANCClients patient = formEntityConverter.getPatientFromFormSubmission(formSubmission);
        ClientReferral clientReferral = formEntityConverter.getPatientReferralFromFormSubmission(formSubmission);
		try {

			long healthfacilityPatientId = referralPatientService.saveClient(patient, clientReferral.getFacilityId(), "");


			try {
				createNextAppointments(healthfacilityPatientId, patient.getLmnpDate().getTime(), true, 0);
			}catch (Exception e){
				e.printStackTrace();
			}


			List<HealthFacilitiesClients> healthFacilitiesPatients = healthFacilitiesClientsRepository.getHealthFacilityPatients("SELECT * FROM "+ HealthFacilitiesClients.tbName+" WHERE "+ HealthFacilitiesClients.COL_HEALTH_FACILITY_CLIENT_ID +" = "+healthfacilityPatientId,null);

			clientReferral.setAncClientId(healthFacilitiesPatients.get(0).getAncClient().getClientId());

            clientReferral.setReferralStatus(0);
            clientReferral.setReferralType(1);

            long id = patientReferralRepository.save(clientReferral);
            clientReferral.setId(id);


			String phoneNumber = patient.getPhoneNumber();
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			try {
				System.out.println("Coze: registered phone number : "+phoneNumber);
				Phonenumber.PhoneNumber tzPhoneNumber = phoneUtil.parse(phoneNumber, "TZ");
				phoneNumber = phoneUtil.format(tzPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);

				System.out.println("Coze:formatted phone number : "+phoneNumber);
			} catch (NumberParseException e) {
				System.err.println("NumberParseException was thrown: " + e.toString());
			}


			List<String> urns;
			urns = new ArrayList<String>();
			urns.add("tel:"+phoneNumber);

			try {
				System.out.println("Coze: sending phone number to rapidpro : "+phoneNumber);
				String response = rapidProService.startFlow(urns, "251c1c0c-a082-474b-826b-a0ab233013e3");

				System.out.println("Coze: received rapidpro response : "+response);
			}catch (Exception e){
				e.printStackTrace();
			}


			Object[] facilityParams = new Object[]{clientReferral.getFacilityId(),1};
			List<GooglePushNotificationsUsers> googlePushNotificationsUsers = googlePushNotificationsUsersRepository.getGooglePushNotificationsUsers("SELECT * FROM "+GooglePushNotificationsUsers.tbName+" WHERE "+GooglePushNotificationsUsers.COL_FACILITY_UIID+" = ? AND "+GooglePushNotificationsUsers.COL_USER_TYPE+" = ?",facilityParams);
			JSONArray tokens = new JSONArray();
			for(GooglePushNotificationsUsers googlePushNotificationsUsers1:googlePushNotificationsUsers){
				tokens.put(googlePushNotificationsUsers1.getGooglePushNotificationToken());
			}


			AncClientReferralsDTO ancClientReferralsDTO = new AncClientReferralsDTO();

			AncClientDTO ancClientDTO = ClientConverter.toPatientsDTO(patient);
			ancClientReferralsDTO.setAncClientDTO(ancClientDTO);


			List<ReferralsDTO> referralsDTOS = new ArrayList<>();
			ReferralsDTO referralsDTO = ClientConverter.toPatientDTO(clientReferral);
			referralsDTOS.add(referralsDTO);
			ancClientReferralsDTO.setPatientReferralsList(referralsDTOS);

			JSONObject body = new JSONObject();

			String json = new Gson().toJson(ancClientReferralsDTO);

			System.out.println("Coze = FCM msg : "+json);

			JSONObject msg = new JSONObject(json);
			msg.put("type","PatientReferral");

			googleFCMService.SendPushNotification(msg,tokens,true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(format("Patient Form submissions processing failed with exception {0}.\nSubmissions: {1}", e, formSubmission));

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





    @RequestMapping(headers = {"Accept=application/json"}, method = GET, value = "/multimedia-file")
    @ResponseBody
    public List<MultimediaDTO> getFiles(@RequestParam("anm-id") String providerId) {
    	
    	List<Multimedia> allMultimedias = multimediaService.getMultimediaFiles(providerId);
    	
    	return with(allMultimedias).convert(new Converter<Multimedia, MultimediaDTO>() {
			@Override
			public MultimediaDTO convert(Multimedia md) {
				return new MultimediaDTO(md.getCaseId(), md.getProviderId(), md.getContentType(), md.getFilePath(), md.getFileCategory());
			}
		});
    }
}
