package com.example.requestservice.dto.request_creation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", namespace = "http://localhost:8085/owner/requests/pending")
@XmlRootElement(name = "requestClass")
public class RequestDTO {
    @XmlElement
    private String owner_username;
    @XmlElement
    private Set<VehicleDTO> vehicles;
}
