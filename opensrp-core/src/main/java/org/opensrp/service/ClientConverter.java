package org.opensrp.service;

import org.opensrp.domain.*;
import org.opensrp.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.*;

import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;

public class ClientConverter {
    private static Logger logger = LoggerFactory.getLogger(ClientConverter.class.toString());


    public static ANCClients toANCClient(AncClientDTO ancClientDTO) {
        try {
            ANCClients client = new ANCClients();


            client.setClientId(ancClientDTO.getAncClientId());
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

            client.setUseOfFamilyPlanningTechniques(ancClientDTO.isUseOfFamilyPlanningTechniques());
            client.setFamilyPlanningTechniqueId(ancClientDTO.getFamilyPlanningTechniqueId());

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

	public static AncClientDTO toPatientsDTO(ANCClients client) {
		try {
			AncClientDTO ancClientDTO = new AncClientDTO();

			ancClientDTO.setAncClientId(client.getClientId());
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

			ancClientDTO.setUseOfFamilyPlanningTechniques(client.isUseOfFamilyPlanningTechniques());
			ancClientDTO.setFamilyPlanningTechniqueId(client.getFamilyPlanningTechniqueId());

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
				ancClientDTO.setCreatedAt(client.getCreatedAt().getTime());
			}catch (Exception e){
				e.printStackTrace();
			}

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

    public static PNCClientDTO toPNCClientDTO(PNCClients clients) {
        try {
            PNCClientDTO pncClientDTO = new PNCClientDTO();

            pncClientDTO.setHealthFacilityClientID(clients.getHealthFacilityClientId());
            pncClientDTO.setPncClientID(clients.getPncClientsId());
            pncClientDTO.setApgarScore(clients.getApgarScore());
            pncClientDTO.setChildAbnomalities(clients.isChilds_abnormalites());
            pncClientDTO.setChildDischargeCondition(clients.getChilds_discharge_condition());
            pncClientDTO.setChildGender(clients.getChildsGender());
            pncClientDTO.setChildWeight(clients.getChildsWeight());
            pncClientDTO.setDateOfAdmission(clients.getDateOfAdmission().getTime());
            pncClientDTO.setDateOfDelivery(clients.getDateOfDelivery().getTime());
            pncClientDTO.setDeliveryComplications(clients.getDelivery_complications());
            pncClientDTO.setDeliveryMethod(clients.getDeliveryMethods());
            pncClientDTO.setDiedWithin24Hours(clients.isDied_within_24_hrs());
            pncClientDTO.setKuharibikaMimba(clients.getKuharibikaMimba());
            pncClientDTO.setMotherDischargeCondition(clients.getMothersDischargeCondition());
            pncClientDTO.setStillBirthTypes(clients.getTypes_of_still_birth());
            pncClientDTO.setChildPlaceOfBirth(clients.getChildPlaceOfBirth());


            return pncClientDTO;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting PNCClientDTO :{0}, failed with error: {1}.", clients, e));
            throw e;
        }
    }

    public static PNCClients toPNCClient(PNCClientDTO clientsDTO) {
        try {
            PNCClients pncClient = new PNCClients();

            pncClient.setHealthFacilityClientId(clientsDTO.getHealthFacilityClientID());
            pncClient.setPncClientsId(clientsDTO.getPncClientID());
            pncClient.setApgarScore(clientsDTO.getApgarScore());
            pncClient.setChilds_abnormalites(clientsDTO.isChildAbnomalities());
            pncClient.setChilds_discharge_condition(clientsDTO.getChildDischargeCondition());
            pncClient.setChildsGender(clientsDTO.getChildGender());
            pncClient.setChildsWeight(clientsDTO.getChildWeight());


            try {
                Date admissionDate = new Date();
                admissionDate.setTime(clientsDTO.getDateOfAdmission());
                pncClient.setDateOfAdmission(admissionDate);
            }catch (Exception e){
                e.printStackTrace();
                pncClient.setDateOfAdmission(null);
            }

            try {
                Date deliveryDate = new Date();
                deliveryDate.setTime(clientsDTO.getDateOfDelivery());
                pncClient.setDateOfDelivery(deliveryDate);
            }catch (Exception e){
                e.printStackTrace();
                pncClient.setDateOfDelivery(null);
            }



            pncClient.setDelivery_complications(clientsDTO.getDeliveryComplications());
            pncClient.setDeliveryMethods(clientsDTO.getDeliveryMethod());
            pncClient.setDied_within_24_hrs(clientsDTO.isDiedWithin24Hours());
            pncClient.setKuharibikaMimba(clientsDTO.isKuharibikaMimba());
            pncClient.setMothersDischargeCondition(clientsDTO.getMotherDischargeCondition());
            pncClient.setTypes_of_still_birth(clientsDTO.getStillBirthTypes());


            return pncClient;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting PNCClientDTO :{0}, failed with error: {1}.", clientsDTO, e));
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


    public static RoutineVisits toRoutineVisit(RoutineVisitDTO encounter) {
        try {
           RoutineVisits routineVisitDTO = new RoutineVisits();


           try {
	           routineVisitDTO.setId(encounter.getId());
           }catch (Exception e){
               e.printStackTrace();
           }

            routineVisitDTO.setHealthFacilityClientId(encounter.getHealthFacilityClientId());
            routineVisitDTO.setVisitNumber(encounter.getVisitNumber());

            routineVisitDTO.setVisitDate(new Date(encounter.getVisitDate()));
            routineVisitDTO.setAppointmentDate(new Date(encounter.getAppointmentDate()));

            routineVisitDTO.setAppointmentId(encounter.getAppointmentId());
            routineVisitDTO.setAnaemia(encounter.isAnaemia());
            routineVisitDTO.setOedema(encounter.isOedema());
            routineVisitDTO.setProtenuria(encounter.isProtenuria());
            routineVisitDTO.setHighBloodPressure(encounter.isHighBloodPressure());
            routineVisitDTO.setWeightStagnation(encounter.isWeightStagnation());
            routineVisitDTO.setAntepartumHaemorrhage(encounter.isAntepartumHaemorrhage());
            routineVisitDTO.setSugarInTheUrine(encounter.isSugarInTheUrine());
            routineVisitDTO.setFetusLie(encounter.isFetusLie());


            return routineVisitDTO;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageFormat.format("Converting TBEncounterDTO :{0}, failed with error: {1}.", encounter, e));
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
			patientsAppointmentsDTO.setHealthFacilityClientId(patientAppointments.getHealthFacilitiesClients().getHealthFacilityClientId());
			patientsAppointmentsDTO.setStatus(patientAppointments.getStatus());
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
