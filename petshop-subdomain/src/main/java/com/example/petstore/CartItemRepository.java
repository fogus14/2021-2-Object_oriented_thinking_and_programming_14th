package com.example.petstore;

import com.example.petstore.domain.CartItem;
import com.example.petstore.domain.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{    // Repository Pattern Interface

}
