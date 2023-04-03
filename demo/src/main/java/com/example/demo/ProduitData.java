package com.example.demo;

import java.util.Date;

public class ProduitData {
    private int id;
    private String produit_id;
    private String name;
    private double price;
    private String category;
    private String status;

    public ProduitData(int id, String produit_id, String name, double price, String category, String status) {
        this.id = id;
        this.produit_id = produit_id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getProduit_id() {
        return produit_id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }
}
