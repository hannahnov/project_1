package daos;

import java.util.List;

import pojos.RequestApproval;

public interface RequestApprovalDao {
public void createRequestApproval(RequestApproval requestApproval, int requestId);
	
	public RequestApproval readRequestApproval(int requestId);
	
	public List<RequestApproval> readAllRequestApprovals();
	
	public RequestApproval updateRequestApproval(int requestId, RequestApproval requestApproval);
	
	public int deleteRequestApproval(int requestId);

	public void approveRequest(int approval, int employeeId, int requestId, String approvalDate, String approvalEmployeeColumn, String approvalDateColumn);
}
