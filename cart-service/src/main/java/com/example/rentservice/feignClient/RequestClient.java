package com.example.rentservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("request-service")
public interface RequestClient {

    @PostMapping(value = "/requests", headers = {"Username={username}"})
    void update(@RequestBody String encodedRequests, @PathVariable("username") String username);

}
