package com.example.reportservice.services;

import com.example.reportservice.DTO.ReportDTO;
import com.example.reportservice.model.VehicleReport;
import com.example.reportservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Set<VehicleReport> getReportsForVehicle(Long vehicle_id){
        Set<VehicleReport> vehicleReports = reportRepository.getReportsForVehicle(vehicle_id);
        return vehicleReports;
    }

    public void postReport(ReportDTO reportDTO, String username) {
        reportRepository.save(new VehicleReport(reportDTO, username));
    }
}
