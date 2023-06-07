package com.example.bluejack_pharmacy_final_mcs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluejack_pharmacy_final_mcs.model.Transaction;

public class UpdateTransactionActivity extends AppCompatActivity {

//    User user;
    Transaction transaction;
    TextView date, medicName, medicPrice;
    EditText qtyEt;
    Button updateBtn, cancelBtn;

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

//        date.setText(transaction.getDate().toString());
//        medicName.setText(transaction.getMedic().getName());
//        medicPrice.setText(String.valueOf(transaction.getMedic().getPrice()));

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
//            Log.e("Transaction index", String.valueOf(transaction.gettId()));
//            int qty = Integer.parseInt(qtyEt.getText().toString());
//            user.getUsertransactions().get(transaction.gettId()-1).setQty(qty);
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