package org.opensrp.service;

import org.opensrp.domain.*;
import org.opensrp.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.*;

import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

public class PatientsConverter {
    private static Logger logger = LoggerFactory.getLogger(PatientsConverter.class.toString());


    public static ANCClients toPatients(AncClientDTO ancClientDTO) {
        try {
            ANCClients client = new ANCClients();




            client.setClientId(ancClientDTO.getClientId());
            client.setFirstName(ancClientDTO.getFirstName());
            client.setMiddleName(ancClientDTO.getMiddleName());
            client.setSurname(ancClientDTO.getSurname());
            client.setPhoneNumber(ancClientDTO.getPhoneNumber());

            Date dob = new Date();
            dob.setTime(ancClientDTO.getDateOfBirth());
            client.setDateOfBirth(dob);
            client.setWard(ancClientDTO.getWard());
            client.setVillage(ancClientDTO.getVillage());

            client.setMapCue(ancClientDTO.getMapCue());
            client.setPmtctStatus(ancClientDTO.getPmtctStatus());
            client.setHeightBelowAverage(ancClientDTO.isHeightBelowAverage());
            client.setLevelOfEducation(ancClientDTO.getLevelOfEducation());

            client.setSpouseName(ancClientDTO.getSpouseName());
            client.setGravida(ancClientDTO.getGravida());
            client.setPara(ancClientDTO.getPara());
            client.setGestationalAgeBelow20(ancClientDTO.isGestationalAgeBelow20());
            client.setHistoryOfAbortion(ancClientDTO.isHistoryOfAbortion());
            client.setAgeBelow20Years(ancClientDTO.isAgeBelow20Years());
            client.setLastPregnancyOver10Years(ancClientDTO.isLastPregnancyOver10Years());
            client.setPregnancyAbove35Years(ancClientDTO.isPregnancyAbove35Years());
            client.setHistoryOfStillBirth(ancClientDTO.isHistoryOfStillBirth());
            client.setHistoryOfPostmartumHaemorrhage(ancClientDTO.isHistoryOfPostmartumHaemorrhage());
            client.setHistoryOfRetainedPlacenta(ancClientDTO.isHistoryOfRetainedPlacenta());
            client.setLastChildbirthYear(ancClientDTO.getLastChildbirthYear());
            client.setLastChildbirthStatus(ancClientDTO.getLastChildbirthStatus());
            client.setClientType(ancClientDTO.getClientType());
            client.setCardNumber(ancClientDTO.getCardNumber());


            try {
                Date lmnp = new Date();
                lmnp.setTime(ancClientDTO.getLmnpDate());
                client.setLmnpDate(lmnp);
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                Date edd = new Date();
                edd.setTime(ancClientDTO.getEdd());
                client.setEdd(edd);
            }catch (Exception e){
                e.printStackTrace();
            }

            client.setCreatedAt(Calendar.getInstance().getTime());
            client.setUpdatedAt(Calendar.getInstance().getTime());




            return client;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", ancClientDTO, e));
            throw e;
        }
    }


