package org.opensrp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_routine_visits")
public class RoutineVisits implements Serializable {

	public static final String tbName = "tbl_routine_visits";

	public static final String COL_ANC_CLIENT_ID = "anc_client_id";

	public static final String COL_VISIT_NUMBER = "visit_umber";

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
	@Column(name = COL_ANC_CLIENT_ID)
	private Long ancClientId;

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
