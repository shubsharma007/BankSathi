package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.Fragment.MyStatsFragment;
import com.example.bank_ui.Fragment.ProductDetailFragment;
import com.example.bank_ui.Fragment.TrainingFragment;
import com.example.bank_ui.Model.CreditCardResponse;
import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.databinding.ActivityProductBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    int Id;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        Id = getIntent().getIntExtra("Id", 0);
        Log.d("ID aarahi hai", String.valueOf(Id));

        getApi();

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

    private void getApi() {
        Call<CreditCardResponse> call = apiInterface.getSingleCard(Id);
        call.enqueue(new Callback<CreditCardResponse>() {
            @Override
            public void onResponse(Call<CreditCardResponse> call, Response<CreditCardResponse> response) {
                if (response.isSuccessful()) {
                    CreditCardResponse res = response.body();
                    Glide.with(ProductActivity.this).load(res.getBanklogo()).into(binding.imageView);
                    binding.cardName.setText(res.getCardname());
                    binding.txtCardDes.setText(res.getDiscription());
                    binding.txtEarning.setText(res.getTotalEarn());
                    binding.txtLeads.setText(res.getTotalLead());
                    binding.txtSales.setText(res.getToatlSale());

                } else {
                    Toast.makeText(ProductActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreditCardResponse> call, Throwable t) {
                Toast.makeText(ProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}