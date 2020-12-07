package controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import pojos.ApprovalStatus;
import pojos.Employee;
import pojos.EventResult;
import pojos.Message;
import pojos.ReimbursementRequest;
import service.AuthService;
import service.AuthServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.EventResultService;
import service.EventResultServiceFullStack;
import service.MessageService;
import service.MessageServiceFullStack;
import service.ReimbursementRequestService;
import service.ReimbursementRequestServiceFullStack;
import service.RequestApprovalService;
import service.RequestApprovalServiceFullStack;

public class TRMSApprover {
	private static Logger log = Logger.getRootLogger();

	private ReimbursementRequestService reimbursementRequestService = new ReimbursementRequestServiceFullStack();

	private EmployeeService employeeService = new EmployeeServiceFullStack();
	
	private MessageService messageService = new MessageServiceFullStack();
	
	private EventResultService resultService = new EventResultServiceFullStack();
	
	private RequestApprovalService requestApprovalService = new RequestApprovalServiceFullStack();
	private AuthController authController = new AuthController();
	
	
	public void supervisorViewRequests(Context ctx) {
		System.out.println("Responding to Get read reimbursement request by supervisor ID");
		
		log.info("Controller: read a reimbursement request by supervisor ID");
		
		List<ReimbursementRequest> reqList = new ArrayList<>();
		
		int supervisorId = AuthController.loginMap.get(ctx.cookie("funcookieId123"));
		
		System.out.println(supervisorId);
		
		reqList = reimbursementRequestService.readRequestBySupervisorId(supervisorId);
		
		System.out.println(reqList.toString());
		
		ctx.json(reqList);
		
		
	}
	
	public void bencoViewRequests(Context ctx) {
		System.out.println("Responding to Get read reimbursement request by benco ID");
		
		List<ReimbursementRequest> reqList = new ArrayList<>();
		
		log.info("Controller: read a reimbursement request by benco ID");
		
		int bencoId = AuthController.loginMap.get(ctx.cookie("funcookieId123"));
		//int bencoId = Integer.valueOf(ctx.formParam("benco_id"));
		
		 reqList = reimbursementRequestService.readRequestsByBencoId(bencoId);
		
		ctx.json(reqList);
		
	}
	
	public void depHeadViewRequests(Context ctx) {
	System.out.println("Responding to Get read reimbursement request by benco ID");
		
		log.info("Controller: read a reimbursement request by benco ID");
		
		int bencoId = AuthController.loginMap.get(ctx.cookie("funcookieId123"));
		

		List<ReimbursementRequest> reqList = reimbursementRequestService.readRequestsByBencoId(bencoId);
		
		ctx.json(reqList);
	}
	
	//Used to request information
	public void sendMessage(Context ctx) {
		System.out.println("Responding to Post request create message");
		
		log.info("Controller: create a message");
		
		
		int requestId = Integer.valueOf(ctx.formParam("request_id"));
		int senderId = Integer.valueOf(ctx.formParam("sender_id"));
		int recipientId = Integer.valueOf(ctx.formParam("recipient_id"));
		String dateSent = ctx.formParam("date_sent");
		boolean received = Boolean.valueOf(ctx.formParam("is_received"));
		String messageHeader = ctx.formParam("message_header");
		String message = ctx.formParam("message");
		
	//	(int messageId, ReimbursementRequest req, Employee sender, Employee recipient, String dateSent,
			//	boolean receieved, String header, String message)
		Message msg = new Message(requestId, senderId, recipientId, dateSent, received, messageHeader, message);
		
		  messageService.createMessage(msg);
	}
	//view requests or reminders
	public void viewMessages(Context ctx) {
		System.out.println("Responding to Get request read a message");
		
		log.info("Controller: read a message");
		int recipientId  = AuthController.loginMap.get(ctx.cookieStore("funcookieId123"));
		//int recipientId = Integer.valueOf(ctx.formParam("recipient_id"));
		System.out.println(recipientId);
		//create array
		List<Message> messageList  = messageService.readMessageByRecipientId(recipientId);
		ctx.json(messageList);
	}
	
	//view grade (if benco) view presentation (if direct supervisor)
	public void viewEventGrade(Context ctx) {
		System.out.println("Responding to Get read eventGrade by benco ID");
		
		log.info("Controller: read an event Grade by benco ID");
		
		int bencoId = AuthController.loginMap.get(ctx.cookieStore("funcookieId123"));
		
		String grade = resultService.readResultGrade(bencoId);
		
		ctx.html(grade);
	}
	
	public void viewEventPresentation(Context ctx) {
		
		System.out.println("Responding to Get read eventPresentation by directSupervisor ID");
			
		log.info("Controller: read an event presentation");
		
		int depheadId = AuthController.loginMap.get(ctx.cookieStore("funcookieId123"));
		
		//TODO figure out byte variable type
		
		byte[] attachment = resultService.readAttachment(depheadId);
		
		ctx.html(attachment.toString());
		
	}
	
	//changes the approval status of the request (if it is a benco approval the whole form is marked approved)
	public void approveRequest(Context ctx) {
		System.out.println("Responding to PUT approve request");
		
		log.info("Controller: update request to add approval");
		
		int approval = 1;
		
		int employeeId = AuthController.loginMap.get(ctx.cookieStore("funcookieId123"));
		
		int requestId = Integer.valueOf(ctx.pathParam("request_id"));
		String date = "";//ctx.formParam("approval_date");
		requestApprovalService.approveRequest(approval, employeeId, requestId, date);
		authController.redirectHomePage(ctx);
		
	}
	//changes the approval status of the request (if it is a benco approval the whole form is marked approved)
		public void denyRequest(Context ctx) {
			System.out.println("Responding to PUT deny request");
			
			log.info("Controller: update request to add denial");
			
			int approval = 3;
			
			int employeeId = AuthController.loginMap.get(ctx.cookieStore("funcookieId123"));
			
			int requestId = Integer.valueOf(ctx.formParam("request_id"));
			String date = ""; //ctx.formParam("approval_date");
			requestApprovalService.approveRequest(approval, employeeId, requestId, date);
			
		}
	
	//puts the appropriate amount of money in the employee's account
	public void grantReimbursement(Context ctx) {
		
		System.out.println("Responding to PUT grant reimbursement request");
		
		log.info("Controller: update employee info to add reimbursement");
		
		int requestId = Integer.valueOf(ctx.formParam("request_id"));
		
		int approverId = AuthController.loginMap.get(ctx.cookieStore("funcookieId123"));
		
		ReimbursementRequest req = reimbursementRequestService.readReimbursementRequest(requestId);
		
		double reimbursement = req.getProjectedReimbursement();
		
		requestApprovalService.grantReimbursement(requestId, approverId, reimbursement);
		
		authController.redirectHomePage(ctx);
		
	}

	public void approveGrade(Context ctx) {
System.out.println("Responding to benco approve grade request");
		
		log.info("Controller: update benco approval on event result");
		
		int requestId = Integer.valueOf(ctx.pathParam("request_id"));
		

		resultService.bencoApproveGrade(requestId);
		authController.redirectHomePage(ctx);
	}

	public void denyGrade(Context ctx) {

		log.info("Controller: benco deny grade");
		
		int requestId = Integer.valueOf(ctx.formParam("request_id"));
		

		resultService.bencoDenyGrade(requestId);
	}

}
