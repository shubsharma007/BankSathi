package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
         
        getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new HomeFragment()).commit();
        binding.txtHii.setVisibility(View.VISIBLE);
        binding.txtName.setVisibility(View.VISIBLE);
        binding.icQuestion.setVisibility(View.VISIBLE);
        binding.maintext.setVisibility(View.GONE);
        binding.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new HomeFragment()).commit();
                    binding.txtHii.setVisibility(View.VISIBLE);
                    binding.txtName.setVisibility(View.VISIBLE);
                    binding.icQuestion.setVisibility(View.VISIBLE);
                    binding.maintext.setVisibility(View.GONE);
                    break;

                case R.id.leads:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new LeadFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.maintext.setText("Leads");
                    break;

                case R.id.referral:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new RrferralFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.maintext.setText("Referral");
                    break;
                case R.id.myTeam:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new MyTeamFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.maintext.setText("My Team");
                    break;
            }
            return true;
        });

//        binding.cardView7.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, CreditActivity.class);
//            startActivity(intent);
//
//        });

    }
}