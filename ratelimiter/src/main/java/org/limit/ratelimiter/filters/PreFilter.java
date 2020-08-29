package org.limit.ratelimiter.filters;

import com.netflix.zuul.ZuulFilter;


public abstract class PreFilter extends ZuulFilter{

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	
	@Override
	public boolean shouldFilter() {
		return true;
	}

		
}
