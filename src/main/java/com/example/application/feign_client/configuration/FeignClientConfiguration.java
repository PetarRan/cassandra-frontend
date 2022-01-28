package com.kaktus.application.feign_client.configuration;

import com.kaktus.application.feign_client.*;
import feign.Client;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;

@Configuration
@Import(FeignClientsConfiguration.class)
@Data
public class FeignClientConfiguration implements ApplicationContextAware {
    @Value("${base.path}")
    private String basePath;

    private ApplicationContext applicationContext;

    @Bean
    public ListToStringCommaRequestInterceptor listToStringCommaRequestInterceptor() { return new ListToStringCommaRequestInterceptor(); }


    @Bean
    public FirmaFeignClient firmaFeignClient(){
        return createClient(FirmaFeignClient.class,"firma/");
    }

    @Bean
    public ZaposleniFeignClient zaposleniFeignClient(){ return createClient(ZaposleniFeignClient.class, "zaposleni/"); }

    @Bean
    public VlasnikFeignClient vlasnikFeignClient(){ return createClient(VlasnikFeignClient.class,"vlasnik/");}

    @Bean
    public KancelarijaFeignClient kancelarijaFeignClient(){ return createClient(KancelarijaFeignClient.class, "kancelarija/");}

    @Bean
    public PoslovniProstorFeignClient poslovniProstorFeignClient(){ return createClient(PoslovniProstorFeignClient.class,"prostor/");}

    @Bean
    public ProjektiFeignClient projektiFeignClient(){ return createClient(ProjektiFeignClient.class,"projekat/");}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private <T> T createClient(Class<T> type, String uri) {
        return Feign.builder()
                .encoder(applicationContext.getBean(Encoder.class))
                .decoder(applicationContext.getBean(Decoder.class))
                .requestInterceptor(applicationContext.getBean(ListToStringCommaRequestInterceptor.class))
                .client(new Client.Default(null, null))
                .target(type, basePath + uri);
    }
}
