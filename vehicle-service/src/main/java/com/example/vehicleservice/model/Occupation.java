package com.example.vehicleservice.model;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long user_id;

    @Column()
    private Date startDate;

    @Column()
    private Date endDate;

    public Occupation(Long parseLong, Date sD, Date eD) {
        this.user_id = parseLong;
        this.startDate = sD;
        this.endDate = eD;
    }
    
}
