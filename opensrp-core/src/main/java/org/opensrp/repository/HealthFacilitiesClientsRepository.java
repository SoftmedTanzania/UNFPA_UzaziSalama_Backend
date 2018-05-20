package org.opensrp.repository;

import org.opensrp.domain.ANCClients;
import org.opensrp.domain.HealthFacilitiesClients;
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


@Repository
public class HealthFacilitiesClientsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public Long save(HealthFacilitiesClients healthFacilitiesClients) throws Exception {
		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(HealthFacilitiesClients.tbName).usingGeneratedKeyColumns(HealthFacilitiesClients.COL_HEALTH_FACILITY_CLIENT_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(HealthFacilitiesClients.COL_CLIENT_ID, healthFacilitiesClients.getAncClient().getClientId());
		parameters.put(HealthFacilitiesClients.COL_FACILITY_ID  , healthFacilitiesClients.getFacilityId());
		parameters.put(HealthFacilitiesClients.COL_CTC_NUMBER , healthFacilitiesClients.getCtcNumber());
		parameters.put(HealthFacilitiesClients.COL_CREATED_AT , healthFacilitiesClients.getCreatedAt());
		parameters.put(HealthFacilitiesClients.COL_UPDATED_AT , healthFacilitiesClients.getCreatedAt());

		return insert.executeAndReturnKey(parameters).longValue();
		
	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + HealthFacilitiesClients.tbName;
		executeQuery(query);
	}



	public List<HealthFacilitiesClients> getHealthFacilityPatients(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql,args, new HealthFacilityPatientsRowMapper());
	}



	public class HealthFacilityPatientsRowMapper implements RowMapper<HealthFacilitiesClients> {
		public HealthFacilitiesClients mapRow(ResultSet rs, int rowNum) throws SQLException {
			HealthFacilitiesClients facilitiesPatients = new HealthFacilitiesClients();

			facilitiesPatients.setHealthFacilityClientId(rs.getLong(rs.findColumn(HealthFacilitiesClients.COL_HEALTH_FACILITY_CLIENT_ID)));

			ANCClients ANCClients = new ANCClients();
			ANCClients.setClientId(rs.getLong(rs.findColumn(HealthFacilitiesClients.COL_CLIENT_ID)));

			facilitiesPatients.setAncClient(ANCClients);
			facilitiesPatients.setCtcNumber(rs.getString(rs.findColumn(HealthFacilitiesClients.COL_CTC_NUMBER)));
			facilitiesPatients.setFacilityId(rs.getLong(rs.findColumn(HealthFacilitiesClients.COL_FACILITY_ID)));
			facilitiesPatients.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(HealthFacilitiesClients.COL_CREATED_AT)).getTime()));
			facilitiesPatients.setUpdatedAt(rs.getDate(rs.findColumn(HealthFacilitiesClients.COL_UPDATED_AT)));
			return facilitiesPatients;
		}
		
	}

}
