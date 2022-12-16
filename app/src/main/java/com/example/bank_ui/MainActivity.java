package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bank_ui.Fragment.HomeFragment;
import com.example.bank_ui.Fragment.LeadFragment;
import com.example.bank_ui.Fragment.MyTeamFragment;
import com.example.bank_ui.Fragment.RrferralFragment;
import com.example.bank_ui.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.firstHome, new HomeFragment()).commit();
                    break;

                case R.id.leads:
                    getSupportFragmentManager().beginTransaction().replace(R.id.firstHome, new LeadFragment()).commit();
                    break;

                case R.id.referral:
                    getSupportFragmentManager().beginTransaction().replace(R.id.firstHome, new RrferralFragment()).commit();
                    break;
                case R.id.myTeam:
                    getSupportFragmentManager().beginTransaction().replace(R.id.firstHome, new MyTeamFragment()).commit();
                    break;
            }
            return true;
        });

        binding.cardView7.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,CreditActivity.class);
            startActivity(intent);

        });

    }
}