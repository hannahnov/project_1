package daos;

import org.apache.log4j.Logger;

import javalin.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Event;
import pojos.EventType;
import pojos.GradingFormat;

public class EventDaoPostgres implements EventDao {
	private static Logger log = Logger.getRootLogger();
	private PreparedStatement statement;
	private ConnectionUtil connUtil = new ConnectionUtil();

	@Override
	public void createEvent(Event event) {
		log.info("Event dao postgres: creating event");
		String sql = "insert into events (event_id, grading_format, event_name, event_start_date, event_type, event_location, event_cost)"
				+ " values(?, ?, ?, date(?), ?, ?, ?)";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, event.getEventId());
			statement.setInt(2, event.getGradingFormat().getValue());
			statement.setString(3, event.getName());
			statement.setString(4, event.getEventStartDate());
			statement.setInt(5, event.getEventType().getValue());
			statement.setString(6, event.getLocation());
			statement.setDouble(7, event.getCost());
		}
		catch (SQLException e) {
			e.printStackTrace();
	
		}
		
	}

	@Override
	public Event readEvent(int eventId) {
		log.info("Event Dao Postgres: reading event");
		String sql = "select * from events where eventid = ?";
		Event event = new Event();
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, eventId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
			//	(event_id, grading_format, event_name, event_start_date, event_type, event_location)"
			//	GradingFormat gradingFormat, int eventId, String name, 
			//String eventStartDate, EventType eventType, String location)
				GradingFormat gradingFormat = GradingFormat.valueOf(rs.getInt("grading_format"));
				String eventName = rs.getString("event_name");
				String eventStartDate = rs.getString("event_start_date");
				EventType eventType = EventType.valueOf(rs.getInt("event_Type"));
				String location = rs.getString("event_location");
				double cost = rs.getDouble("event_cost");
				 event = new Event(gradingFormat, eventId, eventName, eventStartDate, eventType, location, cost);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public List<Event> readAllEvents() {
		log.info("Event Dao Postgres: reading all events");
		String sql = "select * from events ";
		//public Event(GradingFormat gradingFormat, int eventId, String name, Date eventStartDate, EventType eventType)
		List<Event> eventList = new ArrayList<>();
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				int eventId = rs.getInt("event_id");
				GradingFormat gradingFormat = GradingFormat.valueOf(rs.getInt("grading_format"));
				String eventName = rs.getString("event_name");
				String eventStartDate = rs.getString("event_start_date");
				EventType eventType = EventType.valueOf(rs.getInt("event_Type"));
				String location = rs.getString("event_location");
				double cost = rs.getDouble("event_cost");
				Event event = new Event(gradingFormat, eventId, eventName, eventStartDate, eventType, location, cost);
				eventList.add(event);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventList;
	}

	@Override
	public Event updateEvent(int eventId, Event event) {
		log.info("Event dao postgres: updating event");
		//public Event(GradingFormat gradingFormat, int eventId, String name, Date eventStartDate, EventType eventType)
		String sql = "update events set grading_format = ?, event_name = ?, event_start_date = date(?), "
				+ "event_type = ?, event_cost = ?"
				+  " where event_id = ?";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, event.getGradingFormat().getValue());
			statement.setString(2, event.getName());
			statement.setString(3, event.getEventStartDate());
			statement.setInt(4, event.getEventType().getValue());
			statement.setDouble(5, event.getCost());
			statement.setInt(6, eventId);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}
	
	
	@Override
	public int deleteEvent(int eventId) {
			log.info("event dao postgres: deleting event");
			int rowsToDelete = 0;
			String sql = "delete from events where event_id = ?";
			
			try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);	
			statement.setInt(1, eventId);
			rowsToDelete = statement.executeUpdate();
			
			if (rowsToDelete == 0) {
			System.out.println("No rows to delete");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return rowsToDelete;
	}

}
