package pojos;

public class ReimbursementRequest {
	
	
	//if the request is turned in less than 2 weeks before the event start date, it is marked as urgent
	private boolean isUrgent;
	
	private boolean isApproved;

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgency(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void approveRequest(boolean isApproved) {
		this.isApproved = isApproved;
	}
}
