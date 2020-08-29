package org.limit.ratelimiter.core;


import org.limit.ratelimiter.models.TokenBucket;
import org.limit.ratelimiter.service.TokenBucketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <h1>Token Bucket Redis Rate Limiter</h1>
 * Token bucket algorithm implementation.
 * <p>Token bucket is initialized with {@code maxRequest} tokens.
 * When a request comes, a token has to be taken from the bucket for it to be processed. 
 * If there is no token available in the bucket, the request will be rejected and the requester has to retry later. 
 * The token bucket is also refilled per time unit.
 * <p>In this approach the information is read and write from global in-memory redis database.
 * @author  Munish
 * @version 1.0
 */

public class TokenBucketRedisRateLimiter extends RateLimiter {

	private static Logger logger = LoggerFactory.getLogger(TokenBucketRedisRateLimiter.class);
	
	private TokenBucketService tokenBucketService;
	
	
	/**
	 * Initialize the token bucket with {@code tokens} = {@code maxRequest}
	 * Set the {@code lastRefillTime} to the current time.
	 * Save the updated token bucket in redis.
	 * @param {@code id} unique-id
	 */

	public TokenBucketRedisRateLimiter(TokenBucketService tokenBucketService,String id) {
		super(id);
		this.tokenBucketService = tokenBucketService;
		
	}
	
	@Override
	public void init() {
	    logger.info("rate limit updating bucket parameters");
	    TokenBucket tokenBucket = tokenBucketService.getTokenBucket(id);
	    logger.trace("rate limit token bucket {}",tokenBucket.toString());
	    this.maxRequest = tokenBucket.getMaxRequest();
	    this.timeunit = tokenBucket.getTimeunit();
	    tokenBucket.setTokens(maxRequest);
	    tokenBucket.setLastRefillTime(System.currentTimeMillis());
		tokenBucketService.updateTokenBucket(tokenBucket);
		logger.info("rate limit created for userid {} with limit {}",id,maxRequest);
	}


	/**
	 * Throttling check.
	 * <p>Acquires a token from this {@code RateLimiter} bucket, if it can be obtained without exceeding the
	 * specified {@code timeout}, or returns {@code false} immediately if the current number of tokens exceed the {@code maxRequest}
	 * and the time has still not expired
	 * Get and Set token bucket from the redis database.
	 * Update the token bucket with latest {@code tokens} and {@code lastRefillTime} into redis instance.
	 * @return {@code true} if the request was acquired, {@code false} otherwise
	 */
	@Override
	public boolean allow() {
		synchronized (this) {
			TokenBucket tokenBucket = tokenBucketService.getTokenBucket(id);
			logger.trace("allow token bucket {}",tokenBucket.toString());
			tokenBucket = refillBucketTokens(tokenBucket);
			logger.trace("after refill token bucket {}",tokenBucket.toString());
			int tokens = tokenBucket.getTokens();
			if (tokens == 0) {
				logger.info("rate limit context {}-{} request allow false with tokens {} ",id,maxRequest,tokens);
				return false;
			}
			tokens--;
			tokenBucket.setTokens(tokens);
			tokenBucketService.updateTokenBucket(tokenBucket);
			logger.info("rate limit context {}-{} request allow true with tokens left {} ",id,maxRequest,tokens);
			return true;
		}
	}

	/**
	 * Refill tokens {@code tokens} at the specific time rate and also update the {@code lastRefillTime} time.
	 * Get and Set token bucket from the redis database.
	 * Update the token bucket with latest {@code tokens} and {@code lastRefillTime} into redis instance.
	 */
	private TokenBucket refillBucketTokens(TokenBucket tokenBucket) {
		long currentTime = System.currentTimeMillis();
		long lastRefillTime = tokenBucket.getLastRefillTime();
		int tokens = tokenBucket.getTokens();
		double duration = (currentTime - lastRefillTime) / 1000.0;
		if(timeunit != null && timeunit.equalsIgnoreCase("minutes")) {
			duration = duration/60;
		}
		if(duration <1) {
			logger.info("rate limit context {}-{} refill tokens {}, duration {} not exceeded",id,maxRequest,tokens,duration);
			return tokenBucket;
		}
		int count = (int) (duration * maxRequest);
		logger.info("rate limit context {}-{} refill tokens {}, curtime {}, duration {}, count {}",id,maxRequest,tokens,currentTime,duration,count);
		if (count > 0) {
			tokens = Math.min(tokens + count, maxRequest);
			lastRefillTime = currentTime;
			tokenBucket.setTokens(tokens);
			tokenBucket.setLastRefillTime(lastRefillTime);
			logger.info("rate limit context {}-{} refill tokens updated tokens {}, lastRefillTime {} ",id,maxRequest,tokens,lastRefillTime);
		}
		return tokenBucket;
	}
}
