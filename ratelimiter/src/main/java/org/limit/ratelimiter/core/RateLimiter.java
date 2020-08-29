package org.limit.ratelimiter.core;


/**
 * <h1>Rate Limiter Abstract Class</h1>
 * This is an abstract {@code RateLimiter} class. 
 * @author  Munish
 * @version 1.0
 */
public abstract class RateLimiter {

  /**
   * max request that can be allowed in a specific TimeUnit.
   */
  protected  int maxRequest;
  /**
   * unique id to define rate limit at user and resource level.
   * concatenation of user id and request-id.
   */
  protected  String id;
  /**
   * timeunit to specify max request per sec or minute or hour.
   */
  protected  String timeunit;
  
  /**
   * Creates Rate Limiter with specified {@code id}
   * @param {@code id}
   * @return RateLimiter 
   */
  protected RateLimiter(String id) {
	  this.id = id;
	 
  }
  
  /**
   * Creates Rate Limiter with specified {@code id} {@code maxRequest} {@code timeunit}
   * @param {@code id} unique id.
   * @param {@code maxRequest} max request that can be allowed.
   * @param {@code timeunit} timeunit to specify max request per sec or minute or hour.
   * @return RateLimiter 
   */
  protected RateLimiter(String id,int maxRequest,String timeunit) {
	  this.maxRequest = maxRequest;
	  this.timeunit = timeunit;
	  this.id = id;
  }
 
  public abstract boolean allow();
  public abstract void init();
}
