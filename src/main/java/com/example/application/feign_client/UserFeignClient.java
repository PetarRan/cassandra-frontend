package com.example.application.feign_client;

import com.example.application.data.model.Product;
import com.example.application.data.model.User;
import com.example.application.feign_client.CommonFeignClient;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface UserFeignClient extends CommonFeignClient<User> {

    @RequestLine("GET getAll")
    Collection<User> findAllProductsCart();

    @RequestLine("GET getUser/{username}")
    User findByUsername(@Param("username") String username);

    @RequestLine("POST addUser")
    void addUser(@RequestBody(required = true)User user);


}
