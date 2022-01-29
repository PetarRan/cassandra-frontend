package com.example.application.feign_client;

import com.example.application.data.model.MyListings;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface ListingsFeignClient extends CommonFeignClient<MyListings>{

    @RequestLine("GET getAll")
    Collection<MyListings> findAllListings();

}
