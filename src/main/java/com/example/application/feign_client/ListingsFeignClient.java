package com.example.application.feign_client;

import com.example.application.data.model.MyListings;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;

public interface ListingsFeignClient extends CommonFeignClient<MyListings>{

    @RequestLine("GET getAll")
    Collection<MyListings> findAllListings();

    @RequestLine("GET getMyAll/{userid}")
    Collection<MyListings> findMyListings(@Param("userid")String userid);

    @RequestLine("GET getByContinent/{userid}/{continent}")
    Collection<MyListings> findByContinent(@Param("userid")String userId,
                                           @Param("continent") String continent);

    @RequestLine("GET getByContinent/{userid}/{continent}/{country}")
    Collection<MyListings> findByCountry(@Param("userid")String userId,
                                         @Param("continent") String continent, @Param("country") String country);

    @RequestLine("GET getByContinent/{userid}/{continent}/{country}/{city}")
    Collection<MyListings> findByCity(@Param("userid")String userId,
                                      @Param("continent") String continent, @Param("country") String country,
                                   @Param("city") String city);

    @RequestLine("DELETE deleteListing")
    void deleteListing(@Valid @RequestBody(required = true) MyListings myListing);

}
