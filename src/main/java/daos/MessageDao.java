package daos;

import java.util.List;

import pojos.Message;

public interface MessageDao {
public void createMessage(Message message);
	
	public Message readMessage(int messageId);
	
	public List<Message> readAllMessages();
	
	public Message updateMessage(int messageId, Message message);
	
	public int deleteMessage(int messageId);
}
