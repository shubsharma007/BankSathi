package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bank_ui.Adapter.CreditCardAdapter;
import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.Model.CreditCardResponse;
import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.databinding.ActivityCreditBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditActivity extends AppCompatActivity {
    ActivityCreditBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        Call<List<CreditCardResponse>> listCall = apiInterface.getCreditCard();
        listCall.enqueue(new Callback<List<CreditCardResponse>>() {
            @Override
            public void onResponse(Call<List<CreditCardResponse>> call, Response<List<CreditCardResponse>> response) {
                if (response.isSuccessful()) {
                    List<CreditCardResponse> res = response.body();
                    binding.rCreditCard.setLayoutManager(new LinearLayoutManager(CreditActivity.this));
                    binding.rCreditCard.setAdapter(new CreditCardAdapter(CreditActivity.this,res));



                } else {
                    Toast.makeText(CreditActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CreditCardResponse>> call, Throwable t) {
                Toast.makeText(CreditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        binding.icBack.setOnClickListener(v -> {
            finish();
        });

    }
}