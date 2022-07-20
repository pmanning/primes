package primes.server;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.net.ConnectException;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SumPrimesServerTest {

	private static final int TEST_PORT = 8080;
	private static final String TEST_URL = String.format("http://127.0.0.1:%d/primes/sum/10000000", TEST_PORT);

	final SumPrimesServer server = new SumPrimesServer(TEST_PORT);

	@Before
	public void startServer() {
		server.startServer();
	}
	
	@After
	public void stopServer() {
		server.stopServer();
	}
	
	@Test(expected = ConnectException.class)
	public void testRESTCallWithoutServer() throws Throwable {
		server.stopServer();
		Client client = ClientBuilder.newClient();
		WebTarget testTarget = client.target(TEST_URL);
		Invocation.Builder invocationBuilder = testTarget.request(MediaType.TEXT_PLAIN_TYPE);

		try {
			invocationBuilder.get();
		} catch (ProcessingException e) {
			throw e.getCause();
		}
		fail("Expected Connection Exception not thrown");
	}

	@Test
	public void testRESTCall() throws Exception {
		
		Client client = ClientBuilder.newClient();
		WebTarget testTarget = client.target(TEST_URL);
		Invocation.Builder invocationBuilder = testTarget.request(MediaType.TEXT_PLAIN_TYPE);


		long result = 0L;
		try {
			Response response = invocationBuilder.get();
			String body = response.readEntity(String.class);
			if (response.getStatus() != Status.OK.getStatusCode()) {
				fail("http response was " + response.getStatus());
			}
			System.out.println("Response: " + body);
			result = Long.parseLong(body);
		} catch (NumberFormatException e) {
			fail("Response was not a number");
		}
		assertNotEquals("Result is 0", 0L, result);
		
	}

}
