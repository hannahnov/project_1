package service;

import java.util.List;

import org.apache.log4j.Logger;

import daos.MessageDao;
import daos.MessageDaoPostgres;
import pojos.Message;

public class MessageServiceFullStack implements MessageService {
	private Logger log = Logger.getRootLogger();
	private MessageDao messageDao = new MessageDaoPostgres();
	@Override
	public Message createMessage(Message message) {
	log.info("Message service: create message");
		
		 messageDao.createMessage(message);
		 return message;
	}

	@Override
	public Message readMessageByRecipientId(int recipientId) {
	log.info("Message service: read message by recipeint Id");
		
		return messageDao.readMessageByRecipientId(recipientId);
	}

	@Override
	public List<Message> readAllMessages() {
	log.info("Message service: read all messages");
		
		return messageDao.readAllMessages();
	}

	@Override
	public Message updateMessage(int messageId, Message message) {
	log.info("Message service: update message");
		
		return messageDao.updateMessage(messageId, message);
	}

	@Override
	public void deleteMessage(int messageId) {
	log.info("Message service: delete message");
		messageDao.deleteMessage(messageId);
	}

}
