package org.limit.ratelimiter.models;

import java.io.Serializable;

public class TokenBucket implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private int maxRequest = 10;
	private String timeunit = "seconds";
	private int tokens;
	private long lastRefillTime;
	
	
	public TokenBucket(String id, int maxRequest,int tokens,long lastRefillTime,String timeunit) {
		super();
		this.id = id;
		this.maxRequest = maxRequest;
		this.tokens = tokens;
		this.lastRefillTime = lastRefillTime;
		this.timeunit = timeunit;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMaxRequest() {
		return maxRequest;
	}
	public void setMaxRequest(int maxRequest) {
		this.maxRequest = maxRequest;
	}
	public int getTokens() {
		return tokens;
	}
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}
	public long getLastRefillTime() {
		return lastRefillTime;
	}
	public void setLastRefillTime(long lastRefillTime) {
		this.lastRefillTime = lastRefillTime;
	}

	public String getTimeunit() {
		return timeunit;
	}

	public void setTimeunit(String timeunit) {
		this.timeunit = timeunit;
	}

	@Override
	public String toString() {
		return "TokenBucket [id=" + id + ", maxRequest=" + maxRequest + ", timeunit=" + timeunit + ", tokens=" + tokens
				+ ", lastRefillTime=" + lastRefillTime + "]";
	}
	
	
		
}