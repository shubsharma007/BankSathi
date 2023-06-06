package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.Fragment.MyStatsFragment;
import com.example.bank_ui.Fragment.ProductDetailFragment;
import com.example.bank_ui.Fragment.TrainingFragment;
import com.example.bank_ui.Model.CC.GetCCLead;
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
    private static final String TAG = "TAGTAGTAG";
    ApiInterface apiInterface;
    Call<CreditCardResponse> call;
    Call<GetCCLead> callLead;
    String from;
    MyStatsFragment myStatsFragment;
    ProductDetailFragment productDetailFragment;
    TrainingFragment trainingFragment;
    SharedPreferences sharedPreferences;

    int myId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("bank", MODE_PRIVATE);
        myId = sharedPreferences.getInt("id", 0);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        Id = getIntent().getIntExtra("Id", 0);
        from = getIntent().getStringExtra("from");

        binding.dataFound.setVisibility(View.GONE);

        myStatsFragment = new MyStatsFragment();
        productDetailFragment = new ProductDetailFragment();
        trainingFragment = new TrainingFragment();
        Bundle bundle = new Bundle();
        bundle.putString("from", from);
        bundle.putInt("Id", Id);
        myStatsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.secondHome, myStatsFragment).commit();


        //fragment change
        changeFragment();
        clickListener();

        Log.d("ID aarahi hai", String.valueOf(Id));

        Log.d("Frommekyahai", from);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Objects.equals(from, "CC")) {
            callLead = apiInterface.getCCLead(myId, Id);
            call = apiInterface.getSingleCard(Id);
        } else if (Objects.equals(from, "DA")) {
            callLead = apiInterface.getDALead(myId, Id);
            call = apiInterface.getSingleDemetAccount(Id);
        } else if (Objects.equals(from, "BA")) {

            call = apiInterface.getSingleBankAccount(Id);
        } else if (Objects.equals(from, "GL")) {
            callLead = apiInterface.getGLLead(myId, Id);
            call = apiInterface.getSingleGoldLoan(Id);
        } else if (Objects.equals(from, "PL")) {

            call = apiInterface.getSinglePersonalLoan(Id);
            //Insurance(IN)
        } else {
            callLead = apiInterface.getINLead(myId, Id);
            call = apiInterface.getSingleInsurance(Id);
        }

        binding.loadingCard.setVisibility(View.VISIBLE);
        callLead.enqueue(new Callback<GetCCLead>() {
            @Override
            public void onResponse(Call<GetCCLead> call, Response<GetCCLead> response) {
                if (response.isSuccessful()) {
                    if (response.body().getLead().size() > 0) {
                        String leads = response.body().getLead().get(0).getLeadno();
                        Log.d("onResponse", response.message());
                        binding.txtLeads.setText(leads);
                    } else {
                        binding.txtLeads.setText("0 ");

                    }

                } else {
                    Log.d("onResponseElse", response.message());
                    Toast.makeText(ProductActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetCCLead> call, Throwable t) {
                Log.d("Failure", t.getMessage());
                Toast.makeText(ProductActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        call.enqueue(new Callback<CreditCardResponse>() {
            @Override
            public void onResponse(Call<CreditCardResponse> call, Response<CreditCardResponse> response) {
                if (response.isSuccessful()) {
                    CreditCardResponse res = response.body();
                    binding.loadingCard.setVisibility(View.GONE);
                    binding.noDataFound.setVisibility(View.GONE);
                    binding.dataFound.setVisibility(View.VISIBLE);
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
                    Log.d("onResponse", response.message());

                    Glide.with(ProductActivity.this).load(res.getBanklogo()).into(binding.imageView);
                    binding.txtCardDes.setText(res.getDiscription());
                    binding.txtEarning.setText(res.getTotalEarn());
                    binding.txtSales.setText(res.getToatlSale());

                } else {
                    binding.noDataFound.setVisibility(View.VISIBLE);
                    binding.dataFound.setVisibility(View.GONE);
                    binding.loadingCard.setVisibility(View.GONE);
                    Log.d(TAG, response.message());
                    Toast.makeText(ProductActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreditCardResponse> call, Throwable t) {
                binding.loadingCard.setVisibility(View.GONE);
                binding.noDataFound.setVisibility(View.VISIBLE);
                binding.dataFound.setVisibility(View.GONE);
                Log.d(TAG, t.getMessage());
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
            Bundle bundle = new Bundle();
            bundle.putString("from", from);
            bundle.putInt("Id", Id);
            productDetailFragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.secondHome, productDetailFragment).commit();
            binding.btnDetail.setBackgroundResource(R.drawable.bg_button);
            binding.btnDetail.setTextColor(getResources().getColor(R.color.white));
            binding.btnMyStats.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnMyStats.setTextColor(getResources().getColor(R.color.black));
            binding.btnTraining.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnTraining.setTextColor(getResources().getColor(R.color.black));
        });
        binding.btnTraining.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putString("from", from);
            bundle.putInt("Id", Id);
            trainingFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()

                    .replace(R.id.secondHome, trainingFragment).commit();
            binding.btnTraining.setBackgroundResource(R.drawable.bg_button);
            binding.btnTraining.setTextColor(getResources().getColor(R.color.white));
            binding.btnMyStats.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnMyStats.setTextColor(getResources().getColor(R.color.black));
            binding.btnDetail.setBackgroundResource(R.drawable.ic_bg_tv);
            binding.btnDetail.setTextColor(getResources().getColor(R.color.black));
        });
        binding.btnMyStats.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("from", from);
            bundle.putInt("Id", Id);
            myStatsFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.secondHome, myStatsFragment).commit();
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