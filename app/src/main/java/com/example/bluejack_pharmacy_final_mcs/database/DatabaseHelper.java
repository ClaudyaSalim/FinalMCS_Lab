package com.example.bluejack_pharmacy_final_mcs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

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

//        dropTableTransactions(db);
        String query = "CREATE TABLE Transactions (" +
                "transactionID INTEGER PRIMARY KEY, " +
                "medicineID INTEGER, " +
                "userID INTEGER, " +
                "transactionDate DATE, " +
                "quantity INTEGER, " +
                "FOREIGN KEY(medicineID) REFERENCES Medicines(medicineID), " +
                "FOREIGN KEY(userID) REFERENCES Users (userID))";

        db.execSQL(query);

//        createTableTransactions(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTableUsers(db);
        dropTableMedicines(db);
        dropTableTransactions(db);

        onCreate(db);
    }

    private void createTableUsers(SQLiteDatabase db) {
        String query = "CREATE TABLE Users" + "(" +
                "userID INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "email TEXT, " +
                "password TEXT, " +
                "phone TEXT, " +
                "isVerified TEXT)";
        db.execSQL(query);
    }

    private void createTableMedicines(SQLiteDatabase db) {
        String query = "CREATE TABLE Medicines (" +
                "medicineID INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "manufacturer TEXT, " +
                "price INTEGER, " +
                "image TEXT, " +
                "description TEXT)";
        db.execSQL(query);
    }

    private void createTableTransactions(SQLiteDatabase db) {
        String query = "CREATE TABLE Transactions (" +
                "tID INTEGER PRIMARY KEY, " +
                "medicineID INTEGER, " +
                "userID INTEGER, " +
                "transactionDate DATE, " +
                "quantity INTEGER, " +
                "FOREIGN KEY(medicineID) REFERENCES Medicines(medicineID), " +
                "FOREIGN KEY(userID) REFERENCES Users (userID))";
        db.execSQL(query);
    }

    private void dropTableUsers(SQLiteDatabase db) {
        String query = "DROP TABLE IF EXISTS Users";
        db.execSQL(query);
    }

    private void dropTableMedicines(SQLiteDatabase db) {
        String query = "DROP TABLE IF EXISTS Medicines";
        db.execSQL(query);
    }

    private void dropTableTransactions(SQLiteDatabase db) {
        String query = "DROP TABLE IF EXISTS Transactions";
        db.execSQL(query);
    }
}
