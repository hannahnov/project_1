package controllers;

import java.sql.Date;
import java.util.logging.Logger;

import io.javalin.http.Context;
import pojos.EventResult;
import pojos.EventType;
import service.EventService;
import service.EventServiceFullStack;

public class EventController {

	EventService eventService = new EventServiceFullStack();
	
	private static Logger log = Logger.getRootLogger();
	
//	private EventResult eventResult;
//	
//	private int eventId;
//	
//	private String name;
//	
//	private Date eventStartDate;
//	
//	private EventType eventType;
	
	public void createEvent(Context ctx) {
		
		System.out.println("Responding to post create event request");
		
		log.info("Controller: create event");
		
		//TODO figure out
		EventType eventType = EventType.(ctx.formParam("eventResult"));
		
		
		
		int dogId = Integer.valueOf(ctx.formParam("dogid"));
		
		String name = ctx.formParam("name");
		
		int skillLevel = Integer.parseInt(ctx.formParam("skilllevel"));
		
		boolean isAvailable = Boolean.parseBoolean(ctx.formParam("isavailable"));
		String ownername = ctx.formParam("ownerusername");
		
		System.out.println("owner name is " + ownername);
		Dog dog = new Dog(dogId, name, ownername, skillLevel, isAvailable);
		
		System.out.println(" controller owner name is " + dog.getOwnerName());
		System.out.println(" controller dog name is " + dog.getDogName());
		
		dogService.createDog(dog);
		
		ctx.html(((Integer)dog.getDogid()).toString());
	}
	public void readDog(Context ctx) {
		
		log.info("Controller: read a dog");

		System.out.println("Responding to post read dog request");
		
		int dogId = Integer.valueOf(ctx.formParam("dogid"));
		Dog dog = dogService.readDog(dogId);
		ctx.html("Dog with id: " + dogId + "'s name is :" + dog.getDogName() + ", their owner is "
				+ dog.getOwnerName() + ", and their skill level is: " + dog.getSkillLevelDog());
	}
	
	public void readAllDogs(Context ctx) {
		
		System.out.println("Responding to post read all Dogs request");
		log.info("Controller: read all dogs");
			
			List<Dog> dogList = new ArrayList<>();
		
			dogList = dogService.getAllDogs();
			
			String str = "";
		
			for (int i = 0; i < dogList.size(); i++) {
			Dog dog = dogList.get(i);
			str += ("Dog with id: " + dog.getDogid() + "'s name is :" + dog.getDogName() + ", their owner is "
					+ dog.getOwnerName() + ", and their skill level is: " + dog.getSkillLevelDog() + "\n");
	}
			ctx.html(str);
		
	}
	
	public void updateDog(Context ctx) {
		System.out.println("Responding update dog request");
		
		int dogId = Integer.valueOf(ctx.formParam("dogid"));
		
		String dogName = ctx.formParam("name");
		
		String ownerName = ctx.formParam("ownerusername");
		
	
		int skillLevel = Integer.valueOf(ctx.formParam("skilllevel"));
		System.out.print("Controller dog skill level is " + skillLevel);
		
		boolean isAvailable = Boolean.valueOf(ctx.formParam("isavailable"));
		
		Dog dog = new Dog(dogId, dogName, ownerName, skillLevel, isAvailable);
		
		dogService.updateDog(dogId, dog);
		
		ctx.html(Integer.toString(dog.getDogid()));
	}
	
	public void deleteDog(Context ctx) {
		System.out.println("Responding delete dog request");
		
		log.info("deleting dog");
		int dogId = Integer.valueOf(ctx.formParam("dogid"));
		
		dogService.deleteDog(dogId);
		
	}
}
