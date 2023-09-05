package com.poo.entities;

import java.util.ArrayList;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInterface extends JpaRepository<Product, Long>  {

    ArrayList<Product> getProducts();

    String getName();

    void setName(String name);

    float getValue();

    void setValue(float value);

    int getAmount();

    void setAmount(int amount);

    String toString();
    
    //METODO DE RETORNO PARA OS PRODUTOS.
    ArrayList<Product> findAll(Product newProduct, Direction desc);

}