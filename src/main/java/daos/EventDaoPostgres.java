package daos;

import org.apache.log4j.Logger;

import javalin.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pojos.Employee;
import pojos.EmployeeRank;
import pojos.Event;
import pojos.EventType;
import pojos.GradingFormat;
import pojos.Requestor;
import service.EventService;
import service.EventServiceFullStack;

public class EventDaoPostgres implements EventDao {
	private static Logger log = Logger.getRootLogger();
	private PreparedStatement statement;
	EventService eventService = new EventServiceFullStack();
	private ConnectionUtil connUtil = new ConnectionUtil();

	@Override
	public void createEvent(Event event) {
		log.info("Event dao postgres: creating event");
		String sql = "insert into events (gradingformat, eventid, name, startdate, eventtype)"
				+ " values(?, ?, ?, ?, ?)";
		
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, event.getGradingFormat().getValue());
			statement.setInt(2, event.getEventId());
			statement.setString(3, event.getName());
			statement.setString(4, event.getEventStartDate().toString());
			statement.setInt(5, event.getEventType().getValue());
		}
		catch (SQLException e) {
			e.printStackTrace();
	
		}
		
	}

	@Override
	public Event readEvent(int eventId) {
		log.info("Event Dao Postgres: reading event");
		String sql = "select * from events where eventid = ?";
		//public Event(GradingFormat gradingFormat, int eventId, String name, Date eventStartDate, EventType eventType)
		Event event;
		try (Connection conn = connUtil.createConnection()) {
			statement = conn.prepareStatement(sql);
			
			statement.setInt(1, eventId);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				GradingFormat gradingFormat = GradingFormat.valueOf(rs.getInt("gradingformat"));
				String name = rs.getString("name");
				Date eventStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("eventstartdate"));
				EventType eventType = EventType.valueOf(rs.getInt("eventType"));
				 event = new Event(gradingFormat, eventId, name, eventStartDate, eventType);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public List<Event> readAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event updateEvent(int eventId, Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteEvent(int eventId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
