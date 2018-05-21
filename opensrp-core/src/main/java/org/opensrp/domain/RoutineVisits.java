package org.opensrp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_routine_visits")
public class RoutineVisits implements Serializable {

	public static final String tbName = "tbl_routine_visits";

	public static final String COL_HEALTH_FACILITY_CLIENT_ID = "health_facility_client_id";

	public static final String COL_VISIT_NUMBER = "visit_umber";

	public static final String COL_APPOINTMENT_ID = "appointment_id";

	public static final String COL_VISIT_DATE= "visit_date";

	public static final String COL_APPOINTMENT_DATE= "appointment_date";

	public static final String COL_ANAEMIA= "anaemia";

	public static final String COL_OEDEMA = "oedema";

	public static final String COL_PROTENURIA = "protenuria";

	public static final String COL_HIGH_BLOOD_PRESSURE= "high_blood_pressure";

	public static final String COL_WEIGHT_STAGNATION= "weight_stagnation";

	public static final String COL_ANTEPARTUM_HAEMORRHAGE= "antepartum_Haemorrhage";

	public static final String COL_SUGAR_IN_THE_URINE= "sugar_in_the_urine";

	public static final String COL_FETUS_LIE= "fetus_lie";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";


	@GeneratedValue
	@Column(name = "_id")
	private Long id;

	@Id
	@Column(name = COL_HEALTH_FACILITY_CLIENT_ID)
	private Long healthFacilityClientId;

	@Column(name = COL_APPOINTMENT_ID)
	private Long appointmentId;

	@Column(name = COL_VISIT_NUMBER)
	private int visitNumber;

	@Column(name = COL_VISIT_DATE)
	private Date visitDate;

	@Column(name = COL_APPOINTMENT_DATE)
	private Date appointmentDate;

	@Column(name = COL_ANAEMIA)
	private boolean anaemia;

	@Column(name = COL_OEDEMA)
	private boolean oedema;

	@Column(name = COL_PROTENURIA)
	private boolean protenuria;

	@Column(name = COL_HIGH_BLOOD_PRESSURE)
	private boolean highBloodPressure;

	@Column(name = COL_WEIGHT_STAGNATION)
	private boolean weightStagnation;

	@Column(name = COL_ANTEPARTUM_HAEMORRHAGE)
	private boolean antepartumHaemorrhage;

	@Column(name = COL_SUGAR_IN_THE_URINE)
	private boolean sugarInTheUrine;

	@Column(name = COL_FETUS_LIE)
	private boolean fetusLie;




	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHealthFacilityClientId() {
		return healthFacilityClientId;
	}

	public void setHealthFacilityClientId(Long healthFacilityClientId) {
		this.healthFacilityClientId = healthFacilityClientId;
	}

	public int getVisitNumber() {
		return visitNumber;
	}

	public void setVisitNumber(int visitNumber) {
		this.visitNumber = visitNumber;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
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

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
