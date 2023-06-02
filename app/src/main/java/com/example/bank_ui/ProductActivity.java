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

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    int Id;

    ApiInterface apiInterface;
    Call<CreditCardResponse> call;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        getSupportFragmentManager().beginTransaction().replace(R.id.secondHome, new MyStatsFragment()).commit();
        //fragment change
        changeFragment();
        clickListener();
        Id = getIntent().getIntExtra("Id", 0);
        Log.d("ID aarahi hai", String.valueOf(Id));
        from = getIntent().getStringExtra("from");
        Log.d("Frommekyahai", from);
        if (Objects.equals(from, "CC")) {
            call = apiInterface.getSingleCard(Id);
        } else if (Objects.equals(from, "DA")) {
            call = apiInterface.getSingleDemetAccount(Id);
        } else if (Objects.equals(from, "BA")) {
            call = apiInterface.getSingleBankAccount(Id);
        } else if (Objects.equals(from, "GL")) {
            call = apiInterface.getSingleGoldLoan(Id);
        } else if (Objects.equals(from, "PL")) {
            call = apiInterface.getSinglePersonalLoan(Id);
            //Insurance(IN)
        } else {
            call = apiInterface.getSingleInsurance(Id);
        }
        call.enqueue(new Callback<CreditCardResponse>() {
            @Override
            public void onResponse(Call<CreditCardResponse> call, Response<CreditCardResponse> response) {
                if (response.isSuccessful()) {
                    CreditCardResponse res = response.body();

                    if (from.equals("CC")) {
                        binding.cardName.setText(res.getCardname());
                    } else if (from.equals("DA")) {
                        binding.cardName.setText(res.getDematname());
                    } else if (from.equals("BA")) {
                        binding.cardName.setText(res.getBankname());
                    } else if (from.equals("PL")) {
                        binding.cardName.setText(res.getPsname());
                    } else if (from.equals("GL")) {
                        binding.cardName.setText(res.getInvname());
                    } else {
                        binding.cardName.setText(res.getClname());
                    }

                    Glide.with(ProductActivity.this).load(res.getBanklogo()).into(binding.imageView);
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

    private void clickListener() {
        binding.buttonBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void changeFragment() {

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