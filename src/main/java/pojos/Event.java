package pojos;

import java.time.LocalDate;
import java.util.Date;

public class Event {
	
	private GradingFormat gradingFormat;
	
	private int eventId;

	private String name;
	
	
	private String eventStartDate;
	
	private EventType eventType;
	
	

	public Event(GradingFormat gradingFormat, int eventId, String name, String eventStartDate, EventType eventType) {
		super();
		this.gradingFormat = gradingFormat;
		this.eventId = eventId;
		this.name = name;
		this.eventStartDate = eventStartDate;
		this.eventType = eventType;
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
}
