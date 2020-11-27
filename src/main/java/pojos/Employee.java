package pojos;

public class Employee {
	
	private String emailAddress;
	
	private int departmentId;
	
	private int emplId;
	
	private String firstName;

	private String lastName;
	
	private int directSupervisorId;
	
	private double availableReimbursement;
	
	private double pendingReimbursement;
	
	private EmployeeRank employeeRank;
	
	private double awardedReimbursement;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	
public Employee(String emailAddress, int departmentId, int emplId, String firstName, String lastName, int directSupervisorId,
			double availableReimbursement, double pendingReimbursement, EmployeeRank employeeRank, double awardedReimbursement) {
		super();
		this.departmentId = departmentId;
		this.emailAddress = emailAddress;
		this.emplId = emplId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.directSupervisorId = directSupervisorId;
		this.availableReimbursement = availableReimbursement;
		this.pendingReimbursement = pendingReimbursement;
		this.employeeRank = employeeRank;
		this.awardedReimbursement = awardedReimbursement;
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


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public double getAwardedReimbursement() {
		return awardedReimbursement;
	}


	public void setAwardedReimbursement(double awardedReimbursement) {
		this.awardedReimbursement = awardedReimbursement;
	}



}
