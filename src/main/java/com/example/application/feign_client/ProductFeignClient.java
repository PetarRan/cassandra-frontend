package com.example.application.feign_client;

import com.example.application.data.model.Product;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface ProductFeignClient extends CommonFeignClient<Product>{

    @RequestLine("GET getAll")
    Collection<Product> findAllProducts();


}
