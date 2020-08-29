package org.limit.ratelimiter.filters;

import javax.servlet.http.HttpServletRequest;
import org.limit.ratelimiter.constants.Messages;
import org.limit.ratelimiter.core.RateLimiter;
import org.limit.ratelimiter.core.RateLimiterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * <h1>Rate Limiter Filter</h1>
 * <p>This class intercept all the requests and apply rate limiting algorithm.
 * Based on the rate limit, the request is rejected or allowed.
 * @author  Munish
 * @version 1.0
 */
public class RateLimiterFilter extends PreFilter {

	private static Logger logger = LoggerFactory.getLogger(RateLimiterFilter.class);
	
	/**
	 * rate limiter type specified in the applicaiton.properties file
	 */
	@Value("${rate.limit.type}")
	private String rateLimitType;
	
	/**
	 * disable rate limiter type
	 */
	@Value("${rate.limit.disabled}")
    private boolean disable;

	/**
	 * Override run method to intercept request.
	 * <p> It perform basic validation against {@code disable} variable. If rate limit is disabled then, it simply forward the request without checking throttling.
	 * <p> If user-id or request-id is not present in the request header, then rate limit is not checked.
	 * <p>Based on the rate limit, the request is rejected or allowed.
	 * @return Object
	 * 
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info("request url  {} ",request.getRequestURL().toString());
		logger.trace("disable rate limit {}",disable);
		logger.trace("rate limit type {}",rateLimitType);
		if(disable) {
			logger.info("rate limit filter disabled so forwarding the request");
			return null;
		}
		String userId = request.getHeader("user-id");
		logger.trace("user-id {}",userId);
		if(userId == null || userId.isEmpty()) {
			logger.info("user-id not available so forwarding the request");
			return null;
		}
		String apiId = request.getHeader("request-id");
		logger.trace("apiUrl {}",apiId);
		if(apiId == null || apiId.isEmpty()) {
			logger.info("api-url not available so forwarding the request");
			return null;
		}
		String id = userId+"-"+apiId;
		RateLimiter rateLimiter = RateLimiterFactory.getRateLimiter(rateLimitType,id);
		boolean allowRequest = rateLimiter.allow();
		
		if(!allowRequest){
			logger.info("rate limit exceeded so rejecting the request");
            ctx.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
            ctx.setResponseBody(Messages.RATE_LIMIT_EXCEED);
            ctx.setSendZuulResponse(false);

        }
		else logger.info("rate limiter allowed the request");
		return null;
	}
	
	

}
