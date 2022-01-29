package com.example.application.feign_client;

import com.example.application.data.model.Product;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface ProductFeignClient extends CommonFeignClient<Product>{

    @RequestLine("GET getAll")
    Collection<Product> findAllProducts();

    @RequestLine("GET getByContinent/{continent}")
    Collection<Product> findByContinent(@Param("continent") String continent);

    @RequestLine("GET getByContinent/{continent}/{country}")
    Collection<Product> findByCountry(@Param("continent") String continent, @Param("country") String country);

    @RequestLine("GET getByContinent/{continent}/{country}/{city}")
    Collection<Product> findByCity(@Param("continent") String continent, @Param("country") String country,
                                   @Param("city") String city);

    @RequestLine("POST addProduct")
    void addProduct(@RequestBody(required = true)Product product);

}
