package com.example.requestservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("vehicle-service")
public interface VehicleClient {

    @PostMapping(value = "/reserveVehicle", headers = {"Username={username}"})
    void update(@RequestBody String encodedRequests, @PathVariable("username") String username);
}
