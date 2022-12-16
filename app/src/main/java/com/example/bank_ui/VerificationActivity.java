package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        getSupportActionBar().hide();
    }
}