package com.example.requestservice.service;

import com.example.requestservice.model.Request;
import com.example.requestservice.model.UserRequests;
import com.example.requestservice.repository.UserRequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RequestsService {

    @Autowired
    UserRequestsRepository requestsRepository;

    public Set<Request> getApprovedRequests(String username){
        UserRequests user_requests = requestsRepository.getRequestsByUsername(username);
        if(user_requests==null){
            return null;
        }
        return user_requests.getApproved();
    }

    public Set<Request> getPendingRequests(String username){
        UserRequests user_requests = requestsRepository.getRequestsByUsername(username);
        if(user_requests==null){
            return null;
        }
        return user_requests.getPending();
    }
}
