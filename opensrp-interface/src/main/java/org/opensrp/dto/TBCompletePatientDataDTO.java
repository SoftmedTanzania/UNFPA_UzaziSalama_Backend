package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class TBCompletePatientDataDTO {
    @JsonProperty
    private AncClientDTO ancClientDTO;

    @JsonProperty
    private TBPatientDTO tbPatientDTO;

    @JsonProperty
    private List<PatientsAppointmentsDTO> patientsAppointmentsDTOS;


    @JsonProperty
    private List<RoutineVisitDTO> routineVisitDTOS;

    public AncClientDTO getAncClientDTO() {
        return ancClientDTO;
    }

    public void setAncClientDTO(AncClientDTO ancClientDTO) {
        this.ancClientDTO = ancClientDTO;
    }

    public List<PatientsAppointmentsDTO> getPatientsAppointmentsDTOS() {
        return patientsAppointmentsDTOS;
    }

    public void setPatientsAppointmentsDTOS(List<PatientsAppointmentsDTO> patientsAppointmentsDTOS) {
        this.patientsAppointmentsDTOS = patientsAppointmentsDTOS;
    }

    public TBPatientDTO getTbPatientDTO() {
        return tbPatientDTO;
    }

    public void setTbPatientDTO(TBPatientDTO tbPatientDTO) {
        this.tbPatientDTO = tbPatientDTO;
    }

    public List<RoutineVisitDTO> getRoutineVisitDTOS() {
        return routineVisitDTOS;
    }

    public void setRoutineVisitDTOS(List<RoutineVisitDTO> routineVisitDTOS) {
        this.routineVisitDTOS = routineVisitDTOS;
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
