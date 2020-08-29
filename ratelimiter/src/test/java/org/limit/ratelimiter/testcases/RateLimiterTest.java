package org.limit.ratelimiter.testcases;


import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.limit.ratelimiter.core.RateLimiter;
import org.limit.ratelimiter.core.TokenBucketRateLimiter;


public class RateLimiterTest {

	private RateLimiter  rateLimiter;

	@Before
	public void setUp() {
		
	}

	@Test
	public void testTokenBucketRateLimiter() {
		rateLimiter = new TokenBucketRateLimiter("user1-developer", 10, "seconds");
		int totalCount = 200;
		int requestedTps = 20;
		long startTime = System.currentTimeMillis();
		CountDownLatch doneSignal = new CountDownLatch(totalCount);
		for (int i = 0; i < totalCount; i++) {

			try {
				new Thread(() -> {
					while (!rateLimiter.allow()) {
						try {

							TimeUnit.MILLISECONDS.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					doneSignal.countDown();
				}).start();
				TimeUnit.MILLISECONDS.sleep(1000 / requestedTps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double duration = (System.currentTimeMillis() - startTime) / 1000.0;
		double actualTps = (double)totalCount/duration;
		System.out.println("total count "+totalCount+" requestedTps "+requestedTps+" duration "+duration+" actual tps "+actualTps);
		assertTrue(actualTps < (double)requestedTps);

	}
	
	
	public void testTokenBucketRateLimiterMinutes() {
		rateLimiter = new TokenBucketRateLimiter("user1-developer", 2, "minutes");
		int totalCount = 10;
		int requestedTps = 10;
		long startTime = System.currentTimeMillis();
		CountDownLatch doneSignal = new CountDownLatch(totalCount);
		for (int i = 0; i < totalCount; i++) {

			try {
				new Thread(() -> {
					while (!rateLimiter.allow()) {
						try {

							TimeUnit.SECONDS.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					doneSignal.countDown();
				}).start();
				TimeUnit.SECONDS.sleep(60/requestedTps);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double duration = (System.currentTimeMillis() - startTime) / 1000.0;
		double actualTps = (double)totalCount/duration;
		System.out.println("total count "+totalCount+" requestedTps "+requestedTps+" duration "+duration+" actual tps "+actualTps);
		assertTrue(actualTps < (double)requestedTps);

	}



}

