package org.limit.ratelimiter.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.limit.ratelimiter.constants.RateLimiterType;
import org.limit.ratelimiter.service.TokenBucketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;


/**
 * <h1>Rate Limiter Factory</h1>
 * Rate limiter factory class return instance of {@code RateLimiter} based on rate limiter type and unique id.
 * @author  Munish
 * @version 1.0
 */

@Configuration
public class RateLimiterFactory implements ApplicationContextAware{

	private static Logger logger = LoggerFactory.getLogger(RateLimiterFactory.class);
	private static Map<String, RateLimiter> limiters = new ConcurrentHashMap<>();
	
	private static TokenBucketService tokenBucketService;


	/**
	 * Creates a {@code RateLimiter} 
	 * <p>This create a new instance for new unique-id and return the same instance for existing unique-id.
	 * @param  {@code id} unique-id.
	 * @return RateLimiter 
	 */

	public static RateLimiter getRateLimiter(String rateLimiterType,String id) {
		logger.trace("Rate limiter request for type {}, id {} ",rateLimiterType,id);
		if (limiters.containsKey(id)) {
			return limiters.get(id);
		} else {
			synchronized(id.intern()) {
				if (limiters.containsKey(id)) {
					return limiters.get(id);
				}
				RateLimiter rateLimiter = createRateLimiter(rateLimiterType,id);
				rateLimiter.init();
				limiters.put(id, rateLimiter);
				return rateLimiter;
			}
		}
	}

	/**
	 * Creates a {@code RateLimiter} instance based on {@code RateLimiterType}
	 * @param {@code RateLimiterType} RateLimitery type.
	 * @param {@code id} unique-id.
	 * @return RateLimiter 
	 */

	private static RateLimiter createRateLimiter(String rateLimiterType,String id) {
		RateLimiter rateLimiter = null;
		try {

			switch(RateLimiterType.valueOf(rateLimiterType)) {
			case TokenBucket:
				rateLimiter = new TokenBucketRateLimiter(tokenBucketService,id);
				break;
			case RedisTokenBucket:
				rateLimiter = new TokenBucketRedisRateLimiter(tokenBucketService,id);
				break;
			default:
				rateLimiter = new TokenBucketRateLimiter(tokenBucketService,id);
				break;
			}

		} catch(Exception e) {
			logger.error("Error in getting rate limiter for type {}, exception {} :: ",rateLimiterType,e.getMessage());
			rateLimiter = new TokenBucketRateLimiter(tokenBucketService,id);
		}
		return rateLimiter;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		tokenBucketService = applicationContext.getBean(TokenBucketService.class);
		
	}

}
