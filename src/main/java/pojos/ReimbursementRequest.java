package pojos;

public class ReimbursementRequest {
//	This form must collect (required): basic employee information; 
//	date, time, location, description, cost, grading format,
//	and type of event; work-related justification.  
	
	

	private int requestorId;
	
	private int eventId;
	
	private int requestId;
	
	private double projectedReimbursement;
	
	//if the request is turned in less than 2 weeks before the event start date, it is marked as urgent
	private boolean isUrgent;
	
	private String requestDate;
	
	private int workDaysMissed;
	
	private String justification;
	
	private ApprovalStatus approvalStatus;
	
	private String description;
	



	public ReimbursementRequest(int requestorId, int eventId, double projectedReimbursement, boolean isUrgent,
			String requestDate, int workDaysMissed, String justification, ApprovalStatus approvalStatus,
			String description) {
		super();
		this.requestorId = requestorId;
		this.eventId = eventId;
		this.projectedReimbursement = projectedReimbursement;
		this.isUrgent = isUrgent;
		this.requestDate = requestDate;
		this.workDaysMissed = workDaysMissed;
		this.justification = justification;
		this.approvalStatus = approvalStatus;
		this.description = description;
	}

	public ReimbursementRequest(int requestorId, int eventId, int requestId, double projectedReimbursement,
			boolean isUrgent, String requestDate, int workDaysMissed, String justification,
			ApprovalStatus approvalStatus, String description) {
		super();
		this.requestorId = requestorId;
		this.eventId = eventId;
		this.requestId = requestId;
		this.projectedReimbursement = projectedReimbursement;
		this.isUrgent = isUrgent;
		this.requestDate = requestDate;
		this.workDaysMissed = workDaysMissed;
		this.justification = justification;
		this.approvalStatus = approvalStatus;
		this.description = description;
	}

	public int getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(int requestorId) {
		this.requestorId = requestorId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public void setUrgent(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public ReimbursementRequest() {
		// TODO Auto-generated constructor stub
	}

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgency(boolean isUrgent) {
		this.isUrgent = isUrgent;
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


	public int getWorkDaysMissed() {
		return workDaysMissed;
	}

	public void setWorkDaysMissed(int workDaysMissed) {
		this.workDaysMissed = workDaysMissed;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
