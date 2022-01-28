package com.kaktus.application.feign_client.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.ArrayList;

public class ListToStringCommaRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.queries().forEach((key, value) -> {
            if (value.size() > 1) {
                template.query(key, new ArrayList<>());
                template.query(key, String.join(",", value));
            }
        });
    }
}
