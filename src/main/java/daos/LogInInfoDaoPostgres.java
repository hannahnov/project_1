package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import javalin.util.ConnectionUtil;
import pojos.LogInInfo;


public class LogInInfoDaoPostgres implements LogInInfoDao {
	private static Logger log = Logger.getRootLogger();
	private PreparedStatement statement;
	private ConnectionUtil connUtil = new ConnectionUtil();
	

	@Override
	public LogInInfo readLogInInfo(int employeeId) {
		log.info("Login Dao Postgres: reading login info");
		String sql = "select * from logininfo where employee_id = ?";
		LogInInfo logInInfo = new LogInInfo();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, employeeId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
	
				String emailAddress = rs.getString("email_address");
				String userName = rs.getString("user_name");
				String password = rs.getString("password");
				logInInfo = new LogInInfo(employeeId, userName, emailAddress, password);
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logInInfo;
	}


	@Override
	public List<LogInInfo> readAllLogInInfo() {
		log.info("Login Dao Postgres: reading all login info");
		String sql = "select * from logininfo";
		LogInInfo logInInfo = new LogInInfo();
		
		List<LogInInfo> logInList = new ArrayList<>();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String emailAddress = rs.getString("email_address");
				String userName = rs.getString("user_name");
				String password = rs.getString("password");
				logInInfo = new LogInInfo(employeeId, userName, emailAddress, password);
				logInList.add(logInInfo);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logInList;
	}

	@Override
	public LogInInfo updateLogInInfo(int employeeId, LogInInfo login) {
		log.info("Login dao postgres: updating login");
		
		String sql = "update logininfo set email_address = ?, user_name = ?, password = ?,"
				+  " where employee_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setString(1, login.getEmailAddress());
			statement.setString(2, login.getUserName());
			statement.setString(3, login.getPassword());
			statement.setInt(4, employeeId);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}
	
	
}
