package pojos;

public class Employee {
	
	private String emailAddress;
	
	private int emplId;
	
	private String firstName;

	private String lastName;
	
	private int directSupervisorId;
	
	private double availableReimbursement;
	
	private double pendingReimbursement;
	
	private EmployeeRank employeeRank;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	
public Employee(String emailAddress, int emplId, String firstName, String lastName, int directSupervisorId,
			double availableReimbursement, double pendingReimbursement, EmployeeRank employeeRank) {
		super();
		this.emailAddress = emailAddress;
		this.emplId = emplId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.directSupervisorId = directSupervisorId;
		this.availableReimbursement = availableReimbursement;
		this.pendingReimbursement = pendingReimbursement;
		this.employeeRank = employeeRank;
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


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



}
