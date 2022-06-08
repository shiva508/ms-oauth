package com.pool.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class EpoolHeaderFilter extends AbstractGatewayFilterFactory<EpoolHeaderFilter.EpoolConfig> {

	@Autowired
	private Environment environment;

	
	
	public EpoolHeaderFilter() {
		super(EpoolConfig.class);
	}

	public static class EpoolConfig {

	}

	@Override
	public GatewayFilter apply(EpoolConfig config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			if (request.getHeaders().containsKey("Shiva")) {
				return onFailure(exchange, "UNAUTHORISED ", HttpStatus.UNAUTHORIZED);
			}
			String portNumber = environment.getProperty("local.server.port");
			System.out.println("=================================Shiva====================================");
			return chain.filter(exchange);
		};
	}

	private Mono<Void> onFailure(ServerWebExchange exchange, String error, HttpStatus unauthorized) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(unauthorized);
		return response.setComplete();
	}

}
