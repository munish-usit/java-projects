package org.limit.ratelimiter;


import org.limit.ratelimiter.filters.RateLimiterFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


/**
 * <h1>RatelimiterApplication</h1>
 * <p>This class is the entry or starting point to run the application.
 * @author  Munish
 * @version 1.0
 */


@SpringBootApplication
@EnableZuulProxy
public class RatelimiterApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(RatelimiterApplication.class, args);
	}

	/**
	 * {@code RateLimiterFilter} bean.
	 * <p>Register {@code RateLimiterFilter} implementation in the spring boot application.
	 * @return {@code RateLimiterFilter}
	 */
	@Bean
	public RateLimiterFilter rateLimiterFilter() {
		return new RateLimiterFilter();
	}

}
