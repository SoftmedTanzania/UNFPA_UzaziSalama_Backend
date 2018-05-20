package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Date;

public class AncClientDTO {
    private Long clientId;

    private String firstName;

    private String middleName;

    private String surname;

    private String phoneNumber;

    private String ward;

    private String village;

    private String mapCue;

    private long dateOfBirth;

    private boolean heightBelowAverage;

    /**
     * 0 = unknown
     * 1
     * 2
     */
    private int pmtctStatus;

    private int levelOfEducation;


    private String spouseName;

    private int gravida;

    private int para;

    private long lmnpDate;

    private long edd;

    private boolean gestationalAgeBelow20;

    private boolean historyOfAbortion;

    private boolean ageBelow20Years;

    private boolean lastPregnancyOver10Years;

    private boolean pregnancyAbove35Years;

    private boolean historyOfStillBirth;

    private boolean historyOfPostmartumHaemorrhage;

    private boolean historyOfRetainedPlacenta;

    private boolean pncStatus;

    private int lastChildbirthYear;

    /**
     * 0 = Dead
     * 1 = Alive
     */
    private int lastChildbirthStatus;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getMapCue() {
		return mapCue;
	}

	public void setMapCue(String mapCue) {
		this.mapCue = mapCue;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isHeightBelowAverage() {
		return heightBelowAverage;
	}

	public void setHeightBelowAverage(boolean heightBelowAverage) {
		this.heightBelowAverage = heightBelowAverage;
	}

	public int getPmtctStatus() {
		return pmtctStatus;
	}

	public void setPmtctStatus(int pmtctStatus) {
		this.pmtctStatus = pmtctStatus;
	}

	public int getLevelOfEducation() {
		return levelOfEducation;
	}

	public void setLevelOfEducation(int levelOfEducation) {
		this.levelOfEducation = levelOfEducation;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public int getGravida() {
		return gravida;
	}

	public void setGravida(int gravida) {
		this.gravida = gravida;
	}

	public int getPara() {
		return para;
	}

	public void setPara(int para) {
		this.para = para;
	}

	public long getLmnpDate() {
		return lmnpDate;
	}

	public void setLmnpDate(long lmnpDate) {
		this.lmnpDate = lmnpDate;
	}

	public long getEdd() {
		return edd;
	}

	public void setEdd(long edd) {
		this.edd = edd;
	}

	public boolean isGestationalAgeBelow20() {
		return gestationalAgeBelow20;
	}

	public void setGestationalAgeBelow20(boolean gestationalAgeBelow20) {
		this.gestationalAgeBelow20 = gestationalAgeBelow20;
	}

	public boolean isHistoryOfAbortion() {
		return historyOfAbortion;
	}

	public void setHistoryOfAbortion(boolean historyOfAbortion) {
		this.historyOfAbortion = historyOfAbortion;
	}

	public boolean isAgeBelow20Years() {
		return ageBelow20Years;
	}

	public void setAgeBelow20Years(boolean ageBelow20Years) {
		this.ageBelow20Years = ageBelow20Years;
	}

	public boolean isLastPregnancyOver10Years() {
		return lastPregnancyOver10Years;
	}

	public void setLastPregnancyOver10Years(boolean lastPregnancyOver10Years) {
		this.lastPregnancyOver10Years = lastPregnancyOver10Years;
	}

	public boolean isPregnancyAbove35Years() {
		return pregnancyAbove35Years;
	}

	public void setPregnancyAbove35Years(boolean pregnancyAbove35Years) {
		this.pregnancyAbove35Years = pregnancyAbove35Years;
	}

	public boolean isHistoryOfStillBirth() {
		return historyOfStillBirth;
	}

	public void setHistoryOfStillBirth(boolean historyOfStillBirth) {
		this.historyOfStillBirth = historyOfStillBirth;
	}

	public boolean isHistoryOfPostmartumHaemorrhage() {
		return historyOfPostmartumHaemorrhage;
	}

	public void setHistoryOfPostmartumHaemorrhage(boolean historyOfPostmartumHaemorrhage) {
		this.historyOfPostmartumHaemorrhage = historyOfPostmartumHaemorrhage;
	}

	public boolean isHistoryOfRetainedPlacenta() {
		return historyOfRetainedPlacenta;
	}

	public void setHistoryOfRetainedPlacenta(boolean historyOfRetainedPlacenta) {
		this.historyOfRetainedPlacenta = historyOfRetainedPlacenta;
	}

	public boolean isPncStatus() {
		return pncStatus;
	}

	public void setPncStatus(boolean pncStatus) {
		this.pncStatus = pncStatus;
	}

	public int getLastChildbirthYear() {
		return lastChildbirthYear;
	}

	public void setLastChildbirthYear(int lastChildbirthYear) {
		this.lastChildbirthYear = lastChildbirthYear;
	}

	public int getLastChildbirthStatus() {
		return lastChildbirthStatus;
	}

	public void setLastChildbirthStatus(int lastChildbirthStatus) {
		this.lastChildbirthStatus = lastChildbirthStatus;
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
