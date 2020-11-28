package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import daos.LogInInfoDao;
import daos.LogInInfoDaoPostgres;

public class AuthServiceImpl implements AuthService {

	private static byte[] salt = SecureRandom.getSeed(16);
	
	private Map<String, String> tokenRepo = new HashMap<>();
	
	@Override
	public boolean authenticateUser(String username, String password) {
		
		LogInInfoDao loginDao = new LogInInfoDaoPostgres();
		
		String pword = loginDao.getPassword(username);
		if (password.equals(pword)) {
			return true;
		}
		return false;
	}

	@Override
	public String createToken(String username) {
		
		String token = simpleHash(username);
		tokenRepo.put(token,  username);
		return token;
	}

	@Override
	public String validateToken(String token) {
		return tokenRepo.get(token);
	}
	
	private String simpleHash(String username) {
		String hash = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			
			byte[] bytes = md.digest(username.getBytes());
			
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
