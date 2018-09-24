package org.opensrp.repository;

import org.opensrp.domain.PNCClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.opensrp.domain.Patients;

@Repository
public class PNCClientsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;


	public Long save(PNCClients client) throws Exception {
		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(PNCClients.tbName).usingGeneratedKeyColumns(PNCClients.COL_PNC_CLIENTS_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(PNCClients.COL_HEALTH_FACILITY_CLIENT_ID , client.getHealthFacilityClientId());
		parameters.put(PNCClients.COL_DATE_OF_DELIVERY , client.getDateOfDelivery());
		parameters.put(PNCClients.COL_DATE_OF_ADMISSION , client.getDateOfAdmission());
		parameters.put(PNCClients.COL_KUHARIBIKA_MIMBA , client.getKuharibikaMimba());
		parameters.put(PNCClients.COL_DELIVERY_METHODS , client.getDeliveryMethods());
		parameters.put(PNCClients.COL_DELIVERY_COMPLICATIONS , client.getDelivery_complications());
		parameters.put(PNCClients.COL_MOTHERS_DISCHARGE_CONDITION , client.getMothersDischargeCondition());
		parameters.put(PNCClients.COL_CHILDS_GENDER, client.getChildsGender());
		parameters.put(PNCClients.COL_CHILDS_WEIGHT , client.getChildsWeight());
		parameters.put(PNCClients.COL_APGAR_SCORE , client.getApgarScore());
		parameters.put(PNCClients.COL_CHILDS_ABNORMALITIES, client.isChilds_abnormalites());
		parameters.put(PNCClients.COL_CHILDS_DISCHARGE_CONDITION , client.getChilds_discharge_condition());
		parameters.put(PNCClients.COL_DIED_WITHIN_24_HRS, client.isDied_within_24_hrs());
		parameters.put(PNCClients.COL_TYPES_OF_STILL_BIRTH, client.getTypes_of_still_birth());
		parameters.put(PNCClients.COL_CHILDS_PLACE_OF_BIRTH, client.getChildPlaceOfBirth());


		parameters.put(PNCClients.COL_CREATED_AT , client.getCreatedAt());
		parameters.put(PNCClients.COL_UPDATED_AT , client.getCreatedAt());


		return insert.executeAndReturnKey(parameters).longValue();
	}

	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}

	public int checkIfExists(String query, Object[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);

	}

	public void clearTable() throws Exception {
		String query = "DELETE FROM " + PNCClients.tbName;
		executeQuery(query);
	}


	public List<PNCClients> getPncClients(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new pncClientRowMapper());
	}


	public class pncClientRowMapper implements RowMapper<PNCClients> {
		public PNCClients mapRow(ResultSet rs, int rowNum) throws SQLException {

			PNCClients client = new PNCClients();
			client.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(PNCClients.COL_CREATED_AT)).getTime()));
			client.setPncClientsId(rs.getLong(rs.findColumn(PNCClients.COL_PNC_CLIENTS_ID)));
			client.setHealthFacilityClientId(rs.getLong(rs.findColumn(PNCClients.COL_HEALTH_FACILITY_CLIENT_ID)));
			client.setDateOfDelivery(rs.getDate(rs.findColumn(PNCClients.COL_DATE_OF_DELIVERY)));
			client.setDateOfAdmission(rs.getDate(rs.findColumn(PNCClients.COL_DATE_OF_ADMISSION)));
			client.setKuharibikaMimba(rs.getBoolean(rs.findColumn(PNCClients.COL_KUHARIBIKA_MIMBA)));
			client.setDeliveryMethods(rs.getInt(rs.findColumn(PNCClients.COL_DELIVERY_METHODS)));
			client.setDelivery_complications(rs.getInt(rs.findColumn(PNCClients.COL_DELIVERY_COMPLICATIONS)));
			client.setMothersDischargeCondition(rs.getInt(rs.findColumn(PNCClients.COL_MOTHERS_DISCHARGE_CONDITION)));
			client.setChildsGender(rs.getString(rs.findColumn(PNCClients.COL_CHILDS_GENDER)));
			client.setChildsWeight(rs.getDouble(rs.findColumn(PNCClients.COL_CHILDS_WEIGHT)));
			client.setApgarScore(rs.getInt(rs.findColumn(PNCClients.COL_APGAR_SCORE)));
			client.setChilds_abnormalites(rs.getBoolean(rs.findColumn(PNCClients.COL_CHILDS_ABNORMALITIES)));
			client.setChilds_discharge_condition(rs.getInt(rs.findColumn(PNCClients.COL_CHILDS_DISCHARGE_CONDITION)));
			client.setDied_within_24_hrs(rs.getBoolean(rs.findColumn(PNCClients.COL_DIED_WITHIN_24_HRS)));
			client.setTypes_of_still_birth(rs.getInt(rs.findColumn(PNCClients.COL_TYPES_OF_STILL_BIRTH)));
			client.setChildPlaceOfBirth(rs.getInt(rs.findColumn(PNCClients.COL_CHILDS_PLACE_OF_BIRTH)));
			return client;

		}

	}

}
