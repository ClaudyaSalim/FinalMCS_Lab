package com.example.bluejack_pharmacy_final_mcs.model;

import java.io.Serializable;

public class Medic implements Serializable {

    private int id, price;
    private String name, manufacture, image, desc;


    public Medic(int id, String name, String manufacture, int price, String image, String desc) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.manufacture = manufacture;
        this.price = price;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

