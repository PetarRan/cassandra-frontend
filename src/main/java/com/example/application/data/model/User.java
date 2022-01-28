package com.example.application.data.model;

import com.datastax.driver.core.DataType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Table("users")
public class User implements Serializable {
    @PrimaryKey
    @CassandraType(type = DataType.Name.INT)
    private UUID id;
    private String ime;
    private String prezime;
    private String username;
    private List<Product> productList;
    private List<Product> productSellList;

    public List<Product> getProductSellList() {
        return productSellList;
    }

    public void setProductSellList(List<Product> productSellList) {
        this.productSellList = productSellList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
