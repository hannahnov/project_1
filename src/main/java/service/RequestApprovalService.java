package service;

public interface RequestApprovalService {

	void approveRequest(boolean approval, int employeeId, int requestId);

}
