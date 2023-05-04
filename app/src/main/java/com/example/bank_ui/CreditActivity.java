package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.bank_ui.Adapter.CreditCardAdapter;
import com.example.bank_ui.databinding.ActivityCreditBinding;

public class CreditActivity extends AppCompatActivity {
    ActivityCreditBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         
        binding.rCreditCard.setLayoutManager(new LinearLayoutManager(this));
        binding.rCreditCard.setAdapter(new CreditCardAdapter(CreditActivity.this));


        binding.icBack.setOnClickListener(v -> {
            finish();
        });

    }
}