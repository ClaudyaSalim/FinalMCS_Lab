package com.example.bluejack_pharmacy_final_mcs.model;

public class User {

    private int id;
    private String name, email, phone, pass;
    private boolean isVerified;

    public User(String name, String email, String phone, String pass, boolean isVerified) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pass = pass;
        this.isVerified = isVerified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
