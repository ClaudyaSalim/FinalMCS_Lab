package com.example.bluejack_pharmacy_final_mcs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.bluejack_pharmacy_final_mcs.database.UserHelper;
import com.example.bluejack_pharmacy_final_mcs.model.User;

import java.util.Random;

public class OtpActivity extends AppCompatActivity {

    private String otpSend;
    private EditText otpEt;
    private Button verifyButton;
    UserHelper userHelper = new UserHelper(this);
    private SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otpEt = findViewById(R.id.otp_edit);
        verifyButton = findViewById(R.id.buttonVerify);
        smsManager = SmsManager.getDefault();

//      generate OTP
        String otp = generateOtp();
        Log.e("String OTP Code", String.valueOf(otp));

//      get user
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("UserID", 0);
        Log.e("User ID at home", String.valueOf(userId));
        User user = userHelper.getUserByID(userId);
        String phone = user.getPhone();

        // chat
        checkPermission();
        smsManager.sendTextMessage(phone, null, "Bluejack Pharmacy\n " +
                "Your OTP Code is " + otp,null, null);

        verifyButton.setOnClickListener(e->{
            String input = otpEt.getText().toString();
            checkOtp(input, otp, user);
        });
    }

    public String generateOtp(){
        String otp="";
        for (int i = 0; i < 6; i++) {
            int digit = (int) Math.floor(Math.random()*10); // angka random
            String digitString = String.valueOf(digit); // convert ke string
            otp += digitString;
        }
        return otp;
    }

    public void checkPermission(){
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if(permission != PackageManager.PERMISSION_GRANTED){
            String[]permissions = {Manifest.permission.SEND_SMS};
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }

    public void checkOtp(String input, String otp, User user){
        if(input.equals(otp)){
            Intent toHome = new Intent(this, HomeActivity.class);
            user.setVerified(true);
            startActivity(toHome);
        }
        else {
            Toast.makeText(this, "Incorrect OTP code!", Toast.LENGTH_SHORT).show();
        }
    }
}