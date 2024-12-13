package com.pluralsight.NorthwindTradersSpringBoot.dao.impl;

import com.pluralsight.NorthwindTradersSpringBoot.dao.interfaces.ProductDAO;
import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.boot.autoconfigure.service.connection.ConnectionDetailsNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class JdbcProductDAO implements ProductDAO {

    private DataSource dataSource;

    public JdbcProductDAO (DataSource dataSource){
        this.dataSource = dataSource;
    }



    @Override
    public void add(Product product) {
        String sql = """
                INSERT INTO Products (ProductName, Category, UnitPrice)
                VALUES (?, ?, ?)
                """;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement(sql);)
        {
            query.setString(1, product.getName());
            query.setString(2, product.getCategory());
            query.setDouble(3, product.getPrice());

            int rows = query.executeUpdate();

            try (ResultSet results = query.getGeneratedKeys();)
            {
                while(results.next()){
                    product.setProductId(results.getInt(1));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        ArrayList<Product> products = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement query = connection.prepareStatement("""
                    SELECT `products`.`ProductID`,
                           `products`.`ProductName`,
                           `categories`.`CategoryName`,
                           `products`.`UnitPrice`
                    FROM `products`
                    JOIN categories on products.CategoryID = categories.CategoryID
                    """);
            ResultSet results = query.executeQuery();)
        {
            while (results.next()){
                int productId = results.getInt("ProductID");
                String name = results.getString("ProductName");
                String category = results.getString("CategoryName");
                double price = results.getInt("UnitPrice");
                Product p = new Product(productId, name, category, price);
                products.add(p);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
