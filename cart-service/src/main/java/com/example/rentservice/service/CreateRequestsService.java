package com.example.rentservice.service;

import com.example.rentservice.DTO.PurchaseCartDto;
import com.example.rentservice.DTO.request_creation.OccupationDTO;
import com.example.rentservice.DTO.request_creation.RequestDTO;
import com.example.rentservice.DTO.request_creation.VehicleDTO;
import com.example.rentservice.feignClient.RequestClient;
import com.example.rentservice.model.Cart;
import com.example.rentservice.model.CartItem;
import com.example.rentservice.repository.CartRepository;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CreateRequestsService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RequestClient requestClient;

    public boolean createRequests(PurchaseCartDto bundleQuery, String username){

        Cart cart = cartRepository.getCartByUsername(username);
        if (cart==null){
            return false;
        }
        Map<String,Set<VehicleDTO>> owner_vehicles = new HashMap<>();

        for(CartItem ci: cart.getItems()){
            if(!owner_vehicles.containsKey(ci.getOwner_username())){
                owner_vehicles.put(ci.getOwner_username(), new HashSet<>());
            }
            OccupationDTO occupation = new OccupationDTO(ci.getTime_span().getStartDate(), ci.getTime_span().getEndDate());
            owner_vehicles.get(ci.getOwner_username()).add(new VehicleDTO(ci.getVehicle_name(),ci.getVehicle_id(),occupation));
        }

        Set<RequestDTO> requests = new HashSet<>();

        for(String owner_username: bundleQuery.getBundleQuery().keySet()){
            if(bundleQuery.getBundleQuery().get(owner_username).equals("True")){
                RequestDTO request = new RequestDTO();
                request.setOwner_username(owner_username);
                request.setVehicles(new HashSet<>());
                for(VehicleDTO vehicle : owner_vehicles.get(owner_username)){
                    request.getVehicles().add(vehicle);
                }
                requests.add(request);
            }else{
                for(VehicleDTO vehicle : owner_vehicles.get(owner_username)){
                    RequestDTO request = new RequestDTO();
                    request.setOwner_username(owner_username);
                    request.setVehicles(new HashSet<>());
                    request.getVehicles().add(vehicle);
                    requests.add(request);
                }
            }
        }

        Gson gson = new Gson();
        String encodedJSON = gson.toJson(requests);
        requestClient.update(encodedJSON, username);

        cart.setItems(new HashSet<>());
        cartRepository.save(cart);

        return true;
    }

}
