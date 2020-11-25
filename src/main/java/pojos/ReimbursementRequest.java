package pojos;

public class ReimbursementRequest {
//	This form must collect (required): basic employee information; 
//	date, time, location, description, cost, grading format,
//	and type of event; work-related justification.  
	
	private Requestor requestor;
	
	private int requestId;
	
	private double projectedReimbursement;
	
	//if the request is turned in less than 2 weeks before the event start date, it is marked as urgent
	private boolean isUrgent;
	
	
	private String requestDate;
	
	
	
	

	public ReimbursementRequest(int requestId, Requestor requestor, double projectedReimbursement, boolean isUrgent,
			String requestDate) {
		super();
		this.requestId = requestId;
		this.requestor = requestor;
		this.projectedReimbursement = projectedReimbursement;
		this.isUrgent = isUrgent;
		this.requestDate = requestDate;
	}

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgency(boolean isUrgent) {
		this.isUrgent = isUrgent;
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


	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

}
