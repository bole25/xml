
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
 *         &lt;element name="vehicle" type="{http://localhost:8083/vehicle}vehicleDTO"/>
 *         &lt;element name="companyUsername" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "vehicle",
    "companyUsername"
})
@XmlRootElement(name = "postVehicleRequest", namespace = "http://localhost:8083/vehicle")
public class PostVehicleRequest {

    @XmlElement(namespace = "http://localhost:8083/vehicle", required = true)
    protected VehicleDTO vehicle;
    @XmlElement(namespace = "http://localhost:8083/vehicle", required = true)
    protected String companyUsername;

    /**
     * Gets the value of the vehicle property.
     * 
     * @return
     *     possible object is
     *     {@link VehicleDTO }
     *     
     */
    public VehicleDTO getVehicle() {
        return vehicle;
    }

    /**
     * Sets the value of the vehicle property.
     * 
     * @param value
     *     allowed object is
     *     {@link VehicleDTO }
     *     
     */
    public void setVehicle(VehicleDTO value) {
        this.vehicle = value;
    }

    /**
     * Gets the value of the companyUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyUsername() {
        return companyUsername;
    }

    /**
     * Sets the value of the companyUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyUsername(String value) {
        this.companyUsername = value;
    }

}
