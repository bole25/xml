package com.example.requestservice.dto.request_creation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RequestsDTO {

    String username;
    Set<RequestDTO> requests;

    public RequestsDTO(){
        requests = new HashSet<>();
    }
    public void add(RequestDTO request) {
        requests.add(request);
    }
}
