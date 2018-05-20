package org.opensrp.service;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.opensrp.domain.*;
import org.opensrp.dto.PatientReferralsDTO;
import org.opensrp.dto.ReferralsDTO;
import org.opensrp.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReferralPatientsService {

    private static Logger logger = LoggerFactory.getLogger(ReferralPatientsService.class.toString());

    private HttpClient client;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private PatientReferralRepository patientReferralRepository;

    @Autowired
    private PatientsAppointmentsRepository patientsAppointmentsRepository;

	@Autowired
	private PatientReferralIndicatorRepository patientReferralIndicatorRepository;

    @Autowired
    private HealthFacilitiesClientsRepository healthFacilitiesClientsRepository;

    @Autowired
    private HealthFacilityRepository healthFacilityRepository;


    public ReferralPatientsService() {
        this.client = HttpClientBuilder.create().build();
    }


    public void storeHealthFacilityRefferal(ClientReferral clientReferral) throws SQLException {
        if (!this.checkIfClientExists(clientReferral)) {
            try {
                patientReferralRepository.save(clientReferral);
            } catch (Exception e) {
                logger.error("", e);
            }

        }
    }

    public List<PatientReferralsDTO> getAllPatientReferrals(){
        return getClients("SELECT * FROM "+ HealthFacilitiesClients.tbName,null);
    }

    public List<PatientReferralsDTO> getHealthFacilityReferrals(String facilityUUID){

        String[] healthFacilityPatientArg = new String[1];
        healthFacilityPatientArg[0] =  facilityUUID;


        return getClients("SELECT * FROM " + HealthFacilitiesClients.tbName +
                " INNER JOIN "+HealthFacilities.tbName+" ON "+ HealthFacilitiesClients.tbName+"."+ HealthFacilitiesClients.COL_FACILITY_ID +" = "+HealthFacilities.tbName+"._id " +
                " WHERE " + HealthFacilities.COL_OPENMRS_UIID + "=?",healthFacilityPatientArg);
    }

    public  List<PatientReferralsDTO> getClients(String sql, Object[] healthFacilityPatientArg){

        List<HealthFacilitiesClients> healthFacilitiesPatients = null;
        try {
            healthFacilitiesPatients = healthFacilitiesClientsRepository.getHealthFacilityPatients(sql,healthFacilityPatientArg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<PatientReferralsDTO> patientReferralsDTOS = new ArrayList<>();
        for(HealthFacilitiesClients facilitiesPatients:healthFacilitiesPatients){
            String getPatientsSQL = "SELECT * from " + ANCClients.tbName+" WHERE "+ ANCClients.COL_CLIENTS_ID + " = "+facilitiesPatients.getAncClient().getClientId();
            try {
                ANCClients ancClient = patientsRepository.getPatients(getPatientsSQL,null).get(0);

                PatientReferralsDTO patientReferralsDTO = new PatientReferralsDTO();
                patientReferralsDTO.setAncClientDTO(PatientsConverter.toPatientsDTO(ancClient));

                String getReferralPatientsSQL = "SELECT * from " + ClientReferral.tbName+" WHERE "+ ClientReferral.COL_ANC_CLIENT_ID +" =?";
                Object[] args = new Object[]{ancClient.getClientId()};

                List<ReferralsDTO> referralsDTOS = PatientsConverter.toPatientReferralDTOsList(patientReferralRepository.getReferrals(getReferralPatientsSQL,args));

                for(ReferralsDTO referralsDTO : referralsDTOS) {
                    Object[] args2 = new Object[]{referralsDTO.getId()};
                    List<PatientReferralIndicators> patientReferralIndicators = patientReferralIndicatorRepository.getPatientReferralIndicators("SELECT * FROM " + PatientReferralIndicators.tbName + " WHERE " + PatientReferralIndicators.COL_REFERRAL_ID + " =?", args2);
                }

                patientReferralsDTO.setPatientReferralsList(referralsDTOS);


                Object[] args2 = new Object[]{facilitiesPatients.getHealthFacilityClientId()};
                String getPatientsAppointmentsSQL = "SELECT * from " + PatientAppointments.tbName+" WHERE "+PatientAppointments.COL_HEALTH_FACILITY_CLIENT_ID +" =?";
                List<PatientAppointments> patientAppointments = patientsAppointmentsRepository.getAppointments(getPatientsAppointmentsSQL,args2);

                patientReferralsDTO.setPatientsAppointmentsDTOS(PatientsConverter.toPatientAppointmentDTOsList(patientAppointments));

                patientReferralsDTOS.add(patientReferralsDTO);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return patientReferralsDTOS;
    }

    public Boolean checkIfClientExists(ANCClients ancClients) throws SQLException {
        try {
            String checkIfExistQuery = "SELECT count(*) from " + ANCClients.tbName + " WHERE " + ANCClients.COL_CLIENTS_ID + " = ?";
            String[] args = new String[1];
            args[0] = ancClients.getClientId()+"";

            int rowCount = patientsRepository.checkIfExists(checkIfExistQuery, args);

            logger.info(
                    "[checkIfClientExists] - Card Number:" + args[0] + " - [Exists] " + (rowCount == 0 ? "false" : "true"));

            return rowCount >= 1;
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    public Boolean checkIfClientExists(ClientReferral clientReferral) throws SQLException {
        try {
            String checkIfExistQuery = "SELECT count(*) from " + ClientReferral.tbName + " WHERE  "+ ClientReferral.COL_REFERRAL_ID+" = ?";
            Object[] args = new Object[1];
            args[0] = clientReferral.getId();
            int rowCount = patientsRepository.checkIfExists(checkIfExistQuery, args);

            logger.info(
                    "[checkIfClientExists] - Referral ID:" + args[0] + " - [Exists] " + (rowCount == 0 ? "false" : "true"));

            return rowCount >= 1;
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    public String delete_last_char_java(String string) {

        String phrase = "level up lunch";

        String rephrase = null;
        if (phrase != null && phrase.length() > 1) {
            rephrase = phrase.substring(0, phrase.length() - 1);
        }

        return rephrase;
    }



    public long savePatient(ANCClients patient, String healthFacilityCode, String ctcNumber) {
        String query = "SELECT * FROM " + ANCClients.tbName + " WHERE " +
                ANCClients.COL_PATIENT_FIRST_NAME + " = ?     AND " +
                ANCClients.COL_PATIENT_MIDDLE_NAME + " = ?    AND " +
                ANCClients.COL_PATIENT_SURNAME + " = ?        AND " +
                ANCClients.COL_PHONE_NUMBER + " = ?";
        Object[] params = new Object[]{
                patient.getFirstName(),
                patient.getMiddleName(),
                patient.getSurname(),
                patient.getPhoneNumber()};
        List<ANCClients> ANCClientsResults = null;
        try {
            ANCClientsResults = patientsRepository.getPatients(query, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Coze = number of patients found = " + ANCClientsResults.size());
        long id;
        if (ANCClientsResults.size() > 0) {
            System.out.println("Coze = using the received patients ");
            id = ANCClientsResults.get(0).getClientId();
        } else {
            System.out.println("Coze = saving patient Data ");
            try {
                id = patientsRepository.save(patient);
            } catch (Exception e) {
                e.printStackTrace();
                id = -1;
            }
        }

        //Obtaining health facilityId from tbl_facilities
        String healthFacilitySql = "SELECT * FROM " + HealthFacilities.tbName + " WHERE " +
                HealthFacilities.COL_FACILITY_CTC_CODE + " = ? OR " + HealthFacilities.COL_OPENMRS_UIID + " = ?";
        Object[] healthFacilityParams = new Object[]{
                healthFacilityCode,healthFacilityCode};

        System.out.println("Coze facility ctc code = " + healthFacilityCode);
        Long healthFacilityId = (long) 0;
        List<HealthFacilities> healthFacilities = null;
        try {
            healthFacilities = healthFacilityRepository.getHealthFacility(healthFacilitySql, healthFacilityParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (healthFacilities.size() > 0) {
            healthFacilityId = healthFacilities.get(0).getId();
        }

        HealthFacilitiesClients healthFacilitiesClients = new HealthFacilitiesClients();

        ANCClients ANCClients = new ANCClients();
        ANCClients.setClientId(id);

        healthFacilitiesClients.setAncClient(ANCClients);
        healthFacilitiesClients.setCtcNumber(ctcNumber);
        healthFacilitiesClients.setFacilityId(healthFacilityId);


        String healthFacilityPatientsquery = "SELECT * FROM " + HealthFacilitiesClients.tbName + " WHERE " +
                HealthFacilitiesClients.COL_CLIENT_ID + " = ?    AND " +
                HealthFacilitiesClients.COL_FACILITY_ID + " = ?";

        Object[] healthFacilityPatientsparams = new Object[]{
                healthFacilitiesClients.getAncClient().getClientId(),
                healthFacilitiesClients.getFacilityId()};

        List<HealthFacilitiesClients> healthFacilitiesClientsResults = null;
        try {
            healthFacilitiesClientsResults = healthFacilitiesClientsRepository.getHealthFacilityPatients(healthFacilityPatientsquery, healthFacilityPatientsparams);
        } catch (Exception e) {
            e.printStackTrace();
        }


        long healthfacilityPatientId = -1;
        if (healthFacilitiesClientsResults.size() > 0) {
            healthfacilityPatientId = healthFacilitiesClientsResults.get(0).getHealthFacilityClientId();
        } else {
            try {
                healthfacilityPatientId = healthFacilitiesClientsRepository.save(healthFacilitiesClients);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return healthfacilityPatientId;
    }
}
