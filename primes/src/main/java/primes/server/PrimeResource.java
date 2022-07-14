package primes.server;

import java.util.Set;

import primes.api.PrimeService;
import primes.utilities.PrimeSieve;

public class PrimeResource implements PrimeService {

	public Long sumPrimes(Integer upperLimit) {
		PrimeSieve primeSieve = new PrimeSieve();
		Set<Integer> primes = primeSieve.getPrimes(upperLimit);
		// Get all primes up to specified limit and sum them for return value
		Long sum = 0L;
		for (int prime : primes) {
			sum += prime;
		}
		return sum;
	}

}