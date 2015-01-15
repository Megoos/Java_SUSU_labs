package lab5_servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Server server = new Server();
		String host = "127.0.0.1";
		int port = 8080;
		
		ServerConnector connector = new ServerConnector(server);
	    
		connector.setHost(host);
	    connector.setPort(port);
	    
	    connector.setName("main");
	    server.addConnector(connector);  
	    
	    ServletHandler servhandler = new ServletHandler();
	    servhandler.addServletWithMapping(Servlet.class, "/gettime");
	    server.setHandler(servhandler);

	    server.start();
	    server.join();
	}

}