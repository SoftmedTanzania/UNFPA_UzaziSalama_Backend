package org.opensrp.web.controller;

import static ch.lambdaj.collection.LambdaCollections.with;
import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.opensrp.dto.PatientReferralsDTO;
import org.opensrp.dto.ReferralsDTO;
import org.opensrp.dto.form.FormSubmissionDTO;
import org.opensrp.dto.form.MultimediaDTO;
import org.opensrp.form.domain.FormSubmission;
import org.opensrp.form.service.FormSubmissionConverter;
import org.opensrp.form.service.FormSubmissionService;
import org.opensrp.repository.*;
import org.opensrp.scheduler.SystemEvent;
import org.opensrp.scheduler.TaskSchedulerService;
import org.opensrp.service.ErrorTraceService;
import org.opensrp.service.GoogleFCMService;
import org.opensrp.service.MultimediaService;
import org.opensrp.service.PatientsConverter;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ch.lambdaj.function.convert.Converter;

@Controller
public class FormSubmissionController {
    private static Logger logger = LoggerFactory.getLogger(FormSubmissionController.class.toString());
    private FormSubmissionService formSubmissionService;
    private GoogleFCMService googleFCMService;
    private TaskSchedulerService scheduler;
    private EncounterService encounterService;
    private FormEntityConverter formEntityConverter;
    private PatientService patientService;
    private HouseholdService householdService;
    private ErrorTraceService errorTraceService;
    private MultimediaService multimediaService;
    private MultimediaRepository multimediaRepository;
    private PatientsRepository patientsRepository;
    private PatientReferralRepository patientReferralRepository;
    private PatientReferralIndicatorRepository patientReferralIndicatorRepository;
    private HealthFacilityRepository healthFacilityRepository;
    private GooglePushNotificationsUsersRepository googlePushNotificationsUsersRepository;

    @Autowired
    public FormSubmissionController(FormSubmissionService formSubmissionService, TaskSchedulerService scheduler,
    		EncounterService encounterService, FormEntityConverter formEntityConverter, PatientService patientService, 
    		HouseholdService householdService,MultimediaService multimediaService, MultimediaRepository multimediaRepository,
    		ErrorTraceService errorTraceService,PatientsRepository patientsRepository,PatientReferralRepository patientReferralRepository,
		                            GooglePushNotificationsUsersRepository googlePushNotificationsUsersRepository,GoogleFCMService googleFCMService,
		                            HealthFacilityRepository healthFacilityRepository,PatientReferralIndicatorRepository patientReferralIndicatorRepository) {
        this.formSubmissionService = formSubmissionService;
        this.scheduler = scheduler;
        this.errorTraceService=errorTraceService;
        this.encounterService = encounterService;
        this.formEntityConverter = formEntityConverter;
        this.patientService = patientService;
        this.householdService = householdService;
        this.multimediaService = multimediaService;
        this.multimediaRepository = multimediaRepository;
        this.patientsRepository = patientsRepository;
        this.patientReferralRepository = patientReferralRepository;
        this.googlePushNotificationsUsersRepository = googlePushNotificationsUsersRepository;
	    this.googleFCMService =googleFCMService;
	    this.healthFacilityRepository = healthFacilityRepository;
	    this.patientReferralIndicatorRepository = patientReferralIndicatorRepository;
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
	            		addFormToOpenMRS(formSubmission);
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
        System.out.println("Coze = saving patient into OpenSRP");
        Patients patients = formEntityConverter.getPatientFromFormSubmission(formSubmission);
        PatientReferral patientReferral = formEntityConverter.getPatientReferralFromFormSubmission(formSubmission);
		try {
            /**
             * Check if the patient already exists by comparing his/her names and phone numbers.
             */

            String query = "SELECT * FROM " + Patients.tbName + " WHERE " +
                    Patients.COL_PATIENT_FIRST_NAME + " = ?     AND " +
                    Patients.COL_PATIENT_MIDDLE_NAME + " = ?    AND " +
                    Patients.COL_PATIENT_SURNAME + " = ?        AND " +
                    Patients.COL_PHONE_NUMBER + " = ?" ;

            Object[] params = new Object[] {
                    patients.getFirstName(),
                    patients.getMiddleName(),
                    patients.getSurname(),
                    patients.getPhoneNumber()};
            List<Patients> patientsResults = patientsRepository.getPatients(query,params);
            System.out.println("Coze = number of patients found = "+patientsResults.size());

            if(patientsResults.size()>0){
                System.out.println("Coze = using the received patients");
	            patientReferral.setPatient(patientsResults.get(0));
            }else{
                System.out.println("Coze = saving patient Data");
                Long id = patientsRepository.save(patients);
                patients.setPatientId(id);
	            patientReferral.setPatient(patients);
            }

            //TODO remove hardcoding of these values
            patientReferral.setReferralSource(0);
            patientReferral.setReferralStatus(0);
            patientReferral.setReferralType(1);

            System.out.println("Coze = saving referral Data");
            long id = patientReferralRepository.save(patientReferral);
            patientReferral.setId(id);


			JSONArray indicatorIds = formEntityConverter.getReferralIndicatorsFromFormSubmission(formSubmission);
			int size  = indicatorIds.length();

			List<Long> referralIndicatorIds = new ArrayList<>();
			for(int i=0;i<size;i++){
				PatientReferralIndicators referralIndicators = new PatientReferralIndicators();
				referralIndicators.setActive(true);
				referralIndicators.setReferralId(id);
				referralIndicators.setReferralServiceIndicatorId(indicatorIds.getLong(i));

				long patientReferralIndicatorId = patientReferralIndicatorRepository.save(referralIndicators);
				referralIndicatorIds.add(patientReferralIndicatorId);
			}

			Object[] facilityParams = new Object[]{patientReferral.getFacilityId(),1};
			List<GooglePushNotificationsUsers> googlePushNotificationsUsers = googlePushNotificationsUsersRepository.getGooglePushNotificationsUsers("SELECT * FROM "+GooglePushNotificationsUsers.tbName+" WHERE "+GooglePushNotificationsUsers.COL_FACILITY_UIID+" = ? AND "+GooglePushNotificationsUsers.COL_USER_TYPE+" = ?",facilityParams);
			JSONArray tokens = new JSONArray();
			for(GooglePushNotificationsUsers googlePushNotificationsUsers1:googlePushNotificationsUsers){
				tokens.put(googlePushNotificationsUsers1.getGooglePushNotificationToken());
			}



			List<ReferralsDTO> patientReferrals = new ArrayList<>();
			ReferralsDTO referralsDTO = PatientsConverter.toPatientDTO(patientReferral);
			referralsDTO.setServiceIndicatorIds(referralIndicatorIds);
			patientReferrals.add(referralsDTO);

			PatientReferralsDTO patientReferralsDTO = new PatientReferralsDTO();
			patientReferralsDTO.setPatientsDTO(PatientsConverter.toPatientsDTO(patients));
			patientReferralsDTO.setPatientReferralsList(patientReferrals);


			JSONObject body = new JSONObject();
			body.put("type","PatientReferral");

			JSONObject notificationObject = new JSONObject();
			notificationObject.put("body",body);


			String json = new Gson().toJson(patientReferralsDTO);

			JSONObject msg = new JSONObject(json);

			googleFCMService.SendPushNotification(msg,notificationObject,tokens,true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(format("Patient Form submissions processing failed with exception {0}.\nSubmissions: {1}", e, formSubmission));

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
