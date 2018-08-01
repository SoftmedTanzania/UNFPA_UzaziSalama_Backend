package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class PNCCompleteCLientDataDTO {
    @JsonProperty
    private AncClientDTO ancClientDTO;

    @JsonProperty
    private PNCClientDTO pncClientDTO;


    @JsonProperty
    private List<RoutineVisitDTO> routineVisitDTOS;

    public AncClientDTO getAncClientDTO() {
        return ancClientDTO;
    }

    public void setAncClientDTO(AncClientDTO ancClientDTO) {
        this.ancClientDTO = ancClientDTO;
    }

    public PNCClientDTO getPncClientDTO() {
        return pncClientDTO;
    }

    public void setPncClientDTO(PNCClientDTO pncClientDTO) {
        this.pncClientDTO = pncClientDTO;
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
