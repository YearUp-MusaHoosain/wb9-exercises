package com.pluralsight.NorthwindTradersAPI.models;

public class Product {
    private int productId;
    private String productName;
    private int categpryId;
    private double unitPrice;

    public Product() {
    }

    public Product(int productId, String productName, int categpryId, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.categpryId = categpryId;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategpryId() {
        return categpryId;
    }

    public void setCategpryId(int categpryId) {
        this.categpryId = categpryId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", categpryId=" + categpryId +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
