package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(1, "Banana", 1, 5.99));
        products.add(new Product(2, "Apple", 1, 15.79));
        products.add(new Product(3, "Blue Jeans", 2, 2.99));

        return products;
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id){
        if (id == 1){
            return new Product(1, "Banana", 1, 5.99);
        }
        else if (id == 2){
            return new Product(2, "Apple", 1, 15.79);
        }
        return null;
    }
}
