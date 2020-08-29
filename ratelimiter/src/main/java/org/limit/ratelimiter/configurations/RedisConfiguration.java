package org.limit.ratelimiter.configurations;

import org.limit.ratelimiter.models.TokenBucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <h1>Redis Configuration Class</h1>
 * This class is responsible for setting the redis configuration and connection to redis server.
 * @author  Munish
 * @version 1.0
 */
@Configuration
public class RedisConfiguration {
	
	@Value("${spring.redis.host}")
    private String host;
	
	@Value("${spring.redis.port}")
    private int port;

	@Value("${spring.redis.password}")
    private String password;

	

	/**
	 * Creates a {@code JedisConnectionFactory} with the specified redis configuration settings.
	 * <p>The returned {@code JedisConnectionFactory} is used by {@code RedisTemplate} to connect to the redis server.
	 * @return JedisConnectionFactory 
	 */
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName(host);
		configuration.setPort(port);
		if(password != null && !password.isEmpty())configuration.setPassword(password);
		return new JedisConnectionFactory(configuration);
	}

	/**
	 * Creates a {@code RedisTemplate} for redis data access.
	 * <p>The returned {@code JedisConnectionFactory} is used by {@code RedisTemplate} to connect to the redis server.
	 * <p>The returned {@code RateLimiter} is used for redis data access for model {@code TokenBucket}.
	 * @return RedisTemplate 
	 */
	@Bean
	public RedisTemplate<String, TokenBucket> redisTemplate() {
		final RedisTemplate<String, TokenBucket> template = new RedisTemplate<String, TokenBucket>();
		template.setConnectionFactory(redisConnectionFactory());
		return template;
	}
}
