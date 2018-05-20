package org.opensrp.repository;

import org.opensrp.domain.ANCClients;
import org.opensrp.domain.ClientReferral;
import org.opensrp.dto.CHWReferralsSummaryDTO;
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
public class PatientReferralRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;


	public long save(ClientReferral clientReferral) throws Exception {


		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(ClientReferral.tbName).usingGeneratedKeyColumns(ClientReferral.COL_REFERRAL_ID);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put(ClientReferral.COL_ANC_CLIENT_ID, clientReferral.getAncClientId());
		parameters.put(ClientReferral.COL_FACILITY_ID, clientReferral.getFacilityId());
		parameters.put(ClientReferral.COL_REFERRAL_REASON, clientReferral.getReferralReason());
		parameters.put(ClientReferral.COL_REFERRAL_UUID, clientReferral.getReferralUUID());
		parameters.put(ClientReferral.COL_FROM_FACILITY_ID, clientReferral.getFromFacilityId());
		parameters.put(ClientReferral.COL_OTHER_CLINICAL_INFORMATION, clientReferral.getOtherClinicalInformation());
		parameters.put(ClientReferral.COL_REFERRAL_DATE, clientReferral.getReferralDate());
		parameters.put(ClientReferral.COL_REFERRAL_STATUS, clientReferral.getReferralStatus());
		parameters.put(ClientReferral.COL_INSTANCE_ID, clientReferral.getInstanceId());
		parameters.put(ClientReferral.COL_SERVICE_PROVIDER_UUID, clientReferral.getServiceProviderUUID());
		parameters.put(ClientReferral.COL_REFERRAL_TYPE, clientReferral.getReferralType());
		parameters.put(ClientReferral.COL_CREATED_AT, clientReferral.getCreatedAt());
		parameters.put(ClientReferral.COL_UPDATED_AT, clientReferral.getCreatedAt());


		return insert.executeAndReturnKey(parameters).longValue();

	}

	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}

	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);

	}

	public void clearTable() throws Exception {
		String query = "DELETE FROM " + ClientReferral.tbName;
		executeQuery(query);
	}


	public List<ClientReferral> getReferrals(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new HealthFacilityRefferalRowMapper());
	}


	public List<CHWReferralsSummaryDTO> getCHWReferralsSummary(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new CHWReferralsSummaryRowMapper());
	}


	public class HealthFacilityRefferalRowMapper implements RowMapper<ClientReferral> {
		public ClientReferral mapRow(ResultSet rs, int rowNum) throws SQLException {
			ClientReferral clientReferral = new ClientReferral();
			clientReferral.setId(rs.getLong(rs.findColumn(ClientReferral.COL_REFERRAL_ID)));
			clientReferral.setAncClientId(rs.getLong(rs.findColumn(ClientReferral.COL_ANC_CLIENT_ID)));
			clientReferral.setReferralReason(rs.getString(rs.findColumn(ClientReferral.COL_REFERRAL_REASON)));
			clientReferral.setFromFacilityId(rs.getString(rs.findColumn(ClientReferral.COL_FROM_FACILITY_ID)));
			clientReferral.setReferralUUID(rs.getString(rs.findColumn(ClientReferral.COL_REFERRAL_UUID)));
			clientReferral.setReferralDate(rs.getDate(rs.findColumn(ClientReferral.COL_REFERRAL_DATE)));
			clientReferral.setFacilityId(rs.getString(rs.findColumn(ClientReferral.COL_FACILITY_ID)));
			clientReferral.setReferralStatus(rs.getInt(rs.findColumn(ClientReferral.COL_REFERRAL_STATUS)));
			clientReferral.setInstanceId(rs.getString(rs.findColumn(ClientReferral.COL_INSTANCE_ID)));
			clientReferral.setReferralType(rs.getLong(rs.findColumn(ClientReferral.COL_REFERRAL_TYPE)));
			clientReferral.setOtherClinicalInformation(rs.getString(rs.findColumn(ClientReferral.COL_OTHER_CLINICAL_INFORMATION)));
			clientReferral.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(ClientReferral.COL_CREATED_AT)).getTime()));
			clientReferral.setUpdatedAt(rs.getDate(rs.findColumn(ClientReferral.COL_UPDATED_AT)));
			return clientReferral;
		}

	}

	public class CHWReferralsSummaryRowMapper implements RowMapper<CHWReferralsSummaryDTO> {
		public CHWReferralsSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CHWReferralsSummaryDTO chwReferralsSummaryDTO =new CHWReferralsSummaryDTO();

			chwReferralsSummaryDTO.setCount(rs.getInt(rs.findColumn("count")));
			chwReferralsSummaryDTO.setServiceName(rs.getString(rs.findColumn("service_name")));
			return  chwReferralsSummaryDTO;
		}

	}



}
