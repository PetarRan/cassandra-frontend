package com.example.application.feign_client;

import com.example.application.data.model.Cart;
import com.example.application.data.model.MyListings;
import com.example.application.data.model.Product;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collection;

public interface ProductFeignClient extends CommonFeignClient<Product>{

    @RequestLine("GET getAll")
    Collection<Product> findAllProducts();

    @RequestLine("GET getByContinent/{continent}")
    Collection<Product> findByContinent(@Param("continent") String continent);

    @RequestLine("GET getByCountry/{continent}/{country}")
    Collection<Product> findByCountry(@Param("continent") String continent, @Param("country") String country);

    @RequestLine("GET getByCity/{continent}/{country}/{city}")
    Collection<Product> findByCity(@Param("continent") String continent, @Param("country") String country,
                                   @Param("city") String city);

    @RequestLine("GET getByCode/{continent}/{country}/{city}/{id}")
    Product findByCode(@Param("continent") String continent, @Param("country") String country,
                                   @Param("city") String city, @Param("id")String id);

    @RequestLine("POST addProduct?userid={userid}")
    void addProduct(@Valid @RequestBody(required = true)Product product, @Param("userid") String userid);

    @RequestLine("PUT updateProduct?userid={userid}")
    void updateProduct(@Valid @RequestBody(required = true)Product product, @Param("userid") String userid);

    @RequestLine("DELETE deleteProduct?userid={userid}")
    void deleteProduct(@Valid @RequestBody(required = true)Product product, @Param("userid") String userid);

    @RequestLine("POST addToCart")
    void addToCart(@Valid @RequestBody(required = true) Cart cart);

    @RequestLine("GET getMyCart/{userid}")
    Collection<Cart> getMyCart(@Param("userid")String userid);

    @RequestLine("DELETE deleteCart/{userid}")
    void deleteFromCart(@Param("userid")String userid);

    @RequestLine("GET getMyCart/{userid}")
    Collection<Cart> findCartByUserId(@Param("userid") String userid);

    @RequestLine("DELETE deleteFromCatalog")
    void deleteProductFromCatalog(@Valid @RequestBody(required = true) Product product);
}
