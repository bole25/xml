package com.example.reportservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("vehicle-service")
public interface VehicleClient {
	@GetMapping(value = "/vehicle/updateKm/{id}/{km}")
    Boolean updateKm(@PathVariable("id") Long id,@PathVariable("km") Integer km);
	
}