    public static List<PatientAppointments> toPatientsAppointments(CTCPatientsDTO patientsDTO) {
        try {

            List<PatientAppointments> patientAppointments = new ArrayList<>();
            List<CTCPatientsAppointmesDTO> appointments = patientsDTO.getPatientAppointments();

            if (appointments!=null) {
	            for (CTCPatientsAppointmesDTO appointment : appointments) {
		            PatientAppointments patientAppointment = new PatientAppointments();
		            Date appointDate = new Date();
		            appointDate.setTime(appointment.getDateOfAppointment());

		            patientAppointment.setAppointmentDate(appointDate);
		            patientAppointment.setIsCancelled(appointment.isCancelled());


		            try {
			            Date rowVersion = new Date();
			            rowVersion.setTime(appointment.getRowVersion());
			            patientAppointment.setRowVersion(rowVersion);
		            }catch (Exception e){
		            	e.printStackTrace();
			            patientAppointment.setRowVersion(null);
		            }


		            patientAppointment.setStatus("0");
		            patientAppointment.setAppointmentType(1);
		            patientAppointments.add(patientAppointment);
	            }
            }else{
                System.out.println("coze patients appointment is empty");
            }

            return patientAppointments;

        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }


    public static AncClientDTO toPatientsDTO(ANCClients client) {
        try {
            AncClientDTO ancClientDTO = new AncClientDTO();

            ancClientDTO.setClientId(client.getClientId());
            ancClientDTO.setFirstName(client.getFirstName());
            ancClientDTO.setMiddleName(client.getMiddleName());
            ancClientDTO.setSurname(client.getSurname());
            ancClientDTO.setPhoneNumber(client.getPhoneNumber());


            ancClientDTO.setDateOfBirth(client.getDateOfBirth().getTime());

            ancClientDTO.setWard(client.getWard());
            ancClientDTO.setVillage(client.getVillage());

            ancClientDTO.setMapCue(client.getMapCue());
            ancClientDTO.setPmtctStatus(client.getPmtctStatus());
            ancClientDTO.setHeightBelowAverage(client.isHeightBelowAverage());
            ancClientDTO.setLevelOfEducation(client.getLevelOfEducation());

            ancClientDTO.setSpouseName(client.getSpouseName());
            ancClientDTO.setGravida(client.getGravida());
            ancClientDTO.setPara(client.getPara());
            ancClientDTO.setGestationalAgeBelow20(client.isGestationalAgeBelow20());
            ancClientDTO.setHistoryOfAbortion(client.isHistoryOfAbortion());
            ancClientDTO.setAgeBelow20Years(client.isAgeBelow20Years());
            ancClientDTO.setLastPregnancyOver10Years(client.isLastPregnancyOver10Years());
            ancClientDTO.setPregnancyAbove35Years(client.isPregnancyAbove35Years());
            ancClientDTO.setHistoryOfStillBirth(client.isHistoryOfStillBirth());
            ancClientDTO.setHistoryOfPostmartumHaemorrhage(client.isHistoryOfPostmartumHaemorrhage());
            ancClientDTO.setHistoryOfRetainedPlacenta(client.isHistoryOfRetainedPlacenta());
            ancClientDTO.setLastChildbirthYear(client.getLastChildbirthYear());
            ancClientDTO.setLastChildbirthStatus(client.getLastChildbirthStatus());
            ancClientDTO.setClientType(client.getClientType());
            ancClientDTO.setCardNumber(client.getCardNumber());

            try {
                ancClientDTO.setLmnpDate(client.getLmnpDate().getTime());
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                ancClientDTO.setEdd(client.getEdd().getTime());
            }catch (Exception e){
                e.printStackTrace();
            }

            return ancClientDTO;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting ANCClient :{0}, failed with error: {1}.", client, e));
            throw e;
        }
    }

    public static TBPatientDTO toTbPatientDTO(TBPatient patient) {
        try {
            TBPatientDTO tbPatientDTO = new TBPatientDTO();

            tbPatientDTO.setHealthFacilityPatientId(patient.getHealthFacilitiesClients().getHealthFacilityClientId());
            tbPatientDTO.setMakohozi(patient.getMakohozi());
            tbPatientDTO.setOtherTestsDetails(patient.getOtherTestsDetails());
            tbPatientDTO.setTestType(patient.getTestType());
            tbPatientDTO.setOutcome(patient.getOutcome());
            tbPatientDTO.setOutcomeDate(patient.getOutcomeDate().getTime());
            tbPatientDTO.setOutcomeDetails(patient.getOutcomeDetails());
            tbPatientDTO.setPatientType(patient.getPatientType());
            tbPatientDTO.setPregnant(patient.isPregnant());
            tbPatientDTO.setReferralType(patient.getReferralType());
            tbPatientDTO.setTbPatientId(patient.getTbPatientId());
            tbPatientDTO.setTransferType(patient.getTransferType());
            tbPatientDTO.setTreatment_type(patient.getTreatment_type());
            tbPatientDTO.setVeo(patient.getVeo());
            tbPatientDTO.setWeight(patient.getWeight());
            tbPatientDTO.setXray(patient.getXray());

            return tbPatientDTO;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBPatientDTO :{0}, failed with error: {1}.", patient, e));
            throw e;
        }
    }


    public static List<ClientReferral> toPatientReferralsList(List<ReferralsDTO> referralsDTOs) {
        try {

            List<ClientReferral> clientReferrals = new ArrayList<>();
            for(ReferralsDTO referralsDTO:referralsDTOs){
                clientReferrals.add(toPatientReferral(referralsDTO));
            }

            return clientReferrals;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting List<ReferralsDTO> :{0}, failed with error: {1}.", referralsDTOs, e));
            throw e;
        }
    }



    public static List<ReferralsDTO> toPatientReferralDTOsList(List<ClientReferral> referrals) {
        try {

            List<ReferralsDTO> referralsDTOS = new ArrayList<>();
            for(ClientReferral referral:referrals){
                referralsDTOS.add(toPatientDTO(referral));
            }

            return referralsDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageFormat.format("Converting List<PatientReferral> :{0}, failed with error: {1}.", referrals, e));
            throw e;
        }
    }


