package com.example.rentservice.repository;

import com.example.rentservice.model.Cart;
import com.example.rentservice.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

    @Query(value = "select * from cart where username = ?1", nativeQuery = true)
    Cart getCartByUsername(String username);
}
