package com.example.bluejack_pharmacy_final_mcs.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.bluejack_pharmacy_final_mcs.R;
import com.example.bluejack_pharmacy_final_mcs.adapter.TransactionAdapter;
import com.example.bluejack_pharmacy_final_mcs.database.TransactionsHelper;
import com.example.bluejack_pharmacy_final_mcs.database.UserHelper;
import com.example.bluejack_pharmacy_final_mcs.model.Transaction;
import com.example.bluejack_pharmacy_final_mcs.model.User;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionFragment extends Fragment {

    View view;
    TextView userTransaction;
    int userId;
    UserHelper userHelper;
    TransactionsHelper transactionsHelper;
    RecyclerView transactionRv;
    TransactionAdapter tAdapter;

    public TransactionFragment() {
        // Required empty public constructor
    }

    public static TransactionFragment newInstance(int userId) {
        TransactionFragment fragment = new TransactionFragment();
        Bundle args = new Bundle();
        args.putInt("UserID", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt("UserID");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_transaction, container, false);
        userTransaction = view.findViewById(R.id.user_transaction_tv);

        userHelper = new UserHelper(this.getContext());
        User user = userHelper.getUserByID(userId);
        userTransaction.setText(user.getName() + "'s Transactions");

        transactionRv = view.findViewById(R.id.transaction_rv);
        transactionsHelper = new TransactionsHelper(this.getContext());
        ArrayList<Transaction>transactions = new ArrayList<>();
        transactions = transactionsHelper.getTransactionByUser(userId);
        tAdapter = new TransactionAdapter(this.getContext(), transactions);
        transactionRv.setAdapter(tAdapter);
        transactionRv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return view;
    }
}