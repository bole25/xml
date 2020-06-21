package com.example.reportservice.services;

import com.example.reportservice.DTO.ReportDTO;
import com.example.reportservice.ReportServiceApplication;
import com.example.reportservice.enumeration.ReportStatus;
import com.example.reportservice.model.VehicleReport;
import com.example.reportservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Set<VehicleReport> getReportsForVehicle(Long vehicle_id){
        return reportRepository.getReportsForVehicle(vehicle_id);
    }

    public Set<VehicleReport> getPendingReports(){
        return reportRepository.getPendingReports();
    }

    @Transactional
    public void adminReportStatus(Long id, ReportStatus status){
        if(status == ReportStatus.APPROVED){
            reportRepository.adminReportStatus(id, 1);
        }else{
            reportRepository.adminReportStatus(id, 2);
        }
    }
    public void postReport(ReportDTO reportDTO, String username) {
        reportRepository.save(new VehicleReport(reportDTO, username));
    }
}
