package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bank_ui.Adapter.CreditCardAdapter;
import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.Model.CreditCardResponse;
import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.databinding.ActivityCreditBinding;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditActivity extends AppCompatActivity {
    ActivityCreditBinding binding;
    ApiInterface apiInterface;
    String from;
    Call<List<CreditCardResponse>> call;

    private static final String TAG = "CreditActivityLOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);


        from = getIntent().getStringExtra("from");

        if (Objects.equals(from, "CC")) {
            call = apiInterface.getCreditCard();
        } else if (Objects.equals(from, "DA")) {
            call = apiInterface.getDemetAccount();
        } else if (Objects.equals(from, "BA")) {
            call = apiInterface.getBankAccount();
        } else if (Objects.equals(from, "CL")) {

        } else if (Objects.equals(from, "PL")) {
            call = apiInterface.getPersonalLoan();
        }
        //ITR
        else {


        }

        binding.loadingCard.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<CreditCardResponse>>() {
            @Override
            public void onResponse(Call<List<CreditCardResponse>> call, Response<List<CreditCardResponse>> response) {
                if (response.isSuccessful()) {
                    binding.loadingCard.setVisibility(View.GONE);
                    List<CreditCardResponse> res = response.body();
                    binding.rCreditCard.setLayoutManager(new LinearLayoutManager(CreditActivity.this));
                    binding.rCreditCard.setAdapter(new CreditCardAdapter(CreditActivity.this, res, from));


                } else {
                    Log.d(TAG, response.message());
                    binding.loadingCard.setVisibility(View.GONE);
                    Toast.makeText(CreditActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CreditCardResponse>> call, Throwable t) {
                binding.loadingCard.setVisibility(View.GONE);
                Log.d(TAG, t.getMessage());
                Toast.makeText(CreditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        binding.icBack.setOnClickListener(v -> {
            finish();
        });

    }
}