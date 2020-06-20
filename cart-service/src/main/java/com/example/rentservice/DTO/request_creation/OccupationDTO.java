package com.example.rentservice.DTO.request_creation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class OccupationDTO {

    private Date startDate;

    private Date endDate;

    public OccupationDTO(Date sD, Date eD) {
        this.startDate = sD;
        this.endDate = eD;
    }

}
