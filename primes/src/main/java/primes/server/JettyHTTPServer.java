package primes.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;

public class JettyHTTPServer {

	private Server server;

	public JettyHTTPServer(int port, Handler contextHandler) {
		
		server = new Server(port);
		server.setHandler(contextHandler);
		
	}
	
	public void start() throws Exception {
		server.start();
	}
	
	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			System.out.println("Exception thrown while trying to stop JettyHTTPServer");
			e.printStackTrace();
		}
	}
}
