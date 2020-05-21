package com.example.requestservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Request {

    private Long id;

    private Long user_id;
    private Boolean approved;
    private Long vehicle_id;
    private Date startDate;
    private Date endDate;

}
