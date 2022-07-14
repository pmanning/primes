package primes.server;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

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
	public void testRESTCallWithoutServer() throws Exception {
		server.stopServer();
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest sumRequest = HttpRequest.newBuilder().uri(new URI(TEST_URL)).GET().build();
		httpClient.send(sumRequest, BodyHandlers.ofString());
		fail("Expected Connection Exception not thrown");
	}

	@Test
	public void testRESTCall() throws Exception {
		
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest sumRequest = HttpRequest.newBuilder().uri(new URI(TEST_URL)).GET().build();
		HttpResponse<String> sumResponse = httpClient.send(sumRequest, BodyHandlers.ofString());
		long result = 0L;
		try {
			System.out.println("Response: " + sumResponse.body());
			result = Long.parseLong(sumResponse.body());
		} catch (NumberFormatException e) {
			fail("Response was not a number");
		}
		assertNotEquals("Result is 0", 0L, result);
		
	}

}
