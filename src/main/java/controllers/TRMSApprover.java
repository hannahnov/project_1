package controllers;

import io.javalin.http.Context;

public class TRMSApprover {
	
	//a benco, direct supervisor, or department head can view requests associated with their employee ID
	public void viewRequests(Context ctx) {
		
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
