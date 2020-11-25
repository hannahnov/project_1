package pojos;

public class LogInInfo {
	private int employeeId;
	private String userName;
	private String emailAddress;
	private String password;
	private EmployeeRank employeeRank;
	
	
	public LogInInfo(int employeeId, String userName, String emailAddress, String password, EmployeeRank employeeRank) {
		super();
		this.employeeId = employeeId;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.password = password;
		this.employeeRank = employeeRank;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EmployeeRank getEmployeeRank() {
		return employeeRank;
	}
	public void setEmployeeRank(EmployeeRank employeeRank) {
		this.employeeRank = employeeRank;
	}

}
