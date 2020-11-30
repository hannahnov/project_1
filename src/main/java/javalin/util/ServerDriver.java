package javalin.util;

import org.apache.log4j.Logger;

import controllers.AuthController;
import controllers.TRMSApprover;
import controllers.TRMSRequester;
import io.javalin.Javalin;

public class ServerDriver {
	
	private static AuthController authController = new AuthController();
	
	private static TRMSApprover approverController = new TRMSApprover();
	
	private static TRMSRequester requesterController = new TRMSRequester();
	private static Logger log = Logger.getRootLogger();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start(9096);
		log.info("program has started");
		
		
		//methods for auth controller
		app.post("/login", ctx -> authController.login(ctx));
		app.get("/checkuser", ctx -> authController.checkUser(ctx));
		
		//methods for approverController
		app.get("/supervisorrequests", ctx-> approverController.supervisorViewRequests(ctx));
		app.get("/bencoviewrequests", ctx->approverController.bencoViewRequests(ctx));
		app.get("/depheadviewrequests", ctx->approverController.depHeadViewRequests(ctx));
		app.post("/sendmessageap", ctx->approverController.sendMessage(ctx));
		app.get("/viewmessagesap", ctx->approverController.viewMessages(ctx));
		app.get("/vieweventgrade", ctx->approverController.viewEventGrade(ctx));
		app.get("/viewpresentation", ctx->approverController.viewEventPresentation(ctx));
		app.put("/approverequest", ctx->approverController.approveRequest(ctx));
		app.put("/grantreimbursement", ctx->approverController.grantReimbursement(ctx));
		
		//methods for requesterController
		app.post("/createrequest", ctx->requesterController.createRequest(ctx));
		app.post("/sendmessagere", ctx->requesterController.sendMessage(ctx));
		//app.get("/viewmessagere", ctx->requesterController.viewMessage(ctx));
		app.put("/uploadgrade", ctx->requesterController.uploadGrade(ctx));
	//	app.put("/uploadpresentation", ctx->requesterController.uploadPresentation(ctx));
		
		
	}

}
