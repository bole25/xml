
package com.example.vehicleservice.soap.convert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idVehicle" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idVehicle"
})
@XmlRootElement(name = "postVehicleResponse", namespace = "http://localhost:8083/vehicle")
public class PostVehicleResponse {

    @XmlElement(namespace = "http://localhost:8083/vehicle")
    protected long idVehicle;

    /**
     * Gets the value of the idVehicle property.
     * 
     */
    public long getIdVehicle() {
        return idVehicle;
    }

    /**
     * Sets the value of the idVehicle property.
     * 
     */
    public void setIdVehicle(long value) {
        this.idVehicle = value;
    }

}
