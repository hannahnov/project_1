package service;

import java.util.List;

import daos.EventDao;
import daos.EventDaoPostgres;
import pojos.Event;

public class EventServiceFullStack implements EventService {
	EventDao eventDao = new EventDaoPostgres();

	@Override
	public Event createEvent(Event event) {
		log.info("Event service: create event");
		
		eventDao.createEvent(event);
		
		return event;
		
	}

	@Override
	public Event readEvent(int eventId) {
		log.info("Event service: read event");
		
		return eventDao.readEvent(eventId);
	}

	@Override
	public List<Event> readAllEvents() {
		log.info("Event service: read all events");
		
		return eventDao.readAllEvents();
	}

	@Override
	public Event updateEvent(int eventId, Event event) {
		log.info("Event service: update event");
		
		eventDao.updateEvent(eventId, event);
		
		return event;
	}

	@Override
	public void deleteEvent(int eventId) {
		log.info("Event service: delete event");
		
		eventDao.deleteEvent(eventId);
		
	}

}
