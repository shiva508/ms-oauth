package com.pool.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class StudentPoolApiGatewayConfiguration {
	@Bean
	public RouteLocator gateRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r->r.path("/get").filters(f->f.addRequestHeader("408", "508").addRequestParameter("Param", "MyValue")).uri("http://httpbin.org:80"))
				.route(r->r.path("/student-pool-friends/**").uri("lb://student-pool-friends"))
				.route(r->r.path("/student-pool-resource-server/**").uri("lb://student-pool-resource-server"))
				.route(r->r.path("/student-pool-chat/**").uri("lb://student-pool-chat"))
				.route(r->r.path("/api/v1/presentation/**").uri("lb://studentpool-presentations"))
				.route(r->r.path("/money/**").uri("lb://studentpool-money"))
				.build();
	}
}
