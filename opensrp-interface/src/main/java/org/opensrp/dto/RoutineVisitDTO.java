package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class RoutineVisitDTO {
	@JsonProperty
	private long id;

	@JsonProperty
	private long healthFacilityClientId;

	@JsonProperty
	private long appointmentId;

	@JsonProperty
	private int visitNumber;

	@JsonProperty
	private long visitDate;

	@JsonProperty
	private long appointmentDate;

	@JsonProperty
	private boolean anaemia;

	@JsonProperty
	private boolean oedema;

	@JsonProperty
	private boolean protenuria;

	@JsonProperty
	private boolean highBloodPressure;

	@JsonProperty
	private boolean weightStagnation;

	@JsonProperty
	private boolean antepartumHaemorrhage;

	@JsonProperty
	private boolean sugarInTheUrine;

	@JsonProperty
	private boolean fetusLie;

	@JsonProperty
	private long appointment_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHealthFacilityClientId() {
		return healthFacilityClientId;
	}

	public void setHealthFacilityClientId(long healthFacilityClientId) {
		this.healthFacilityClientId = healthFacilityClientId;
	}

	public int getVisitNumber() {
		return visitNumber;
	}

	public void setVisitNumber(int visitNumber) {
		this.visitNumber = visitNumber;
	}

	public long getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(long visitDate) {
		this.visitDate = visitDate;
	}

	public long getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(long appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public boolean isAnaemia() {
		return anaemia;
	}

	public void setAnaemia(boolean anaemia) {
		this.anaemia = anaemia;
	}

	public boolean isOedema() {
		return oedema;
	}

	public void setOedema(boolean oedema) {
		this.oedema = oedema;
	}

	public boolean isProtenuria() {
		return protenuria;
	}

	public void setProtenuria(boolean protenuria) {
		this.protenuria = protenuria;
	}

	public boolean isHighBloodPressure() {
		return highBloodPressure;
	}

	public void setHighBloodPressure(boolean highBloodPressure) {
		this.highBloodPressure = highBloodPressure;
	}

	public boolean isWeightStagnation() {
		return weightStagnation;
	}

	public void setWeightStagnation(boolean weightStagnation) {
		this.weightStagnation = weightStagnation;
	}

	public boolean isAntepartumHaemorrhage() {
		return antepartumHaemorrhage;
	}

	public void setAntepartumHaemorrhage(boolean antepartumHaemorrhage) {
		this.antepartumHaemorrhage = antepartumHaemorrhage;
	}

	public boolean isSugarInTheUrine() {
		return sugarInTheUrine;
	}

	public void setSugarInTheUrine(boolean sugarInTheUrine) {
		this.sugarInTheUrine = sugarInTheUrine;
	}

	public boolean isFetusLie() {
		return fetusLie;
	}

	public void setFetusLie(boolean fetusLie) {
		this.fetusLie = fetusLie;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
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
