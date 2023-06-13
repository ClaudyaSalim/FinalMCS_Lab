package com.example.bluejack_pharmacy_final_mcs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.bluejack_pharmacy_final_mcs.model.Medic;
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

    public ArrayList<Transaction> getAllTransactions() {
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
//                Date transactionDate = DateFormat.getDateInstance().parse(date);
                Transaction transaction = new Transaction(medicineID, userID, qty, date);
                transaction.setId(transactionID);
                transactionList.add(transaction);
            } while(cursor.moveToNext());
        }
        close();
        return transactionList;
    }

    public Transaction getTransactionByID(int id){
        open();

        String query = "SELECT * FROM Transactions WHERE transactionID=?";
        String[]whereParam = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(query, whereParam);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        int medicId = cursor.getInt(1);
        int userId = cursor.getInt(2);
        String date = cursor.getString(3);
        int qty = cursor.getInt(4);
        Transaction transaction = new Transaction(medicId, userId, qty, date);
        transaction.setId(id);

        close();
        return transaction;
    }

    public ArrayList<Transaction> getTransactionByUser(int userId){
        open();
        ArrayList<Transaction>transactionList = new ArrayList<>();
        String query = "SELECT * FROM Transactions WHERE userID=?";
        String[]whereParam = {String.valueOf(userId)};
        Cursor cursor = db.rawQuery(query, whereParam);

        if(cursor.moveToFirst()){
            do {
                int transactionID = cursor.getInt(0);
                int medicineID = cursor.getInt(1);
                int userID = cursor.getInt(2);
                String date = cursor.getString(3);
                int qty = cursor.getInt(4);
//                Date transactionDate = DateFormat.getDateInstance().parse(date);
                Transaction transaction = new Transaction(medicineID, userID, qty, date);
                transaction.setId(transactionID);
                transactionList.add(transaction);
            } while(cursor.moveToNext());
        }
        close();
        return transactionList;
    }

    public void insertTransaction(Transaction transaction){
        open();

        ContentValues values = new ContentValues();
        values.put("medicineID", transaction.getMedicineId());
        values.put("userID", transaction.getuserId());
        values.put("transactionDate", transaction.getDate());
        values.put("quantity", transaction.getQty());

        db.insert("Transactions", null, values);
        close();
    }

    public void updateTransaction(Transaction transaction, int qty){
        open();

        String[]whereParam = {String.valueOf(transaction.getId())};
        ContentValues values = new ContentValues();
        values.put("quantity", qty);
        int update = db.update("Transactions", values, "transactionID=?", whereParam);
        Log.i("Number of rows", String.valueOf(update));

        close();
    }

    public void deleteTransaction(Transaction transaction){
        open();

        String[]whereParam = {String.valueOf(transaction.getId())};
        db.delete("Transactions", "transactionID=?", whereParam);

        close();
    }

}
