package com.example.bank_ui.WelcomeActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.bank_ui.LoginActivity.LoginActivity;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {
    ActivitySplashBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        //  sharedPreferences
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.sharedPreferenceName), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        binding.imageView3.animate().scaleXBy(0.3f).setDuration(2000);
        binding.imageView3.animate().scaleYBy(0.3f).setDuration(2000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sharedPreferences.contains("onBoarding")) {
                    intent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    editor.putBoolean("onBoarding", true);
                    editor.apply();
                    intent = new Intent(Splash.this, SliderActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, 2300);
    }
}