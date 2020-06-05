package com.example.vehicleservice.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.vehicleservice.dto.SearchByCompanyUsernameDTO;
import com.example.vehicleservice.dto.SearchDTO;
import com.example.vehicleservice.dto.ShowVehicleDTO;
import com.example.vehicleservice.model.Occupation;
import com.example.vehicleservice.model.Vehicle;
import com.example.vehicleservice.repository.VehicleRepository;

@Service
public class ShowVehiclesByCompanyUsername {

	 	@Autowired
	    VehicleRepository vehicleRepository;
	 	
	 	public ResponseEntity<Set<ShowVehicleDTO>> showVehiclesByCompanyUsername(String username){
	    	 Set<Vehicle> vehicleSet = new HashSet<>();
	         Set<ShowVehicleDTO> showVehicleDTOS = new HashSet<>();
	         try {
	             vehicleSet = vehicleRepository.showVehiclesByCompanyUsername(username);
	             for (Vehicle v : vehicleSet) {
	                 ShowVehicleDTO showVehicleDTO = new ShowVehicleDTO(v);
	                 showVehicleDTOS.add(showVehicleDTO);
	             }
	             return new ResponseEntity<>(showVehicleDTOS,HttpStatus.OK);
	         } catch (Exception e){
	             e.printStackTrace();
	             return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	         }
	    }
	 	
	 	public ResponseEntity<Set<ShowVehicleDTO>> GetVehiclesByDateAndCompanyUsername(SearchByCompanyUsernameDTO searchByDTO){
	    	 Set<Vehicle> searchVehicle = vehicleRepository.searchVehicle(searchByDTO.getFrom(), searchByDTO.getTo());
	    	 Set<Vehicle> afterCheckVehicles = GetVehiclesAfterCheck(searchVehicle, searchByDTO.getFrom(), searchByDTO.getTo());
	    	 Set<ShowVehicleDTO> returnVehiclesDTO = new HashSet<ShowVehicleDTO>();
	    	 for(Vehicle v : afterCheckVehicles) {
	    		 if(v.getCompanyUsername().equals(searchByDTO.getUsername())) {
	    			 returnVehiclesDTO.add(new ShowVehicleDTO(v));
	    		 }
	    	 }
	    	 return new ResponseEntity<>(returnVehiclesDTO, HttpStatus.OK);
	    }
	 	public Set<Vehicle> GetVehiclesAfterCheck(Set<Vehicle> currVehicles,Date from,Date to) {
	 		Set<Vehicle> retVehicles = new HashSet<Vehicle>();
	 		Boolean work = Boolean.TRUE;
            for(Vehicle v: currVehicles){
                work = Boolean.TRUE;
                for(Occupation o: v.getOccupations()){
                    if(!compareDates(from,to,o.getStartDate(),o.getEndDate())){
                        work = Boolean.FALSE;
                        break;
                    }
                }
                if(work){
                    retVehicles.add(v);
                }
            }
	 		return retVehicles;
	 	}
	 	private Boolean compareDates(Date startDate, Date endDate, Date startCompare, Date endCompare){
	        Boolean ret = Boolean.FALSE;
	        if(startDate.after(endCompare)){
	            ret = Boolean.TRUE;
	        }
	        if(endDate.before(startCompare)){
	            ret = Boolean.TRUE;
	        }
	        return ret;
	    }
	    
	
}
