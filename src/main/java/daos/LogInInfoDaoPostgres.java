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
	
				String userName = rs.getString("user_name");
				String password = rs.getString("password");
				logInInfo = new LogInInfo(employeeId, userName, password);
				
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
				String userName = rs.getString("user_name");
				String password = rs.getString("password");
				logInInfo = new LogInInfo(employeeId, userName, password);
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
		
		String sql = "update logininfo set user_name = ?, password = ?,"
				+  " where employee_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setString(1, login.getUserName());
			statement.setString(2, login.getPassword());
			statement.setInt(3, employeeId);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}


	@Override
	public String getPassword(String userName) {
		log.info("Login Dao Postgres: reading password info");
		String sql = "select * from logininfo where user_name = ?";
		LogInInfo logInInfo = new LogInInfo();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setString(1, userName);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String password = rs.getString("password");
				logInInfo = new LogInInfo(employeeId, userName, password);
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logInInfo.getPassword();
	}
	
	
}
