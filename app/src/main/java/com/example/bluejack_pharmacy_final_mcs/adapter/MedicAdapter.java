package com.example.bluejack_pharmacy_final_mcs.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluejack_pharmacy_final_mcs.MedicDetailActivity;
import com.example.bluejack_pharmacy_final_mcs.model.Medic;
import com.example.bluejack_pharmacy_final_mcs.R;

import java.util.ArrayList;

public class MedicAdapter extends RecyclerView.Adapter<MedicAdapter.ViewHolder> {

    //    HomeFragment home;
    Context context;
    ArrayList<Medic> medicList;
//    User user;

    public MedicAdapter(Context context, ArrayList<Medic> medicList) {
        this.context = context;
        this.medicList = medicList;
//        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.card_medic, parent, false);
        return new ViewHolder(layoutView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        InputStream is = null;
//        try {
//            is = (InputStream) new URL(medicList.get(position).getImage()).getContent();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Drawable medicPhoto = Drawable.createFromStream(is, "Medicine");
        holder.medicIm.setImageDrawable(Drawable.createFromPath(medicList.get(position).getImage()));
//        holder.medicIm.setImageResource(medicList.get(position).getImage());
        holder.nameMedicTv.setText(medicList.get(position).getName());
        holder.manufactureMedicTv.setText(medicList.get(position).getManufacturer());
        holder.priceMedicTv.setText("Rp" + String.valueOf(medicList.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
//        return 0;
        return medicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView medicIm;
        TextView nameMedicTv, manufactureMedicTv, priceMedicTv;
        CardView medicCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            medicIm = itemView.findViewById(R.id.medic_im);
            nameMedicTv = itemView.findViewById(R.id.name_medic_tv);
            manufactureMedicTv = itemView.findViewById(R.id.manufacture_medic_tv);
            priceMedicTv = itemView.findViewById(R.id.price_medic_tv);

            medicCard = itemView.findViewById(R.id.medic_card);

            medicCard.setOnClickListener(e-> {
                Intent toDetail = new Intent(context, MedicDetailActivity.class);
//                toDetail.putExtra("User", user);
                toDetail.putExtra("Medicine", medicList.get(getAdapterPosition()));
                context.startActivity(toDetail);
            });
        }
    }
}
