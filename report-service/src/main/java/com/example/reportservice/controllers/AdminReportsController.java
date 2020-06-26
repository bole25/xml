package com.example.reportservice.controllers;

import com.example.reportservice.enumeration.ReportStatus;
import com.example.reportservice.model.VehicleReport;
import com.example.reportservice.services.ReportService;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/reports/admin")
public class AdminReportsController {

    @Autowired
    private ReportService reportService;

    Logger logger = LoggerFactory.getLogger(AdminReportsController.class);

    @GetMapping()
    public ResponseEntity<?> getPendingReports(@RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio sve izvjestaje koji treba da se odobre. {}", username, LocalDateTime.now());
        Set<VehicleReport> reports = reportService.getPendingReports();
        if(reports == null){
            logger.error("Admin {} nije dobio izvjestaje koje treba da odobri. {}", username, LocalDateTime.now());
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        logger.error("Admin {} je uspjesno dobio izvjestaje koje treba da odobri. {}", username, LocalDateTime.now());
        return new ResponseEntity<>(reports,HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveReport(@PathVariable String id, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio odobravanje izvjestaja sa id-jem {}. {}", username, id, LocalDateTime.now());
        Long report_id;
        try{
            report_id = Long.parseLong(id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("asda", HttpStatus.BAD_REQUEST);
        }
        reportService.adminReportStatus(report_id, ReportStatus.APPROVED);
        logger.info("Admin {} je uspjesno odobrio izvjestaj sa id-jem {}. {}", username, id, LocalDateTime.now());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectReport(@PathVariable String id, @RequestHeader("Username") String username){
        logger.info("Admin {} je zatrazio odbijanje izvjestaja sa id-jem {}. {}", username, id, LocalDateTime.now());
        Long report_id;
        try{
            report_id = Long.parseLong(id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("asda", HttpStatus.BAD_REQUEST);
        }
        logger.info("Admin {} je uspjesno odbio izvjestaj sa id-jem {}. {}", username, id, LocalDateTime.now());
        reportService.adminReportStatus(report_id, ReportStatus.REJECTED);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
