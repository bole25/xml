package com.example.rentservice.controller;

import com.example.rentservice.model.CartItem;
import com.example.rentservice.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

//Username will be extracted from header
@RestController
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    CartService cartService;

    Logger logger = LoggerFactory.getLogger(CartItemController.class);

    @GetMapping()
    public ResponseEntity<Set<CartItem>> getCartItems(@RequestHeader(value = "Username") String username) {
        logger.info("Korisnik {} je zatrazio item-e iz korpe. {}", username, LocalDateTime.now());
        return new ResponseEntity<>(cartService.getCartItems(username), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addCartItem(@RequestBody CartItem item, @RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} zatrazio da doda u korpu {}. {}", username, item.getVehicle_name(), LocalDateTime.now());
        boolean successful = cartService.addCartItem(item, username);
        if(successful){
            logger.info("Korisnik {} je uspjesno dodao {} u korpu. {}", username, item.getVehicle_name(), LocalDateTime.now());
            return new ResponseEntity<String>("", HttpStatus.CREATED);
        }else{
            logger.error("Neuspjesno dodavanje itema {} u korpu za korisnika {}. {}", item.getVehicle_name(), username, LocalDateTime.now());
            return new ResponseEntity<String>("Not Successful", HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity<String> removeCartItem(@PathVariable String item_id, @RequestHeader(value = "Username") String username){
        logger.info("Korisnik {} je zatrazio izbacivanje iz korpe itema sa id-jem {}. {}", username, item_id, LocalDateTime.now());
        Long id;
        try {
            id = Long.valueOf(item_id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("Id given in the url path is not a number",HttpStatus.BAD_REQUEST);
        }
        boolean success = cartService.removeCartItem(username, id);
        if(success){
            logger.info("Korisnik {} je uspjesno izbacio iz korpe item sa id-jem {}. {}", username, item_id, LocalDateTime.now());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        logger.error("Neuspjesno izbacivanje itema iz korpe za korisnika {}. {}", username, LocalDateTime.now());
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
}
