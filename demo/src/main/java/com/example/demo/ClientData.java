package com.example.demo;

import java.util.Date;

public class ClientData {
    private int id;
    private String clientId;
    private String name;

    private String phone;

    private String produit;
    private String status;
    private double price;

    public ClientData(int id, String clientId, String name, String phone, String produit, String status, double price) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.phone = phone;
        this.produit = produit;
        this.status = status;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProduit() {
        return produit;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }
}
