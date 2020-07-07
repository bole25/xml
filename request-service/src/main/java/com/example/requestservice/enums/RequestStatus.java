package com.example.requestservice.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "requestStatus", namespace = "http://localhost:8085/owner/requests/pending")
@XmlEnum
public enum RequestStatus {
    PENDING, RESERVED, REJECTED
}
