package com.example.requestservice.service;

import com.example.requestservice.dto.VehicleReservationDTO;
import com.example.requestservice.feignClient.LoginClient;
import com.example.requestservice.feignClient.VehicleClient;
import com.example.requestservice.model.Request;
import com.example.requestservice.model.Vehicle;
import com.example.requestservice.repository.RequestRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class OwnerRequestsService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private VehicleClient vehicleClient;

    @Autowired
    private LoginClient loginClient;
    
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

    @Transactional
	public boolean checkPermission(String username) {
    	Boolean connected = loginClient.checkPerm(username,"2");
		return connected;
	}
}
