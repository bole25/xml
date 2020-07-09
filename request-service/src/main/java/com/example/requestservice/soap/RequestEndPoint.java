package com.example.requestservice.soap;

import com.example.requestservice.enums.RequestStatus;
import com.example.requestservice.model.Request;
import com.example.requestservice.model.Vehicle;
import com.example.requestservice.repository.RequestRepository;
import com.example.requestservice.service.OwnerRequestsService;
import com.example.requestservice.soap.convert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

@Endpoint
public class RequestEndPoint {

    private static final String NAMESPACE_URI = "http://localhost:8085/owner/requests/pending";

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private OwnerRequestsService ownerRequestsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRequestsRequest")
    @ResponsePayload
    public GetRequestsResponse getRequestResponse(@RequestPayload GetRequestsRequest request) throws DatatypeConfigurationException {
        System.out.println("Soap request 1");
        //TODO MOZDA CE TREBATI AUTENTIFIKACIJA
        //
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        GetRequestsResponse response = new GetRequestsResponse();
        String username = request.getCompanyUsername();

        Set<Request> zahtjevi = requestRepository.getOwnersPending(username);
        for(Request r : zahtjevi){
            com.example.requestservice.soap.convert.Request tempReq = new com.example.requestservice.soap.convert.Request();
            tempReq.setId(r.getId());
            tempReq.setOwnerUsername(r.getOwner_username());
            if(r.getStatus().equals(RequestStatus.PENDING)){
                tempReq.setStatus(com.example.requestservice.soap.convert.RequestStatus.PENDING);
            }
            else {
                continue;
            }
            for(Vehicle v : r.getVehicles()){
                com.example.requestservice.soap.convert.Vehicle vozilo = new com.example.requestservice.soap.convert.Vehicle();
                vozilo.setId(v.getId());
                vozilo.setVehicleId(v.getVehicle_id());
                vozilo.setVehicleName(v.getVehicle_name());
                Occupation oc = new Occupation();
                oc.setId(v.getTime_span().getId());

                XMLGregorianCalendar xmlDate1 = null;
                XMLGregorianCalendar xmlDate2 = null;
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTime(v.getTime_span().getStartDate());
                xmlDate1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                gc.setTime(v.getTime_span().getEndDate());
                xmlDate2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);

                oc.setStartDate(xmlDate1);
                oc.setEndDate(xmlDate2);
                vozilo.setTimeSpan(oc);
                tempReq.getVehicles().add(vozilo);
            }
            response.getRequests().add(tempReq);
        }



        System.out.println("zavrsio request");
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "approveRequest")
    @ResponsePayload
    public ApproveResponse approveResponse(@RequestPayload ApproveRequest request){
        System.out.println("Endpoint za prihvatanje zahtjevaf");
        ApproveResponse approveResponse = new ApproveResponse();
        Long id = request.getId();
        boolean successful = ownerRequestsService.approveRequest("BOLE", id);
        if(!successful){
            approveResponse.setId(0);
        }
        else {
            approveResponse.setId(id);
        }
        return approveResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "rejectRequest")
    @ResponsePayload
    public RejectResponse rejectResponse(@RequestPayload RejectRequest request){
        System.out.println("Endpoint za odbijanje zahtjeva");
        RejectResponse rejectResponse = new RejectResponse();
        Long id = request.getId();
        boolean successful = ownerRequestsService.rejectRequest("BOLE", id);
        if(!successful){
            rejectResponse.setId(0);
        }
        else {
            rejectResponse.setId(id);
        }

        return rejectResponse;
    }
}
