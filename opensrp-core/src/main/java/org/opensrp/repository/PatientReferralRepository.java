package org.opensrp.repository;

import org.codehaus.jackson.annotate.JsonProperty;
import org.opensrp.domain.ClientReferral;
import org.opensrp.dto.CHWReferralsListDTO;
import org.opensrp.dto.CHWReferralsSummaryDTO;
import org.opensrp.dto.FacilityReferralsSummaryDTO;
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
		parameters.put(ClientReferral.COL_OTHER_NOTES, clientReferral.getOtherNotes());
		parameters.put(ClientReferral.COL_REFERRAL_FEEDBACK, clientReferral.getReferralFeedback());
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


	public List<CHWReferralsSummaryDTO> getCHWReferrals(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new CHWReferralsSummaryRowMapper());
	}

	public List<FacilityReferralsSummaryDTO> getFacilityReferrals(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new FacilityReferralsSummaryRowMapper());
	}

	public List<CHWReferralsListDTO> getCHWReferralsList(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new CHWReferralsListRowMapper());
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
			clientReferral.setOtherNotes(rs.getString(rs.findColumn(ClientReferral.COL_OTHER_NOTES)));
			clientReferral.setServiceProviderUUID(rs.getString(rs.findColumn(ClientReferral.COL_SERVICE_PROVIDER_UUID)));
			clientReferral.setReferralFeedback(rs.getString(rs.findColumn(ClientReferral.COL_REFERRAL_FEEDBACK)));
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

	public class FacilityReferralsSummaryRowMapper implements RowMapper<FacilityReferralsSummaryDTO> {
		public FacilityReferralsSummaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			FacilityReferralsSummaryDTO facilityReferralsSummaryDTO =new FacilityReferralsSummaryDTO();

			facilityReferralsSummaryDTO.setCount(rs.getInt(rs.findColumn("count")));
			facilityReferralsSummaryDTO.setFacility_uuid(rs.getString(rs.findColumn("facility_id")));
			return  facilityReferralsSummaryDTO;
		}

	}


	public class CHWReferralsListRowMapper implements RowMapper<CHWReferralsListDTO> {
		public CHWReferralsListDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CHWReferralsListDTO chwReferralsSummaryDTO =new CHWReferralsListDTO();

			String referralStatus = "";
			if(rs.getInt(rs.findColumn("referral_status"))==0){
				referralStatus="PENDING";
			}else if(rs.getInt(rs.findColumn("referral_status"))==1){
				referralStatus="SUCCESS";
			}else if(rs.getInt(rs.findColumn("referral_status"))==2){
				referralStatus="FAILED";
			}

			chwReferralsSummaryDTO.setReferral_status(referralStatus);
			chwReferralsSummaryDTO.setFacility_name(rs.getString(rs.findColumn("facility_name")));
			chwReferralsSummaryDTO.setService_provider_uuid(rs.getString(rs.findColumn("service_provider_uuid")));
			chwReferralsSummaryDTO.setClient_name(
									rs.getString(rs.findColumn("first_name"))+"  "+
									rs.getString(rs.findColumn("surname")));

//
// 			chwReferralsSummaryDTO.setClient_name(
//									rs.getString(rs.findColumn("first_name"))+"  "+
//									rs.getString(rs.findColumn("middle_name")+" "+
//									rs.getString(rs.findColumn("surname"))));

			return  chwReferralsSummaryDTO;
		}

	}



}
