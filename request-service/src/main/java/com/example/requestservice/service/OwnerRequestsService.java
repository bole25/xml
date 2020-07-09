package com.example.requestservice.service;

import com.example.requestservice.dto.VehicleReservationDTO;
import com.example.requestservice.feignClient.VehicleClient;
import com.example.requestservice.model.Request;
import com.example.requestservice.model.Vehicle;
import com.example.requestservice.repository.RequestRepository;
import com.google.gson.Gson;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OwnerRequestsService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private VehicleClient vehicleClient;

    public Set<Request> getPending(String username){
        return requestRepository.getOwnersPending(username);
    }

    public Set<Request> getUpcoming(String username){
        return requestRepository.getOwnersUpcoming(username);
    }

    public Set<Request> getFinished(String username){
        Set<Request> approved = requestRepository.getOwnersUpcoming(username);
        Set<Request> finished = new HashSet<>();
        for(Request request : approved){
            boolean add = true;
            for(Vehicle v: request.getVehicles()){
                if(!v.getTime_span().getEndDate().before(new Date())){
                    add = false;
                    break;
                }
            }
            if(add){
                finished.add(request);
            }
        }
        return finished;
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
        //TODO posalji zahtev za rezervaciju vehicle-service-u
        for(Vehicle vehicle:request.get().getVehicles()){
            Gson gson = new Gson();
            String encodedBody = gson.toJson(new VehicleReservationDTO(
                    vehicle.getVehicle_id(),vehicle.getTime_span().getStartDate(),vehicle.getTime_span().getEndDate()));
            vehicleClient.sendReservation(encodedBody);
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
