package com.example.reportservice.controllers;

import com.example.reportservice.DTO.ReportDTO;
import com.example.reportservice.model.VehicleReport;
import com.example.reportservice.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    Logger logger = LoggerFactory.getLogger(ReportController.class);

    @GetMapping("/{vehicle_id_str}")
    public ResponseEntity<?> getReportsForVehicle(@PathVariable String vehicle_id_str){
        Long vehicle_id;
        try{
            vehicle_id = Long.parseLong(vehicle_id_str);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("asda", HttpStatus.BAD_REQUEST);
        }

        Set<VehicleReport> reports = reportService.getReportsForVehicle(vehicle_id);
        if(reports == null){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> postReport(@RequestBody ReportDTO reportDTO,@RequestHeader(value = "Username") String username){
        if(username == null){
            logger.warn("Neko je pokusao da ostavi izvjestaj bez logovanja. {}", LocalDateTime.now());
            return new ResponseEntity<>("You must be logged in to leave a report", HttpStatus.UNAUTHORIZED);
        }

        reportService.postReport(reportDTO, username);
        logger.info("Korisnik {} je postavio izvjestaj koji admin treba da odobri da bi bio prikazan. {}", username, LocalDateTime.now());
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
