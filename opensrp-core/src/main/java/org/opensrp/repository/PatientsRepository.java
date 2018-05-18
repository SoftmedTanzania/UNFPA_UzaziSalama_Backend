package org.opensrp.repository;

import org.opensrp.domain.ANCClients;
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
public class PatientsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;


	public Long save(ANCClients ANCClients) throws Exception {
		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(ANCClients.tbName).usingGeneratedKeyColumns(ANCClients.COL_CLIENTS_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(ANCClients.COL_PATIENT_FIRST_NAME , ANCClients.getFirstName());
		parameters.put(ANCClients.COL_PATIENT_MIDDLE_NAME , ANCClients.getMiddleName());
		parameters.put(ANCClients.COL_PATIENT_SURNAME , ANCClients.getSurname());
		parameters.put(ANCClients.COL_PHONE_NUMBER , ANCClients.getPhoneNumber());
		parameters.put(ANCClients.COL_DATE_OF_BIRTH , ANCClients.getDateOfBirth());
		parameters.put(ANCClients.COL_GENDER , ANCClients.getGender());
		parameters.put(ANCClients.COL_DATE_OF_DEATH , ANCClients.getDateOfDeath());
		parameters.put(ANCClients.COL_PMTCT_STATUS, ANCClients.isHivStatus());
		parameters.put(ANCClients.COL_VILLAGE , ANCClients.getVillage());
		parameters.put(ANCClients.COL_WARD , ANCClients.getWard());
		parameters.put(ANCClients.COL_MAP_CUE, ANCClients.getHamlet());
		parameters.put(ANCClients.COL_COMMUNITY_BASED_HIV_SERVICE , ANCClients.getCommunityBasedHivService());
		parameters.put(ANCClients.COL_SPOUSE_NAME, ANCClients.getCareTakerName());
		parameters.put(ANCClients.COL_CARE_TAKER_PHONE_NUMBER, ANCClients.getPhoneNumber());
		parameters.put(ANCClients.COL_CARE_TAKER_RELATIONSHIP, ANCClients.getCareTakerRelationship());
		parameters.put(ANCClients.COL_CREATED_AT , ANCClients.getCreatedAt());
		parameters.put(ANCClients.COL_UPDATED_AT , ANCClients.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
	}

	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}

	public int checkIfExists(String query, Object[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);

	}

	public void clearTable() throws Exception {
		String query = "DELETE FROM " + ANCClients.tbName;
		executeQuery(query);
	}


	public List<ANCClients> getPatients(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new referralPatientsRowMapper());
	}


	public class referralPatientsRowMapper implements RowMapper<ANCClients> {
		public ANCClients mapRow(ResultSet rs, int rowNum) throws SQLException {
			ANCClients ANCClients = new ANCClients();
			ANCClients.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(ANCClients.COL_CREATED_AT)).getTime()));
			ANCClients.setPatientId(rs.getLong(rs.findColumn(ANCClients.COL_CLIENTS_ID)));
			ANCClients.setFirstName(rs.getString(rs.findColumn(ANCClients.COL_PATIENT_FIRST_NAME)));
			ANCClients.setSurname(rs.getString(rs.findColumn(ANCClients.COL_PATIENT_SURNAME)));
			ANCClients.setMiddleName(rs.getString(rs.findColumn(ANCClients.COL_PATIENT_MIDDLE_NAME)));
			ANCClients.setWard(rs.getString(rs.findColumn(ANCClients.COL_WARD)));
			ANCClients.setVillage(rs.getString(rs.findColumn(ANCClients.COL_VILLAGE)));
			ANCClients.setHamlet(rs.getString(rs.findColumn(ANCClients.COL_MAP_CUE)));
			ANCClients.setPhoneNumber(rs.getString(rs.findColumn(ANCClients.COL_PHONE_NUMBER)));
			ANCClients.setDateOfBirth(rs.getDate(rs.findColumn(ANCClients.COL_DATE_OF_BIRTH)));
			ANCClients.setCommunityBasedHivService(rs.getString(rs.findColumn(ANCClients.COL_COMMUNITY_BASED_HIV_SERVICE)));
			ANCClients.setGender(rs.getString(rs.findColumn(ANCClients.COL_GENDER)));
			ANCClients.setCareTakerName(rs.getString(rs.findColumn(ANCClients.COL_SPOUSE_NAME)));
			ANCClients.setCareTakerPhoneNumber(rs.getString(rs.findColumn(ANCClients.COL_CARE_TAKER_PHONE_NUMBER)));
			ANCClients.setCareTakerRelationship(rs.getString(rs.findColumn(ANCClients.COL_CARE_TAKER_RELATIONSHIP)));
			ANCClients.setHivStatus(rs.getBoolean(rs.findColumn(ANCClients.COL_PMTCT_STATUS)));
			ANCClients.setDateOfDeath(rs.getDate(rs.findColumn(ANCClients.COL_DATE_OF_DEATH)));
			return ANCClients;
		}

	}

}
