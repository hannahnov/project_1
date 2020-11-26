package daos;

import java.util.List;

import pojos.RequestApproval;

public interface RequestApprovalDao {
public void createRequestApproval(RequestApproval requestApproval);
	
	public RequestApproval readRequestApproval(int requestId);
	
	public List<RequestApproval> readAllRequestApprovals();
	
	public RequestApproval updateRequestApproval(int requestId, RequestApproval requestApproval);
	
	public int deleteRequestApproval(int requestId);
}
