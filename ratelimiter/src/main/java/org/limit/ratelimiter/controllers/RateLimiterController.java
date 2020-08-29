package org.limit.ratelimiter.controllers;
import java.util.List;

import org.limit.ratelimiter.models.TokenBucket;
import org.limit.ratelimiter.service.TokenBucketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>Rate Limit controller</h1>
 * This class exposed multiple reset url to the outer world to set the rate limiting configuration per user and resource level.
 * @author  Munish
 * @version 1.0
 */

@RestController
@RequestMapping("ratelimit")
public class RateLimiterController {

	private static final Logger logger = LoggerFactory.getLogger(RateLimiterController.class);

	/**
	 * Token Bucket service to interact with the Token Bucket Repository. 
	 */
	@Autowired
	private TokenBucketService tokenBucketService;

	/**
	 * Get all token buckets.
	 * <p>The endpoint return the list of all the token buckets.
	 * <pre>
	 * [
    	{
        "id": "client-1",
        "maxRequest": 10,
        "tokens": 0,
        "lastRefillTime": 0
    	},
    	{
        "id": "client-2",
        "maxRequest": 10,
        "tokens": 0,
        "lastRefillTime": 0
    	}
	 ]
	 * </pre>
	 * @return {@code List<TokenBucket>} list of all token buckets stored in redis.
	 */
	@RequestMapping("/tokenbucket")
	public List<TokenBucket> getTokenBuckets() {
		logger.trace("rate limit get token buckets request");
		return tokenBucketService.getAll();
	}

	/**
	 * Get specific token bucket.
	 * <p>The endpoint return the list of all the token buckets with {@code id}.
	 * <pre>
	 * 
    	{
        "id": "client-1",
        "maxRequest": 10,
        "tokens": 0,
        "lastRefillTime": 0
    	}
	 * </pre>
	 * @param {@code id} unique-id
	 * @return {@code TokenBucket} with {@code id}.
	 */
	@RequestMapping("/tokenbucket/{id}")
	public TokenBucket getTokenBucket(@PathVariable("id") String id) {
		logger.trace("rate limit get token bucket request for id {}",id);
		return tokenBucketService.getTokenBucket(id);
	}

	/**
	 * Save token bucket.
	 * <p>The endpoint is used to save the token bucket configuration in redis.
	 * <pre>
	 * 
    	{
        "id": "client-1",
        "maxRequest": 10,
        "tokens": 0,
        "lastRefillTime": 0
    	}
	 * </pre>
	 * @param {@code TokenBucket}
	 */
	@RequestMapping(method=RequestMethod.POST,value="/tokenbucket")
	public void addTokenBucket(@RequestBody TokenBucket tokenBucket) {
		logger.trace("rate limit post token buckets request for bucket {} ",tokenBucket);
		tokenBucketService.addTokenBucket(tokenBucket);
	}
	
	/**
	 * Update token bucket.
	 * <p>The endpoint is used to update the token bucket configuration in redis.
	 * <pre>
	 * 
    	{
        "id": "client-1",
        "maxRequest": 10,
        "tokens": 0,
        "lastRefillTime": 0
    	}
	 * </pre>
	 * @param {@code TokenBucket}
	 */
	@RequestMapping(method=RequestMethod.PUT,value="/tokenbucket")
	public void updateTokenBucket(@RequestBody TokenBucket tokenBucket) {
		logger.trace("rate limit update token buckets request for bucket {} ",tokenBucket);
		tokenBucketService.updateTokenBucket(tokenBucket);
	}
	
	/**
	 * Delete specific token bucket.
	 * @param {@code id} unique-id
	 * 
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="/tokenbucket/{id}")
	public void deleteTokenBucket(@PathVariable("id") String id) {
		logger.trace("rate limit delete token bucket request for id {}",id);
		tokenBucketService.deleteTokenBucket(id);
	}

	
	@RequestMapping(value="/ping")
	public String ping() {
		return "ping";
	}
}

