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
import pojos.ReimbursementRequest;
import pojos.RequestApproval;

public class RequestApprovalDaoPostgres implements RequestApprovalDao {
	private static Logger log = Logger.getRootLogger();
	private PreparedStatement statement;
	private ConnectionUtil connUtil = new ConnectionUtil();
	EmployeeDao employeeDao = new EmployeeDaoPostgres();
	ReimbursementRequestDao requestDao = new ReimbursementRequestDaoPostgres();


	@Override
	public void createRequestApproval(RequestApproval requestApproval) {
		log.info("RequestApproval dao postgres: creating requestApproval");
		String sql = "insert into requestapprovals (request_id, supervisor_approval_date, dephead_approval_date, "
				+ "benco_approval_date, supervisor_approval, dephead_approval, benco_approval)"
				+ " values(?, date(?), date(?), date(?), ?, ?, ?)";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, requestApproval.getRequest().getRequestId());
			statement.setString(2, requestApproval.getDirectSupApprovalDate());
			statement.setString(3, requestApproval.getDepHeadApprovalDate());
			statement.setString(4, requestApproval.getBenCoApprovalDate());
			statement.setInt(5, requestApproval.getSupervisorApproval().getValue());
			statement.setInt(6, requestApproval.getDepHeadApproval().getValue());
			statement.setInt(7, requestApproval.getBenCoApproval().getValue());
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
	
		}
		
	}

	@Override
	public RequestApproval readRequestApproval(int requestId) {
		log.info("requestApproval Dao Postgres: reading requestApproval");
		String sql = "select * from requestapprovals where request_id = ?";
		RequestApproval requestApproval = new RequestApproval();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, requestId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
	//	public RequestApproval(ReimbursementRequest request, String directSupApprovalDate, String depHeadApprovalDate, String benCoApprovalDate,
		//ApprovalStatus supervisorApproval, ApprovalStatus depHeadApproval, ApprovalStatus benCoApproval) {
				ReimbursementRequest req = requestDao.readReimbursementRequest(rs.getInt("request_id"));
				String directSupApprovalDate = rs.getString("supervisor_approval_date");
				String depHeadApprovalDate = rs.getString("dephead_approval_date");
				String bencoApprovalDate = rs.getString("benco_approval_date");
				ApprovalStatus supervisorApproval = ApprovalStatus.valueOf(rs.getInt("supervisor_approval"));
				ApprovalStatus depHeadApproval = ApprovalStatus.valueOf(rs.getInt("dephead_approval"));
				ApprovalStatus benCoApproval = ApprovalStatus.valueOf(rs.getInt("benco_approval"));
				
				requestApproval = new RequestApproval(req, directSupApprovalDate, depHeadApprovalDate,
						bencoApprovalDate, supervisorApproval, depHeadApproval, benCoApproval);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requestApproval;
	}

	@Override
	public List<RequestApproval> readAllRequestApprovals() {
		log.info("requestApproval Dao Postgres: reading all requestApprovals");
		String sql = "select * from requestapprovals ";
		List<RequestApproval> approvalList = new ArrayList<>();
		RequestApproval requestApproval = new RequestApproval();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
	
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
	//	public RequestApproval(ReimbursementRequest request, String directSupApprovalDate, String depHeadApprovalDate, String benCoApprovalDate,
		//ApprovalStatus supervisorApproval, ApprovalStatus depHeadApproval, ApprovalStatus benCoApproval) {
				
				ReimbursementRequest req = requestDao.readReimbursementRequest(rs.getInt("request_id"));
				String directSupApprovalDate = rs.getString("supervisor_approval_date");
				String depHeadApprovalDate = rs.getString("dephead_approval_date");
				String bencoApprovalDate = rs.getString("benco_approval_date");
				ApprovalStatus supervisorApproval = ApprovalStatus.valueOf(rs.getInt("supervisor_approval"));
				ApprovalStatus depHeadApproval = ApprovalStatus.valueOf(rs.getInt("dephead_approval"));
				ApprovalStatus benCoApproval = ApprovalStatus.valueOf(rs.getInt("benco_approval"));
				
				requestApproval = new RequestApproval(req, directSupApprovalDate, depHeadApprovalDate,
						bencoApprovalDate, supervisorApproval, depHeadApproval, benCoApproval);
				approvalList.add(requestApproval);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	@Override
	public RequestApproval updateRequestApproval(int requestId, RequestApproval requestApproval) {
		log.info("requestApproval Dao Postgres: updating requestApproval");
		String sql = "update requestapprovals set supervisor_approval_date = date(?), dephead_approval_date = date(?),"
				+ " benco_approval_date = date(?), supervisor_approval = ?, dephead_approval = ?, "
				+ "benco_approval = ? "
				+ "where request_id = ?";
					
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setString(1, requestApproval.getDirectSupApprovalDate());
			statement.setString(2, requestApproval.getDepHeadApprovalDate());
			statement.setString(3, requestApproval.getBenCoApprovalDate());
			statement.setInt(4, requestApproval.getSupervisorApproval().getValue());
			statement.setInt(5, requestApproval.getDepHeadApproval().getValue());
			statement.setInt(6, requestApproval.getBenCoApproval().getValue());
			statement.setInt(7, requestId);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requestApproval;
	}

	@Override
	public int deleteRequestApproval(int requestId) {
		log.info("requestApproval dao postgres: deleting requestApproval");
		int rowsToDelete = 0;
		String sql = "delete from requestapprovals where request_id = ?";
		
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
	public void approveRequest(boolean approval, int employeeId, int requestId) {
		// TODO Auto-generated method stub
		
	}

}
