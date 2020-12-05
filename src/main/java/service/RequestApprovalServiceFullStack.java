package service;

import org.apache.log4j.Logger;

import daos.EmployeeDao;
import daos.EmployeeDaoPostgres;
import daos.ReimbursementRequestDao;
import daos.ReimbursementRequestDaoPostgres;
import daos.RequestApprovalDao;
import daos.RequestApprovalDaoPostgres;
import pojos.ApprovalStatus;
import pojos.Employee;
import pojos.ReimbursementRequest;
import pojos.RequestApproval;

public class RequestApprovalServiceFullStack implements RequestApprovalService {
	private static Logger log = Logger.getRootLogger();
	private RequestApprovalDao approvalDao = new RequestApprovalDaoPostgres();
	private EmployeeDao employeeDao = new EmployeeDaoPostgres();
	private ReimbursementRequestDao requestDao = new ReimbursementRequestDaoPostgres();
	
	@Override
	public void approveRequest(int approval, int employeeId, int requestId, String approvalDate) {
	log.info("Request Approval service: update Request Approval by approver ID");
	
		Employee empl = employeeDao.readEmployee(employeeId);
		
		ReimbursementRequest request = requestDao.readReimbursementRequest(requestId);
		
		Employee requestor = employeeDao.readEmployee(request.getRequestorId());
		
		
		
		String approvalEmployeeColumn = "";
		String approvalDateColumn = "";
		
		//if the approver is the direct supervisor, update the approvals
		if (employeeId == requestor.getDirectSupervisorId()) {
			approvalEmployeeColumn = "supervisor_approval";
			approvalDateColumn = "supervisor_approval_date";
		}
		//if the approver is the department head, update the approvals
		if (empl.getEmployeeRank().getValue() == 1) {
			approvalEmployeeColumn = "dephead_approval";
			approvalDateColumn = "dephead_approval_date";
		}
	
		//if the approver is the benco, the entire reimbursement form gets approved and updated as well
		if (empl.getEmployeeRank().getValue() == 2) {
			approvalEmployeeColumn = "benco_approval";
			approvalDateColumn = "benco_approval_date";
			request.setApprovalStatus(ApprovalStatus.valueOf(approval));
			requestDao.updateReimbursementRequest(requestId, request);
		}
		
		 approvalDao.approveRequest(approval, employeeId, requestId, approvalDate, 
				 approvalEmployeeColumn, approvalDateColumn);
	}

	@Override
	public void grantReimbursement(int requestId, int approverId, double reimbursement) {
		log.info("Request Approval service: granting reimbursement to employee");
		ReimbursementRequest req = requestDao.readReimbursementRequest(requestId);
		Employee employee = employeeDao.readEmployee(req.getRequestorId());
		double actualReimbursement = reimbursement;
		
		if (reimbursement + (employee.getAvailableReimbursement() +  employee.getPendingReimbursement()) >= 1000) {
			actualReimbursement = 1000 - (employee.getAvailableReimbursement() + employee.getPendingReimbursement());
		}
		employee.setAwardedReimbursement(employee.getAwardedReimbursement() + actualReimbursement);
		employee.setAvailableReimbursement(employee.getAvailableReimbursement() - actualReimbursement);
		employeeDao.updateEmployee(employee.getEmplId(), employee);
	}

	@Override
	public void createRequestApproval(RequestApproval Request) {
		log.info("Request Approval service: creating a request approval");
		approvalDao.createRequestApproval(Request);
	}

}
