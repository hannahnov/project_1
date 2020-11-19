package pojos;

public class ReimbursementRequest {
//	This form must collect (required): basic employee information; 
//	date, time, location, description, cost, grading format,
//	and type of event; work-related justification.  
	
	private Requestor requestor;
	
	private double projectedReimbursement;
	
	//if the request is turned in less than 2 weeks before the event start date, it is marked as urgent
	private boolean isUrgent;
	
	private boolean isPending;
	
	private boolean isApprovedByDirectSupervisor;
	
	private boolean isApprovedByBenCo;

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgency(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public boolean isPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}

	public Requestor getRequestor() {
		return requestor;
	}

	public void setRequestor(Requestor requestor) {
		this.requestor = requestor;
	}

	public double getProjectedReimbursement() {
		return projectedReimbursement;
	}

	public void setProjectedReimbursement(double projectedReimbursement) {
		this.projectedReimbursement = projectedReimbursement;
	}

	public boolean isApprovedByDirectSupervisor() {
		return isApprovedByDirectSupervisor;
	}

	public void setApprovedByDirectSupervisor(boolean isApprovedByDirectSupervisor) {
		this.isApprovedByDirectSupervisor = isApprovedByDirectSupervisor;
	}

	public boolean isApprovedByBenCo() {
		return isApprovedByBenCo;
	}

	public void setApprovedByBenCo(boolean isApprovedByBenCo) {
		this.isApprovedByBenCo = isApprovedByBenCo;
	}

}
