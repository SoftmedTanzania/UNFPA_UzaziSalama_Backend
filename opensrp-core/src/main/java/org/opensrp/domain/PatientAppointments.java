package org.opensrp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_client_appointments")
public class PatientAppointments implements Serializable {

	public static final String tbName = "tbl_client_appointments";

	public static final String COL_HEALTH_FACILITY_CLIENT_ID = "health_facility_client_id";

	public static final String COL_VISIT_NUMBER = "visit_umber";

	public static final String COL_APPOINTMENT_ID = "appointment_id";

	public static final String COL_APPOINTMENT_DATE = "appointment_date";

	public static final String COL_IS_CANCELLED = "is_cancelled";

	public static final String COL_STATUS = "status";

	public static final String COL_APPOINTMENT_TYPE = "appointment_type";

	public static final String COL_ROW_VERSION = "row_version";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";


	@Column(name = COL_APPOINTMENT_ID, unique = true, nullable = false, insertable = false, updatable = false)
	private Long appointment_id;

	private Long healthFacilityClientId;

	@Id
	@ManyToOne
	@JoinColumn(name= COL_HEALTH_FACILITY_CLIENT_ID)
	private HealthFacilitiesClients healthFacilitiesClients;


	@Id
	@Column(name = COL_APPOINTMENT_DATE)
	private Date appointmentDate;

	@Column(name = COL_IS_CANCELLED)
	private boolean isCancelled;

	@Column(name = COL_STATUS)
	private String status;

	@Column(name = COL_ROW_VERSION)
	private Date rowVersion;

	@Column(name = COL_VISIT_NUMBER)
	private int visitNumber;

	//TODO implement table and configurations for saving this
	/***
	 * 1 = CTC
	 * 2 = TB
	 */
	@Column(name = COL_APPOINTMENT_TYPE)
	private int appointmentType;


	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public HealthFacilitiesClients getHealthFacilitiesClients() {
		return healthFacilitiesClients;
	}

	public void setHealthFacilitiesClients(HealthFacilitiesClients healthFacilitiesClients) {
		this.healthFacilitiesClients = healthFacilitiesClients;
	}

	public Long getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(Long appointment_id) {
		this.appointment_id = appointment_id;
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

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public boolean getIsCancelled() {
		return isCancelled;
	}

	public void setIsCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(int appointmentType) {
		this.appointmentType = appointmentType;
	}

	public Date getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(Date rowVersion) {
		this.rowVersion = rowVersion;
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
