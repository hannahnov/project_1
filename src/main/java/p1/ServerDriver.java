package p1;

public class ServerDriver {
	//create Controller instances here
	
	public static void main(String[] args) {
		//set up and start embedded jetty server
		Javalin app = Javalin.create().start(9096);
		
		//put CRUD end points here
	}
}
