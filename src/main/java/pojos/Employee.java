package pojos;

public abstract class Employee {
	
	private String firstName;

	private String lastName;
	
	private String userName;
	
	private String password;
	
	private Approver directSupervisor;
	
	private Event event;
	
	private double availableReimbursement;
	
	private double pendingReimbursement;
	
	private boolean isDepartmentHead;
	
	private boolean isBenefitCoordinator;
	
	private boolean isRequesting;
	
	private boolean isApproving;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String firstName, String lastName, String userName, String password, Approver directSupervisor,
			Event event, double availableReimbursement, double pendingReimbursement, boolean isDepartmentHead,
			boolean isBenefitCoordinator, boolean isRequesting, boolean isApproving) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.directSupervisor = directSupervisor;
		this.event = event;
		this.availableReimbursement = availableReimbursement;
		this.pendingReimbursement = pendingReimbursement;
		this.isDepartmentHead = isDepartmentHead;
		this.isBenefitCoordinator = isBenefitCoordinator;
		this.isRequesting = isRequesting;
		this.isApproving = isApproving;
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

	public boolean isDepartmentHead() {
		return isDepartmentHead;
	}

	public void setDepartmentHead(boolean isDepartmentHead) {
		this.isDepartmentHead = isDepartmentHead;
	}

	public boolean isBenefitCoordinator() {
		return isBenefitCoordinator;
	}

	public void setBenefitCoordinator(boolean isBenefitCoordinator) {
		this.isBenefitCoordinator = isBenefitCoordinator;
	}


	public boolean isRequesting() {
		return isRequesting;
	}


	public void setRequesting(boolean isRequesting) {
		this.isRequesting = isRequesting;
	}


	public boolean isApproving() {
		return isApproving;
	}


	public void setApproving(boolean isApproving) {
		this.isApproving = isApproving;
	}



}
