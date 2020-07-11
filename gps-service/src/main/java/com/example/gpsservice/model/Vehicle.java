package com.example.gpsservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.Pair;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    private Long vehicle_id;

    @Transient
    private Double longitude;

    @Transient
    private Double latitude;

    @Transient
    private LocalDateTime time_stamp;
}
