package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bank_ui.databinding.ActivityCreditBinding;

public class CreditActivity extends AppCompatActivity {
    ActivityCreditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

//        binding.btnFirstQV.setOnClickListener(v -> {
//            startActivity(new Intent(this,ProductActivity.class));
//        });

        binding.icBack.setOnClickListener(v -> {
            finish();
        });

    }
}