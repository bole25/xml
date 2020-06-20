package com.example.requestservice.dto.request_creation;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OccupationDTO {

    private Date startDate;

    private Date endDate;

    public OccupationDTO(Date sD, Date eD) {
        this.startDate = sD;
        this.endDate = eD;
    }

}
