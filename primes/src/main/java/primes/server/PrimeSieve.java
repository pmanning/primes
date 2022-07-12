package primes.server;

import java.util.HashSet;
import java.util.Set;

public class PrimeSieve {

	public Set<Integer> getPrimes(int upperLimit) {
		
		boolean[] isPrime = new boolean[upperLimit + 1];
		
		// 0 and 1 are not prime by definition.
		isPrime[0] = false;
		isPrime[1] = false;

		for (int i = 2; i <= upperLimit; i++) {
			// Assume all other numbers are prime until shown not to be.
			isPrime[i] = true;
		}

		for (int i = 0; i < Math.sqrt(upperLimit); i++) {
			if (isPrime[i]) {
				//Mark all multiples of this prime to be not prime
				for (int j = i*i; j <= upperLimit; j += i) {
					isPrime[j] = false;
				}
			}
		}

		Set<Integer> primes = new HashSet<Integer>();
		
		for (int i = 0; i <= upperLimit; i++) {
			if (isPrime[i]) {
				primes.add(i);
			}
		}
		
		return primes;
	}
}
