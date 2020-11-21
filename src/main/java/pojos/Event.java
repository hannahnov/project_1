package pojos;

import java.util.Date;

public class Event {
	
	//could be a grade or presentation
	private EventResult eventResult;
	
	private int eventId;

	private String name;
	
	private Date eventStartDate;
	
	private EventType eventType;
	
	

	public Event(EventResult eventResult, int eventId, String name, Date eventStartDate, EventType eventType) {
		super();
		this.eventResult = eventResult;
		this.eventId = eventId;
		this.name = name;
		this.eventStartDate = eventStartDate;
		this.eventType = eventType;
	}
	
	public EventResult getEventResult() {
		return eventResult;
	}

	public void setEventResult(EventResult eventResult) {
		this.eventResult = eventResult;
	}

	public Date getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
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
		return "Event [eventResult=" + eventResult + ", eventId=" + eventId + ", name=" + name + ", eventStartDate="
				+ eventStartDate + ", eventType=" + eventType + "]";
	}
}
