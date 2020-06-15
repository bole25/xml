package com.example.requestservice.dto;

import com.example.requestservice.model.Occupation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long vehicle_id;

    private String vehicle_name;

    private Double price;

    private String owner_username;

    private Occupation time_span;
}
