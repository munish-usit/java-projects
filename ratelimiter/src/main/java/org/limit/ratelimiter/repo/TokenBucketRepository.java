package org.limit.ratelimiter.repo;
import java.util.Map;

import org.limit.ratelimiter.models.TokenBucket;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenBucketRepository {

	public static final String KEY = "buckets";
    private HashOperations hashOperations; 
    
    public TokenBucketRepository(RedisTemplate<String, TokenBucket> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }
    
    public void save(TokenBucket tokenBucket) {
        hashOperations.put(KEY,tokenBucket.getId(),tokenBucket);
    }
    public Map<String,TokenBucket> findAll() {
        return hashOperations.entries(KEY);
    }
    public TokenBucket findById(String id) {
        return (TokenBucket)hashOperations.get(KEY,id);
    }
    public void update(TokenBucket tokenBucket) {
      save(tokenBucket);
    }
    public void deleteById(String id) {
       hashOperations.delete(KEY,id);
    }
}