    public static ClientReferral toPatientReferral(ReferralsDTO referralsDTO) {
        try {
            ClientReferral referral = new ClientReferral();

            referral.setId(referralsDTO.getId());
            referral.setAncClientId(referralsDTO.getAncClientId());
            referral.setReferralReason(referralsDTO.getReferralReason());
            referral.setInstanceId(referralsDTO.getInstanceId());
            referral.setReferralUUID(referralsDTO.getReferralUUID());
            referral.setServiceProviderUUID(referralsDTO.getServiceProviderUUID());
            referral.setOtherNotes(referralsDTO.getOtherClinicalInformation());
            referral.setReferralFeedback(referralsDTO.getReferralFeedback());
            referral.setReferralType(referralsDTO.getReferralType());
            referral.setFromFacilityId(referralsDTO.getFromFacilityId());

            Date referralDate = new Date();
            referralDate.setTime(referralsDTO.getReferralDate());

            referral.setReferralDate(referralDate);
            referral.setFacilityId(referralsDTO.getFacilityId());
            referral.setReferralStatus(referralsDTO.getReferralStatus());


            return referral;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", referralsDTO, e));
            throw e;
        }
    }


    public static ReferralsDTO toPatientDTO(ClientReferral referral) {
        try {
            ReferralsDTO referralsDTO = new ReferralsDTO();

            referralsDTO.setId(referral.getId());
            referralsDTO.setAncClientId(referral.getAncClientId());
            referralsDTO.setReferralReason(referral.getReferralReason());
            referralsDTO.setInstanceId(referral.getInstanceId());
            referralsDTO.setReferralUUID(referral.getReferralUUID());
            referralsDTO.setServiceProviderUUID(referral.getServiceProviderUUID());
            referralsDTO.setOtherClinicalInformation(referral.getOtherNotes());
            referralsDTO.setReferralFeedback(referral.getReferralFeedback());
            referralsDTO.setReferralType(referral.getReferralType());
            referralsDTO.setFromFacilityId(referral.getFromFacilityId());

            referralsDTO.setReferralDate(referral.getReferralDate().getTime());
            referralsDTO.setFacilityId(referral.getFacilityId());
            referralsDTO.setReferralStatus(referral.getReferralStatus());


            return referralsDTO;

        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting ClientReferral :{0}, failed with error: {1}.", referral, e));
            e.printStackTrace();
            throw e;
        }
    }


    public static RoutineVisits toRoutineVisit(RoutineVisitDTO routineVisitDTO) {
        try {
           RoutineVisits encounter = new RoutineVisits();


            routineVisitDTO.setId(encounter.getId());
            routineVisitDTO.setHealthFacilityClientId(encounter.getHealthFacilityClientId());
            routineVisitDTO.setVisitNumber(encounter.getVisitNumber());

            routineVisitDTO.setVisitDate(encounter.getVisitDate().getTime());
            routineVisitDTO.setAppointmentDate(encounter.getAppointmentDate().getTime());

            routineVisitDTO.setAnaemia(encounter.isAnaemia());
            routineVisitDTO.setOedema(encounter.isOedema());
            routineVisitDTO.setProtenuria(encounter.isProtenuria());
            routineVisitDTO.setHighBloodPressure(encounter.isHighBloodPressure());
            routineVisitDTO.setWeightStagnation(encounter.isWeightStagnation());
            routineVisitDTO.setAntepartumHaemorrhage(encounter.isAntepartumHaemorrhage());
            routineVisitDTO.setSugarInTheUrine(encounter.isSugarInTheUrine());
            routineVisitDTO.setFetusLie(encounter.isFetusLie());


            return encounter;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBEncounterDTO :{0}, failed with error: {1}.", routineVisitDTO, e));
            throw e;
        }
    }


