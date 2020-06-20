package com.example.rentservice.controller;

import com.example.rentservice.model.CartItem;
import com.example.rentservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

//Username will be extracted from header
@RestController
@RequestMapping("/cart")
public class CartItemController {

    @Autowired
    CartService cartService;

    @GetMapping()
    public ResponseEntity<Set<CartItem>> getCartItems(@RequestHeader(value = "Username") String username) {
        return new ResponseEntity<>(cartService.getCartItems(username), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> addCartItem(@RequestBody CartItem item, @RequestHeader(value = "Username") String username){
        boolean successful = cartService.addCartItem(item, username);
        if(successful){
            return new ResponseEntity<String>("", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("Not Successful", HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @DeleteMapping("/{item_id}")
    public ResponseEntity<String> removeCartItem(@PathVariable String item_id, @RequestHeader(value = "Username") String username){
        Long id;
        try {
            id = Long.valueOf(item_id);
        }catch (NumberFormatException e){
            return new ResponseEntity<>("Id given in the url path is not a number",HttpStatus.BAD_REQUEST);
        }
        boolean success = cartService.removeCartItem(username, id);
        if(success){
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
}
