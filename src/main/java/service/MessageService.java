package service;

import java.util.List;

import pojos.Message;


public interface MessageService {
	public Message createMessage(Message message);
	
	public Message readMessageByRecipientId(int recipientId);
	
	public List<Message> readAllMessages();
	
	public Message updateMessage(int messageId, Message message);
	
	public void deleteMessage(int messageId);
}
