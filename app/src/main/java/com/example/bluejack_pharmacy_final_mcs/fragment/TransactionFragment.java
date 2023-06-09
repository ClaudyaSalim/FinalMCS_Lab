package com.example.bluejack_pharmacy_final_mcs.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bluejack_pharmacy_final_mcs.R;
import com.example.bluejack_pharmacy_final_mcs.adapter.TransactionAdapter;
import com.example.bluejack_pharmacy_final_mcs.model.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionFragment extends Fragment {

//    private User user;
    View view;
    TextView userTransaction;
    RecyclerView transactionRv;
    TransactionAdapter tAdapter;

    public TransactionFragment() {
        // Required empty public constructor
    }

    public static TransactionFragment newInstance() {
        TransactionFragment fragment = new TransactionFragment();
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

        view = inflater.inflate(R.layout.fragment_transaction, container, false);
        userTransaction = view.findViewById(R.id.user_transaction_tv);
//        userTransaction.setText(user.getName() + "'s Transactions");
//
        transactionRv = view.findViewById(R.id.transaction_rv);
//        // make adapter
////        Log.e("Medic name", user.getUsertransactions().get(0).getMedic().getName());
        ArrayList<Transaction>transactions = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Date date = Date.valueOf(today.toString());
        transactions.add(new Transaction(1, 1, 15, date));
        tAdapter = new TransactionAdapter(this.getContext(), transactions);
        transactionRv.setAdapter(tAdapter);
        transactionRv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }
}