package com.example.bank_ui.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bank_ui.BottomSheets.OtpVerification;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.ActivitySignupBinding;

public class Signup extends AppCompatActivity {
    ActivitySignupBinding binding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.clickForLogin.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.fullNameEt.getText().toString().isEmpty()) {
                    binding.fullNameEt.setError("please enter name");
                    binding.fullNameEt.requestFocus();
                } else if (binding.emailEt.getText().toString().isEmpty()) {
                    binding.emailEt.setError("please enter email address");
                    binding.emailEt.requestFocus();
                } else if (!binding.emailEt.getText().toString().contains(".") || !binding.emailEt.getText().toString().contains("@")) {
                    binding.emailEt.setError("please enter correct email address");
                    binding.emailEt.requestFocus();
                } else if (binding.mobileEt.getText().toString().isEmpty()) {
                    binding.mobileEt.setError("please enter mobile number");
                    binding.mobileEt.requestFocus();
                } else if (binding.mobileEt.getText().toString().length() < 10) {
                    binding.mobileEt.setError("please enter correct mobile number");
                    binding.mobileEt.requestFocus();
                } else {
                    OtpVerification otpFragment = new OtpVerification();
                    otpFragment.show(getSupportFragmentManager(), otpFragment.getTag());
                }
            }
        });
    }
}