package pojos;

public class Message {

	private Employee sender;
	
	private Employee recipient;
	
	private String dateSent;
	
	private boolean receieved;
	
	private String header;
	
	private String message;
	
	
	

	public Message(Employee sender, Employee recipient, String dateSent, boolean receieved, String header, String message) {
		super();
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
	
}
