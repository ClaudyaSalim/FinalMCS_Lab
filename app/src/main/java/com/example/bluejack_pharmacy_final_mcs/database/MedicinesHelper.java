package com.example.bluejack_pharmacy_final_mcs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bluejack_pharmacy_final_mcs.model.Medic;
import com.example.bluejack_pharmacy_final_mcs.model.User;

import java.util.ArrayList;

public class MedicinesHelper {

    DatabaseHelper dbHelper;
    Context context;
    SQLiteDatabase db;

    public MedicinesHelper(Context context) {
        this.context = context;
    }

    // akan dipanggil tiap mau akses database
    public void open(){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // akan dipanggil ketika database tidak dipakai lagi
    public void close(){ dbHelper.close(); }

    public ArrayList <Medic> getAllMedics(){
        open();
        ArrayList<Medic>medicList = new ArrayList<>();
        String query = "SELECT * from Medicines";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String manufacturer = cursor.getString(2);
                int price = cursor.getInt(3);
                String image = cursor.getString(4);
                String desc = cursor.getString(5);
                Medic medic = new Medic(name, manufacturer, price, image ,desc);
                medic.setId(id);
                medicList.add(medic);
            } while(cursor.moveToNext());
        }
        close();
        return medicList;
    }

    public void insertMedicine(Medic medicine) {
        open();

        ContentValues values = new ContentValues();
        values.put("name", medicine.getName());
        values.put("manufacturer", medicine.getManufacturer());
        values.put("price", medicine.getPrice());
        values.put("description", medicine.getDescription());

        db.insert("Medicines", null, values);
        close();
    }

    public int getMedicCount(){
        open();
        Cursor cursor = db.rawQuery("SELECT * FROM " + "Medicines", null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

}
