package com.example.reportservice.model;

import com.example.reportservice.DTO.ReportDTO;
import com.example.reportservice.enumeration.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VehicleReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long vehicle_id;

    @Column(nullable = false)
    private String writer_username;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private ReportStatus status;

    public VehicleReport(ReportDTO reportDTO, String username) {
        this.vehicle_id = reportDTO.getVehicle_id();
        this.comment =reportDTO.getComment();
        this.rating = reportDTO.getRating();
        this.writer_username = username;
        this.status = ReportStatus.PENDING;
    }
}
