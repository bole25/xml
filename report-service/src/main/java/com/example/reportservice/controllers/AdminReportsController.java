package com.example.reportservice.controllers;

import com.example.reportservice.enumeration.ReportStatus;
import com.example.reportservice.model.VehicleReport;
import com.example.reportservice.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/reports/admin")
public class AdminReportsController {

    @Autowired
    private ReportService reportService;

    @GetMapping()
    public ResponseEntity<?> getPendingReports(){
        Set<VehicleReport> reports = reportService.getPendingReports();
        if(reports == null){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(reports,HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveReport(@PathVariable String id){
        Long report_id;
        try{
            report_id = Long.parseLong(id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("asda", HttpStatus.BAD_REQUEST);
        }
        reportService.adminReportStatus(report_id, ReportStatus.APPROVED);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectReport(@PathVariable String id){
        Long report_id;
        try{
            report_id = Long.parseLong(id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("asda", HttpStatus.BAD_REQUEST);
        }
        reportService.adminReportStatus(report_id, ReportStatus.REJECTED);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
