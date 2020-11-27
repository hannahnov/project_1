package controllers;

import java.io.File;

import org.apache.log4j.Logger;

import com.google.common.primitives.Bytes;

import io.javalin.http.Context;
import pojos.Employee;
import pojos.EventResult;
import pojos.Message;
import pojos.ReimbursementRequest;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.EventResultService;
import service.EventResultServiceFullStack;
import service.MessageService;
import service.MessageServiceFullStack;
import service.ReimbursementRequestService;
import service.ReimbursementRequestServiceFullStack;

public class TRMSApprover {
	private static Logger log = Logger.getRootLogger();

	private ReimbursementRequestService reimbursementRequestService = new ReimbursementRequestServiceFullStack();

	private EmployeeService employeeService = new EmployeeServiceFullStack();
	
	private MessageService messageService = new MessageServiceFullStack();
	
	private EventResultService resultService = new EventResultServiceFullStack();
	
	public void supervisorViewRequests(Context ctx) {
		System.out.println("Responding to Get read reimbursement request by supervisor ID");
		
		log.info("Controller: read a reimbursement request by supervisor ID");
		
		int supervisorId = Integer.valueOf(ctx.formParam("directsupervisor_id"));
		
		ReimbursementRequest req = reimbursementRequestService.readRequestBySupervisorId(supervisorId);
		
		ctx.html(req.toString());
	}
	
	public void bencoViewRequests(Context ctx) {
		System.out.println("Responding to Get read reimbursement request by benco ID");
		
		log.info("Controller: read a reimbursement request by benco ID");
		
		int bencoId = Integer.valueOf(ctx.formParam("benco_id"));
		
		ReimbursementRequest req = reimbursementRequestService.readRequestsByBencoId(bencoId);
		
		ctx.html(req.toString());
		
	}
	
	public void depHeadViewRequests(Context ctx) {
	System.out.println("Responding to Get read reimbursement request by benco ID");
		
		log.info("Controller: read a reimbursement request by benco ID");
		
		int bencoId = Integer.valueOf(ctx.formParam("benco_id"));
		
		ReimbursementRequest req = reimbursementRequestService.readRequestsByBencoId(bencoId);
		
		ctx.html(req.toString());
	}
	
	//Used to request information
	public void sendMessage(Context ctx) {
		System.out.println("Responding to Post request create message");
		
		log.info("Controller: create a message");
		
		
		int requestId = Integer.valueOf(ctx.formParam("request_id"));
		ReimbursementRequest req = reimbursementRequestService.readReimbursementRequest(requestId);
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
	//view requests or reminders
	public void viewMessages(Context ctx) {
		System.out.println("Responding to Get request read a message");
		
		log.info("Controller: read a message");
		
		int recipientId = Integer.valueOf(ctx.formParam("recipient_id"));
		
		Message message = messageService.readMessageByRecipientId(recipientId);
		
		ctx.html(message.toString());
	}
	
	//view grade (if benco) view presentation (if direct supervisor)
	public void viewEventGrade(Context ctx) {
		System.out.println("Responding to Get read eventGrade by benco ID");
		
		log.info("Controller: read an event Grade by benco ID");
		
		int bencoId = Integer.valueOf(ctx.formParam("benco_id"));
		
		String grade = resultService.readResultGrade(bencoId);
		
		ctx.html(grade);
	}
	
	public void viewEventPresentation(Context ctx) {
		
		System.out.println("Responding to Get read eventPresentation by directSupervisor ID");
			
		log.info("Controller: read an event presentation");
		
		int depheadId = Integer.valueOf(ctx.formParam("dephead_id"));
		
		//TODO figure out byte variable type
		
		byte[] attachment = resultService.readAttachment(depheadId);
		
		ctx.html(attachment.toString());
		
	}
	
	//changes the approval status of the request (if it is a benco approval the whole form is marked approved)
	public void approveRequest(Context ctx) {
		System.out.println("Responding to PUT approve request");
		
		log.info("Controller: update request to add approval");
		
		boolean approval = Boolean.valueOf(ctx.formParam("approval_status"));
		
		int employeeId = Integer.valueOf(ctx.formParam("employee_id"));
		
		requestApprovalService.approveRequest(approval, employeeId);
		
	}
	
	//puts the appropriate amount of money in the employee's account
	public void grantReimbursement(Context ctx) {
		
	}

}