	public static List<PatientsAppointmentsDTO> toPatientAppointmentDTOsList(List<PatientAppointments> patientAppointments) {
		try {

			List<PatientsAppointmentsDTO> patientsAppointmentsDTOS = new ArrayList<>();
			for(PatientAppointments appointments:patientAppointments){
				patientsAppointmentsDTOS.add(toPatientAppointmentsDTO(appointments));
			}

			return patientsAppointmentsDTOS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Converting  List<PatientsAppointmentsDTO> :{0}, failed with error: {1}.", patientAppointments, e));
			throw e;
		}
	}

	public static PatientsAppointmentsDTO toPatientAppointmentsDTO(PatientAppointments patientAppointments) {
		try {
			PatientsAppointmentsDTO patientsAppointmentsDTO = new PatientsAppointmentsDTO();

			patientsAppointmentsDTO.setAppointment_id(patientAppointments.getAppointment_id());
			patientsAppointmentsDTO.setAppointmentDate(patientAppointments.getAppointmentDate().getTime());
			patientsAppointmentsDTO.setIsCancelled(patientAppointments.getIsCancelled());
			patientsAppointmentsDTO.setHealthFacilityClientId(patientAppointments.getHealthFacilityClientId());
			patientsAppointmentsDTO.setStatus(patientAppointments.getStatus());
			patientsAppointmentsDTO.setAppointmentType(patientAppointments.getAppointmentType());
			patientsAppointmentsDTO.setVisitNumber(patientAppointments.getVisitNumber());

			return patientsAppointmentsDTO;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Converting PatientsAppointmentsDTO :{0}, failed with error: {1}.", patientAppointments, e));
			throw e;
		}
	}

    public static List<RoutineVisitDTO> toTbPatientEncounterDTOsList(List<RoutineVisits> routineVisits) {
        try {

            List<RoutineVisitDTO> routineVisitDTOS = new ArrayList<>();
            for(RoutineVisits routineVisit : routineVisits){
                routineVisitDTOS.add(toTbEncounterDTO(routineVisit));
            }

            return routineVisitDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageFormat.format("Converting   List<RoutineVisits> :{0}, failed with error: {1}.", routineVisits, e));
            throw e;
        }
    }

	public static RoutineVisitDTO toTbEncounterDTO(RoutineVisits routineVisits) {
		try {
            RoutineVisitDTO routineVisitDTO = new RoutineVisitDTO();

            routineVisitDTO.setId(routineVisits.getId());
            routineVisitDTO.setHealthFacilityClientId(routineVisits.getHealthFacilityClientId());
            routineVisitDTO.setVisitNumber(routineVisits.getVisitNumber());

            routineVisitDTO.setVisitDate(routineVisits.getVisitDate().getTime());
            routineVisitDTO.setAppointmentDate(routineVisits.getAppointmentDate().getTime());

            routineVisitDTO.setAnaemia(routineVisits.isAnaemia());
            routineVisitDTO.setOedema(routineVisits.isOedema());
            routineVisitDTO.setProtenuria(routineVisits.isProtenuria());
            routineVisitDTO.setHighBloodPressure(routineVisits.isHighBloodPressure());
            routineVisitDTO.setWeightStagnation(routineVisits.isWeightStagnation());
            routineVisitDTO.setAntepartumHaemorrhage(routineVisits.isAntepartumHaemorrhage());
            routineVisitDTO.setSugarInTheUrine(routineVisits.isSugarInTheUrine());
            routineVisitDTO.setFetusLie(routineVisits.isFetusLie());

			return routineVisitDTO;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Converting TBEncounterDTO :{0}, failed with error: {1}.", routineVisits, e));
			throw e;
		}
	}


}
