package com.example.bank_ui.LoginActivity;

import static android.os.Build.VERSION_CODES.R;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.bank_ui.BottomSheets.OtpVerification;
import com.example.bank_ui.MainActivity;
import com.example.bank_ui.WelcomeActivity.SliderActivity;
import com.example.bank_ui.WelcomeActivity.Splash;
import com.example.bank_ui.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.clickForSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, Signup.class);
                startActivity(intent);
                finish();
            }
        });


        binding.mainLayout.setBackgroundColor(Color.WHITE);
        binding.scroll.setVisibility(View.GONE);
        binding.scroll.setVisibility(View.GONE);
        binding.introAnimation.setVisibility(View.VISIBLE);
        binding.loginBtn.setOnClickListener(view -> {

            OtpVerification otpFragment = new OtpVerification();
            otpFragment.show(getSupportFragmentManager(), otpFragment.getTag());
        });
    }

    @Override

    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.mainLayout.setBackgroundColor(Color.BLUE);
                binding.scroll.setVisibility(View.VISIBLE);
                binding.scroll.setVisibility(View.VISIBLE);
                binding.introAnimation.setVisibility(View.GONE);
            }

        }, 5000);

    }
}