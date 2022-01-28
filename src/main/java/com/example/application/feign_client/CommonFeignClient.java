package com.kaktus.application.feign_client;

import feign.Param;
import feign.RequestLine;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.net.URI;

public interface CommonFeignClient<T> {
    @RequestLine("GET")
    CollectionModel<T> findAll();

    @RequestLine("GET /{id}")
    EntityModel<T> findOne(@Param("id") Long id);

    @RequestLine("POST")
    T add(T t);

    @RequestLine("PUT /{id}")
    T updateAllAttributes(@Param("id") Long id, T t);

    @RequestLine("PATCH /{id}")
    T update(@Param("id") Long id, T t);

    @RequestLine("DELETE /{id}")
    void delete(@Param("id") Long id);

    @RequestLine("GET")
    EntityModel<T> getObjectFromURI(URI baseUri);
}
