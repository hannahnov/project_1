package pojos;


public class Event {
	
	private GradingFormat gradingFormat;
	
	private int eventId;

	private String name;
		
	private String eventStartDate;
	
	private EventType eventType;
	
	private String location;
	
	private double cost;
	
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Event(GradingFormat gradingFormat, int eventId, String name, 
			String eventStartDate, EventType eventType, String location, double cost) {
		super();
		this.gradingFormat = gradingFormat;
		this.eventId = eventId;
		this.name = name;
		this.eventStartDate = eventStartDate;
		this.eventType = eventType;
		this.location = location;
		this.cost = cost;
	}
	
	public Event() {
		// TODO Auto-generated constructor stub
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}
	
	public String getEventStartDate() {
		return eventStartDate;
	}
	

	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	@Override
	public String toString() {
		return "Event [gradingFormat=" + gradingFormat + ", eventId=" + eventId + ", name=" + name + ", eventStartDate="
				+ eventStartDate + ", eventType=" + eventType + "]";
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
