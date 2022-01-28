package com.example.application.feign_client.configuration;

import com.example.application.data.model.Product;
import com.example.application.feign_client.CommonFeignClient;

public interface MarketHistoryFeignClient extends CommonFeignClient<Product> {

}
