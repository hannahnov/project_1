package service;

public interface RequestApprovalService {

	void approveRequest(int approval, int employeeId, int requestId, String date);

}
