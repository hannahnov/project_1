package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import daos.EmployeeDao;
import daos.EmployeeDaoPostgres;
import daos.EventDao;
import daos.EventDaoPostgres;
import daos.EventResultDao;
import daos.EventResultDaoPostgres;
import daos.ReimbursementRequestDao;
import daos.ReimbursementRequestDaoPostgres;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import pojos.ApprovalStatus;
import pojos.Employee;
import pojos.Event;
import pojos.EventResult;
import pojos.EventType;
import pojos.GradingFormat;
import pojos.Message;
import pojos.ReimbursementRequest;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.EventService;
import service.EventServiceFullStack;
import service.MessageService;
import service.MessageServiceFullStack;
import service.ReimbursementRequestService;
import service.ReimbursementRequestServiceFullStack;

public class TRMSRequester {
	private static Logger log = Logger.getRootLogger();
	private EmployeeDao employeeDao = new EmployeeDaoPostgres();
	private EmployeeService employeeService = new EmployeeServiceFullStack();
	private EventDao eventDao = new EventDaoPostgres();
	private EventResultDao resultDao = new EventResultDaoPostgres();
	private ReimbursementRequestDao requestDao = new ReimbursementRequestDaoPostgres();
	private ReimbursementRequestService requestService = new ReimbursementRequestServiceFullStack();
	private MessageService messageService = new MessageServiceFullStack();
	private EventService eventService = new EventServiceFullStack();
	
	//creates request form with appropriate information
	public void createRequest(Context ctx) {
		System.out.println("Responding to Post create ReimbursementRequest");
		
		log.info("Controller: creating reimbursement request");
		
		int employeeId = Integer.valueOf(ctx.formParam("employee_id"));
		String parm = ctx.formParam("grading_format");
		System.out.println("The value is " + parm);
		GradingFormat gradingFormat = GradingFormat.valueOf(Integer.valueOf(ctx.formParam("grading_format")));
		EventType eventType = EventType.valueOf(Integer.valueOf(ctx.formParam("event_type")));
		String eventStartDate = ctx.formParam("event_start_date");
		Employee requestor = employeeDao.readEmployee(employeeId);
		boolean isUrgent = false;
		String requestDate = ctx.formParam("request_date");
		int workDaysMissed = Integer.valueOf(ctx.formParam("work_days_missed"));
		String justification = ctx.formParam("justification");
		ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
		String description = ctx.formParam("description");
		String eventLocation = ctx.formParam("event_location");
		double eventCost = Double.valueOf(ctx.formParam("event_cost"));
		String eventName = ctx.formParam("event_name");
		double projectedReimbursement = 0;
		int approvalInt = approvalStatus.getValue();
		double percentage = 0.0;
		if (approvalInt == 1) {
			percentage = .8;
		}
		if (approvalInt == 2) {
			percentage = .6;
		}
		if (approvalInt == 3) {
			percentage = .75;
		}
		if (approvalInt == 4) {
			percentage = 1;
		}
		if (approvalInt == 5) {
			percentage = .9;
		}
		if (approvalInt == 6) {
			percentage = .3;
		}
		projectedReimbursement = percentage * eventCost;
		Event event = new Event(gradingFormat, eventName, eventStartDate, eventType, eventLocation, eventCost);
		eventService.createEvent(event);
		List<Event> eventList = eventService.readAllEvents();
		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getName().compareTo(event.getName()) == 0) {
			event = eventList.get(i);
			break;
			}
		}
	ReimbursementRequest request = new ReimbursementRequest(requestor, event, projectedReimbursement, isUrgent,
				requestDate, workDaysMissed, justification, approvalStatus, description);
	requestService.createReimbursementRequest(request);
		
		
		ctx.html(event.toString());
		
		ctx.status(200);
		ctx.redirect("http://127.0.0.1:5500/project1_login.html");

	}
	
	//if more information is requested
	public void sendMessage(Context ctx) {
		System.out.println("Responding to Post request create message");
		
		log.info("Controller: create a message");
		
		
		int requestId = Integer.valueOf(ctx.formParam("request_id"));
		ReimbursementRequest req = requestService.readReimbursementRequest(requestId);
		int senderId = Integer.valueOf(ctx.formParam("sender_id"));
		Employee sender = employeeService.readEmployee(senderId);
		int recipientId = Integer.valueOf(ctx.formParam("recipient_id"));
		Employee recipient = employeeService.readEmployee(recipientId);
		String dateSent = ctx.formParam("date_sent");
		boolean received = Boolean.valueOf(ctx.formParam("is_received"));
		String messageHeader = ctx.formParam("message_header");
		String message = ctx.formParam("message");
		
	//	(int messageId, ReimbursementRequest req, Employee sender, Employee recipient, String dateSent,
			//	boolean receieved, String header, String message)
		Message msg = new Message(req, sender, recipient, dateSent, received, messageHeader, message);
		
		  messageService.createMessage(msg);
	}
	
	//read request for more information
	public void viewMessage(Context ctx) {
		System.out.println("Responding to Get request read a message");
		
		log.info("Controller: read a message");
		
		int recipientId = Integer.valueOf(ctx.formParam("recipient_id"));
		
		Message message = messageService.readMessageByRecipientId(recipientId);
		
		ctx.html(message.toString());
		
	}
	//for benco approval
	public void uploadGrade(Context ctx) {
		String grade = ctx.formParam("grade");
		
		int requestId = Integer.valueOf(ctx.formParam("request_id"));
		
		ReimbursementRequest req = requestDao.readReimbursementRequest(requestId);
		
		EventResult result = new EventResult(req, grade);
		
		resultDao.createEventResult(result);
		
		ctx.html(result.getGrade());
		
	}
	//for direct supervisor approval
	public void uploadPresentation(Context ctx) {
int requestId = Integer.valueOf(ctx.formParam("request_id"));
		
		ReimbursementRequest req = requestDao.readReimbursementRequest(requestId);
		
		//TODO figure out how to upload
		EventResult result = new EventResult();
		UploadedFile presentation = ctx.uploadedFile("presentation");
		InputStream q =	presentation.getContent();
		byte[] attachment;
		try {
			attachment = q.readAllBytes();
			result = new EventResult(req, attachment);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		resultDao.createEventResult(result);
		
		ctx.html(result.getGrade());
	}

}
