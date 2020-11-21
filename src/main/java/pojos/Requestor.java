package pojos;

public class Requestor implements Employee {
	
	private String firstName;

	private String lastName;
	
	private String userName;
	
	private String password;
	
	private Approver directSupervisor;
	
	private Event event;
	
	
	private double availableReimbursement;
	
	private double pendingReimbursement;
	
	
	

	public Requestor() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Approver getDirectSupervisor() {
		return directSupervisor;
	}

	public void setDirectSupervisor(Approver directSupervisor) {
		this.directSupervisor = directSupervisor;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public double getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}




	public double getPendingReimbursement() {
		return pendingReimbursement;
	}




	public void setPendingReimbursement(double pendingReimbursement) {
		this.pendingReimbursement = pendingReimbursement;
	}


	

}
