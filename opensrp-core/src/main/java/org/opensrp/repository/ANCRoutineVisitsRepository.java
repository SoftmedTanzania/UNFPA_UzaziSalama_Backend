package org.opensrp.repository;

import org.opensrp.domain.ClientReferral;
import org.opensrp.domain.RoutineVisits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ANCRoutineVisitsRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insert;

	
	public long save(RoutineVisits routineVisits) throws Exception {


		insert = new SimpleJdbcInsert(this.jdbcTemplate).withTableName(RoutineVisits.tbName).usingGeneratedKeyColumns("_id");


		Map<String, Object> parameters = new HashMap<>();
		parameters.put(RoutineVisits.COL_HEALTH_FACILITY_CLIENT_ID, routineVisits.getHealthFacilityClientId());
		parameters.put(RoutineVisits.COL_VISIT_NUMBER, routineVisits.getVisitNumber());
		parameters.put(RoutineVisits.COL_VISIT_DATE, routineVisits.getVisitDate());
		parameters.put(RoutineVisits.COL_APPOINTMENT_DATE, routineVisits.getAppointmentDate());
		parameters.put(RoutineVisits.COL_ANAEMIA, routineVisits.isAnaemia());
		parameters.put(RoutineVisits.COL_OEDEMA, routineVisits.isOedema());
		parameters.put(RoutineVisits.COL_PROTENURIA, routineVisits.isProtenuria());
		parameters.put(RoutineVisits.COL_HIGH_BLOOD_PRESSURE, routineVisits.isHighBloodPressure());
		parameters.put(RoutineVisits.COL_WEIGHT_STAGNATION, routineVisits.isWeightStagnation());
		parameters.put(RoutineVisits.COL_ANTEPARTUM_HAEMORRHAGE, routineVisits.isAntepartumHaemorrhage());
		parameters.put(RoutineVisits.COL_SUGAR_IN_THE_URINE, routineVisits.isSugarInTheUrine());
		parameters.put(RoutineVisits.COL_FETUS_LIE, routineVisits.isFetusLie());
		parameters.put(RoutineVisits.COL_CREATED_AT, routineVisits.getCreatedAt());
		parameters.put(RoutineVisits.COL_UPDATED_AT, routineVisits.getUpdatedAt());


		return insert.executeAndReturnKey(parameters).longValue();
		
	}


	public int update(RoutineVisits routineVisit) throws Exception {
		String insertQuery = "UPDATE " + RoutineVisits.tbName + " SET " +
				RoutineVisits.COL_VISIT_NUMBER + " = ? ," +
				RoutineVisits.COL_VISIT_DATE + " = ? ," +
				RoutineVisits.COL_ANAEMIA + " = ? ," +
				RoutineVisits.COL_OEDEMA + " = ? ," +
				RoutineVisits.COL_PROTENURIA + " = ? ," +
				RoutineVisits.COL_HIGH_BLOOD_PRESSURE + " = ? ," +
				RoutineVisits.COL_WEIGHT_STAGNATION + " = ?," +
				RoutineVisits.COL_ANTEPARTUM_HAEMORRHAGE + " = ?," +
				RoutineVisits.COL_SUGAR_IN_THE_URINE + " = ?," +
				RoutineVisits.COL_FETUS_LIE + " = ?," +
				RoutineVisits.COL_UPDATED_AT + " = ? " +
				"WHERE _id = ? ";

		Object[] params = new Object[] {
				routineVisit.getVisitNumber(),
				routineVisit.getVisitDate(),
				routineVisit.isAnaemia(),
				routineVisit.isOedema(),
				routineVisit.isProtenuria(),
				routineVisit.isHighBloodPressure(),
				routineVisit.isWeightStagnation(),
				routineVisit.isAntepartumHaemorrhage(),
				routineVisit.isSugarInTheUrine(),
				routineVisit.isFetusLie(),
				routineVisit.getUpdatedAt(),
				routineVisit.getId()};
		int[] types = new int[] {
				Types.INTEGER,
				Types.DATE,
				Types.BOOLEAN,
				Types.BOOLEAN,
				Types.BOOLEAN,
				Types.BOOLEAN,
				Types.BOOLEAN,
				Types.BOOLEAN,
				Types.BOOLEAN,
				Types.BOOLEAN,
				Types.DATE,
				Types.BIGINT};

		return jdbcTemplate.update(insertQuery, params, types);

	}
	
	public void executeQuery(String query) throws Exception {
		jdbcTemplate.execute(query);
	}
	
	public int checkIfExists(String query, String[] args) throws Exception {
		return this.jdbcTemplate.queryForObject(query, args, Integer.class);
		
	}
	
	public void clearTable() throws Exception {
		String query = "DELETE FROM " + RoutineVisits.tbName;
		executeQuery(query);
	}


	public List<RoutineVisits> getTBEncounters(String sql, Object[] args) throws Exception {
		return this.jdbcTemplate.query(sql, args, new TBEncounterRowMapper());
	}

	
	public class TBEncounterRowMapper implements RowMapper<RoutineVisits> {
		public RoutineVisits mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoutineVisits routineVisits = new RoutineVisits();


			routineVisits.setCreatedAt(new Date(rs.getTimestamp(rs.findColumn(RoutineVisits.COL_CREATED_AT)).getTime()));
			routineVisits.setHealthFacilityClientId(rs.getLong(rs.findColumn(RoutineVisits.COL_HEALTH_FACILITY_CLIENT_ID)));
			routineVisits.setVisitNumber(rs.getInt(rs.findColumn(RoutineVisits.COL_VISIT_NUMBER)));
			routineVisits.setVisitDate(rs.getDate(rs.findColumn(RoutineVisits.COL_VISIT_DATE)));
			routineVisits.setAppointmentDate(rs.getDate(rs.findColumn(RoutineVisits.COL_APPOINTMENT_DATE)));
			routineVisits.setAnaemia(rs.getBoolean(rs.findColumn(RoutineVisits.COL_ANAEMIA)));
			routineVisits.setOedema(rs.getBoolean(rs.findColumn(RoutineVisits.COL_OEDEMA)));
			routineVisits.setProtenuria(rs.getBoolean(rs.findColumn(RoutineVisits.COL_PROTENURIA)));
			routineVisits.setHighBloodPressure(rs.getBoolean(rs.findColumn(RoutineVisits.COL_HIGH_BLOOD_PRESSURE)));
			routineVisits.setWeightStagnation(rs.getBoolean(rs.findColumn(RoutineVisits.COL_WEIGHT_STAGNATION)));
			routineVisits.setAntepartumHaemorrhage(rs.getBoolean(rs.findColumn(RoutineVisits.COL_ANTEPARTUM_HAEMORRHAGE)));
			routineVisits.setSugarInTheUrine(rs.getBoolean(rs.findColumn(RoutineVisits.COL_SUGAR_IN_THE_URINE)));
			routineVisits.setFetusLie(rs.getBoolean(rs.findColumn(RoutineVisits.COL_FETUS_LIE)));
			routineVisits.setUpdatedAt(rs.getDate(rs.findColumn(RoutineVisits.COL_UPDATED_AT)));


			routineVisits.setUpdatedAt(rs.getDate(rs.findColumn(RoutineVisits.COL_UPDATED_AT)));
			routineVisits.setId(rs.getLong(rs.findColumn("_id")));
			return routineVisits;
		}
		
	}

}
