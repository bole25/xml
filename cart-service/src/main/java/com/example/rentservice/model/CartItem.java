package com.example.rentservice.model;

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
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long vehicle_id;

    @Column(nullable = false)
    private String vehicle_name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String owner_username;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Occupation time_span;
}
