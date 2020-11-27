package controllers;

import io.javalin.http.Context;
import service.AuthService;
import service.AuthServiceHardCoded;

public class AuthController {
	
	private AuthService auth = new AuthServiceHardCoded();
	
	public void login(Context ctx) {
		String username = ctx.formParam("user_name");
		
		String password = ctx.formParam("password");
		
		boolean authenticated = auth.authenticateUser(username, password);
		if(authenticated) {
			ctx.status(200);
			ctx.cookieStore("security", auth.createToken(username));
			//TODO put html page here:
			ctx.redirect("");
		} else {
			ctx.status(401);
			ctx.redirect("login.html?error=failed-login");
		}
		
	}
	
	public void checkUser(Context ctx) {
		ctx.html(auth.validateToken(ctx.cookieStore("security")));
	}
	

}
