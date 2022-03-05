package com.pool.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.pool.model.CommonResponse;

@FeignClient("studentpool-money")
public interface MoneyFeignProxy {
	@GetMapping("/money/hellomoney")
	public ResponseEntity<CommonResponse> helloMoney();
}
