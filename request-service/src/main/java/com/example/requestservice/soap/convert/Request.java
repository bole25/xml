
package com.example.requestservice.soap.convert;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="owner_username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vehicles" type="{http://localhost:8085/owner/requests/pending}vehicle" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", namespace = "http://localhost:8085/owner/requests/pending", propOrder = {
    "ownerUsername",
    "vehicles"
})
public class Request {

    @XmlElement(name = "owner_username", namespace = "http://localhost:8085/owner/requests/pending")
    protected String ownerUsername;
    @XmlElement(namespace = "http://localhost:8085/owner/requests/pending")
    protected List<Vehicle> vehicles;

    /**
     * Gets the value of the ownerUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerUsername() {
        return ownerUsername;
    }

    /**
     * Sets the value of the ownerUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerUsername(String value) {
        this.ownerUsername = value;
    }

    /**
     * Gets the value of the vehicles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vehicles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVehicles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Vehicle }
     * 
     * 
     */
    public List<Vehicle> getVehicles() {
        if (vehicles == null) {
            vehicles = new ArrayList<Vehicle>();
        }
        return this.vehicles;
    }

}
