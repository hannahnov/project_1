package service;

public interface AuthService {
	
	public int authenticateUser(String username, String password);
	
	public String createToken(int employeeId);
	
	public String validateToken(String token);
}
