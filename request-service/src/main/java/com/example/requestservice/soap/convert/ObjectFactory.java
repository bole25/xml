
package com.example.requestservice.soap.convert;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.requestservice.soap.convert package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.requestservice.soap.convert
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRequestResponse }
     * 
     */
    public GetRequestResponse createGetRequestResponse() {
        return new GetRequestResponse();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link GetRequestRequest }
     * 
     */
    public GetRequestRequest createGetRequestRequest() {
        return new GetRequestRequest();
    }

    /**
     * Create an instance of {@link OccupationDTO }
     * 
     */
    public OccupationDTO createOccupationDTO() {
        return new OccupationDTO();
    }

    /**
     * Create an instance of {@link Vehicle }
     * 
     */
    public Vehicle createVehicle() {
        return new Vehicle();
    }

}
