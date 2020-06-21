package com.example.requestservice.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("vehicle-service")
public interface VehicleClient {

    @PostMapping(value = "/vehicle/reserve/requestService")
    void sendReservation(@RequestBody String encodedRequests);
}
