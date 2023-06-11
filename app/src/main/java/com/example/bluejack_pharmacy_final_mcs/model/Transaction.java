package com.example.bluejack_pharmacy_final_mcs.model;

import java.io.Serializable;
import java.util.Date;

public class Transaction {

    private int id, medicineId, userId, qty;
    private String date;

    public Transaction(int medicineId, int userId, int qty, String date) {
        this.medicineId = medicineId;
        this.userId = userId;
        this.qty = qty;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setmedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

