package com.example.reportservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Rating {

    private Long id;

    private Long user_id;
    private Long vehicle_id;
    private Integer rating;
    
}
