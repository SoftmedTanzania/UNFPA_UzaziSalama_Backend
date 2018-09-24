package org.opensrp.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_pnc_clients")
public class PNCClients {

	public static final String tbName = "tbl_pnc_clients";

	public static final String COL_PNC_CLIENTS_ID = "pnc_clients_id";

	public static final String COL_HEALTH_FACILITY_CLIENT_ID = "health_facility_client_id";

	public static final String COL_DATE_OF_DELIVERY = "date_of_delivery";

	public static final String COL_DATE_OF_ADMISSION = "date_of_admission";

	public static final String COL_KUHARIBIKA_MIMBA = "kuharibika_mimba";

	public static final String COL_DELIVERY_METHODS = "delivery_methods";

	public static final String COL_DELIVERY_COMPLICATIONS= "delivery_complications";

	public static final String COL_MOTHERS_DISCHARGE_CONDITION= "mothers_discharge_condition";

	public static final String COL_CHILDS_GENDER= "childs_gender";

	public static final String COL_CHILDS_WEIGHT= "childs_weight";

	public static final String COL_APGAR_SCORE= "apgar_score";

	public static final String COL_CHILDS_PLACE_OF_BIRTH= "childs_place_of_birth";

	public static final String COL_CHILDS_ABNORMALITIES= "childs_abnormalites";

	public static final String COL_CHILDS_DISCHARGE_CONDITION= "childs_discharge_condition";

	public static final String COL_DIED_WITHIN_24_HRS= "died_within_24_hrs";

	public static final String COL_TYPES_OF_STILL_BIRTH= "types_of_still_birth";

	public static final String COL_CREATED_AT = "created_at";

	public static final String COL_UPDATED_AT = "updated_at";

	@Id
	@GeneratedValue
	@Column(name = COL_PNC_CLIENTS_ID)
	private Long pncClientsId;

	@Column(name = COL_HEALTH_FACILITY_CLIENT_ID)
	private Long healthFacilityClientId;

	@Column(name = COL_DATE_OF_DELIVERY)
	private Date dateOfDelivery;

	@Column(name = COL_DATE_OF_ADMISSION)
	private Date dateOfAdmission;

	@Column(name = COL_KUHARIBIKA_MIMBA)
	private Boolean kuharibikaMimba;

	/**
	 * 1 = normal delivery
	 * 2 = vaccum delivery
	 * 3 = scissory incection
	 */
	@Column(name = COL_DELIVERY_METHODS)
	private int deliveryMethods;


	/**
	 * 1 = postmortum hemerage
	 * 2 = third degree tear
	 * 3 = retain placenta
	 */
	@Column(name = COL_DELIVERY_COMPLICATIONS)
	private int delivery_complications;

	/**
	 *  0 = BAD
	 *  1 = TRUE
	 */
	@Column(name = COL_MOTHERS_DISCHARGE_CONDITION)
	private int mothersDischargeCondition;



	@Column(name = COL_CHILDS_GENDER)
	private String childsGender;

	@Column(name = COL_CHILDS_PLACE_OF_BIRTH)
	private int childPlaceOfBirth;


	@Column(name = COL_CHILDS_WEIGHT)
	private double childsWeight;

	@Column(name = COL_APGAR_SCORE)
	private int apgarScore;


	@Column(name = COL_CHILDS_ABNORMALITIES)
	private boolean childs_abnormalites;

	/**
	 *  0 = BAD
	 *  1 = TRUE
	 */
	@Column(name = COL_CHILDS_DISCHARGE_CONDITION)
	private int childs_discharge_condition;

	@Column(name = COL_DIED_WITHIN_24_HRS)
	private boolean died_within_24_hrs;

	/**
	 * 1 = FBS
	 * 2 = MSB
	 */
	@Column(name = COL_TYPES_OF_STILL_BIRTH)
	private int types_of_still_birth;



	@Column(name = COL_CREATED_AT, columnDefinition = "TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = COL_UPDATED_AT, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;


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

	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public Date getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Date dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public Boolean getKuharibikaMimba() {
		return kuharibikaMimba;
	}

	public void setKuharibikaMimba(Boolean kuharibikaMimba) {
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

	public int getChildPlaceOfBirth() {
		return childPlaceOfBirth;
	}

	public void setChildPlaceOfBirth(int childPlaceOfBirth) {
		this.childPlaceOfBirth = childPlaceOfBirth;
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

