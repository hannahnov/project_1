package controllers;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import pojos.ReimbursementRequest;
import service.ReimbursementRequestService;
import service.ReimbursementRequestServiceFullStack;

public class TRMSApprover {
	private static Logger log = Logger.getRootLogger();

	private ReimbursementRequestService reimbursementRequestService = new ReimbursementRequestServiceFullStack();
	public void supervisorViewRequests(Context ctx) {
		System.out.println("Responding to Get read reimbursement request by supervisor ID");
		
		log.info("Controller: read a reimbursement request by supervisor ID");
		
		int supervisorId = Integer.valueOf(ctx.formParam("directsupervisor_id"));
		
		ReimbursementRequest req = reimbursementRequestService.readReimbursementRequest(supervisorId);
		
		ctx.html(req.toString());
	}
	
	public void bencoViewRequests(Context ctx) {
		
	}
	
	public void depHeadViewRequests(Context ctx) {
		
	}
	
	//Used to request information
	public void sendMessage(Context ctx) {
		
	}
	//view requests or reminders
	public void viewMessages(Context ctx) {
		
	}
	
	//view grade (if benco) view presentation (if direct supervisor)
	public void viewEventResult(Context ctx) {
		
	}
	
	//changes the approval status of the request (if it is a benco approval the whole form is marked approved)
	public void approveRequest(Context ctx) {
		
	}
	
	//puts the appropriate amount of money in the employee's account
	public void grantReimbursement(Context ctx) {
		
	}

}
