package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import javalin.util.ConnectionUtil;
import pojos.ApprovalStatus;
import pojos.Employee;
import pojos.Event;
import pojos.ReimbursementRequest;

public class ReimbursementRequestDaoPostgres implements ReimbursementRequestDao {
	private static Logger log = Logger.getRootLogger();
	private PreparedStatement statement;
	private ConnectionUtil connUtil = new ConnectionUtil();
	EmployeeDao employeeDao = new EmployeeDaoPostgres();
	EventDao eventDao = new EventDaoPostgres();

	@Override
	public void createReimbursementRequest(ReimbursementRequest req) {
		log.info("Request dao postgres: creating request");
		String sql = "insert into requests (employee_id, projected_reimbursement, is_urgent, "
				+ "request_date, work_days_missed, justification, approval_status, description, event_id)"
				+ " values(?, ?, ?, date(?), ?, ?, ?, ?, ?);"
				+ " insert into requestapprovals (request_id)"
				+ "values ((select request_id from requests where employee_id = ? and justification = ? "
				+ "and event_id = ?)); "
				+ "insert into eventresults (request_id)"
				+ " values ((select request_id from requests where employee_id = ? and justification = ?"
				+ " and event_id = ?))";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, req.getRequestorId());
			statement.setDouble(2, req.getProjectedReimbursement());
			statement.setBoolean(3, req.isUrgent());
			statement.setString(4, req.getRequestDate());
			statement.setInt(5, req.getWorkDaysMissed());
			statement.setString(6, req.getJustification());
			statement.setInt(7, req.getApprovalStatus().getValue());
			statement.setString(8, req.getDescription());
			statement.setInt(9, req.getEventId());
			statement.setInt(10, req.getRequestorId());
			statement.setString(11, req.getJustification());
			statement.setInt(12, req.getEventId());
			statement.setInt(13, req.getRequestorId());
			statement.setString(14, req.getJustification());
			statement.setInt(15, req.getEventId());
			statement.executeQuery();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
	
		}
		
	}

	@Override
	public ReimbursementRequest readReimbursementRequest(int reqId) {
		log.info("request Dao Postgres: reading request");
		String sql = "select * from requests where request_id = ?";
		ReimbursementRequest request = new ReimbursementRequest();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, reqId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Event event = eventDao.readEvent(rs.getInt("event_id"));
				double projectedReimbursement = rs.getDouble("projected_reimbursement");
				boolean isUrgent = rs.getBoolean("is_urgent");
				String requestDate = rs.getString("request_date");
				int workDaysMissed = rs.getInt("work_days_missed");
				String justification = rs.getString("justification");
				Employee requestor = employeeDao.readEmployee(rs.getInt("employee_id"));
				ApprovalStatus approvalStatus = ApprovalStatus.valueOf(rs.getInt("approval_status"));
				String description = rs.getString("description");
				request = new ReimbursementRequest(requestor.getEmplId(), event.getEventId(), reqId, projectedReimbursement, isUrgent, 
						requestDate, workDaysMissed, justification, approvalStatus, description);
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	public List<ReimbursementRequest> readAllReimbursementRequests() {
		log.info("request Dao Postgres: reading all requests");
		String sql = "select * from requests ";
		ReimbursementRequest request = new ReimbursementRequest();
		List<ReimbursementRequest> requestList = new ArrayList<>();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int reqId = rs.getInt("request_id");
				Event event = eventDao.readEvent(rs.getInt("event_id"));
				double projectedReimbursement = rs.getDouble("projected_reimbursement");
				boolean isUrgent = rs.getBoolean("is_urgent");
				String requestDate = rs.getString("request_date");
				int workDaysMissed = rs.getInt("work_days_missed");
				String justification = rs.getString("justification");
				Employee requestor = employeeDao.readEmployee(rs.getInt("employee_id"));
				ApprovalStatus approvalStatus = ApprovalStatus.valueOf(rs.getInt("approval_status"));
				String description = rs.getString("description");
				request = new ReimbursementRequest(requestor.getEmplId(), event.getEventId(), reqId, projectedReimbursement, isUrgent, 
						requestDate, workDaysMissed, justification, approvalStatus, description);
				requestList.add(request);
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requestList;
	}

	@Override
	public ReimbursementRequest updateReimbursementRequest(int reqId, ReimbursementRequest req) {
		log.info("request Dao Postgres: updating request");
		String sql = "update requests set event_id = ?, projected_reimbursement = ?, is_urgent = ?,"
				+ " request_date = date(?), work_days_missed = ?, justification = ?, employee_id = ?, approval_status = ?,"
				+ " description = ?"
				+ "where request_id = ?";
					
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, req.getEventId());
			statement.setDouble(2, req.getProjectedReimbursement());
			statement.setBoolean(3, req.isUrgent());
			statement.setString(4, req.getRequestDate());
			statement.setInt(5, req.getWorkDaysMissed());
			statement.setString(6, req.getJustification());
			statement.setInt(7, req.getRequestorId());
			statement.setInt(8, req.getApprovalStatus().getValue());
			statement.setString(9, req.getDescription());
			statement.setInt(10, reqId);
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return req;
	}

	@Override
	public int deleteReimbursementRequest(int reqId) {
		log.info("request dao postgres: deleting request");
		int rowsToDelete = 0;
		String sql = "delete from requests where request_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
		statement = conn.prepareStatement(sql);	
		statement.setInt(1, reqId);
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
	public List<ReimbursementRequest> readRequestBySupervisorId(int supervisorId) {
		log.info("request Dao Postgres: reading request by supervisorId");
		//TODO make sure correct
		String sql = "select * from requests r "
				+ "inner join employees e on r.employee_id = e.employee_id "
				+ "where directsupervisor_id = ?";
		ReimbursementRequest request = new ReimbursementRequest();
		List<ReimbursementRequest> reqList = new ArrayList<>();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, supervisorId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int reqId = rs.getInt("request_id");
				Event event = eventDao.readEvent(rs.getInt("event_id"));
				double projectedReimbursement = rs.getDouble("projected_reimbursement");
				boolean isUrgent = rs.getBoolean("is_urgent");
				String requestDate = rs.getString("request_date");
				int workDaysMissed = rs.getInt("work_days_missed");
				String justification = rs.getString("justification");
				Employee requestor = employeeDao.readEmployee(rs.getInt("employee_id"));
				ApprovalStatus approvalStatus = ApprovalStatus.valueOf(rs.getInt("approval_status"));
				String description = rs.getString("description");
				request = new ReimbursementRequest(requestor.getEmplId(), event.getEventId(), reqId, projectedReimbursement, isUrgent, 
						requestDate, workDaysMissed, justification, approvalStatus, description);
				reqList.add(request);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqList;
	}

	@Override
	public List<ReimbursementRequest> readRequestsByBencoId(int bencoId) {
		log.info("request Dao Postgres: reading request by bencoId");
		String sql = "select * "
				+ "from requests r "
				+ "inner join employees e "
				+ "on  r.employee_id = e.employee_id "
				+ "where e.department_id = (select department_id from employees where employee_id = ?)";
		ReimbursementRequest request = new ReimbursementRequest();
		List <ReimbursementRequest> reqList = new ArrayList<>();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, bencoId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int reqId = rs.getInt("request_id");
				Event event = eventDao.readEvent(rs.getInt("event_id"));
				double projectedReimbursement = rs.getDouble("projected_reimbursement");
				boolean isUrgent = rs.getBoolean("is_urgent");
				String requestDate = rs.getString("request_date");
				int workDaysMissed = rs.getInt("work_days_missed");
				String justification = rs.getString("justification");
				Employee requestor = employeeDao.readEmployee(rs.getInt("employee_id"));
				ApprovalStatus approvalStatus = ApprovalStatus.valueOf(rs.getInt("approval_status"));
				String description = rs.getString("description");
				request = new ReimbursementRequest(requestor.getEmplId(), event.getEventId(), reqId, projectedReimbursement, isUrgent, 
						requestDate, workDaysMissed, justification, approvalStatus, description);
				reqList.add(request);
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqList;
	}

	@Override
	public List<ReimbursementRequest> readRequestsByDepheadId(int depheadId) {
		log.info("request Dao Postgres: reading request by depheadId");
		String sql = "select * "
				+ "from requests r "
				+ "inner join employees e "
				+ "on  r.employee_id = e.employee_id "
				+ "where e.department_id = (select department_id from employees where employee_id = ?)";
		ReimbursementRequest request = new ReimbursementRequest();
		List<ReimbursementRequest> reqList = new ArrayList<>();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, depheadId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int reqId = rs.getInt("request_id");
				Event event = eventDao.readEvent(rs.getInt("event_id"));
				double projectedReimbursement = rs.getDouble("projected_reimbursement");
				boolean isUrgent = rs.getBoolean("is_urgent");
				String requestDate = rs.getString("request_date");
				int workDaysMissed = rs.getInt("work_days_missed");
				String justification = rs.getString("justification");
				Employee requestor = employeeDao.readEmployee(rs.getInt("employee_id"));
				ApprovalStatus approvalStatus = ApprovalStatus.valueOf(rs.getInt("approval_status"));
				String description = rs.getString("description");
				request = new ReimbursementRequest(requestor.getEmplId(), event.getEventId(), reqId, projectedReimbursement, isUrgent, 
						requestDate, workDaysMissed, justification, approvalStatus, description);
				reqList.add(request);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqList;
	}

	@Override
	public List<ReimbursementRequest> readByRequestor(int employeeId) {
		log.info("request Dao Postgres: reading request by supervisorId");
		//TODO make sure correct
		String sql = "select * from requests where employee_id = ?";
		ReimbursementRequest request = new ReimbursementRequest();
		List<ReimbursementRequest> reqList = new ArrayList<>();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, employeeId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int reqId = rs.getInt("request_id");
				Event event = eventDao.readEvent(rs.getInt("event_id"));
				double projectedReimbursement = rs.getDouble("projected_reimbursement");
				boolean isUrgent = rs.getBoolean("is_urgent");
				String requestDate = rs.getString("request_date");
				int workDaysMissed = rs.getInt("work_days_missed");
				String justification = rs.getString("justification");
				Employee requestor = employeeDao.readEmployee(employeeId);
				ApprovalStatus approvalStatus = ApprovalStatus.valueOf(rs.getInt("approval_status"));
				String description = rs.getString("description");
				request = new ReimbursementRequest(requestor.getEmplId(), event.getEventId(), reqId, projectedReimbursement, isUrgent, 
						requestDate, workDaysMissed, justification, approvalStatus, description);
				reqList.add(request);
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reqList;
	}

}
