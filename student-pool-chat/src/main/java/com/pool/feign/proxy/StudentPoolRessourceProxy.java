package com.pool.feign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pool.config.FeignConfig;

@FeignClient(name = "student-pool-resource-server", path = "/student-pool-resource-server", configuration = {FeignConfig.class })
public interface StudentPoolRessourceProxy {
	@GetMapping("/okta/token")
	public ResponseEntity<?> getOthenticationDetails(@RequestParam("jwt") Jwt jwt);

	@DeleteMapping("/method/deleteuser/{userId}")
	public String deleteUser(@RequestParam("userId") Integer userId);

	@GetMapping("/method/getPreauthorize")
	public String verifyWithPreAuthorize();
}
