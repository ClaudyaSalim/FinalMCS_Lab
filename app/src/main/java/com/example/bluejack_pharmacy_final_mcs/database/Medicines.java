package com.example.bluejack_pharmacy_final_mcs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class Medicines {
    private int medicineID;
    private String name;
    private String manufacturer;
    private double price;
    private String description;

    public Medicines(int medicineID, String name, String manufacturer, double price, String description) {
        this.medicineID = medicineID;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.description = description;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
