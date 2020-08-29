package org.limit.ratelimiter.service;

import java.util.List;
import java.util.stream.Collectors;
import org.limit.ratelimiter.models.TokenBucket;
import org.limit.ratelimiter.repo.TokenBucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBucketService {

	@Autowired
	private TokenBucketRepository tokenBucketRepository;
		
	public List<TokenBucket> getAll() {
		return tokenBucketRepository.findAll().values().stream().collect(Collectors.toList());
	}
	public TokenBucket getTokenBucket(String id) {
		return tokenBucketRepository.findById(id);
	}
	
	public void addTokenBucket(TokenBucket tokenBucket) {
		tokenBucketRepository.save(tokenBucket);
	}
	
	public void updateTokenBucket(TokenBucket tokenBucket) {
		tokenBucketRepository.save(tokenBucket);
	}
	
	public void deleteTokenBucket(String id) {
		tokenBucketRepository.deleteById(id);
	}
	
}
