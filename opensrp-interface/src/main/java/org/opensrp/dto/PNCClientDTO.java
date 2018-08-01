package org.opensrp.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public class PNCClientDTO {

	@JsonProperty
	private Long pncClientsId;

    @JsonProperty
    private Long healthFacilityClientId;

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
	private int deliveryMethods;


	/**
	 * 1 = postmortum hemerage
	 * 2 = third degree tear
	 * 3 = retain placenta
	 */
	@JsonProperty
	private int delivery_complications;

	/**
	 *  0 = BAD
	 *  1 = TRUE
	 */
	@JsonProperty
	private int mothersDischargeCondition;



	@JsonProperty
	private String childsGender;


	@JsonProperty
	private double childsWeight;

	@JsonProperty
	private int apgarScore;


	@JsonProperty
	private boolean childs_abnormalites;

	/**
	 *  0 = BAD
	 *  1 = TRUE
	 */
	@JsonProperty
	private int childs_discharge_condition;

	@JsonProperty
	private boolean died_within_24_hrs;

	/**
	 * 1 = FBS
	 * 2 = MSB
	 */
	@JsonProperty
	private int types_of_still_birth;

	public Long getPncClientsId() {
		return pncClientsId;
	}

	public void setPncClientsId(Long pncClientsId) {
		this.pncClientsId = pncClientsId;
	}

	public Long getHealthFacilityClientId() {
		return healthFacilityClientId;
	}

	public void setHealthFacilityClientId(Long healthFacilityClientId) {
		this.healthFacilityClientId = healthFacilityClientId;
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

	public int getDeliveryMethods() {
		return deliveryMethods;
	}

	public void setDeliveryMethods(int deliveryMethods) {
		this.deliveryMethods = deliveryMethods;
	}

	public int getDelivery_complications() {
		return delivery_complications;
	}

	public void setDelivery_complications(int delivery_complications) {
		this.delivery_complications = delivery_complications;
	}

	public int getMothersDischargeCondition() {
		return mothersDischargeCondition;
	}

	public void setMothersDischargeCondition(int mothersDischargeCondition) {
		this.mothersDischargeCondition = mothersDischargeCondition;
	}

	public String getChildsGender() {
		return childsGender;
	}

	public void setChildsGender(String childsGender) {
		this.childsGender = childsGender;
	}

	public double getChildsWeight() {
		return childsWeight;
	}

	public void setChildsWeight(double childsWeight) {
		this.childsWeight = childsWeight;
	}

	public int getApgarScore() {
		return apgarScore;
	}

	public void setApgarScore(int apgarScore) {
		this.apgarScore = apgarScore;
	}

	public boolean isChilds_abnormalites() {
		return childs_abnormalites;
	}

	public void setChilds_abnormalites(boolean childs_abnormalites) {
		this.childs_abnormalites = childs_abnormalites;
	}

	public int getChilds_discharge_condition() {
		return childs_discharge_condition;
	}

	public void setChilds_discharge_condition(int childs_discharge_condition) {
		this.childs_discharge_condition = childs_discharge_condition;
	}

	public boolean isDied_within_24_hrs() {
		return died_within_24_hrs;
	}

	public void setDied_within_24_hrs(boolean died_within_24_hrs) {
		this.died_within_24_hrs = died_within_24_hrs;
	}

	public int getTypes_of_still_birth() {
		return types_of_still_birth;
	}

	public void setTypes_of_still_birth(int types_of_still_birth) {
		this.types_of_still_birth = types_of_still_birth;
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
