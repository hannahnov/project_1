package pojos;

public class DepartmentHead extends Employee implements Approver {

	@Override
	public boolean approveForms(ReimbursementRequest req, boolean approval) {
		req.setApprovedByBenCo(approval);
		
		return approval;
	}

}
