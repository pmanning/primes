package primes.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/primes")
public interface PrimeService {

	@GET
	@Path("/sum/{upperLimit}")
	public Long sumPrimes(@PathParam("upperLimit") Integer upperLimit);
}