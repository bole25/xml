package com.example.requestservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vehicle_name;

    @Column(nullable = false)
    private Long vehicle_id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Occupation time_span;
}
