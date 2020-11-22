package controllers;

import java.util.ArrayList;
import java.util.List;

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
		
		int emplId = Integer.valueOf(ctx.formParam("employeeid"));
		
		String firstName = ctx.formParam("firstname");
		
		String lastName = ctx.formParam("lastname");
		
		String userName = ctx.formParam("username");
		
		String password = ctx.formParam("password");
		
		int supId = Integer.valueOf(ctx.formParam("supervisorid"));
		
		Approver directSupervisor = employeeService.readEmployee(supId);
		
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
			
		Requestor empl = (Requestor) new Employee(emplId, firstName, lastName, userName, password, directSupervisor, event, 
					availableReimbursement, pendingReimbursement, employeeRank, isRequesting, isApproving);
		employeeService.createEmployee(empl);
		ctx.html(empl.toString());
			
		}
		if (isApproving) {
			Requestor empl = (Requestor) new Employee(emplId, firstName, lastName, userName, password, directSupervisor, event, 
					availableReimbursement, pendingReimbursement, employeeRank, isRequesting, isApproving);
			employeeService.createEmployee(empl);
			ctx.html(empl.toString());
		}
		
		
	}
	
	public void readEmployee(Context ctx) {
		System.out.println("Responding to GET read employee request");
		log.info("Controller: read an Employee");
		
		String emplId = ctx.formParam("employeeid");
		Employee empl = employeeService.readEmployee(emplId);
		ctx.html(empl.toString());
	}
	
	public void readAllEmployees(Context ctx) {
		System.out.println("Responding to GET read all employees request");
		log.info("Controller: read all employees");
		
		List<Employee> emplList = new ArrayList<>();
		emplList = employeeService.readAllEmployees();
		String str = "";
		
		for (int i = 0; i < emplList.size(); i++) {
			Employee empl = emplList.get(i);
			str += (empl.toString() + "\n");
		}
		
		ctx.html(str);
	}
	
	public void updateEmployee(Context ctx) {
		System.out.println("Responding to PUT update employee request");
		log.info("Controller: updating employee");
int emplId = Integer.valueOf(ctx.formParam("employeeid"));
		
		String firstName = ctx.formParam("firstname");
		
		String lastName = ctx.formParam("lastname");
		
		String userName = ctx.formParam("username");
		
		String password = ctx.formParam("password");
		
		int supId = Integer.valueOf(ctx.formParam("supervisorid"));
		
		Approver directSupervisor = employeeService.readEmployee(supId);
		
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
			
		Requestor empl = (Requestor) new Employee(emplId, firstName, lastName, userName, password, directSupervisor, event, 
					availableReimbursement, pendingReimbursement, employeeRank, isRequesting, isApproving);
		employeeService.updateEmployee(emplId, empl);
		ctx.html(empl.toString());
			
		}
		if (isApproving) {
			Requestor empl = (Requestor) new Employee(emplId, firstName, lastName, userName, password, directSupervisor, event, 
					availableReimbursement, pendingReimbursement, employeeRank, isRequesting, isApproving);
			employeeService.updateEmployee(emplId, empl);
			ctx.html(empl.toString());
		}
		
		
	}
	
	public void deleteEmployee(Context ctx) {
		System.out.println("Responding to DELETE delete employee request");
		log.info("Controller: deleting employee");
		
		int emplId = Integer.valueOf(ctx.formParam("employeeid"));
		employeeService.deleteEmployee(emplId);
	}

}
 
 