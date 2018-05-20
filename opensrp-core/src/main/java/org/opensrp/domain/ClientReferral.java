package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_client_referral")
public class ClientReferral {

	public static final String tbName = "tbl_client_referral";

	public static final String COL_ANC_CLIENT_ID = "anc_client_id";

	public static final String COL_REFERRAL_ID = "referral_id";

	public static final String COL_REFERRAL_UUID = "referral_uuid";

	public static final String COL_REFERRAL_REASON = "referral_reason";

	public static final String COL_FACILITY_ID = "facility_id";

	public static final String COL_REFERRAL_DATE= "referral_date";

	public static final String COL_SERVICE_PROVIDER_UUID= "service_provider_uuid";

	public static final String COL_FROM_FACILITY_ID= "from_facility_id";

	public static final String COL_REFERRAL_FEEDBACK= "referral_feedback";

	public static final String COL_OTHER_NOTES = "other_notes";

	public static final String COL_REFERRAL_TYPE= "referral_type";

	public static final String COL_REFERRAL_STATUS= "referral_status";

	public static final String COL_INSTANCE_ID= "instance_id";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";


	@Id
	@GeneratedValue
	@Column(name = COL_REFERRAL_ID)
	private Long id;


	@Column(name = COL_ANC_CLIENT_ID)
	private long ancClientId;

	@Column(name = COL_REFERRAL_REASON)
	private String referralReason;

	@Column(name = COL_INSTANCE_ID,unique = true)
	private String instanceId;

	@Column(name = COL_REFERRAL_UUID)
	private String referralUUID;

	@Column(name = COL_SERVICE_PROVIDER_UUID)
	private String serviceProviderUUID;

	@Column(name = COL_OTHER_NOTES)
	private String otherNotes;

	@Column(name = COL_REFERRAL_FEEDBACK)
	private String referralFeedback;


	@Column(name = COL_REFERRAL_TYPE)
	private long referralType;


	@Column(name = COL_FROM_FACILITY_ID)
	private String fromFacilityId;

	@Column(name = COL_REFERRAL_DATE)
	private Date referralDate;

	@Column(name = COL_FACILITY_ID)
	private String facilityId;

	/*
	 *  0 = new
	 * -1 = rejected/discarded
	 *  1 = complete referral
	 */
	@Column(name = COL_REFERRAL_STATUS)
	private int referralStatus;

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

	public long getAncClientId() {
		return ancClientId;
	}

	public void setAncClientId(long ancClientId) {
		this.ancClientId = ancClientId;
	}

	public String getReferralReason() {
		return referralReason;
	}

	public void setReferralReason(String referralReason) {
		this.referralReason = referralReason;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getReferralUUID() {
		return referralUUID;
	}

	public void setReferralUUID(String referralUUID) {
		this.referralUUID = referralUUID;
	}

	public String getServiceProviderUUID() {
		return serviceProviderUUID;
	}

	public void setServiceProviderUUID(String serviceProviderUUID) {
		this.serviceProviderUUID = serviceProviderUUID;
	}

	public String getOtherNotes() {
		return otherNotes;
	}

	public void setOtherNotes(String otherNotes) {
		this.otherNotes = otherNotes;
	}

	public long getReferralType() {
		return referralType;
	}

	public void setReferralType(long referralType) {
		this.referralType = referralType;
	}

	public String getFromFacilityId() {
		return fromFacilityId;
	}

	public void setFromFacilityId(String fromFacilityId) {
		this.fromFacilityId = fromFacilityId;
	}

	public Date getReferralDate() {
		return referralDate;
	}

	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public int getReferralStatus() {
		return referralStatus;
	}

	public void setReferralStatus(int referralStatus) {
		this.referralStatus = referralStatus;
	}

	public String getReferralFeedback() {
		return referralFeedback;
	}

	public void setReferralFeedback(String referralFeedback) {
		this.referralFeedback = referralFeedback;
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
