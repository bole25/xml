package com.example.reportservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Comment {

    private Long id;

    private Long user_id;
    private Long vehicle_id;
    private String title;
    private String content;
    private Date publishing_date;

}
