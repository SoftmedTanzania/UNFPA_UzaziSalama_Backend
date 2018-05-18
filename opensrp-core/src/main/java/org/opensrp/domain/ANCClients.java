package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_anc_clients")
public class ANCClients {

	public static final String tbName = "tbl_anc_clients";

	public static final String COL_CLIENTS_ID = "clients_id";

	public static final String COL_PATIENT_FIRST_NAME = "first_name";

	public static final String COL_PATIENT_MIDDLE_NAME = "middle_name";

	public static final String COL_PATIENT_SURNAME = "surname";

	public static final String COL_PHONE_NUMBER = "phone_number";

	public static final String COL_WARD = "ward";

	public static final String COL_VILLAGE = "village";

	public static final String COL_MAP_CUE = "map_cue";

	public static final String COL_DATE_OF_BIRTH = "date_of_birth";

	public static final String COL_PMTCT_STATUS = "pmtct_status";

	public static final String COL_HEIGHT_BELOW_AVERAGE = "height_below_average";

	public static final String COL_LEVEL_OF_EDUCATION = "level_of_education";

	public static final String COL_SPOUSE_NAME = "spouse_name";

	public static final String COL_GRAVIDA = "gravida";

	public static final String COL_PARA = "para";

	public static final String COL_LMNP_DATE = "lmnp_date";

	public static final String COL_EDD = "edd";

	public static final String COL_GESTATIONAL_AGE_BELOW_20 = "gestational_age_below_20";

	public static final String COL_HISTORY_OF_ABORTION = "history_of_abortion";

	public static final String COL_AGE_BELOW_20_YEARS = "age_below_20_years";

	public static final String COL_LAST_PREGNANCY_OVER_10_YEARS = "last_pregnancy_over_10_years";

	public static final String COL_PREGNANCY_ABOVE_35_YEARS = "pregnancy_above_35_years";

	public static final String COL_HISTORY_OF_STILL_BIRTH = "history_of_still_birth";

	public static final String COL_HISTORY_OF_POSTMARTUM_HAEMORRHAGE = "history_of_postmartum_haemorrhage";

	public static final String COL_HISTORY_OF_RETAINED_PLACENTA = "history_of_retained_placenta";

	public static final String COL_LAST_CHILD_BIRTH_YEAR = "last_child_birth_year";

	public static final String COL_LAST_CHILD_BIRTH_STATUS = "last_child_birth_status";

	public static final String COL_PNC_STATUS = "PNC_STATUS";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";

	@Id
	@GeneratedValue
	@Column(name = COL_CLIENTS_ID)
	private Long clientId;

	@Column(name = COL_PATIENT_FIRST_NAME)
	private String firstName;


	@Column(name = COL_PATIENT_MIDDLE_NAME)
	private String middleName;

	@Column(name = COL_PATIENT_SURNAME)
	private String surname;

	@Column(name = COL_PHONE_NUMBER)
	private String phoneNumber;

	@Column(name = COL_WARD)
	private String ward;

	@Column(name = COL_VILLAGE)
	private String village;

	@Column(name = COL_MAP_CUE)
	private String mapCue;

	@Column(name = COL_DATE_OF_BIRTH)
	private Date dateOfBirth;

	@Column(name = COL_HEIGHT_BELOW_AVERAGE)
	private boolean heightBelowAverage;

	/**
	 * 0 = unknown
	 * 1
	 * 2
	 */

	@Column(name = COL_PMTCT_STATUS)
	private int pmtctStatus;

	@Column(name = COL_LEVEL_OF_EDUCATION)
	private int levelOfEducation;


	@Column(name = COL_SPOUSE_NAME)
	private String spouseName;

	@Column(name = COL_GRAVIDA)
	private int gravida;

	@Column(name = COL_PARA)
	private int para;

	@Column(name = COL_LMNP_DATE)
	private Date lmnpDate;

	@Column(name = COL_EDD)
	private Date edd;

	@Column(name = COL_GESTATIONAL_AGE_BELOW_20)
	private boolean gestationalAgeBelow20;


	@Column(name = COL_HISTORY_OF_ABORTION)
	private boolean historyOfAbortion;


	@Column(name = COL_AGE_BELOW_20_YEARS)
	private boolean ageBelow20Years;


	@Column(name = COL_LAST_PREGNANCY_OVER_10_YEARS)
	private boolean lastPregnancyOver10Years;

	@Column(name = COL_PREGNANCY_ABOVE_35_YEARS)
	private boolean pregnancyAbove35Years;

	@Column(name = COL_HISTORY_OF_STILL_BIRTH)
	private boolean historyOfStillBirth;

	@Column(name = COL_HISTORY_OF_POSTMARTUM_HAEMORRHAGE)
	private boolean historyOfPostmartumHaemorrhage;

	@Column(name = COL_HISTORY_OF_RETAINED_PLACENTA)
	private boolean historyOfRetainedPlacenta;

	@Column(name = COL_PNC_STATUS)
	private boolean pncStatus;

	@Column(name = COL_LAST_CHILD_BIRTH_YEAR)
	private int lastChildbirthYear;

	/**
	 * 0 = Dead
	 * 1 = Alive
	 */
	@Column(name = COL_LAST_CHILD_BIRTH_STATUS)
	private int lastChildbirthStatus;


	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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

	public Date getLmnpDate() {
		return lmnpDate;
	}

	public void setLmnpDate(Date lmnpDate) {
		this.lmnpDate = lmnpDate;
	}

	public Date getEdd() {
		return edd;
	}

	public void setEdd(Date edd) {
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

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


}

