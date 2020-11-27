package service;

public interface RequestApprovalService {

	void approveRequest(int approval, int employeeId, int requestId, String date);

	void grantReimbursement(int requestId, int approverId, double reimbursement);

}
