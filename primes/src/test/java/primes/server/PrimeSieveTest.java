package primes.server;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class PrimeSieveTest {
	PrimeSieve primeSieve = new PrimeSieve();

	@Test
	public void testKnownPrimes() {
		Set<Integer> result = primeSieve.getPrimes(100);
		assertTrue("Known Prime not in results", result.contains(2));
		assertTrue("Known Prime not in results", result.contains(17));
		assertTrue("Known Prime not in results", result.contains(71));
		assertTrue("Known Prime not in results", result.contains(97));
	}

	@Test
	public void testKnownNonPrimes() {
		Set<Integer> result = primeSieve.getPrimes(100);
		assertFalse("Non Prime found in results", result.contains(1));
		assertFalse("Non Prime found in results", result.contains(12));
		assertFalse("Non Prime found in results", result.contains(63));
		assertFalse("Non Prime found in results", result.contains(100));
	}

}
