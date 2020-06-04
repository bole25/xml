package com.example.vehicleservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Occupation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;

    private Date startDate;
    private Date endDate;

    public Occupation(Long parseLong, Date sD, Date eD) {
        this.user_id = parseLong;
        this.startDate = sD;
        this.endDate = eD;
    }
}
