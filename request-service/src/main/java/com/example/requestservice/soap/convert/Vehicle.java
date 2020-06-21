
package com.example.requestservice.soap.convert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for vehicle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vehicle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vehicle_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vehicle_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="time_span" type="{http://localhost:8085/owner/requests/pending}occupationDTO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vehicle", namespace = "http://localhost:8085/owner/requests/pending", propOrder = {
    "vehicleName",
    "vehicleId",
    "timeSpan"
})
public class Vehicle {

    @XmlElement(name = "vehicle_name", namespace = "http://localhost:8085/owner/requests/pending")
    protected String vehicleName;
    @XmlElement(name = "vehicle_id", namespace = "http://localhost:8085/owner/requests/pending")
    protected Long vehicleId;
    @XmlElement(name = "time_span", namespace = "http://localhost:8085/owner/requests/pending")
    protected OccupationDTO timeSpan;

    /**
     * Gets the value of the vehicleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleName() {
        return vehicleName;
    }

    /**
     * Sets the value of the vehicleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleName(String value) {
        this.vehicleName = value;
    }

    /**
     * Gets the value of the vehicleId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the value of the vehicleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVehicleId(Long value) {
        this.vehicleId = value;
    }

    /**
     * Gets the value of the timeSpan property.
     * 
     * @return
     *     possible object is
     *     {@link OccupationDTO }
     *     
     */
    public OccupationDTO getTimeSpan() {
        return timeSpan;
    }

    /**
     * Sets the value of the timeSpan property.
     * 
     * @param value
     *     allowed object is
     *     {@link OccupationDTO }
     *     
     */
    public void setTimeSpan(OccupationDTO value) {
        this.timeSpan = value;
    }

}
