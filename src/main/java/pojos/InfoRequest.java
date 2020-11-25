package pojos;

public class InfoRequest {

	private Employee sender;
	
	private Employee recipient;
	
	private String dateSent;
	
	private String dateDelivered;
	
	private boolean receieved;
	
	

	public InfoRequest(Employee sender, Employee recipient, String dateSent, String dateDelivered, boolean receieved) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.dateSent = dateSent;
		this.dateDelivered = dateDelivered;
		this.receieved = receieved;
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

	public String getDateDelivered() {
		return dateDelivered;
	}

	public void setDateDelivered(String dateDelivered) {
		this.dateDelivered = dateDelivered;
	}

	public boolean isReceieved() {
		return receieved;
	}

	public void setReceieved(boolean receieved) {
		this.receieved = receieved;
	}
	
}
