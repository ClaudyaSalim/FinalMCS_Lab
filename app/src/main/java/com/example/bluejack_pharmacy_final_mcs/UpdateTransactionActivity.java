package com.example.bluejack_pharmacy_final_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluejack_pharmacy_final_mcs.database.MedicinesHelper;
import com.example.bluejack_pharmacy_final_mcs.database.TransactionsHelper;
import com.example.bluejack_pharmacy_final_mcs.model.Medic;
import com.example.bluejack_pharmacy_final_mcs.model.Transaction;

public class UpdateTransactionActivity extends AppCompatActivity {

//    User user;
    Transaction transaction;
    TextView date, medicName, medicPrice;
    EditText qtyEt;
    Button updateBtn, cancelBtn;
    int transactionId, medicineId;
    TransactionsHelper transactionsHelper = new TransactionsHelper(this);
    Medic medic;
    MedicinesHelper medicinesHelper = new MedicinesHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaction);

//        user = (User) getIntent().getSerializableExtra("Logged User");
//        transaction = (Transaction) getIntent().getSerializableExtra("Transaction");

        date = findViewById(R.id.t_date);
        medicName = findViewById(R.id.medic_name);
        medicPrice = findViewById(R.id.medic_price);
        qtyEt = findViewById(R.id.qty_medic_et);
        updateBtn = findViewById(R.id.update_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        transactionId = getIntent().getIntExtra("TransactionID", 0);
        medicineId = getIntent().getIntExtra("MedicineID", 0);
        transaction = transactionsHelper.getTransactionByID(transactionId);
        medic = medicinesHelper.getMedicByID(transaction.getMedicineId());
        date.setText(transaction.getDate());
        medicName.setText(medic.getName());
        medicPrice.setText(String.valueOf(medic.getPrice()));

        updateBtn.setOnClickListener(e-> {
            String qtyText = qtyEt.getText().toString();
            Log.e("Qty", qtyText);
            if(qtyText.equals("")){
                Toast.makeText(this, "Quantity must be filled!", Toast.LENGTH_SHORT).show();
                return;
            } else if (Integer.parseInt(qtyText)<=0) {
                Toast.makeText(this, "Quantity must be more than 0!", Toast.LENGTH_SHORT).show();
                return;
            }

            int qty = Integer.parseInt(qtyEt.getText().toString());
//            transaction.setQty(qty);
            // panggil helper buat update transaction
            transactionsHelper.updateTransaction(transaction, qty);
//            Log.e("Transaction index", String.valueOf(transaction.gettId()));

//            user.getUsertransactions().get(transaction.gettId()-1).setQty(qty);
            Toast.makeText(this, "Transaction has been updated", Toast.LENGTH_SHORT).show();
            // intent to home activity
            Intent toHome = new Intent(this, HomeActivity.class);
//            toHome.putExtra("Logged User", user);
            startActivity(toHome);
        });

        cancelBtn.setOnClickListener(e->{
            // intent to home activity
            Intent toHome = new Intent(this, HomeActivity.class);
//            toHome.putExtra("Logged User", user);
            startActivity(toHome);
        });


    }
}