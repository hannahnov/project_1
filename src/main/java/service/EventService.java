package service;

import java.util.List;

import pojos.Event;

public interface EventService {
	
	public Event createEvent(Event event);
	
	public Event readEvent(int eventId);
	
	public List<Event> readAllEvents();
	
	public Event updateEvent(int eventId, Event event);
	
	public void deleteEvent(int eventId);

}
