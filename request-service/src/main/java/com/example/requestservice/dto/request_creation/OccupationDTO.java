package com.example.requestservice.dto.request_creation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class OccupationDTO {

    private Date startDate;

    private Date endDate;

    public OccupationDTO(Date sD, Date eD) {
        this.startDate = sD;
        this.endDate = eD;
    }

}
