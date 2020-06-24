package com.example.registerservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="login-service")
public interface LoginClient {
    @PostMapping(value = "/addCredentials")
    void update(@RequestBody String encodedRequests);
	
}
