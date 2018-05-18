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


    public static ANCClients toPatients(PatientsDTO patientsDTO) {
        try {
            ANCClients ANCClients = new ANCClients();


            ANCClients.setPatientId(patientsDTO.getPatientId());
            ANCClients.setFirstName(patientsDTO.getFirstName());
            ANCClients.setMiddleName(patientsDTO.getMiddleName());
            ANCClients.setSurname(patientsDTO.getSurname());
            ANCClients.setPhoneNumber(patientsDTO.getPhoneNumber());

            Date dob = new Date();
            dob.setTime(patientsDTO.getDateOfBirth());

            ANCClients.setDateOfBirth(dob);
            ANCClients.setGender(patientsDTO.getGender());
            ANCClients.setWard(patientsDTO.getWard());
            ANCClients.setVillage(patientsDTO.getVillage());
            ANCClients.setHamlet(patientsDTO.getHamlet());
            ANCClients.setCareTakerName(patientsDTO.getCareTakerName());
            ANCClients.setCareTakerPhoneNumber(patientsDTO.getPhoneNumber());
            ANCClients.setCareTakerRelationship(patientsDTO.getCareTakerRelationship());
            ANCClients.setCommunityBasedHivService(patientsDTO.getCommunityBasedHivService());

            try {
                Date deathDate = new Date();
                deathDate.setTime(patientsDTO.getDateOfDeath());
                ANCClients.setDateOfDeath(deathDate);
            }catch (Exception e){
                e.printStackTrace();
            }

            ANCClients.setHivStatus(patientsDTO.isHivStatus());
            ANCClients.setCreatedAt(Calendar.getInstance().getTime());
            ANCClients.setUpdatedAt(Calendar.getInstance().getTime());

            return ANCClients;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }



    public static ANCClients toPatients(CTCPatientsDTO patientsDTO) {
        try {
            ANCClients ANCClients = new ANCClients();
            ANCClients.setFirstName(patientsDTO.getFirstName());
	        ANCClients.setMiddleName(patientsDTO.getMiddleName());
            ANCClients.setSurname(patientsDTO.getSurname());
            ANCClients.setPhoneNumber(patientsDTO.getContact());
            Date dob = new Date();
            dob.setTime(patientsDTO.getDateOfBirth());
            ANCClients.setDateOfBirth(dob);
            ANCClients.setGender(patientsDTO.getGender());

            Date deathDate = new Date();
            deathDate.setTime(patientsDTO.getDateOfDeath());

            ANCClients.setCareTakerName(patientsDTO.getCareTakerName());
            ANCClients.setCareTakerPhoneNumber(patientsDTO.getCareTakerPhoneNumber());
            ANCClients.setCareTakerRelationship(patientsDTO.getCareTakerRelationship());

            ANCClients.setDateOfDeath(deathDate);
            ANCClients.setCreatedAt(Calendar.getInstance().getTime());
            ANCClients.setUpdatedAt(Calendar.getInstance().getTime());
            ANCClients.setHivStatus(patientsDTO.isHivStatus());

            return ANCClients;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }


    public static ANCClients toPatients(TBPatientMobileClientDTO patientsDTO) {
        try {
            ANCClients ANCClients = new ANCClients();


            ANCClients.setFirstName(patientsDTO.getFirstName());
            ANCClients.setSurname(patientsDTO.getSurname());
            ANCClients.setMiddleName(patientsDTO.getMiddleName());
            ANCClients.setPhoneNumber(patientsDTO.getPhoneNumber());
            ANCClients.setHamlet(patientsDTO.getHamlet());
            ANCClients.setVillage(patientsDTO.getVillage());
            ANCClients.setHamlet(patientsDTO.getHamlet());
            ANCClients.setPhoneNumber(patientsDTO.getPhoneNumber());
            Date dob = new Date();
            dob.setTime(patientsDTO.getDateOfBirth());
            ANCClients.setDateOfBirth(dob);
            ANCClients.setGender(patientsDTO.getGender());
            ANCClients.setHivStatus(patientsDTO.isHivStatus());
            ANCClients.setHivStatus(patientsDTO.isHivStatus());

            Date deathDate = new Date();
            deathDate.setTime(patientsDTO.getDateOfDeath());

            ANCClients.setDateOfDeath(deathDate);
            ANCClients.setCreatedAt(Calendar.getInstance().getTime());
            ANCClients.setUpdatedAt(Calendar.getInstance().getTime());

            return ANCClients;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBPatientMobileClientDTO :{0}, failed with error: {1}.", patientsDTO, e));
            throw e;
        }
    }

    public static TBPatient toTBPatients(TBPatientMobileClientDTO patientsDTO) {
        try {
            TBPatient tbPatient = new TBPatient();
            tbPatient.setOutcomeDetails(patientsDTO.getOutcomeDetails());
            tbPatient.setOutcome(patientsDTO.getOutcome());
            tbPatient.setTbPatientId(patientsDTO.getPatientId());

	        Date outcomeDate = new Date();
	        outcomeDate.setTime(patientsDTO.getOutcomeDate());

            tbPatient.setOutcomeDate(outcomeDate);
            tbPatient.setOtherTestsDetails(patientsDTO.getOtherTestsDetails());
            tbPatient.setTestType(patientsDTO.getTestType());
            tbPatient.setMakohozi(patientsDTO.getMakohozi());
            tbPatient.setWeight(patientsDTO.getWeight());
            tbPatient.setXray(patientsDTO.getXray());
            tbPatient.setVeo(patientsDTO.getVeo());
            tbPatient.setReferralType(patientsDTO.getReferralType());
            tbPatient.setTreatment_type(patientsDTO.getTreatment_type());
            tbPatient.setTransferType(patientsDTO.getTransferType());
            tbPatient.setPatientType(patientsDTO.getPatientType());
            tbPatient.setPregnant(patientsDTO.isPregnant());

            return tbPatient;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBPatientMobileClientDTO :{0}, failed with error: {1}.", patientsDTO, e));
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


    public static PatientsDTO toPatientsDTO(ANCClients ANCClients) {
        try {
            PatientsDTO patientsDTO = new PatientsDTO();

            patientsDTO.setPatientId(ANCClients.getPatientId());
            patientsDTO.setFirstName(ANCClients.getFirstName());
            patientsDTO.setSurname(ANCClients.getSurname());
            patientsDTO.setMiddleName(ANCClients.getMiddleName());
            patientsDTO.setPhoneNumber(ANCClients.getPhoneNumber());
            patientsDTO.setDateOfBirth(ANCClients.getDateOfBirth().getTime());
            patientsDTO.setGender(ANCClients.getGender());
            patientsDTO.setWard(ANCClients.getWard());
            patientsDTO.setVillage(ANCClients.getVillage());
            patientsDTO.setHamlet(ANCClients.getHamlet());

            patientsDTO.setCareTakerName(ANCClients.getCareTakerName());
            patientsDTO.setCareTakerPhoneNumber(ANCClients.getCareTakerPhoneNumber());
            patientsDTO.setCareTakerRelationship(ANCClients.getCareTakerRelationship());

            try {
	            patientsDTO.setDateOfDeath(ANCClients.getDateOfDeath().getTime());
            }catch (Exception e){
	            patientsDTO.setDateOfDeath((long)0);
            	e.printStackTrace();
            }
            patientsDTO.setHivStatus(ANCClients.isHivStatus());

            return patientsDTO;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting Patient :{0}, failed with error: {1}.", ANCClients, e));
            throw e;
        }
    }

    public static TBPatientDTO toTbPatientDTO(TBPatient patient) {
        try {
            TBPatientDTO tbPatientDTO = new TBPatientDTO();

            tbPatientDTO.setHealthFacilityPatientId(patient.getHealthFacilitiesPatients().getHealthFacilityPatientId());
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


    public static List<PatientReferral> toPatientReferralsList(List<ReferralsDTO> referralsDTOs) {
        try {

            List<PatientReferral> patientReferrals = new ArrayList<>();
            for(ReferralsDTO referralsDTO:referralsDTOs){
                patientReferrals.add(toPatientReferral(referralsDTO));
            }

            return patientReferrals;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting List<ReferralsDTO> :{0}, failed with error: {1}.", referralsDTOs, e));
            throw e;
        }
    }



    public static List<ReferralsDTO> toPatientReferralDTOsList(List<PatientReferral> referrals) {
        try {

            List<ReferralsDTO> referralsDTOS = new ArrayList<>();
            for(PatientReferral referral:referrals){
                referralsDTOS.add(toPatientDTO(referral));
            }

            return referralsDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageFormat.format("Converting List<PatientReferral> :{0}, failed with error: {1}.", referrals, e));
            throw e;
        }
    }


    public static PatientReferral toPatientReferral(ReferralsDTO referralsDTO) {
        try {
            PatientReferral referral = new PatientReferral();

	        ANCClients patient  = new ANCClients();
	        patient.setPatientId(referralsDTO.getPatientId());
	        referral.setId(referralsDTO.getReferralId());
            referral.setPatient(patient);
            referral.setCommunityBasedHivService(referralsDTO.getCommunityBasedHivService());
            referral.setReferralReason(referralsDTO.getReferralReason());
            referral.setServiceId(referralsDTO.getServiceId());
            referral.setCtcNumber(referralsDTO.getServiceProviderGroup());
            referral.setReferralUUID(referralsDTO.getReferralUUID());
            referral.setReferralType(referralsDTO.getReferralType());

            //TODO implement new mechanism for saving referral indicators
            referral.setReferralStatus(referralsDTO.getReferralStatus());
            referral.setServiceProviderUIID(referralsDTO.getServiceProviderUIID());
            referral.setServiceProviderGroup(referralsDTO.getServiceProviderGroup());
            referral.setVillageLeader(referralsDTO.getVillageLeader());
            referral.setTestResults(referralsDTO.getTestResults());
            referral.setLabTest(referralsDTO.getLabTest());


            Date referralDate = new Date();
            referralDate.setTime(referralsDTO.getReferralDate());

            referral.setReferralDate(referralDate);
            referral.setFacilityId(referralsDTO.getFacilityId());

            referral.setReferralSource(referralsDTO.getReferralSource());
            referral.setServiceGivenToPatient(referralsDTO.getServiceGivenToPatient());
            referral.setFromFacilityId(referralsDTO.getFromFacilityId());
            referral.setOtherClinicalInformation(referralsDTO.getOtherClinicalInformation());
            referral.setOtherNotes(referralsDTO.getOtherNotes());

	        referral.setInstanceId(referralsDTO.getIntanceId());

            referral.setCreatedAt(Calendar.getInstance().getTime());
            referral.setUpdatedAt(Calendar.getInstance().getTime());

            return referral;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting CTCPatientDTO :{0}, failed with error: {1}.", referralsDTO, e));
            throw e;
        }
    }


    public static ReferralsDTO toPatientDTO(PatientReferral referral) {
        try {
            ReferralsDTO referralsDTO = new ReferralsDTO();

            referralsDTO.setPatientId(referral.getPatient().getPatientId());
            referralsDTO.setCommunityBasedHivService(referral.getCommunityBasedHivService());
            referralsDTO.setReferralReason(referral.getReferralReason());
            referralsDTO.setServiceId(referral.getServiceId());
            referralsDTO.setReferralId(referral.getId());
            referralsDTO.setReferralStatus(referral.getReferralStatus());
            referralsDTO.setReferralUUID(referral.getReferralUUID());
            referralsDTO.setReferralSource(referral.getReferralSource());
            referralsDTO.setReferralType((int)referral.getReferralType());
            referralsDTO.setOtherNotes(referral.getOtherNotes());
            referralsDTO.setServiceGivenToPatient(referral.getServiceGivenToPatient());
            referralsDTO.setTestResults(referral.isTestResults());
            referralsDTO.setLabTest(referral.getLabTest());
            referralsDTO.setFromFacilityId(referral.getFromFacilityId());
            referralsDTO.setOtherClinicalInformation(referral.getOtherClinicalInformation());
            referralsDTO.setIntanceId(referral.getInstanceId());


            //TODO implement new mechanism for saving referral indicators
            referralsDTO.setServiceProviderUIID(referral.getServiceProviderUIID());
            referralsDTO.setServiceProviderGroup(referral.getServiceProviderGroup());
            referralsDTO.setVillageLeader(referral.getVillageLeader());
            referralsDTO.setReferralDate(referral.getReferralDate().getTime());
            referralsDTO.setFacilityId(referral.getFacilityId());

            return referralsDTO;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting ReferralsDTO :{0}, failed with error: {1}.", referral, e));
            e.printStackTrace();
            throw e;
        }
    }


    public static RoutineVisits toTBEncounter(TBEncounterDTO tbEncounterDTO) {
        try {
           RoutineVisits encounter = new RoutineVisits();
           encounter.setTbPatientId(tbEncounterDTO.getTbPatientId());
           Date medicationDate = new Date();
           medicationDate.setTime(tbEncounterDTO.getMedicationDate());

           encounter.setLocalID(tbEncounterDTO.getLocalID());
           encounter.setWeight(tbEncounterDTO.getWeight());
           encounter.setMedicationDate(medicationDate);
           encounter.setMedicationStatus(tbEncounterDTO.isMedicationStatus());

           encounter.setEncounterYear(tbEncounterDTO.getEncounterYear());

           Date scheduledDate = new Date();
           scheduledDate.setTime(tbEncounterDTO.getScheduledDate());
           encounter.setScheduledDate(scheduledDate);
           encounter.setHasFinishedPreviousMonthMedication(tbEncounterDTO.isHasFinishedPreviousMonthMedication());
           encounter.setEncounterMonth(tbEncounterDTO.getEncounterMonth());
           encounter.setAppointmentId(tbEncounterDTO.getAppointmentId());
           encounter.setMakohozi(tbEncounterDTO.getMakohozi());
           encounter.setUpdatedAt(Calendar.getInstance().getTime());

            return encounter;
        } catch (Exception e) {
            logger.error(MessageFormat.format("Converting TBEncounterDTO :{0}, failed with error: {1}.", tbEncounterDTO, e));
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
			patientsAppointmentsDTO.setHealthFacilityPatientId(patientAppointments.getHealthFacilityPatientId());
			patientsAppointmentsDTO.setStatus(patientAppointments.getStatus());
			patientsAppointmentsDTO.setAppointmentType(patientAppointments.getAppointmentType());

			return patientsAppointmentsDTO;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Converting PatientsAppointmentsDTO :{0}, failed with error: {1}.", patientAppointments, e));
			throw e;
		}
	}

    public static List<TBEncounterDTO> toTbPatientEncounterDTOsList(List<RoutineVisits> routineVisits) {
        try {

            List<TBEncounterDTO> tbEncounterDTOS = new ArrayList<>();
            for(RoutineVisits routineVisits : routineVisits){
                tbEncounterDTOS.add(toTbEncounterDTO(routineVisits));
            }

            return tbEncounterDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(MessageFormat.format("Converting   List<TBEncounterDTO> :{0}, failed with error: {1}.", routineVisits, e));
            throw e;
        }
    }

	public static TBEncounterDTO toTbEncounterDTO(RoutineVisits routineVisits) {
		try {
            TBEncounterDTO tbEncounterDTO = new TBEncounterDTO();

            tbEncounterDTO.setLocalID(routineVisits.getLocalID());
            tbEncounterDTO.setAppointmentId(routineVisits.getAppointmentId());
            tbEncounterDTO.setEncounterMonth(routineVisits.getEncounterMonth());
            tbEncounterDTO.setEncounterYear(routineVisits.getEncounterYear());
            tbEncounterDTO.setHasFinishedPreviousMonthMedication(routineVisits.isHasFinishedPreviousMonthMedication());
            tbEncounterDTO.setId(routineVisits.getId());
            tbEncounterDTO.setMakohozi(routineVisits.getMakohozi());
            tbEncounterDTO.setMedicationDate(routineVisits.getMedicationDate().getTime());
            tbEncounterDTO.setMedicationStatus(routineVisits.isMedicationStatus());
            tbEncounterDTO.setTbPatientId(routineVisits.getTbPatientId());
            tbEncounterDTO.setWeight(routineVisits.getWeight());
            tbEncounterDTO.setScheduledDate(routineVisits.getScheduledDate().getTime());

			return tbEncounterDTO;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(MessageFormat.format("Converting TBEncounterDTO :{0}, failed with error: {1}.", routineVisits, e));
			throw e;
		}
	}


}
