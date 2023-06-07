package com.example.bluejack_pharmacy_final_mcs.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bluejack_pharmacy_final_mcs.AboutActivity;
import com.example.bluejack_pharmacy_final_mcs.R;
import com.example.bluejack_pharmacy_final_mcs.adapter.MedicAdapter;
import com.example.bluejack_pharmacy_final_mcs.model.Medic;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

//    private User user;
    ImageView aboutIm;
    View homeView;
    RecyclerView medicRv;
    MedicAdapter medicAdapter;
//    MedicDatabase dbMedic;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
//        args.putSerializable("Logged User", currUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            user = (User) getArguments().getSerializable("Logged User");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView cekName = homeView.findViewById(R.id.cek_name);
        cekName.setText("Tap on logo to see about us");

        aboutIm = homeView.findViewById(R.id.about_im);
        aboutIm.setOnClickListener(e->{
            Intent toAbout = new Intent(this.getContext(), AboutActivity.class);
//            toAbout.putExtra("Logged User", user);
            startActivity(toAbout);
        });

//        dbMedic = new MedicDatabase();
//        dbMedic.addMedic();

        ArrayList<Medic> medics = new ArrayList<>();
        medics.add(new Medic(1, "Flowflex COVID-19 Antigen Home Test - 1.0 ea",
                "Flowflex", 100000,
                "https://cdn11.bigcommerce.com/s-fe8s4uj/images/stencil/960w/products/63584/132595/8260766026__76719.1667473645.jpg?c=2",
                "The Flowflex™ COVID-19 Antigen Home Test is all you need to determine your family’s Covid-19 status, whether symptoms are present or not. Can be used on children as young as 2 years old. Get the convenience of Flowflex! -Easy and Affordable -Highly Accurate Nasal Swab Test -Quick Results in 15 minutes -Safe for children as young as 2 years old -For use with and without symptoms -No need to send off to a lab to obtain results -Compact packaging for “On-The-Go” testing")
        );
        medics.add(new Medic(1, "Swan Iodine Tincture First Aid Antiseptic - 1 oz",
                "SWAN", 40000,
                "https://cdn11.bigcommerce.com/s-fe8s4uj/images/stencil/960w/products/63584/132595/8260766026__76719.1667473645.jpg?c=2",
                "Clean the affected area. Apply a small amount on the area 1 to 3 times daily. May be covered with a sterile bandage. If bandaged let dry first. Product will stain skin and clothing.")
        );

        medicRv = homeView.findViewById(R.id.medic_rv);
        medicAdapter = new MedicAdapter(this.getContext(), medics);
        medicRv.setAdapter(medicAdapter);
        medicRv.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        return homeView;
    }
}