package com.pluralsight.NorthwindTradersSpringBoot.dao.interfaces;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;

import java.util.List;


public interface ProductDAO {
    void add(Product product);
    List<Product> getAll();
}
