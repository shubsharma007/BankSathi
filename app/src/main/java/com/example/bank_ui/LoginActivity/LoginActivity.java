package com.example.bank_ui.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bank_ui.BottomSheets.OtpVerification;
import com.example.bank_ui.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.btnLogin.setOnClickListener(view -> {

            OtpVerification otpFragment = new OtpVerification();
            otpFragment.show(getSupportFragmentManager(),otpFragment.getTag());
        });
    }
}