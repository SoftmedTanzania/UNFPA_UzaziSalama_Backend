package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferralsDTO {

    @JsonProperty
    private Long id;

    @JsonProperty
    private long healthFacilityClientId;

    @JsonProperty
    private long ancClientId;

    @JsonProperty
    private String referralReason;

    @JsonProperty
    private String instanceId;

    @JsonProperty
    private String referralUUID;

    @JsonProperty
    private String serviceProviderUUID;

    @JsonProperty
    private String otherClinicalInformation;

    @JsonProperty
    private String referralFeedback;

    @JsonProperty
    private long referralType;

    @JsonProperty
    private String fromFacilityId;

    @JsonProperty
    private long referralDate;

    @JsonProperty
    private String facilityId;

    /*
	 *  0 = new
	 * -1 = rejected/discarded
	 *  1 = complete referral
	 */
    @JsonProperty
    private int referralStatus;

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

    public String getOtherClinicalInformation() {
        return otherClinicalInformation;
    }

    public void setOtherClinicalInformation(String otherClinicalInformation) {
        this.otherClinicalInformation = otherClinicalInformation;
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

    public long getReferralDate() {
        return referralDate;
    }

    public void setReferralDate(long referralDate) {
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

    public long getHealthFacilityClientId() {
        return healthFacilityClientId;
    }

    public void setHealthFacilityClientId(long healthFacilityClientId) {
        this.healthFacilityClientId = healthFacilityClientId;
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
