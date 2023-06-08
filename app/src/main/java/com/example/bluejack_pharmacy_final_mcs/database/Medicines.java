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

    public class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "BluejackPharmacyDB";
        private static final int DATABASE_VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            createTableUsers(db);
            createTableMedicines(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            dropTableUsers(db);
            dropTableMedicines(db);
            onCreate(db);
        }

        private void createTableUsers(SQLiteDatabase db) {
            String query = "CREATE TABLE Users (" +
                    "userID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "phone TEXT, " +
                    "isVerified TEXT)";
            db.execSQL(query);
        }

        private void dropTableUsers(SQLiteDatabase db) {
            String query = "DROP TABLE IF EXISTS Users";
            db.execSQL(query);
        }

        private void createTableMedicines(SQLiteDatabase db) {
            String query = "CREATE TABLE Medicines (" +
                    "medicineID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "manufacturer TEXT, " +
                    "price REAL, " +
                    "description TEXT)";
            db.execSQL(query);
        }

        private void dropTableMedicines(SQLiteDatabase db) {
            String query = "DROP TABLE IF EXISTS Medicines";
            db.execSQL(query);
        }

        public void insertMedicine(Medicines medicine) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", medicine.getName());
            values.put("manufacturer", medicine.getManufacturer());
            values.put("price", medicine.getPrice());
            values.put("description", medicine.getDescription());

            db.insert("Medicines", null, values);
            db.close();
        }

    }
}
