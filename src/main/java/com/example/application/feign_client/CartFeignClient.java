package com.example.application.feign_client;

import com.example.application.data.model.Cart;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface CartFeignClient extends CommonFeignClient<Cart>{

    @RequestLine("GET getAll")
    Collection<Cart> findAllProductsCart();


}
