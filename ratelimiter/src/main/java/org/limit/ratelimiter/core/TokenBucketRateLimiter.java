package org.limit.ratelimiter.core;

import org.limit.ratelimiter.models.TokenBucket;
import org.limit.ratelimiter.service.TokenBucketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <h1>Token Bucket Rate Limiter</h1>
 * Token bucket algorithm implementation.
 * <p>Token bucket is initialized with {@code maxRequest} tokens.
 * When a request comes, a token has to be taken from the bucket for it to be processed. 
 * If there is no token available in the bucket, the request will be rejected and the requester has to retry later. 
 * The token bucket is also refilled per time unit.
 * <p>In this way, we can limit the requests per user per time unit by assigning a bucket with fixed amount of tokens to each user. 
 * When a user uses up all the tokens in a time period, we know that he has exceeded the limit and reject his requests until his bucket is refilled.
 * @author  Munish
 * @version 1.0
 */

public class TokenBucketRateLimiter extends RateLimiter {

	private static Logger logger = LoggerFactory.getLogger(TokenBucketRateLimiter.class);

	/**
	 * current number tokens per user and per request
	 */
	private int tokens;
	/**
	 * last refill time
	 */
	private long lastRefillTime;
	
	private TokenBucketService tokenBucketService;
	
	
	
	/**
	 * <p>Initialize the token bucket with {@code tokens} = {@code maxRequest}
	 * <p>Set the {@code lastRefillTime} to the current time.
	 * @param {@code id} unique-id
	 */
	public TokenBucketRateLimiter(TokenBucketService tokenBucketService,String id) {
		super(id);
		this.tokenBucketService = tokenBucketService;
		
	}
	
	public TokenBucketRateLimiter(String id,int maxRequest,String timeunit) {
		  super(id,maxRequest,timeunit);
	}
	
	
	
	@Override
	public void init() {
	    logger.info("rate limit updating bucket parameters");
	    TokenBucket tokenBucket = tokenBucketService.getTokenBucket(id);
	    logger.trace("rate limit token bucket {}",tokenBucket.toString());
	    this.maxRequest = tokenBucket.getMaxRequest();
	    this.timeunit = tokenBucket.getTimeunit();
	    this.tokens = maxRequest;
		this.lastRefillTime = System.currentTimeMillis();
		logger.info("rate limit created for userid {} with limit {}",id,maxRequest);
	}


	/**
	 * Throttling check.
	 * <p>Acquires a token from this {@code RateLimiter} bucket, if it can be obtained without exceeding the
	 * specified {@code timeout}, or returns {@code false} immediately if the current number of tokens exceed the {@code maxRequest}
	 * and the time has still not expired
	 *
	 * @return {@code true} if the request was acquired, {@code false} otherwise
	 */
	@Override
	public boolean allow() {
		synchronized (this) {
			refillBucketTokens();
			if (tokens == 0) {
				logger.info("rate limit context {}-{} request allow false with tokens {} ",id,maxRequest,tokens);
				return false;
			}
			tokens--;
			logger.info("rate limit context {}-{} request allow true with tokens left {} ",id,maxRequest,tokens);
			return true;
		}
	}

	/**
	 * Refill tokens {@code tokens} at the specific time rate and also update the {@code lastRefillTime} time.
	 *
	 */
	private void refillBucketTokens() {
		long currentTime = System.currentTimeMillis();
		double duration = (currentTime - lastRefillTime) / 1000.0;
		if(timeunit != null && timeunit.equalsIgnoreCase("minutes")) {
			duration = duration/60;
		}
		if(duration <1) {
			logger.info("rate limit context {}-{} refill tokens {}, duration {} not exceeded",id,maxRequest,tokens,duration);
			return;
		}
		int count = (int) (duration * maxRequest);
		logger.info("rate limit context {}-{} refill tokens {}, curtime {}, duration {}, count {}",id,maxRequest,tokens,currentTime,duration,count);
		if (count > 0) {
			tokens = Math.min(tokens + count, maxRequest);
			lastRefillTime = currentTime;
			logger.info("rate limit context {}-{} refill tokens updated tokens {}, lastRefillTime {} ",id,maxRequest,tokens,lastRefillTime);
		}
	}
}
