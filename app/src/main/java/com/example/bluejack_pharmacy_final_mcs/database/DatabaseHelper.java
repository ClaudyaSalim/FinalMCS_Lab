package com.example.bluejack_pharmacy_final_mcs.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    
    public DatabaseHelper(Context context) {
        super(context, "Bluejack", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUser = "CREATE TABLE Users" + "(" +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(50), " +
                "email varchar(50), " +
                "password varchar(20), " +
                "phone varchar(20), " +
                "isVerified varchar(10))";

        String queryMedic = "CREATE TABLE Medicines" + "(" +
                "medicineID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name varchar(50), " +
                "manufacturer varchar(50), " +
                "price integer, " +
                "image varchar(50), " +
                "description varchar(255))";

        db.execSQL(queryUser);
        db.execSQL(queryMedic);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryUser = "DROP TABLE Users";
        db.execSQL(queryUser);

        String queryMedicines = "DROP TABLE Medicines";
        db.execSQL(queryMedicines);

        onCreate(db);
    }
}
