package pojos;

public class Requestor {
	
	private String firstName;

	private String lastName;
	
	private String userName;
	
	private String password;
	
	private Approver directSupervisor;
	
	private Event event;
	
	private int availableReimbursement;
	
	

	public Requestor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Requestor(String firstName, String lastName, String userName, String password, Approver directSupervisor,
			Event event, int availableReimbursement) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.directSupervisor = directSupervisor;
		this.event = event;
		this.availableReimbursement = availableReimbursement;
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

	public int getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(int availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}
	
	

}
