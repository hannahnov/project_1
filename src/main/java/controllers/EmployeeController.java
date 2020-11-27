package controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import pojos.EmployeeRank;
import pojos.Event;
import service.EmployeeService;
import service.EmployeeServiceFullStack;
import service.EventService;
import service.EventServiceFullStack;

public class EmployeeController {
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	
	EventService eventService = new EventServiceFullStack();
	
	private static Logger log = Logger.getRootLogger();
	
	public void createEmployee(Context ctx) {
		System.out.println("Responding to post create employee request");
		
		log.info("Controller: create Employee");
		
		String emailAddress = ctx.formParam("email_address");
		
		int emplId = Integer.valueOf(ctx.formParam("employee_id"));
		
		
		
		String firstName = ctx.formParam("first_name");
		
		String lastName = ctx.formParam("last_name");
		
		int supId = Integer.valueOf(ctx.formParam("supervisor_id"));
	
		double availableReimbursement = Double.valueOf(ctx.formParam("available_reimbursement"));
		
		double pendingReimbursement = Double.valueOf(ctx.formParam("pending_reimbursement"));
		
		int departmentId = Integer.valueOf(ctx.formParam("department_id"));
		
		int rankInt = Integer.valueOf(ctx.formParam("employee_Rank"));
		
		EmployeeRank employeeRank = EmployeeRank.valueOf(rankInt);
	
	

		Employee empl = new Employee(emailAddress, departmentId, emplId, firstName, lastName, supId,
				availableReimbursement, pendingReimbursement, employeeRank);
		employeeService.createEmployee(empl);
		ctx.html(empl.toString());
			
	
			ctx.html(empl.toString());
		}
		
		
	}
	
	public void readEmployee(Context ctx) {
		System.out.println("Responding to GET read employee request");
		log.info("Controller: read an Employee");
		
		int emplId = Integer.valueOf(ctx.formParam("employe_eid"));
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
		int emplId = Integer.valueOf(ctx.formParam("employee_id"));
		
		String emailAddress = ctx.formParam("email_address");
		
		int departmentId = Integer.valueOf(ctx.formParam("department_id"));
		
		String firstName = ctx.formParam("first_name");
		
		String lastName = ctx.formParam("last_name");
				
		int supId = Integer.valueOf(ctx.formParam("supervisorid"));
	
		double availableReimbursement = Double.valueOf(ctx.formParam("available_reimbursement"));
		
		double pendingReimbursement = Double.valueOf(ctx.formParam("pending_reimbursement"));
		
		
		EmployeeRank employeeRank = EmployeeRank.valueOf((ctx.formParam("employee_rank")));
		
		Employee employee = new Employee(emailAddress, departmentId, emplId, firstName, lastName, supId,
				availableReimbursement, pendingReimbursement, employeeRank);
		
		
	}
	
	public void deleteEmployee(Context ctx) {
		System.out.println("Responding to DELETE delete employee request");
		log.info("Controller: deleting employee");
		
		int emplId = Integer.valueOf(ctx.formParam("employeeid"));
		employeeService.deleteEmployee(emplId);
	}

}
 
 