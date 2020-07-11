package com.example.reportservice.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reportservice.DTO.AgentVehicleReportDTO;
import com.example.reportservice.feignclients.VehicleClient;
import com.example.reportservice.model.AgentVehicleReport;
import com.example.reportservice.repository.AgentVehicleReportRepository;

@Service
public class AgentVehicleReportService {

	@Autowired
	private AgentVehicleReportRepository agentReportRepository;
	
	@Autowired
	private VehicleClient vClient;
	
	public Set<AgentVehicleReport> getReportsByVehicle(Long vehicleId){
		return agentReportRepository.getAgentReportsForVehicle(vehicleId);
	}
	
	public void addAgentReport(AgentVehicleReportDTO reportDTO) {
		AgentVehicleReport report = new AgentVehicleReport();
		report.setComment(reportDTO.getComment());
		report.setKm(reportDTO.getKm());
		report.setVehicleId(reportDTO.getVehicleId());
		agentReportRepository.save(report);
		vClient.updateKm(reportDTO.getVehicleId(),reportDTO.getKm());
		// TODO connect to vehicle service and change vehicles mileage
		// ili dodaj u vehicle service funkciju za povecavanje km i gadjaj sa fronta - lakse
		
	}
}
