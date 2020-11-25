package pojos;

public class ReimbursementRequest {
//	This form must collect (required): basic employee information; 
//	date, time, location, description, cost, grading format,
//	and type of event; work-related justification.  
	
	private Employee requestor;
	
	private Event event;
	
	private int requestId;
	
	private double projectedReimbursement;
	
	//if the request is turned in less than 2 weeks before the event start date, it is marked as urgent
	private boolean isUrgent;
	
	private String requestDate;
	
	private int workDaysMissed;
	
	private String justification;
	
	


	public ReimbursementRequest(Employee requestor, Event event, int requestId, double projectedReimbursement,
			boolean isUrgent, String requestDate, int workDaysMissed, String justification) {
		super();
		this.requestor = requestor;
		this.event = event;
		this.requestId = requestId;
		this.projectedReimbursement = projectedReimbursement;
		this.isUrgent = isUrgent;
		this.requestDate = requestDate;
		this.workDaysMissed = workDaysMissed;
		this.justification = justification;
	}

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgency(boolean isUrgent) {
		this.isUrgent = isUrgent;
	}

	public Employee getRequestor() {
		return requestor;
	}

	public void setRequestor(Employee requestor) {
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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

}
