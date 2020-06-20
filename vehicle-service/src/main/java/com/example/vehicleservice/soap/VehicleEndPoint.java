package com.example.vehicleservice.soap;

import com.example.vehicleservice.dto.VehicleDTO;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.service.VehicleService;
import com.example.vehicleservice.soap.convert.PostVehicleRequest;
import com.example.vehicleservice.soap.convert.PostVehicleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Endpoint
public class VehicleEndPoint {
    private static final String NAMESPACE_URI = "http://localhost:8083/vehicle";

    @Autowired
    private VehicleService vehicleService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postVehicleRequest")
    @ResponsePayload
    public PostVehicleResponse getTest(@RequestPayload PostVehicleRequest request) throws DatatypeConfigurationException {
        System.out.println("Soap request");
    //TODO MOZDA CE TREBATI AUTENTIFIKACIJA
        //
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        PostVehicleResponse response = new PostVehicleResponse();
        VehicleDTO newVehicle = new VehicleDTO();

        newVehicle.setAllowedMileage(request.getVehicle().getAllowedMileage());

        //TODO napraviti konverziju gregorian to date

        Calendar myCal = Calendar.getInstance();
   /*     myCal.set(Calendar.YEAR, request.getVehicle().getStartDate().getYear());
        myCal.set(Calendar.MONTH, request.getVehicle().getStartDate().getYear());
        myCal.set(Calendar.DAY_OF_MONTH, request.getVehicle().getStartDate().getYear());
        myCal.set(Calendar.YEAR, request.getVehicle().getEndDate().getYear());
        myCal.set(Calendar.MONTH, request.getVehicle().getEndDate().getYear());
        myCal.set(Calendar.DAY_OF_MONTH, request.getVehicle().getEndDate().getYear());*/

        GregorianCalendar gCalendar = request.getVehicle().getStartDate().toGregorianCalendar();
        Date d1 = gCalendar.getTime();
        GregorianCalendar gCalendar2 = request.getVehicle().getEndDate().toGregorianCalendar();
        Date d2 = gCalendar2.getTime();

        newVehicle.setStartDate(d1);
        newVehicle.setEndDate(d2);
        newVehicle.setBrand(request.getVehicle().getBrand());
        newVehicle.setChildSeat(request.getVehicle().getChildSeat());
        newVehicle.setCollisionDamageWaiver(request.getVehicle().isCollisionDamageWaiver());
        newVehicle.setCompanyUsername(request.getVehicle().getCompanyUsername());
        newVehicle.setFuelType(request.getVehicle().getFuelType());
        newVehicle.setLimitedRentMileage(request.getVehicle().isLimitedRentMileage());
        newVehicle.setMileage(request.getVehicle().getMileage());
        newVehicle.setModel(request.getVehicle().getModel());
        newVehicle.setPrice(request.getVehicle().getPrice());
        newVehicle.setTransmission(request.getVehicle().getTransmission());
        newVehicle.setVehicleClass(request.getVehicle().getVehicleClass());

//treba discount

        ResponseEntity responseEntity = vehicleService.createVehicle(newVehicle, request.getCompanyUsername());
        Vehicle postedVehicle = (Vehicle) responseEntity.getBody();
        response.setIdVehicle(postedVehicle.getId());


        System.out.println("zavrsio request");
        return response;

    }

}
