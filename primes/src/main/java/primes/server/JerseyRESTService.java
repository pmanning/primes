package primes.server;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyRESTService extends ResourceConfig {

	public JerseyRESTService() {
		register(PrimeResource.class);
	}
}
