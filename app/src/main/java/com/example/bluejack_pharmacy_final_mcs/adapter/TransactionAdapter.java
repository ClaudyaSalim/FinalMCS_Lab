package com.example.bluejack_pharmacy_final_mcs.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluejack_pharmacy_final_mcs.HomeActivity;
import com.example.bluejack_pharmacy_final_mcs.R;
import com.example.bluejack_pharmacy_final_mcs.UpdateTransactionActivity;
import com.example.bluejack_pharmacy_final_mcs.database.MedicinesHelper;
import com.example.bluejack_pharmacy_final_mcs.database.TransactionsHelper;
import com.example.bluejack_pharmacy_final_mcs.model.Medic;
import com.example.bluejack_pharmacy_final_mcs.model.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    Context context;
    ArrayList<Transaction> tList;
    MedicinesHelper medicinesHelper;
    TransactionsHelper transactionsHelper;

    public TransactionAdapter(Context context, ArrayList<Transaction> tList) {
        this.context = context;
        this.tList = tList;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.card_transaction, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        int medicineId = tList.get(position).getMedicineId();
        Transaction transaction = tList.get(position);
        medicinesHelper = new MedicinesHelper(context);
        Medic medic = medicinesHelper.getMedicByID(medicineId);
        holder.date.setText(transaction.getDate());
        holder.medicName.setText(medic.getName());
        holder.medicPrice.setText("Rp" + medic.getPrice()*transaction.getQty());
        holder.qtyNum.setText(String.valueOf(transaction.getQty()));
    }

    @Override
    public int getItemCount() {
        if(tList.size()==0){
            return 0;
        }
        else {
            Log.i("Size of array", String.valueOf(tList.size()));
            return tList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView date, medicName, medicPrice, qtyText, qtyNum;
        Button updateBtn, deleteBtn;
        CardView tCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.transaction_date_tv);
            medicName = itemView.findViewById(R.id.medic_name_tv);
            medicPrice = itemView.findViewById(R.id.medic_price_tv);
            qtyText = itemView.findViewById(R.id.quantity_tv);
            qtyNum = itemView.findViewById(R.id.num_quantity_tv);
            updateBtn = itemView.findViewById(R.id.update_btn);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

            tCard = itemView.findViewById(R.id.transaction_card);

            updateBtn.setOnClickListener(e->{
                Intent toUpdateT = new Intent(context, UpdateTransactionActivity.class);
                toUpdateT.putExtra("TransactionID", tList.get(getAdapterPosition()).getId());
                toUpdateT.putExtra("MedicineID", tList.get(getAdapterPosition()).getMedicineId());
                context.startActivity(toUpdateT);
            });

            deleteBtn.setOnClickListener(e->{
                // panggil helper buat delete transaksi
                transactionsHelper = new TransactionsHelper(context);
                transactionsHelper.deleteTransaction(tList.get(getAdapterPosition()));
                Toast.makeText(context, "Transaction has been deleted", Toast.LENGTH_SHORT).show();
                Intent toHome = new Intent(context, HomeActivity.class);
                context.startActivity(toHome);
            });

        }
    }
}
