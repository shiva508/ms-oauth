package com.pool.feign.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.pool.config.AuthInterceptor;
import com.pool.config.FeignConfig;

@FeignClient(name = "student-pool-friends",path = "/student-pool-friends",configuration = {FeignConfig.class})
public interface FriendsFeignClient {
	@GetMapping("/friends/list")
	public List<String> friendsList(); 
	
}
