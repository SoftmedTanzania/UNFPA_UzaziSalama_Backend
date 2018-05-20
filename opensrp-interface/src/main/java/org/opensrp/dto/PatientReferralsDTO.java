package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class PatientReferralsDTO {
    @JsonProperty
    private AncClientDTO ancClientDTO;

    @JsonProperty
    private List<ReferralsDTO> patientReferralsList;

    @JsonProperty
    private List<PatientsAppointmentsDTO> patientsAppointmentsDTOS;


    public PatientReferralsDTO() {
    }

    public AncClientDTO getAncClientDTO() {
        return ancClientDTO;
    }

    public void setAncClientDTO(AncClientDTO ancClientDTO) {
        this.ancClientDTO = ancClientDTO;
    }

    public List<ReferralsDTO> getPatientReferralsList() {
        return patientReferralsList;
    }

    public void setPatientReferralsList(List<ReferralsDTO> patientReferralsList) {
        this.patientReferralsList = patientReferralsList;
    }

    public List<PatientsAppointmentsDTO> getPatientsAppointmentsDTOS() {
        return patientsAppointmentsDTOS;
    }

    public void setPatientsAppointmentsDTOS(List<PatientsAppointmentsDTO> patientsAppointmentsDTOS) {
        this.patientsAppointmentsDTOS = patientsAppointmentsDTOS;
    }

    @Override
    public final boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public final int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
