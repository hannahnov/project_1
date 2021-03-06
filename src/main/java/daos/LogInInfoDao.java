package daos;

import java.util.List;

import pojos.LogInInfo;


public interface LogInInfoDao {
	
	public LogInInfo readLogInInfo(int employeeId);
	
	public List<LogInInfo> readAllLogInInfo();
	
	public LogInInfo updateLogInInfo(int employeeId, LogInInfo login);
	
	public String getPassword(String userName);

	public LogInInfo readLogInInfo(String username, String password);

}
