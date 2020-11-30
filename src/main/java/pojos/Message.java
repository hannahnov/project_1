package pojos;

public class Message {
	
	private int messageId;
	
	private int requestId;
	
	private int senderId;
	
	private int recipientId;
	
	private String dateSent;
	
	private boolean receieved;
	
	private String header;
	
	private String message;
	
	


	public Message(int requestId, int senderId, int recipientId, String dateSent, boolean receieved, String header,
			String message) {
		super();
		this.requestId = requestId;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.dateSent = dateSent;
		this.receieved = receieved;
		this.header = header;
		this.message = message;
	}



	public Message(int messageId, int requestId, int senderId, int recipientId, String dateSent, boolean receieved,
			String header, String message) {
		super();
		this.messageId = messageId;
		this.requestId = requestId;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.dateSent = dateSent;
		this.receieved = receieved;
		this.header = header;
		this.message = message;
	}



	public int getRequestId() {
		return requestId;
	}



	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}



	public int getSenderId() {
		return senderId;
	}



	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}



	public int getRecipientId() {
		return recipientId;
	}



	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}



	public Message() {
		// TODO Auto-generated constructor stub
	}



	public String getDateSent() {
		return dateSent;
	}

	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}


	public boolean isReceieved() {
		return receieved;
	}

	public void setReceieved(boolean receieved) {
		this.receieved = receieved;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	
}
