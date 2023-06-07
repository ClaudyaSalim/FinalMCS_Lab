package com.example.bluejack_pharmacy_final_mcs.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluejack_pharmacy_final_mcs.HomeActivity;
import com.example.bluejack_pharmacy_final_mcs.R;
import com.example.bluejack_pharmacy_final_mcs.UpdateTransactionActivity;
import com.example.bluejack_pharmacy_final_mcs.model.Transaction;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    Context context;
    ArrayList<Transaction> tList;
//    User user;

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
        holder.date.setText(tList.get(position).getDate().toString());
//        holder.medicName.setText(tList.get(position).getmId());
//        holder.medicPrice.setText("Rp" + String.valueOf(tList.get(position).getmId()));
        holder.qtyNum.setText(String.valueOf(tList.get(position).getQty()));
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
                context.startActivity(toUpdateT);
            });

            deleteBtn.setOnClickListener(e->{
                tList.remove(getAdapterPosition());
                Intent toHome = new Intent(context, HomeActivity.class);
                context.startActivity(toHome);
            });

        }
    }
}
