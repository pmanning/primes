package primes.server;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleRESTServerTest {

	private static SimpleRESTServer server;

	@BeforeClass
	public static void startServer() throws Exception {
		server = new SimpleRESTServer();
		Thread.sleep(5000); // Quick hack to make sure server is fully started
	}

	@AfterClass
	public static void stopServer() throws Exception {
		server.shutDown();
	}

	@Test
	public void testRESTCall() throws URISyntaxException, IOException, InterruptedException {
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest sumRequest = HttpRequest.newBuilder().uri(new URI("http://127.0.0.1:8080/primes/sum/10000000"))
				.GET().build();
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
