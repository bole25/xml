package com.example.reportservice.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.reportservice.model.AgentVehicleReport;

public interface AgentVehicleReportRepository extends JpaRepository<AgentVehicleReport, Long> {
	
    @Query(value = "SELECT * FROM agent_vehicle_report WHERE vehicle_id = ?1", nativeQuery = true)
    Set<AgentVehicleReport> getAgentReportsForVehicle(Long vehicleId);
}
