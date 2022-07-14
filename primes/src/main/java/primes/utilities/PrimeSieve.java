package primes.utilities;

import java.util.HashSet;
import java.util.Set;

/**
 * Finds primes using the 'Sieve of Eratosthenes' method see: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 * One of the most efficient methods to find primes up to an upper limit, but has Space complexity O(n) so may not 
 * be suitable for very large values of 'upper limit'
 */
public class PrimeSieve {

	public Set<Integer> getPrimes(int upperLimit) {

		//Basic idea is make array of all numbers, then step through.
		//For each known prime encountered, cross-out all multiples of that prime (as multiples of prime cannot be prime).
		//Then all not crossed-out values still remaining at the end will be prime.
		
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
				// Mark all multiples of this prime to be not prime
				for (int j = i * i; j <= upperLimit; j += i) {  //optimisation: can start loop at i*i as all smaller multiples will have already been dealt with
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
