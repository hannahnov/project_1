package pojos;

public class Message {
	
	private int messageId;
	
	private ReimbursementRequest req;
	
	private Employee sender;
	
	private Employee recipient;
	
	private String dateSent;
	
	private boolean receieved;
	
	private String header;
	
	private String message;

	public Message(int messageId, ReimbursementRequest req, Employee sender, Employee recipient, String dateSent,
			boolean receieved, String header, String message) {
		super();
		this.messageId = messageId;
		this.req = req;
		this.sender = sender;
		this.recipient = recipient;
		this.dateSent = dateSent;
		this.receieved = receieved;
		this.header = header;
		this.message = message;
	}


	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
	}

	public Employee getRecipient() {
		return recipient;
	}

	public void setRecipient(Employee recipient) {
		this.recipient = recipient;
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

	public ReimbursementRequest getReq() {
		return req;
	}

	public void setReq(ReimbursementRequest req) {
		this.req = req;
	}
	
}
