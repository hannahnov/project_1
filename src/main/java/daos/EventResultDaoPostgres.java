package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import javalin.util.ConnectionUtil;
import pojos.EventResult;
import pojos.ReimbursementRequest;

public class EventResultDaoPostgres implements EventResultDao {
	private static Logger log = Logger.getRootLogger();
	private PreparedStatement statement;
	private ConnectionUtil connUtil = new ConnectionUtil();
	ReimbursementRequestDao reqDao = new ReimbursementRequestDaoPostgres();
	@Override
	public void createEventResult(EventResult result) {
		log.info("Eventresult dao postgres: creating eventresult");
		String sql = "insert into eventresults (request_id, grade, attachment)"
				+ " values(?, ?, ?)";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, result.getReq().getRequestId());
			statement.setString(2, result.getGrade());
			statement.setBytes(3, result.getPresentation());
	
		}
		catch (SQLException e) {
			e.printStackTrace();
	
		}
			
	}

	@Override
	public EventResult readEventResult(int requestId) {
		log.info("EventResult Dao Postgres: reading eventResult");
		String sql = "select * from eventresults where request_id = ?";
		EventResult eventResult = new EventResult();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, requestId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
			//	(ReimbursementRequest req, String grade, byte[] presentation)
				ReimbursementRequest req = reqDao.readReimbursementRequest(requestId);
				String grade = rs.getString("grade");
				byte[] presentation = rs.getBytes("attachment");

				 eventResult = new EventResult(req, grade, presentation);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventResult;
	}

	@Override
	public List<EventResult> readAllEventResults() {
		log.info("EventResult Dao Postgres: reading all eventResults");
		String sql = "select * from eventresults";
		EventResult eventResult = new EventResult();
		List<EventResult> eventResultList = new ArrayList<>();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
			//	(ReimbursementRequest req, String grade, byte[] presentation)
				ReimbursementRequest req = reqDao.readReimbursementRequest(rs.getInt("request_id"));
				String grade = rs.getString("grade");
				byte[] presentation = rs.getBytes("attachment");

				 eventResult = new EventResult(req, grade, presentation);
				 eventResultList.add(eventResult);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventResultList;
	}

	@Override
	public EventResult updateEventResult(int requestId, EventResult eventResult) {
		log.info("EventResult Dao Postgres: updating eventResult");
		String sql = "update eventresults set grade = ?, attachment = ? "
				+ "where request_id = ?";
					
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			//	(ReimbursementRequest req, String grade, byte[] presentation)
				statement.setString(1, eventResult.getGrade());
				statement.setBytes(2, eventResult.getPresentation());
				statement.setInt(3, requestId);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventResult;
	}

	@Override
	public int deleteEventResult(int requestId) {
		log.info("eventresult dao postgres: deleting eventresult");
		int rowsToDelete = 0;
		String sql = "delete from eventresults where request_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
		statement = conn.prepareStatement(sql);	
		statement.setInt(1, requestId);
		rowsToDelete = statement.executeUpdate();
		
		if (rowsToDelete == 0) {
		System.out.println("No rows to delete");
		
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return rowsToDelete;
}

	@Override
	public String readGradeByBencoId(int bencoId) {
		
		log.info("EventResult Dao Postgres: reading grade by bencoid");
		String sql = "select * from eventresults where employee_id = (select employee_id from employees where "
				+ "	department_id = (select department_id from employees where employee_id = ?))";
			EventResult eventResult = new EventResult();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, bencoId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
			//	(ReimbursementRequest req, String grade, byte[] presentation)
				int requestId = rs.getInt("request_id");
				ReimbursementRequest req = reqDao.readReimbursementRequest(requestId);
				String grade = rs.getString("grade");
				byte[] presentation = rs.getBytes("attachment");

				 eventResult = new EventResult(req, grade, presentation);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventResult.getGrade();
	}

	@Override
	public byte[] readAttachmentByDepheadId(int depheadId) {
		log.info("EventResult Dao Postgres: reading presentation");
		String sql = "select * from eventresults where employee_id = (select employee_id from employees where "
				+ "	department_id = (select department_id from employees where employee_id = ?))";
			EventResult eventResult = new EventResult();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, depheadId);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
			//	(ReimbursementRequest req, String grade, byte[] presentation)
				int requestId = rs.getInt("request_id");
				ReimbursementRequest req = reqDao.readReimbursementRequest(requestId);
				String grade = rs.getString("grade");
				byte[] presentation = rs.getBytes("attachment");

				 eventResult = new EventResult(req, grade, presentation);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventResult.getPresentation();
	}
		
}

