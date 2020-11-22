package pojos;

public class Employee {
	
	private int emplId;
	
	private String firstName;

	private String lastName;
	
	private String userName;
	
	private String password;
	
	private int directSupervisorId;
	
	private Event event;
	
	private double availableReimbursement;
	
	private double pendingReimbursement;
	
	private EmployeeRank employeeRank;
	
	private boolean isRequesting;
	
	private boolean isApproving;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	
public Employee(int emplId, String firstName, String lastName, String userName, String password, int directSupervisorId,
			Event event, double availableReimbursement, double pendingReimbursement, EmployeeRank employeeRank,
			boolean isRequesting, boolean isApproving) {
		super();
		this.emplId = emplId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.directSupervisorId = directSupervisorId;
		this.event = event;
		this.availableReimbursement = availableReimbursement;
		this.pendingReimbursement = pendingReimbursement;
		this.employeeRank = employeeRank;
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







	public EmployeeRank getEmployeeRank() {
		return employeeRank;
	}







	public void setEmployeeRank(EmployeeRank employeeRank) {
		this.employeeRank = employeeRank;
	}


	public int getEmplId() {
		return emplId;
	}


	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}


	public int getDirectSupervisorId() {
		return directSupervisorId;
	}


	public void setDirectSupervisorId(int directSupervisorId) {
		this.directSupervisorId = directSupervisorId;
	}



}
