package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class PNCClientDTO {
	@JsonProperty
	private long pncClientID;

    @JsonProperty
    private long healthFacilityClientID;

    @JsonProperty
    private long dateOfDelivery;

    @JsonProperty
    private long dateOfAdmission;

    @JsonProperty
    private boolean kuharibikaMimba;

	/**
	 * 1 = normal delivery
	 * 2 = vaccum delivery
	 * 3 = scissory incection
	 */
	@JsonProperty
	private int deliveryMethod;


	/**
	 * 1 = postmortum hemerage
	 * 2 = third degree tear
	 * 3 = retain placenta
	 */
	@JsonProperty
	private int deliveryComplications;

	/**
	 *  0 = BAD
	 *  1 = TRUE
	 */
	@JsonProperty
	private int motherDischargeCondition;



	@JsonProperty
	private String childGender;


	@JsonProperty
	private double childWeight;

	@JsonProperty
	private int apgarScore;


	@JsonProperty
	private boolean childAbnomalities;

	/**
	 *  0 = BAD
	 *  1 = TRUE
	 */
	@JsonProperty
	private int childDischargeCondition;

	@JsonProperty
	private boolean diedWithin24Hours;

	@JsonProperty
	private int childPlaceOfBirth;

	/**
	 * 1 = FBS
	 * 2 = MSB
	 */
	@JsonProperty
	private int stillBirthTypes;

	public Long getPncClientID() {
		return pncClientID;
	}

	public void setPncClientID(Long pncClientID) {
		this.pncClientID = pncClientID;
	}

	public Long getHealthFacilityClientID() {
		return healthFacilityClientID;
	}

	public void setHealthFacilityClientID(Long healthFacilityClientID) {
		this.healthFacilityClientID = healthFacilityClientID;
	}

	public long getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(long dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public long getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(long dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public boolean isKuharibikaMimba() {
		return kuharibikaMimba;
	}

	public void setKuharibikaMimba(boolean kuharibikaMimba) {
		this.kuharibikaMimba = kuharibikaMimba;
	}

	public int getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(int deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public int getDeliveryComplications() {
		return deliveryComplications;
	}

	public void setDeliveryComplications(int deliveryComplications) {
		this.deliveryComplications = deliveryComplications;
	}

	public int getMotherDischargeCondition() {
		return motherDischargeCondition;
	}

	public void setMotherDischargeCondition(int motherDischargeCondition) {
		this.motherDischargeCondition = motherDischargeCondition;
	}

	public String getChildGender() {
		return childGender;
	}

	public void setChildGender(String childGender) {
		this.childGender = childGender;
	}

	public double getChildWeight() {
		return childWeight;
	}

	public void setChildWeight(double childWeight) {
		this.childWeight = childWeight;
	}

	public int getApgarScore() {
		return apgarScore;
	}

	public void setApgarScore(int apgarScore) {
		this.apgarScore = apgarScore;
	}

	public boolean isChildAbnomalities() {
		return childAbnomalities;
	}

	public void setChildAbnomalities(boolean childAbnomalities) {
		this.childAbnomalities = childAbnomalities;
	}

	public int getChildDischargeCondition() {
		return childDischargeCondition;
	}

	public void setChildDischargeCondition(int childDischargeCondition) {
		this.childDischargeCondition = childDischargeCondition;
	}

	public boolean isDiedWithin24Hours() {
		return diedWithin24Hours;
	}

	public void setDiedWithin24Hours(boolean diedWithin24Hours) {
		this.diedWithin24Hours = diedWithin24Hours;
	}

	public int getStillBirthTypes() {
		return stillBirthTypes;
	}

	public void setStillBirthTypes(int stillBirthTypes) {
		this.stillBirthTypes = stillBirthTypes;
	}

	public int getChildPlaceOfBirth() {
		return childPlaceOfBirth;
	}

	public void setChildPlaceOfBirth(int childPlaceOfBirth) {
		this.childPlaceOfBirth = childPlaceOfBirth;
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
