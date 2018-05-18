package org.opensrp.repository;

import org.opensrp.domain.RoutineVisits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;


@Repository
public class TBEncounterRepository {


	@Autowired
	JdbcTemplate jdbcTemplate;

	
	public int save(RoutineVisits tBEncounter) throws Exception {
		String insertQuery = "insert into " + RoutineVisits.tbName + " (" +
				RoutineVisits.COL_TB_PATIENT_ID + "," +
				RoutineVisits.COL_APPOINTMENT_ID + "," +
				RoutineVisits.COL_LOCAL_ID + "," +
				RoutineVisits.COL_ENCOUNTER_MONTH + "," +
				RoutineVisits.COL_ENCOUNTER_YEAR + "," +
				RoutineVisits.COL_HAS_FINISHED_PREVIOUS_MONTH_MEDICATION + "," +
				RoutineVisits.COL_MAKOHOZI + "," +
				RoutineVisits.COL_WEIGHT + "," +
				RoutineVisits.COL_SCHEDULED_DATE + "," +
				RoutineVisits.COL_MEDICATION_DATE + "," +
				RoutineVisits.COL_MEDICATION_STATUS + "," +
				RoutineVisits.COL_UPDATED_AT + "," +
				RoutineVisits.COL_CREATED_AT + ") values (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

		Object[] params = new Object[] {
				tBEncounter.getTbPatientId(),
				tBEncounter.getAppointmentId(),
				tBEncounter.getLocalID(),
				tBEncounter.getEncounterMonth(),
				tBEncounter.getEncounterYear(),
				tBEncounter.isHasFinishedPreviousMonthMedication(),
		        tBEncounter.getMakohozi(),
		        tBEncounter.getWeight(),
				tBEncounter.getScheduledDate(),
				tBEncounter.getMedicationDate(),
				tBEncounter.isMedicationStatus(),
		        tBEncounter.getUpdatedAt(),
				tBEncounter.getCreatedAt() };
		int[] types = new int[] {
				Types.BIGINT,
				Types.BIGINT,
				Types.VARCHAR,
				Types.INTEGER,
				Types.INTEGER,
				Types.BOOLEAN,
				Types.VARCHAR,
				Types.DOUBLE,
				Types.DATE,
				Types.DATE,
				Types.BOOLEAN,
				Types.DATE,
				Types.TIMESTAMP };
		
		return jdbcTemplate.update(insertQuery, params, types);
		
	}


	public int update(RoutineVisits tBEncounter) throws Exception {
		String insertQuery = "UPDATE " + RoutineVisits.tbName + " SET " +
				RoutineVisits.COL_ENCOUNTER_MONTH + " = ? ," +
				RoutineVisits.COL_ENCOUNTER_YEAR + " = ? ," +
				RoutineVisits.COL_HAS_FINISHED_PREVIOUS_MONTH_MEDICATION + " = ? ," +
				RoutineVisits.COL_MAKOHOZI + " = ? ," +
				RoutineVisits.COL_WEIGHT + " = ? ," +
				RoutineVisits.COL_MEDICATION_DATE + " = ? ," +
				RoutineVisits.COL_MEDICATION_STATUS + " = ?," +
				RoutineVisits.COL_UPDATED_AT + " = ? " +
				"WHERE _id = ? ";

		Object[] params = new Object[] {
				tBEncounter.getEncounterMonth(),
				tBEncounter.getEncounterYear(),
				tBEncounter.isHasFinishedPreviousMonthMedication(),
				tBEncounter.getMakohozi(),
				tBEncounter.getWeight(),
				tBEncounter.getMedicationDate(),
				tBEncounter.isMedicationStatus(),
				tBEncounter.getUpdatedAt(),
				tBEncounter.getId()};
		int[] types = new int[] {
				Types.INTEGER,
				Types.INTEGER,
				Types.BOOLEAN,
				Types.VARCHAR,
				Types.DOUBLE,
				Types.DATE,
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
			routineVisits.setAppointmentId(rs.getLong(rs.findColumn(RoutineVisits.COL_APPOINTMENT_ID)));
			routineVisits.setEncounterMonth(rs.getInt(rs.findColumn(RoutineVisits.COL_ENCOUNTER_MONTH)));
			routineVisits.setEncounterYear(rs.getInt(rs.findColumn(RoutineVisits.COL_ENCOUNTER_YEAR)));
			routineVisits.setEncounterYear(rs.getInt(rs.findColumn(RoutineVisits.COL_ENCOUNTER_YEAR)));
			routineVisits.setTbPatientId(rs.getLong(rs.findColumn(RoutineVisits.COL_TB_PATIENT_ID)));
			routineVisits.setLocalID(rs.getString(rs.findColumn(RoutineVisits.COL_LOCAL_ID)));
			routineVisits.setMakohozi(rs.getString(rs.findColumn(RoutineVisits.COL_MAKOHOZI)));
			routineVisits.setWeight(rs.getDouble(rs.findColumn(RoutineVisits.COL_WEIGHT)));
			routineVisits.setScheduledDate(rs.getDate(rs.findColumn(RoutineVisits.COL_SCHEDULED_DATE)));
			routineVisits.setMedicationDate(rs.getDate(rs.findColumn(RoutineVisits.COL_MEDICATION_DATE)));
			routineVisits.setMedicationStatus(rs.getBoolean(rs.findColumn(RoutineVisits.COL_MEDICATION_STATUS)));
			routineVisits.setUpdatedAt(rs.getDate(rs.findColumn(RoutineVisits.COL_UPDATED_AT)));
			routineVisits.setId(rs.getLong(rs.findColumn("_id")));
			return routineVisits;
		}
		
	}

}
