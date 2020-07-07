
package com.example.requestservice.soap.convert;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for requestStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="requestStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="RESERVED"/>
 *     &lt;enumeration value="REJECTED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "requestStatus", namespace = "http://localhost:8085/owner/requests/pending")
@XmlEnum
public enum RequestStatus {

    PENDING,
    RESERVED,
    REJECTED;

    public String value() {
        return name();
    }

    public static RequestStatus fromValue(String v) {
        return valueOf(v);
    }

}
