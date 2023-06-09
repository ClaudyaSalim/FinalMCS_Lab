package com.example.bluejack_pharmacy_final_mcs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bluejack_pharmacy_final_mcs.model.Transaction;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class TransactionsHelper {

    DatabaseHelper dbHelper;
    Context context;
    SQLiteDatabase db;

    public TransactionsHelper(Context context) {
        this.context = context;
    }

    // akan dipanggil tiap mau akses database
    public void open(){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // akan dipanggil ketika database tidak dipakai lagi
    public void close(){ dbHelper.close(); }

    public ArrayList<Transaction> getAllTransactions() throws ParseException {
        open();
        ArrayList<Transaction>transactionList = new ArrayList<>();
        String query = "SELECT * from Transactions";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int transactionID = cursor.getInt(0);
                int medicineID = cursor.getInt(1);
                int userID = cursor.getInt(2);
                String date = cursor.getString(3);
                int qty = cursor.getInt(4);
                Date transactionDate = DateFormat.getDateInstance().parse(date);
                Transaction transaction = new Transaction(medicineID, userID, qty, transactionDate);
                transactionList.add(transaction);
            } while(cursor.moveToNext());
        }
        close();
        return transactionList;
    }

    public void insertTransaction(Transaction transaction){
        open();

        ContentValues values = new ContentValues();
        values.put("medicineID", transaction.getmId());
        values.put("userID", transaction.getuId());
        values.put("transactionDate", transaction.getDate().toString());
        values.put("quantity", transaction.getQty());

        db.insert("Medicines", null, values);
        close();
    }

}
