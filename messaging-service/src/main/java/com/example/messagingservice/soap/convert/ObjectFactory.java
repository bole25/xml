
package com.example.messagingservice.soap.convert;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.messagingservice.soap.convert package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.messagingservice.soap.convert
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InboxRequest }
     * 
     */
    public InboxRequest createInboxRequest() {
        return new InboxRequest();
    }

    /**
     * Create an instance of {@link SendResponse }
     * 
     */
    public SendResponse createSendResponse() {
        return new SendResponse();
    }

    /**
     * Create an instance of {@link InboxResponse }
     * 
     */
    public InboxResponse createInboxResponse() {
        return new InboxResponse();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link SendRequest }
     * 
     */
    public SendRequest createSendRequest() {
        return new SendRequest();
    }

}
