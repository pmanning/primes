package primes.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class SimpleRESTServer extends ResourceConfig {

	private Server server;

	public SimpleRESTServer() throws Exception {
		
        register(PrimeResource.class);

        ServletContextHandler contextHandler = new ServletContextHandler();
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(this));
        contextHandler.addServlet(servletHolder, "/*");

        Thread serverThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
		        server = new Server(8080);
		        server.setHandler(contextHandler);
		        try {
					server.start();
			        server.join();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
        serverThread.start();
        
	}

	public void shutDown() throws Exception {
		server.stop();
		
	}

    public static void main(String[] args) throws Exception {
        
    	new SimpleRESTServer();
    }


}
