package controllers;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import pojos.Approver;
import pojos.Employee;
import pojos.EmployeeRank;
import pojos.Event;
import pojos.Requestor;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.EventService;
import service.EventServiceFullStack;

public class EmployeeController {
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	
	EventService eventService = new EventServiceFullStack();
	
	private static java.util.logging.Logger log = Logger.getRootLogger();
	
	public void createEmployee(Context ctx) {
		System.out.println("Responding to post create employee request");
		
		log.info("Controller: create Employee");
		
		String firstName = ctx.formParam("firstname");
		
		String lastName = ctx.formParam("lastname");
		
		String userName = ctx.formParam("username");
		
		String password = ctx.formParam("password");
		
		int supId = Integer.valueOf(ctx.formParam("supervisorid"));
		
		Employee directSupervisor = employeeService.readEmployee(supId);
		
		Event event = eventService.createEvent();
		
		double availableReimbursement = Double.valueOf(ctx.formParam("availablereimbursement"));
		
		double pendingReimbursement = Double.valueOf(ctx.formParam("pendingreimbursement"));
		
		int rankInt = Integer.valueOf(ctx.formParam("employeeRank"));
		
		EmployeeRank employeeRank;
		
		switch(rankInt) {
		case 1: employeeRank = EmployeeRank.DEPARTMENT_HEAD;
			break;
		case 2: employeeRank = EmployeeRank.BENEFIT_COORDINATOR;
			break;
		case 3: employeeRank = EmployeeRank.OTHER;
			break;
		default: employeeRank = EmployeeRank.OTHER;
		}
		
		boolean isRequesting = Boolean.valueOf(ctx.formParam("requesting"));
		
		boolean isApproving = Boolean.valueOf(ctx.formParam("approving"));
	
		if (isRequesting) {
			
			Employee empl = new Requestor();
			
			Requestor requestor = new Employee(firstName, lastName, userName, password, directSupervisor,
					Event event, availableReimbursement, pendingReimbursement, employeeRank,
				 isRequesting, isApproving);
		}
		
		public Employee(String firstName, String lastName, String userName, String password, Approver directSupervisor,
				Event event, double availableReimbursement, double pendingReimbursement, EmployeeRank employeeRank,
				boolean isRequesting, boolean isApproving)
	}

}
 