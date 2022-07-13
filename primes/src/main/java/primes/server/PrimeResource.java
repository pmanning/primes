package primes.server;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/primes")
public class PrimeResource {
	
	@GET
	@Path("/sum/{upperLimit}")
	public Long sumPrimes(@PathParam("upperLimit") Integer upperLimit) {
		PrimeSieve primeSieve = new PrimeSieve();
		Set<Integer> primes = primeSieve.getPrimes(upperLimit);
		Long sum = 0L;
		for (int prime: primes) {
			sum += prime;
		}
		return sum;
	}

}