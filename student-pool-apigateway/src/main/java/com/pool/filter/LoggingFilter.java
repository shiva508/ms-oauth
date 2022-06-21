package com.pool.filter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
public class LoggingFilter implements GlobalFilter {
	private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		List<String> shivaHeader = headers.get("Shiva");
		if(null !=shivaHeader && !shivaHeader.isEmpty()) {
			logger.info("current request header is --------->{}", shivaHeader);
		}
		
		logger.info("current request url is --------->{}", exchange.getRequest().getPath());
		return chain.filter(exchange);
	}

}
