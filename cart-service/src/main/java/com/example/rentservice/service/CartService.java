package com.example.rentservice.service;

import com.example.rentservice.model.Cart;
import com.example.rentservice.model.CartItem;
import com.example.rentservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Set<CartItem> getCartItems(String username){
        Cart cart = cartRepository.getCartByUsername(username);
        return cart.getItems();
    }

    public boolean addCartItem(CartItem item, String username){
        Cart cart = cartRepository.getCartByUsername(username);
        boolean successful_add = cart.addCartItem(item);
        if(successful_add){
            cartRepository.save(cart);
            return true;
        }else{
            return false;
        }
    }
}
