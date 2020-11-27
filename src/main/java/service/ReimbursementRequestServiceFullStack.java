package service;

import java.util.List;

import daos.ReimbursementRequestDao;
import daos.ReimbursementRequestDaoPostgres;
import pojos.ReimbursementRequest;
import org.apache.log4j.Logger;

public class ReimbursementRequestServiceFullStack implements ReimbursementRequestService {
	
	ReimbursementRequestDao reqDao = new ReimbursementRequestDaoPostgres();
	
	private static Logger log = Logger.getRootLogger();

	@Override
	public ReimbursementRequest createReimbursementRequest(ReimbursementRequest req) {
		log.info("Reimbursement request service: create request");
		
		reqDao.createReimbursementRequest(req);
		return req;
	}

	@Override
	public ReimbursementRequest readReimbursementRequest(int requestId) {
		log.info("Reimbursement request service: read request");
		
		return reqDao.readReimbursementRequest(requestId);
		
	}

	@Override
	public List<ReimbursementRequest> readAllReimbursementRequests() {
		log.info("Reimbursement request service: read all requests");
		
		return reqDao.readAllReimbursementRequests();
	}

	@Override
	public ReimbursementRequest updateReimbursementRequest(int requestId, ReimbursementRequest req) {
		log.info("Reimbursement request service: update request");
		
		reqDao.updateReimbursementRequest(requestId, req);
		
		return req;
	}

	@Override
	public void deleteReimbursementRequest(int requestId) {
		log.info("Reimbursement request service: delete request");
		
		reqDao.deleteReimbursementRequest(requestId);
		
		
	}

	@Override
	public ReimbursementRequest readRequestBySupervisorId(int supervisorId) {
		
		log.info("Reimbursement request service: read request by supervisor ID");
		
		return reqDao.readRequestBySupervisorId(supervisorId);
	}

	@Override
	public ReimbursementRequest readRequestsByBencoId(int bencoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReimbursementRequest readRequestsByDepheadId(int depheadId) {
		// TODO Auto-generated method stub
		return null;
	}

}
