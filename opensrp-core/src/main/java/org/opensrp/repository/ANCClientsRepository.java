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
public class ANCClientsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;


	public Long save(ANCClients client) throws Exception {
		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(ANCClients.tbName).usingGeneratedKeyColumns(ANCClients.COL_CLIENTS_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(ANCClients.COL_PATIENT_FIRST_NAME , client.getFirstName());
		parameters.put(ANCClients.COL_PATIENT_MIDDLE_NAME , client.getMiddleName());
		parameters.put(ANCClients.COL_PATIENT_SURNAME , client.getSurname());
		parameters.put(ANCClients.COL_PHONE_NUMBER , client.getPhoneNumber());
		parameters.put(ANCClients.COL_DATE_OF_BIRTH , client.getDateOfBirth());
		parameters.put(ANCClients.COL_VILLAGE , client.getVillage());
		parameters.put(ANCClients.COL_MAP_CUE , client.getMapCue());
		parameters.put(ANCClients.COL_PMTCT_STATUS, client.getPmtctStatus());
		parameters.put(ANCClients.COL_VILLAGE , client.getVillage());
		parameters.put(ANCClients.COL_WARD , client.getWard());
		parameters.put(ANCClients.COL_MAP_CUE, client.getMapCue());
		parameters.put(ANCClients.COL_DATE_OF_BIRTH , client.getDateOfBirth());
		parameters.put(ANCClients.COL_SPOUSE_NAME, client.getSpouseName());
		parameters.put(ANCClients.COL_PMTCT_STATUS, client.getPmtctStatus());
		parameters.put(ANCClients.COL_HEIGHT_BELOW_AVERAGE, client.isHeightBelowAverage());
		parameters.put(ANCClients.COL_LEVEL_OF_EDUCATION, client.getLevelOfEducation());
		parameters.put(ANCClients.COL_GRAVIDA, client.getGravida());
		parameters.put(ANCClients.COL_PARA, client.getPara());
		parameters.put(ANCClients.COL_LMNP_DATE, client.getLmnpDate());
		parameters.put(ANCClients.COL_EDD, client.getEdd());
		parameters.put(ANCClients.COL_GESTATIONAL_AGE_BELOW_20, client.isGestationalAgeBelow20());
		parameters.put(ANCClients.COL_HISTORY_OF_ABORTION, client.isHistoryOfAbortion());
		parameters.put(ANCClients.COL_AGE_BELOW_20_YEARS, client.isGestationalAgeBelow20());
		parameters.put(ANCClients.COL_LAST_PREGNANCY_OVER_10_YEARS, client.isLastPregnancyOver10Years());
		parameters.put(ANCClients.COL_PREGNANCY_ABOVE_35_YEARS, client.isLastPregnancyOver10Years());
		parameters.put(ANCClients.COL_HISTORY_OF_STILL_BIRTH, client.isHistoryOfStillBirth());
		parameters.put(ANCClients.COL_HISTORY_OF_POSTMARTUM_HAEMORRHAGE, client.isHistoryOfPostmartumHaemorrhage());
		parameters.put(ANCClients.COL_HISTORY_OF_RETAINED_PLACENTA, client.isHistoryOfRetainedPlacenta());
		parameters.put(ANCClients.COL_LAST_CHILD_BIRTH_YEAR, client.getLastChildbirthYear());
		parameters.put(ANCClients.COL_LAST_CHILD_BIRTH_STATUS, client.getLastChildbirthStatus());
		parameters.put(ANCClients.COL_CLIENT_TYPE, client.getClientType());
		parameters.put(ANCClients.COL_CLIENT_CARD_NUMBER, client.getCardNumber());


		parameters.put(ANCClients.COL_CREATED_AT , client.getCreatedAt());
		parameters.put(ANCClients.COL_UPDATED_AT , client.getCreatedAt());


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

			ANCClients client = new ANCClients();
			client.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(ANCClients.COL_CREATED_AT)).getTime()));
			client.setClientId(rs.getLong(rs.findColumn(ANCClients.COL_CLIENTS_ID)));
			client.setFirstName(rs.getString(rs.findColumn(ANCClients.COL_PATIENT_FIRST_NAME)));
			client.setSurname(rs.getString(rs.findColumn(ANCClients.COL_PATIENT_SURNAME)));
			client.setMiddleName(rs.getString(rs.findColumn(ANCClients.COL_PATIENT_MIDDLE_NAME)));
			client.setWard(rs.getString(rs.findColumn(ANCClients.COL_WARD)));
			client.setVillage(rs.getString(rs.findColumn(ANCClients.COL_VILLAGE)));
			client.setMapCue(rs.getString(rs.findColumn(ANCClients.COL_MAP_CUE)));
			client.setPhoneNumber(rs.getString(rs.findColumn(ANCClients.COL_PHONE_NUMBER)));
			client.setDateOfBirth(rs.getDate(rs.findColumn(ANCClients.COL_DATE_OF_BIRTH)));
			client.setPmtctStatus(rs.getInt(rs.findColumn(ANCClients.COL_PMTCT_STATUS)));
			client.setHeightBelowAverage(rs.getBoolean(rs.findColumn(ANCClients.COL_HEIGHT_BELOW_AVERAGE)));
			client.setLevelOfEducation(rs.getInt(rs.findColumn(ANCClients.COL_LEVEL_OF_EDUCATION)));
			client.setSpouseName(rs.getString(rs.findColumn(ANCClients.COL_SPOUSE_NAME)));
			client.setGravida(rs.getInt(rs.findColumn(ANCClients.COL_GRAVIDA)));
			client.setPara(rs.getInt(rs.findColumn(ANCClients.COL_PARA)));
			client.setLmnpDate(rs.getDate(rs.findColumn(ANCClients.COL_LMNP_DATE)));
			client.setEdd(rs.getDate(rs.findColumn(ANCClients.COL_EDD)));
			client.setPmtctStatus(rs.getInt(rs.findColumn(ANCClients.COL_PMTCT_STATUS)));
			client.setGestationalAgeBelow20(rs.getBoolean(rs.findColumn(ANCClients.COL_GESTATIONAL_AGE_BELOW_20)));
			client.setHistoryOfAbortion(rs.getBoolean(rs.findColumn(ANCClients.COL_HISTORY_OF_ABORTION)));
			client.setAgeBelow20Years(rs.getBoolean(rs.findColumn(ANCClients.COL_AGE_BELOW_20_YEARS)));
			client.setLastPregnancyOver10Years(rs.getBoolean(rs.findColumn(ANCClients.COL_LAST_PREGNANCY_OVER_10_YEARS)));
			client.setPregnancyAbove35Years(rs.getBoolean(rs.findColumn(ANCClients.COL_PREGNANCY_ABOVE_35_YEARS)));
			client.setHistoryOfStillBirth(rs.getBoolean(rs.findColumn(ANCClients.COL_HISTORY_OF_STILL_BIRTH)));
			client.setHistoryOfPostmartumHaemorrhage(rs.getBoolean(rs.findColumn(ANCClients.COL_HISTORY_OF_POSTMARTUM_HAEMORRHAGE)));
			client.setHistoryOfRetainedPlacenta(rs.getBoolean(rs.findColumn(ANCClients.COL_HISTORY_OF_RETAINED_PLACENTA)));
			client.setLastChildbirthYear(rs.getInt(rs.findColumn(ANCClients.COL_LAST_CHILD_BIRTH_YEAR)));
			client.setLastChildbirthStatus(rs.getInt(rs.findColumn(ANCClients.COL_LAST_CHILD_BIRTH_STATUS)));
			client.setClientType(rs.getInt(rs.findColumn(ANCClients.COL_CLIENT_TYPE)));
			client.setCardNumber(rs.getString(rs.findColumn(ANCClients.COL_CLIENT_CARD_NUMBER)));
			return client;

		}

	}

}
