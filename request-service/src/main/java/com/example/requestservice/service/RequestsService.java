package com.example.requestservice.service;

import com.example.requestservice.dto.request_creation.RequestDTO;
import com.example.requestservice.enums.RequestStatus;
import com.example.requestservice.model.Request;
import com.example.requestservice.model.UserRequests;
import com.example.requestservice.repository.RequestRepository;
import com.example.requestservice.repository.UserRequestsRepository;
import org.bouncycastle.cert.ocsp.Req;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class RequestsService {

    @Autowired
    UserRequestsRepository userRequestsRepository;

    @Autowired
    RequestRepository requestRepository;

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

    public boolean createRequests(Set<RequestDTO> requestsDto, String username){

        UserRequests request = userRequestsRepository.getRequestsByUsername(username);
        if(request == null){
            logger.error("Neuspjesno kreiranje zahtjeva za korisnika {}. {}", username, LocalDateTime.now());
            return false;
        }
        Set<Request> requests = request.getRequests();
        for(RequestDTO requestDTO: requestsDto){
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
}
