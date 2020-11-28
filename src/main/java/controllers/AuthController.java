package controllers;

import java.util.ArrayList;
import java.util.List;

import io.javalin.http.Context;
import pojos.Employee;
import service.AuthService;
import service.AuthServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceFullStack;

public class AuthController {
	private EmployeeService employeeService = new EmployeeServiceFullStack();
	private AuthService auth = new AuthServiceImpl();
	
	public void login(Context ctx) {
		String username = ctx.formParam("user_name");
		
		String password = ctx.formParam("password");
		
		int employeeId = Integer.valueOf(ctx.formParam("employee_id"));
		
		boolean authenticated = auth.authenticateUser(username, password);
		if(authenticated) {
			List<Employee> employeeList = new ArrayList<>();
			ctx.status(200);
			ctx.cookieStore("security", auth.createToken(username));
			Employee empl = employeeService.readEmployee(employeeId);
			for (int i = 0; i < employeeList.size(); i++) {
				if (employeeList.get(i).getDirectSupervisorId() == employeeId) {
					ctx.redirect("supervisor_page.html");
					break;
				}
			}
			if (empl.getEmployeeRank().getValue() == 1) {
			ctx.redirect("deparment_head.html");
			}
			if (empl.getEmployeeRank().getValue() == 2) {
				ctx.redirect("benco_page.html");
			}
			else {
				ctx.redirect("underling_page.html");
			}
			

		} else {
			ctx.status(401);
			ctx.redirect("login.html?error=failed-login");
		}
		
	}
	
	public void checkUser(Context ctx) {
		ctx.html(auth.validateToken(ctx.cookieStore("security")));
	}
	

}
