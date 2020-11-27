package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import javalin.util.ConnectionUtil;
import pojos.Employee;
import pojos.Message;
import pojos.ReimbursementRequest;

public class MessageDaoPostgres implements MessageDao {
	private static Logger log = Logger.getRootLogger();
	private PreparedStatement statement;
	private ConnectionUtil connUtil = new ConnectionUtil();
	ReimbursementRequestDao reqDao = new ReimbursementRequestDaoPostgres();
	EmployeeDao employeeDao = new EmployeeDaoPostgres();
	@Override
	public void createMessage(Message message) {
		log.info("Message dao postgres: creating message");
		String sql = "insert into messages (request_id, sender_id, recipient_id, date_sent, "
				+ "is_received, message_header, message)"
				+ " values(?, ?, ?, date(?), ?, ?, ?)";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, message.getReq().getRequestId());
			statement.setInt(2, message.getSender().getEmplId());
			statement.setInt(3, message.getRecipient().getEmplId());
			statement.setString(4, message.getDateSent());
			statement.setBoolean(5, message.isReceieved());
			statement.setString(6, message.getHeader());
			statement.setString(7, message.getMessage());
	
		}
		catch (SQLException e) {
			e.printStackTrace();
	
		}
			
		
	}

	@Override
	public Message readMessageByRecipientId(int recipientId) {
		log.info("message Dao Postgres: reading message");
		String sql = "select * from messages where recipient_id = ?";
		Message message = new Message();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, recipientId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int messageId = rs.getInt("message_id");
				ReimbursementRequest req = reqDao.readReimbursementRequest(rs.getInt("request_id"));
				Employee sender = employeeDao.readEmployee(rs.getInt("sender_id"));
				Employee recipient = employeeDao.readEmployee(recipientId);
				String date = rs.getString("date_sent");
				Boolean isReceived = rs.getBoolean("is_received");
				String header = rs.getString("message_header");
				String message1 = rs.getString("message");
				message = new Message(messageId, req, sender, recipient, date, isReceived, header, message1);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<Message> readAllMessages() {
		log.info("message Dao Postgres: reading all messages");
		String sql = "select * from messages ";
		Message message = new Message();
		List<Message> messageList = new ArrayList<>();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int messageId = rs.getInt("message_id");
				ReimbursementRequest req = reqDao.readReimbursementRequest(rs.getInt("request_id"));
				Employee sender = employeeDao.readEmployee(rs.getInt("sender_id"));
				Employee recipient = employeeDao.readEmployee(rs.getInt("recipient_id"));
				String date = rs.getString("date_sent");
				Boolean isReceived = rs.getBoolean("is_received");
				String header = rs.getString("message_header");
				String message1 = rs.getString("message");
				message = new Message(messageId, req, sender, recipient, date, isReceived, header, message1);
				messageList.add(message);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}

	@Override
	public Message updateMessage(int messageId, Message message) {
		log.info("message Dao Postgres: updating message");
		String sql = "update messages set request_id = ?, sender_id = ?, recipient_id = ?,"
				+ " date_sent = date(?), is_received = ?, message_header = ?, message = ? "
				+ "where request_id = ?";
					
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, message.getReq().getRequestId());
			statement.setInt(2, message.getSender().getEmplId());
			statement.setInt(3, message.getRecipient().getEmplId());
			statement.setString(4, message.getDateSent());
			statement.setBoolean(5, message.isReceieved());
			statement.setString(6, message.getHeader());
			statement.setString(7, message.getMessage());
			statement.setInt(8, messageId);
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public int deleteMessage(int messageId) {
		log.info("message dao postgres: deleting message");
		int rowsToDelete = 0;
		String sql = "delete from messages where message_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
		statement = conn.prepareStatement(sql);	
		statement.setInt(1, messageId);
		rowsToDelete = statement.executeUpdate();
		
		if (rowsToDelete == 0) {
		System.out.println("No rows to delete");
		
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return rowsToDelete;
	}
	
}
