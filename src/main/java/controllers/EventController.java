package controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import io.javalin.http.Context;
import pojos.Event;
import pojos.EventResult;
import pojos.EventType;
import service.EventService;
import service.EventServiceFullStack;

public class EventController {

	EventService eventService = new EventServiceFullStack();
	
	private static Logger log = Logger.getRootLogger();
	
	
	public void createEvent(Context ctx) {
		
		System.out.println("Responding to post create event request");
		
		log.info("Controller: create event");
		
		EventType eventType;
		int eventTypeint = Integer.valueOf(ctx.formParam("eventtype"));
		
		switch (eventTypeint) {
		
		case 1: eventType = EventType.UNIVERSITY_COURSE;
			break;
		case 2: eventType = EventType.CERTIFICATION;
			break;
		case 3: eventType = EventType.CERTIFICATION_PREP_CLASS;
			break;
		case 4: eventType = EventType.TECHNICAL_TRAINING;
			break;
		case 5: eventType = EventType.SEMINAR;
			break;
		case 6: eventType = EventType.OTHER;
			break;
		default: eventType = EventType.OTHER;
			break;
		}
		
		int eventId = Integer.valueOf(ctx.formParam("eventid"));
		
		String name = ctx.formParam("name");
		
		int skillLevel = Integer.parseInt(ctx.formParam("skilllevel"));
		
		DateFormat dateForm;
	
		Date eventStartDate = new Date();
		eventStartDate = dateForm.parse(ctx.formParam("startdate"));
		
		//TODO figure out eventResult
		EventResult eventResult = ctx.formParam("eventResult");
		
		Event event = new Event (eventResult, eventId, name, eventStartDate, eventType);
		eventService.createEvent(event);
		
		ctx.html(event.toString());
	}
	public void readEvent(Context ctx) {
		
		log.info("Controller: read an event");

		System.out.println("Responding to get read event request");
		
		int eventId = Integer.valueOf(ctx.formParam("eventid"));
		Event event = eventService.readEvent(eventId);
		ctx.html(event.toString());
	}
	
	public void readAllEvents(Context ctx) {
		
		System.out.println("Responding to get read all Events request");
		log.info("Controller: read all events");
			
			List<Event> eventList = new ArrayList<>();
		
			eventList = eventService.getAllEvents();
			
			String str = "";
		
			for (int i = 0; i < eventList.size(); i++) {
			Event event = eventList.get(i);
			str += (event.toString() + "\n");
	}
			ctx.html(str);
		
	}
	
	public void updateEvent(Context ctx) {
		System.out.println("Responding put update event request");
		
		int eventId = Integer.valueOf(ctx.formParam("eventid"));
		EventType eventType;
		int eventTypeint = Integer.valueOf(ctx.formParam("eventtype"));
		
		switch (eventTypeint) {
		
		case 1: eventType = EventType.UNIVERSITY_COURSE;
			break;
		case 2: eventType = EventType.CERTIFICATION;
			break;
		case 3: eventType = EventType.CERTIFICATION_PREP_CLASS;
			break;
		case 4: eventType = EventType.TECHNICAL_TRAINING;
			break;
		case 5: eventType = EventType.SEMINAR;
			break;
		case 6: eventType = EventType.OTHER;
			break;
		default: eventType = null;
			break;
		}
		
		String name = ctx.formParam("name");
		
		int skillLevel = Integer.parseInt(ctx.formParam("skilllevel"));
		
		DateFormat dateForm;
	
		Date eventStartDate = new Date();
		eventStartDate = dateForm.parse(ctx.formParam("startdate"));
		
		//TODO figure out eventResult
		EventResult eventResult = ctx.formParam("eventResult");
		
		Event event = new Event (eventResult, eventId, name, eventStartDate, eventType);

		eventService.updateEvent(eventId, event);
		
		ctx.html(event.toString());
	}
	
	public void deleteEvent(Context ctx) {
		System.out.println("Responding delete event request");
		
		log.info("deleting event");
		int eventId = Integer.valueOf(ctx.formParam("eventid"));
		
		eventService.deleteEvent(eventId);
		
	}
}
