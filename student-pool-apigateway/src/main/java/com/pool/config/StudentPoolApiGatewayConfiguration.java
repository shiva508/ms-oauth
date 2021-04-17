package com.pool.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentPoolApiGatewayConfiguration {
	@Bean
	public RouteLocator gateRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r->r.path("/get").filters(
						f->f.addRequestHeader("408", "508")
							.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(r->r.path("/student-pool-friends/**")
						.uri("http://localhost:8002"))
				.route(r->r.path("/student-pool-resource-server/**").uri("http://localhost:8000"))
				.build();
	}
}
