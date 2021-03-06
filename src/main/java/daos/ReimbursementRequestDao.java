package daos;

import java.util.List;

import pojos.ReimbursementRequest;

public interface ReimbursementRequestDao {
	public void createReimbursementRequest(ReimbursementRequest req);
	
	public ReimbursementRequest readReimbursementRequest(int reqId);
	
	public List<ReimbursementRequest> readAllReimbursementRequests();
	
	public ReimbursementRequest updateReimbursementRequest(int reqId, ReimbursementRequest req);
	
	public int deleteReimbursementRequest(int reqId);
	
	public List<ReimbursementRequest> readRequestBySupervisorId(int supervisorId);

	public List<ReimbursementRequest> readRequestsByBencoId(int bencoId);
	
	public List<ReimbursementRequest> readRequestsByDepheadId(int depheadId);

	public List<ReimbursementRequest> readByRequestor(int employeeId);

}
