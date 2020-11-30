package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import daos.EmployeeDao;
import daos.EmployeeDaoPostgres;
import daos.LogInInfoDao;
import daos.LogInInfoDaoPostgres;
import javalin.util.ConnectionUtil;
import pojos.Employee;
import pojos.LogInInfo;

public class AuthServiceImpl implements AuthService {
	
	private Statement statement;
	
	private PreparedStatement prepSt;

	private ConnectionUtil connUtil = new ConnectionUtil();
	
	
	private static Logger log = Logger.getRootLogger();

	private static byte[] salt = SecureRandom.getSeed(16);
	
	private Map<String, String> tokenRepo = new HashMap<>();
	
	public void setConnUtil(ConnectionUtil connUtil) {
		this.connUtil = connUtil;
	}
	

	EmployeeDao employeeDao = new EmployeeDaoPostgres();
	static Employee emp = new Employee();
	
	@Override
	public int authenticateUser(String username, String password) {
		
		LogInInfoDao loginDao = new LogInInfoDaoPostgres();
		
		LogInInfo login = loginDao.readLogInInfo(username, password);
		
		String pword = loginDao.getPassword(username);
		if (password.equals(pword)) {
			log.info("login information approved");
			return login.getEmployeeId();
		}
		return -1;
	}

	@Override
	public String createToken(int employeeId) {
		
		String id = String.valueOf(employeeId);
		String token = simpleHash(id);

		tokenRepo.put(token, id);

		return token;
	}

	@Override
	public String validateToken(String token) {
		return tokenRepo.get(token);
	}
	
	private String simpleHash(String id) {
		String hash = null;
		
		MessageDigest md;
		
		try {
			
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			
			byte[] bytes = md.digest(id.getBytes());

			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < bytes.length; i++) {
				
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(0));
			}
			
			hash = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
		
			e.printStackTrace();
		}
		
		return hash;
	}
	
}

