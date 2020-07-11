package com.example.reportservice.controllers;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reportservice.DTO.AgentVehicleReportDTO;
import com.example.reportservice.model.AgentVehicleReport;
import com.example.reportservice.services.AgentVehicleReportService;

@RestController
@RequestMapping("/agentReports")
public class AgentVehicleReportController {
    @Autowired
    AgentVehicleReportService agentReportService;
    
    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getAgentReportsForVehicle(@PathVariable String vehicle_id_str){
        Long vehicle_id;
        try{
            vehicle_id = Long.parseLong(vehicle_id_str);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("asda", HttpStatus.BAD_REQUEST);
        }
        
        Set<AgentVehicleReport> reports = agentReportService.getReportsByVehicle(vehicle_id);
        
        if(reports == null) {
        	return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }
    
    @PostMapping()
    public ResponseEntity<?> postReport(@RequestBody AgentVehicleReportDTO reportDTO){
        agentReportService.addAgentReport(reportDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    
}
