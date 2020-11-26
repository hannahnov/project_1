package daos;

import java.util.List;

import pojos.Event;

public interface EventResultDao {
	public void createEvent(Event event);
	
	public Event readEvent(int eventId);
	
	public List<Event> readAllEvents();
	
	public Event updateEvent(int eventId, Event event);
	
	public int deleteEvent(int eventId);
}
