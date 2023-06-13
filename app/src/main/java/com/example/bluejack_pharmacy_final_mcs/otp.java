package com.example.bluejack_pharmacy_final_mcs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class otp extends AppCompatActivity {
    private EditText[] otpEditText;
    private static final int SMS_PERMISSION_REQUEST_CODE = 1;
    private Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otpEditText = new EditText[6];
        otpEditText[0] = findViewById(R.id.editTextNumber2);
        otpEditText[1] = findViewById(R.id.editTextNumber3);
        otpEditText[2] = findViewById(R.id.editTextNumber4);
        otpEditText[3] = findViewById(R.id.editTextNumber5);
        otpEditText[4] = findViewById(R.id.editTextNumber6);
        otpEditText[5] = findViewById(R.id.editTextNumber7);

        verifyButton = findViewById(R.id.buttonVerify);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP = getEnteredOTP();
                String generatedOTP = "";

                if(enteredOTP.isEmpty()){
                    showToast("Please enter the OTP code");
                } else if (!enteredOTP.equals(generatedOTP)) {
                    showToast("Invalid OTP code");
                    //intent ke home
                    Intent intent = new Intent(otp.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showToast("OTP verification successful");
                    Intent intent = new Intent(otp.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // SMS
        requestSMSPermission();
    }
    // Method untuk meminta izin mengirim SMS
    private void requestSMSPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_REQUEST_CODE);
        }
    }

    // Callback yang dipanggil setelah pengguna memberikan atau menolak izin
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Izin diberikan, Anda dapat mengirim SMS
            } else {
                // Izin ditolak, Anda tidak dapat mengirim SMS
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getEnteredOTP(){
        StringBuilder otpBuilder = new StringBuilder();
        for (EditText editText : otpEditText){
            otpBuilder.append(editText.getText().toString());
        }
        return otpBuilder.toString();
    }
    private void showToast(String message){
        switch (message){
            case "empty_otp":
                Toast.makeText(this, "Please enter the OTP code", Toast.LENGTH_SHORT).show();
                break;
            case "invalid_otp":
                Toast.makeText(this, "Invalid OTP code", Toast.LENGTH_SHORT).show();
                break;
            case "verification_success":
                Toast.makeText(this, "OTP verification successful", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

}