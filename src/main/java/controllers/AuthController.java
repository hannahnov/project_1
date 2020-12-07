package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.javalin.http.Context;
import pojos.Employee;
import service.AuthService;
import service.AuthServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceFullStack;

public class AuthController {
	public static Map<String, Integer> loginMap = new HashMap<>();
	private EmployeeService employeeService = new EmployeeServiceFullStack();
	private AuthService auth = new AuthServiceImpl();
	
	public void login(Context ctx) {
		String username = ctx.formParam("user_name");
		
		String password = ctx.formParam("password");
		
		System.out.println("username is " + username + " and password is " + password);
		
		int employeeId = Integer.valueOf(ctx.formParam("employee_id"));
		
		if (username == null || password == null) {
			ctx.status(401);
			ctx.redirect("login.html?error=failed-login");
		}
		
		int authenticated = auth.authenticateUser(username, password);
		if(authenticated != -1) {
			List<Employee> employeeList = employeeService.readAllEmployees();
			ctx.status(200);
			
			

			//ctx.cookieStore("security", auth.createToken(authenticated));
			loginMap.put(ctx.cookieStore("funcookieId123"), authenticated);
			Employee empl = employeeService.readEmployee(employeeId);
			
			
			
			System.out.println(empl.getFirstName() + "'s rank is " + empl.getEmployeeRank().getValue());
			
			
			if (empl.getEmployeeRank().getValue() == 1) {
			ctx.redirect("http://127.0.0.1:5500/department_head.html");
			}
			else if (empl.getEmployeeRank().getValue() == 2) {
				ctx.redirect("http://127.0.0.1:5500/benco_page.html");
			}
			else {
			ctx.redirect("http://127.0.0.1:5500/underling_page.html");
			}
			for (int i = 0; i < employeeList.size(); i++) {
				System.out.println(employeeList.get(i).getDirectSupervisorId());
				if (employeeList.get(i).getDirectSupervisorId() == employeeId) {
					System.out.println("redirecting to supervisor page");
					ctx.redirect("http://127.0.0.1:5500/supervisor_page.html");
					break;
				}
			}

		} else {
			ctx.status(401);
			ctx.redirect("http://127.0.0.1:5500/incorrect_login_info.html");
		}
		
	}
	
	public void checkUser(Context ctx) {
		ctx.html(auth.validateToken(ctx.cookieStore("security")));
	}
	
	public void logout(Context ctx) {	
			ctx.clearCookieStore();
			ctx.redirect( "index.html");
		}

	public void redirectHomePage(Context ctx) {
		int employeeId = AuthController.loginMap.get(ctx.cookieStore("funcookieId123"));
		List<Employee> employeeList = employeeService.readAllEmployees();
		Employee empl = employeeService.readEmployee(employeeId);

		for (int i = 0; i < employeeList.size(); i++) {
			System.out.println(employeeList.get(i).getDirectSupervisorId());
			if (employeeList.get(i).getDirectSupervisorId() == employeeId) {
				ctx.redirect("http://127.0.0.1:5500/supervisor_page.html");
				break;
			}
		}
		if (empl.getEmployeeRank().getValue() == 1) {
		ctx.redirect("http://127.0.0.1:5500/department_head.html");
		}
		else if (empl.getEmployeeRank().getValue() == 2) {
			ctx.redirect("http://127.0.0.1:5500/benco_page.html");
		}
		else {
		ctx.redirect("http://127.0.0.1:5500/underling_page.html");
		}
		
		
	}

}


