package com.example.bluejack_pharmacy_final_mcs.model;

import java.io.Serializable;
import java.util.Date;

public class Transaction {

    private int id, mId, uId, qty;
    private Date date;

    public Transaction(int mId, int uId, int qty, Date date) {
        this.mId = mId;
        this.uId = uId;
        this.qty = qty;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

