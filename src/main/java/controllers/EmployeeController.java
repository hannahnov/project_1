package controllers;

import org.apache.log4j.Logger;

import io.javalin.http.Context;
import service.EmployeeService;
import service.EmployeeServiceFullStack;

public class EmployeeController {
	
	EmployeeService employeeService = new EmployeeServiceFullStack();
	
	private static java.util.logging.Logger log = Logger.getRootLogger();
	
	public void createEmployee(Context ctx) {
		System.out.println("Responding to post create employee request");
		
		log.info("Controller: create Employee");
		
		
	}

}
