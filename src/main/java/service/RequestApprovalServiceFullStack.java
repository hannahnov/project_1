package service;

import org.apache.log4j.Logger;

import daos.RequestApprovalDao;
import daos.RequestApprovalDaoPostgres;

public class RequestApprovalServiceFullStack implements RequestApprovalService {
	private static Logger log = Logger.getRootLogger();
	private RequestApprovalDao approvalDao = new RequestApprovalDaoPostgres();
	
	@Override
	public void approveRequest(boolean approval, int employeeId, int requestId) {
	log.info("Request Approval service: update Request Approval by approver ID");
		
		 approvalDao.approveRequest(approval, employeeId);
		
	}

}
