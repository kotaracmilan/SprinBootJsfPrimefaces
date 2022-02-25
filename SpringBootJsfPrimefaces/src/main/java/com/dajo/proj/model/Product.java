package com.dajo.proj.model;

import java.util.List;

import lombok.Data;

@Data
public class Product {

	private int id;
    private String code;
    private String name;
    private String description;
    private String image;
    private double price;
    private String category;
    private int quantity;
    private InventoryStatus inventoryStatus;
    private int rating;
    private List<Order> orders;

    public Product() {
    }

    public Product(int id, String code, String name, String description, String image, double price, String category, int quantity,
            InventoryStatus inventoryStatus, int rating) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.inventoryStatus = inventoryStatus;
        this.rating = rating;
    }


    public Product clone() {
        return new Product(getId(), getCode(), getName(), getDescription(), getImage(), getPrice(), getCategory(), getQuantity(),
                getInventoryStatus(), getRating());
    }

   
}
