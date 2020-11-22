package service;

import java.util.List;

import pojos.ReimbursementRequest;

public interface ReimbursementRequestService {
	
	public ReimbursementRequest createReimbursementRequest(ReimbursementRequest req);
	
	public ReimbursementRequest readReimbursementRequest(int requestId);
	
	public List<ReimbursementRequest> readAllReimbursementRequests();
	
	public ReimbursementRequest updateReimbursementRequest(int requestId, ReimbursementRequest req);
	
	public void deleteReimbursementRequest(int requestId);

}
