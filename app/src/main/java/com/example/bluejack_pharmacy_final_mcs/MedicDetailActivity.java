package com.example.bluejack_pharmacy_final_mcs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bluejack_pharmacy_final_mcs.database.MedicinesHelper;
import com.example.bluejack_pharmacy_final_mcs.database.TransactionsHelper;
import com.example.bluejack_pharmacy_final_mcs.model.Medic;
import com.example.bluejack_pharmacy_final_mcs.model.Transaction;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class MedicDetailActivity extends AppCompatActivity {

    ImageView medicImg;
    TextView medicNameTv, medicManufacturerTv, medicPriceTv, medicDescTv, medicQtyTv;
    EditText medicQtyEt;
    Button insertBtn;
    SharedPreferences sharedPreferences;
    int userId, medicId;
    Medic medic;
    MedicinesHelper medicinesHelper = new MedicinesHelper(this);
    TransactionsHelper transactionsHelper = new TransactionsHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_detail);
        medicImg = findViewById(R.id.medic_im);
        medicNameTv = findViewById(R.id.name_medic_tv);
        medicManufacturerTv = findViewById(R.id.manufacture_medic_tv);
        medicPriceTv = findViewById(R.id.price_medic_tv);
        medicDescTv = findViewById(R.id.desc_medic_tv);
        medicQtyTv = findViewById(R.id.qty_medic_tv);
        medicQtyEt = findViewById(R.id.qty_medic_et);
        insertBtn = findViewById(R.id.insert_btn);
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        userId = sharedPreferences.getInt("UserID", 0);
        medicId = getIntent().getIntExtra("MedicineID", 0);
        medic = medicinesHelper.getMedicByID(medicId);
//        user = (User) getIntent().getSerializableExtra("User");

//        medicImg.setImageDrawable(Drawable.createFromPath(medic.getImage()));
        Picasso.get().load(medic.getImage()).into(medicImg);
        Log.e("Medic image link", medic.getImage());
        medicNameTv.setText(medic.getName());
        medicManufacturerTv.setText(medic.getManufacturer());
        medicPriceTv.setText("Rp" + medic.getPrice());
        medicDescTv.setText(medic.getDescription());

        // bikin database transaction buat dapetin input qty
        insertBtn.setOnClickListener(e->{
            String qtyMedic = medicQtyEt.getText().toString();
            Log.i("Qty", qtyMedic);
            // validasi
            if(qtyMedic.equals("")){
                Toast.makeText(this, "Quantity must be filled!", Toast.LENGTH_SHORT).show();
                return;
            } else if (Integer.parseInt(qtyMedic)<=0) {
                Toast.makeText(this, "Quantity must be more than 0!", Toast.LENGTH_SHORT).show();
                return;
            }
            int qty = Integer.parseInt(qtyMedic);
            LocalDate today = LocalDate.now();
//            Date date = Date.valueOf(today.toString());
            String date = today.toString();
//            Transaction newTransaction = new Transaction(1, 1, 1, 15, date);
//            user.getUsertransactions().add(newTransaction);
            transactionsHelper.insertTransaction(new Transaction(medicId, userId, qty, date));
            Toast.makeText(this, "Transaction has been made!", Toast.LENGTH_SHORT).show();
            Intent toHome = new Intent(this, HomeActivity.class);
//            toHome.putExtra("Logged User", user);
            startActivity(toHome);
        });

    }
}