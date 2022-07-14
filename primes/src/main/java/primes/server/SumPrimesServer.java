package primes.server;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class SumPrimesServer {

	private JettyHTTPServer httpServer;

	public SumPrimesServer(int port) {
		JerseyRESTService restService = new JerseyRESTService();

		ServletContextHandler contextHandler = new ServletContextHandler();
		ServletHolder servletHolder = new ServletHolder(new ServletContainer(restService));
		contextHandler.addServlet(servletHolder, "/*");

		//Start an embedded Jetty server with our Jersey REST Service as the context handler
		httpServer = new JettyHTTPServer(port, contextHandler);
	}
	
	public void startServer() {
		try {
			httpServer.start();
		} catch (Exception e) {
			System.out.println("Exception thrown while trying to start SumPrimesServer - terminating");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void stopServer() {
		httpServer.stop();
	}
	
	public static void main(String[] args) {
		SumPrimesServer sumPrimesServer = new SumPrimesServer(8080);
		sumPrimesServer.startServer();
	}
}
