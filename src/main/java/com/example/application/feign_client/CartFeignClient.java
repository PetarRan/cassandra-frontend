package com.example.application.feign_client;

import com.example.application.data.model.Cart;
import com.example.application.data.model.Product;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface CartFeignClient extends CommonFeignClient<Cart>{

    @RequestLine("GET getAll")
    Collection<Cart> findAllProductsCart();

    @RequestLine("GET getByUserId/{userid}")
    Collection<Cart> findByUserId(@Param("userid") String userid);

    @RequestLine("POST addCartProduct")
    void addCartProduct(@RequestBody(required = true) Cart cart);


}
