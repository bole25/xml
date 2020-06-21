package com.example.requestservice.service;

import com.example.requestservice.dto.request_creation.RequestDTO;
import com.example.requestservice.enums.RequestStatus;
import com.example.requestservice.model.Request;
import com.example.requestservice.model.UserRequests;
import com.example.requestservice.repository.UserRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RequestsService {

    @Autowired
    UserRequestsRepository requestsRepository;

    public Set<Request> getRequests(String username, RequestStatus type){
        UserRequests user_requests = requestsRepository.getRequestsByUsername(username);
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

        UserRequests request = requestsRepository.getRequestsByUsername(username);
        if(request == null){
            return false;
        }
        Set<Request> requests = request.getRequests();
        for(RequestDTO requestDTO: requestsDto){
            requests.add(new Request(requestDTO));
            if(requestDTO.getOwner_username().equals("BOLE")){
                //TODO Salji soap
            }
        }

        requestsRepository.save(request);
        return true;
    }
}
