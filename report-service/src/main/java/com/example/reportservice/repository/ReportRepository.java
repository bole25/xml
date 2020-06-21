package com.example.reportservice.repository;

import com.example.reportservice.model.VehicleReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReportRepository extends JpaRepository<VehicleReport, Long> {

    @Query(value = "SELECT v FROM VehicleReport v WHERE v.status = 1 AND v.vehicle_id=?1")
    Set<VehicleReport> getReportsForVehicle(Long vehicle_id);
}
