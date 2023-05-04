package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.bank_ui.Fragment.MyStatsFragment;
import com.example.bank_ui.Fragment.ProductDetailFragment;
import com.example.bank_ui.Fragment.TrainingFragment;
import com.example.bank_ui.databinding.ActivityProductBinding;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.secondHome, new MyStatsFragment()).commit();


        binding.btnDetail.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.secondHome, new ProductDetailFragment()).commit();
            binding.btnDetail.setBackgroundResource(R.drawable.bg_button);
            binding.btnDetail.setTextColor(getResources().getColor(R.color.white));
            binding.btnMyStats.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnMyStats.setTextColor(getResources().getColor(R.color.black));
            binding.btnTraining.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnTraining.setTextColor(getResources().getColor(R.color.black));
        });

        binding.btnTraining.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.secondHome, new TrainingFragment()).commit();
            binding.btnTraining.setBackgroundResource(R.drawable.bg_button);
            binding.btnTraining.setTextColor(getResources().getColor(R.color.white));
            binding.btnMyStats.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnMyStats.setTextColor(getResources().getColor(R.color.black));
            binding.btnDetail.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnDetail.setTextColor(getResources().getColor(R.color.black));
        });
        binding.buttonBack.setOnClickListener(v -> {
            finish();
        });

        binding.btnMyStats.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.secondHome, new MyStatsFragment()).commit();
            binding.btnMyStats.setBackgroundResource(R.drawable.bg_button);
            binding.btnMyStats.setTextColor(getResources().getColor(R.color.white));
            binding.btnTraining.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnTraining.setTextColor(getResources().getColor(R.color.black));
            binding.btnDetail.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnDetail.setTextColor(getResources().getColor(R.color.black));
        });

        binding.btnMarketing.setOnClickListener(v -> {
            startActivity(new Intent(this, ProductDetailAddActivity.class));

        });

    }
}