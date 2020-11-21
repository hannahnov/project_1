package controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import io.javalin.http.Context;
import pojos.ReimbursementRequest;
import pojos.Requestor;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.ReimbursementRequestService;
import service.ReimbursementRequestServiceFullStack;

public class ReimbursementRequestController {
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	ReimbursementRequestService reimbursementRequestService = new ReimbursementRequestServiceFullStack();
	
	private static java.util.logging.Logger log = Logger.getRootLogger();
	
	public void createReimbursementRequest(Context ctx) {
		
		System.out.println("Responding to post create ReimbursementRequest request");
		
		log.info("controller: create reimbursementRequest");
		
		int reqId = Integer.valueOf(ctx.formParam("requestid"));
		
		double projectedReimbursement = Double.valueOf(ctx.formParam("projectedreimbursement"));
		
		boolean isUrgent = Boolean.parseBoolean(ctx.formParam("isurgent"));
		
		boolean isPending = Boolean.parseBoolean(ctx.formParam("ispending"));
		
		boolean isApprovedByDirectSupervisor = Boolean.parseBoolean(ctx.formParam("approvedbydirectsup"));
		
		boolean isApprovedByBenCo = Boolean.parseBoolean(ctx.formParam("approvedbybenco"));
		
		int requestorId = Integer.parseInt(ctx.formParam("requestorid"));
		
		Requestor requestor = employeeService.readEmployee(requestorId);
				
		
		ReimbursementRequest req = new ReimbursementRequest(reqId, requestor, projectedReimbursement, isUrgent, isPending,
				 isApprovedByDirectSupervisor, isApprovedByBenCo);
		
	}
	
	public void readReimbursementRequest(Context ctx) {
		
		System.out.println("Responding to Get read reimbursement request request");
	
		log.info("Controller: read a reimbursement request");
		
		int reqId = Integer.valueOf(ctx.formParam("reqId"));
		
		ReimbursementRequest req = reimbursementRequestService.readReimbursementRequest(reqId);
		
		ctx.html(req.toString());
	}
	
	public void readAllReimbursementRequests(Context ctx) {
		
		System.out.println("Responding to Get read all reimbursement requests request");
		log.info("Controller: read all reimbursement requests");
		
		List<ReimbursementRequest> reqList = new ArrayList<>();
		
		reqList = ReimbursementRequestService.getAllReimbursementRequests();
		
		String str = "";
		for (int i = 0; i < reqList.size(); i++) {
			ReimbursementRequest req = reqList.get(i);
			str += (req.toString() + "\n");
			
		}
		
		ctx.html(str);
	}
	
	public void updateReimbursementRequest(Context ctx) {
		System.out.println("Responding to put update Reimbursement Request");
		log.info("Controller: updtate reimbursement request");
		
		int reqId = Integer.valueOf(ctx.formParam("requestid"));
		
		double projectedReimbursement = Double.valueOf(ctx.formParam("projectedreimbursement"));
		
		boolean isUrgent = Boolean.parseBoolean(ctx.formParam("isurgent"));
		
		boolean isPending = Boolean.parseBoolean(ctx.formParam("ispending"));
		
		boolean isApprovedByDirectSupervisor = Boolean.parseBoolean(ctx.formParam("approvedbydirectsup"));
		
		boolean isApprovedByBenCo = Boolean.parseBoolean(ctx.formParam("approvedbybenco"));
		
		int requestorId = Integer.parseInt(ctx.formParam("requestorid"));
		
		Requestor requestor = employeeService.readEmployee(requestorId);
				
		
		ReimbursementRequest req = new ReimbursementRequest(reqId, requestor, projectedReimbursement, isUrgent, isPending,
				 isApprovedByDirectSupervisor, isApprovedByBenCo);
		
		reimbursementRequestService.updateReimbursementRequest(reqId, req);
		
		ctx.html(req.toString());
		
	}
		public void deleteReimbursementRequest(Context ctx) {
			System.out.println("Responding to delete reimbursement request");
			log.info("Controller: deleting reimbursement request");
			
			int reqId = Integer.valueOf(ctx.formParam("requestid"));
			
			reimbursementRequestService.deleteReimbursementRequest(reqId);
		
	}

}
