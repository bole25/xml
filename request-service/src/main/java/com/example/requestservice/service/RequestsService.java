package com.example.requestservice.service;

import com.example.requestservice.dto.request_creation.RequestDTO;
import com.example.requestservice.dto.request_creation.VehicleDTO;
import com.example.requestservice.enums.RequestStatus;
import com.example.requestservice.feignClient.VehicleClient;
import com.example.requestservice.model.Request;
import com.example.requestservice.model.UserRequests;
import com.example.requestservice.model.Vehicle;
import com.example.requestservice.repository.RequestRepository;
import com.example.requestservice.repository.UserRequestsRepository;
import org.bouncycastle.cert.ocsp.Req;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class RequestsService {

    @Autowired
    UserRequestsRepository userRequestsRepository;

    @Autowired
    RequestRepository requestRepository;
    
    @Autowired
    VehicleClient vClient;

    Logger logger = LoggerFactory.getLogger(RequestsService.class);

    public Set<Request> getRequests(String username, RequestStatus type){
        UserRequests user_requests = userRequestsRepository.getRequestsByUsername(username);
        if(user_requests==null){
            return null;
        }
        Set<Request> requests = new HashSet<>();
        for(Request r: user_requests.getRequests()){
            if(r.getStatus() == type)
                requests.add(r);
        }
        return requests;
    }

    public Set<Request> getFinished(String username, RequestStatus type){
        UserRequests user_requests = userRequestsRepository.getRequestsByUsername(username);
        if(user_requests==null){
            return null;
        }
        Set<Request> requests = new HashSet<>();
        for(Request r: user_requests.getRequests()){
            if(r.getStatus() != RequestStatus.RESERVED)
                break;
            boolean add = true;
            for(Vehicle v : r.getVehicles()){
                if(!v.getTime_span().getEndDate().before(new Date())){
                    add = false;
                    break;
                }
            }
            if(add){
                requests.add(r);
            }

        }
        return  requests;
    }

    public boolean createRequests(Set<RequestDTO> requestsDto, String username){

        UserRequests request = userRequestsRepository.getRequestsByUsername(username);
        if(request == null){
            logger.error("Neuspjesno kreiranje zahtjeva za korisnika {}. {}", username, LocalDateTime.now());
            return false;
        }
        Set<Request> requests = request.getRequests();
        for(RequestDTO requestDTO: requestsDto){
        	for(VehicleDTO v : requestDTO.getVehicles()) {
        		String result = vClient.getDiscountByVehicle(v.getVehicle_id());
        		String[] array = result.split(",");
        		long diff = v.getTime_span().getEndDate().getTime() - v.getTime_span().getStartDate().getTime();
    			int daysCount = (int) (diff / 86400000);
    			Double price = Double.parseDouble(array[2]) * daysCount;
        		if(!array[0].equals("null") && !array[1].equals("null") && daysCount >= Integer.parseInt(array[1])) {
        			price -= ((double) Integer.parseInt(array[0]) / 100 * price);
        		}
        		requestDTO.setPrice(price);
        	}
            requests.add(new Request(requestDTO));
        }
        logger.info("Korisnik {} uspjesno kreirao zahtjeve za vozila. {}", username, LocalDateTime.now());
        userRequestsRepository.save(request);
        return true;
    }

    public Boolean areConnected(String sender, String receiver) {
        // Number of requests between sender and receiver that are RESERVED
        Long count = userRequestsRepository.connected(sender,receiver);
        if (count > 0)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }


    // Funkcija koja odbija zahtjev koji nije prihvacen nakon 24h od kreiranja
    public void removeAfter24h(){
        Set<Request> requests = requestRepository.getAllPendingRequests();
        for(Request r : requests){
            Date toCompare = this.addHoursToJavaUtilDate(r.getCreated(), 24);
            if(toCompare.before(new Date())){
                r.setStatus(RequestStatus.REJECTED);
                requestRepository.save(r);
            }
        }
    }


    private Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 24);
        return calendar.getTime();
    }
}
