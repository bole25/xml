package com.example.requestservice.service;

import com.example.requestservice.model.Request;
import com.example.requestservice.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class OwnerRequestsService {

    @Autowired
    private RequestRepository requestRepository;

    public Set<Request> getPending(String username){
        return requestRepository.getOwnersPending(username);
    }

    public Set<Request> getUpcoming(String username){
        return requestRepository.getOwnersUpcoming(username);
    }

    @Transactional
    public boolean approveRequest(String username, Long id){
        Optional<Request> request = requestRepository.findById(id);
        if(!request.isPresent()){
            return false;
        }
        if(!request.get().getOwner_username().equals(username)){
            return false;
        }
        requestRepository.approveRequest(id);
        return true;
    }

    @Transactional
    public boolean rejectRequest(String username, Long id) {
        Optional<Request> request = requestRepository.findById(id);
        if(!request.isPresent()){
            return false;
        }
        if(!request.get().getOwner_username().equals(username)){
            return false;
        }
        requestRepository.rejectRequest(id);
        return true;
    }
}
