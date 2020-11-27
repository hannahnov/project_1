package controllers;

import org.apache.log4j.Logger;

import daos.EmployeeDao;
import daos.EmployeeDaoPostgres;
import daos.EventDao;
import daos.EventDaoPostgres;
import io.javalin.http.Context;
import pojos.ApprovalStatus;
import pojos.Employee;
import pojos.Event;
import pojos.Message;
import pojos.ReimbursementRequest;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.MessageService;
import service.MessageServiceFullStack;
import service.ReimbursementRequestService;
import service.ReimbursementRequestServiceFullStack;

public class TRMSRequester {
	private static Logger log = Logger.getRootLogger();
	private EmployeeDao employeeDao = new EmployeeDaoPostgres();
	private EmployeeService employeeService = new EmployeeServiceFullStack();
	private EventDao eventDao = new EventDaoPostgres();
	private ReimbursementRequestService requestService = new ReimbursementRequestServiceFullStack();
	private MessageService messageService = new MessageServiceFullStack();
	
	//creates request form with appropriate information
	public void createRequest(Context ctx) {
		System.out.println("Responding to Post create ReimbursementRequest");
		
		log.info("Controller: creating reimbursement request");
		
		int employeeId = Integer.valueOf(ctx.formParam("employee_id"));
		Employee requestor = employeeDao.readEmployee(employeeId);
		int eventId = Integer.valueOf(ctx.formParam("event_id"));
		Event event = eventDao.readEvent(eventId);
		double projectedReimbursement = Double.valueOf(ctx.formParam("projected_reimbursement"));
		boolean isUrgent = false;
		String requestDate = ctx.formParam("request_date");
		int workDaysMissed = Integer.valueOf(ctx.formParam("work_days_missed"));
		String justification = ctx.formParam("justification");
		ApprovalStatus approvalStatus = ApprovalStatus.PENDING;
		String description = ctx.formParam("description");
		ReimbursementRequest request = new ReimbursementRequest(requestor, event, projectedReimbursement, isUrgent,
				requestDate, workDaysMissed, justification, approvalStatus, description);
		requestService.createReimbursementRequest(request);
		
		ctx.html(request.toString());
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
		
		
	}
	//for direct supervisor approval
	public void uploadPresentation(Context ctx) {
		
	}

}
