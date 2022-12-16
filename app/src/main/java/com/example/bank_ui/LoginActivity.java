package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bank_ui.Fragment.OTPFragment;
import com.example.bank_ui.databinding.ActivityLoginBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.btnLogin.setOnClickListener(view -> {

            OTPFragment otpFragment = new OTPFragment();
            otpFragment.show(getSupportFragmentManager(),otpFragment.getTag());
        });
    }
}