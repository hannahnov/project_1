package pojos;

public class Approver extends Employee {
	
	public boolean approveForms(ReimbursementRequest req, boolean approval) {
		req.setApprovedByBenCo(approval);
		
		return approval;
	}
	

}
