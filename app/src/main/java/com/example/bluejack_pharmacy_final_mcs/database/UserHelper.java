package com.example.bluejack_pharmacy_final_mcs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.bluejack_pharmacy_final_mcs.model.User;
import java.util.ArrayList;

public class UserHelper {
    DatabaseHelper dbHelper;
    Context context;
    SQLiteDatabase db;

    public UserHelper(Context context) {
        this.context = context;
    }

    // akan dipanggil tiap mau akses database
    public void open(){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // akan dipanggil ketika database tidak dipakai lagi
    public void close(){ dbHelper.close(); }

    public ArrayList<User> getAllUsers(){
        open();
        ArrayList<User> userList = new ArrayList();
        String query = "SELECT * from Users";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String pass = cursor.getString(3);
                String phone = cursor.getString(4);
                String isVerifStr = cursor.getString(5);
                boolean isVerified = Boolean.parseBoolean(isVerifStr);
                User user = new User(name, email, phone, pass, isVerified);
                user.setId(id);
                userList.add(user);
            } while(cursor.moveToNext());
        }
        close();
        return userList;
    }

//    public void getUserByLogin(String email, String pass){}

    public User getUserByID(int id){
        open();

        String query = "SELECT * FROM Users WHERE UserID=?";
        String[]whereParam = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(query, whereParam);
        if(cursor!=null){
            cursor.moveToFirst();
        }

        String name = cursor.getString(1);
        String email = cursor.getString(2);
        String pass = cursor.getString(3);
        String phone = cursor.getString(4);
        String isVerifStr = cursor.getString(5);
        boolean isVerified = Boolean.parseBoolean(isVerifStr);
        User user = new User(name, email, phone, pass, isVerified);
        user.setId(id);

        close();
        return user;
    }

    public void regisUser(User user){
        open();
        ContentValues values = new ContentValues();
//        values.put("userID", user.getId());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPass());
        values.put("phone", user.getPhone());
        values.put("isVerified", user.isVerified());

        db.insert("Users", null, values);
        close();
    }

    public void updateUserVerified(User user){

    }
}
