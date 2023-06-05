package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.Model.AddCustomerResponse;
import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.databinding.ActivityAddCustomerBinding;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerActivity extends AppCompatActivity {

    ActivityAddCustomerBinding binding;
    ApiInterface apiInterface;
    int Id, cardId;
    String fullName, email, contact, panNumber;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCustomerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Id = getIntent().getExtras().getInt("Id", 0);
        cardId = getIntent().getExtras().getInt("cardId", 0);
        from = getIntent().getExtras().getString("from");

        Log.d("IDDDDD", String.valueOf(Id));
        Log.d("cardIDDDDD", String.valueOf(cardId));
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.saveBtn.setOnClickListener(v -> {
            if (binding.fullNameEt.getText().toString().isEmpty()) {
                binding.fullNameEt.setError("Enter FullName");
                binding.fullNameEt.requestFocus();
            } else if (binding.emailEt.getText().toString().isEmpty()) {
                binding.emailEt.setError("Enter Email Address");
                binding.emailEt.requestFocus();
            } else if (binding.contactEt.getText().toString().isEmpty()) {
                binding.contactEt.setError("Enter Contact Number");
                binding.contactEt.requestFocus();
            } else if (binding.panEt.getText().toString().isEmpty()) {
                binding.panEt.setError("Enter PAN Number");
                binding.panEt.requestFocus();
            } else {
                fullName = binding.fullNameEt.getText().toString();
                email = binding.emailEt.getText().toString();
                contact = binding.contactEt.getText().toString();
                panNumber = binding.panEt.getText().toString();


                if (Objects.equals(from, "CC")) {
                    Call<AddCustomerResponse> call = apiInterface.postAddCustomer(fullName, contact, email, panNumber, String.valueOf(cardId), String.valueOf(Id));
                    call.enqueue(new Callback<AddCustomerResponse>() {
                        @Override
                        public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddCustomerActivity.this, "Lead Create Successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(AddCustomerActivity.this, ProductActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AddCustomerActivity.this, "try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                            Toast.makeText(AddCustomerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                } else if (Objects.equals(from, "DA")) {
                    Call<AddCustomerResponse> call1 = apiInterface.postAddCustomerDemat(fullName, contact, email, panNumber, String.valueOf(cardId), String.valueOf(Id));
                    call1.enqueue(new Callback<AddCustomerResponse>() {
                        @Override
                        public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddCustomerActivity.this, "Lead Create Successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(AddCustomerActivity.this, ProductActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AddCustomerActivity.this, "try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                            Toast.makeText(AddCustomerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                } else if (Objects.equals(from, "BA")) {
                    Call<AddCustomerResponse> call2 = apiInterface.postAddCustomerBuisness(fullName, contact, email, panNumber, String.valueOf(cardId), String.valueOf(Id));
                    call2.enqueue(new Callback<AddCustomerResponse>() {
                        @Override
                        public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddCustomerActivity.this, "Lead Create Successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(AddCustomerActivity.this, ProductActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AddCustomerActivity.this, "try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                            Toast.makeText(AddCustomerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                } else if (Objects.equals(from, "GL")) {
                    Call<AddCustomerResponse> call3 = apiInterface.postAddCustomerGold(fullName, contact, email, panNumber, String.valueOf(cardId), String.valueOf(Id));
                    call3.enqueue(new Callback<AddCustomerResponse>() {
                        @Override
                        public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddCustomerActivity.this, "Lead Create Successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(AddCustomerActivity.this, ProductActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AddCustomerActivity.this, "try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                            Toast.makeText(AddCustomerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                } else if (Objects.equals(from, "PL")) {

                    Call<AddCustomerResponse> call4 = apiInterface.postAddCustomerCar(fullName, contact, email, panNumber, String.valueOf(cardId), String.valueOf(Id));
                    call4.enqueue(new Callback<AddCustomerResponse>() {
                        @Override
                        public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddCustomerActivity.this, "Lead Create Successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(AddCustomerActivity.this, ProductActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AddCustomerActivity.this, "try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                            Toast.makeText(AddCustomerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                } else if (Objects.equals(from, "IN")) {
                    Call<AddCustomerResponse> call5 = apiInterface.postAddCustomerInsuranse(fullName, contact, email, panNumber, String.valueOf(cardId), String.valueOf(Id));
                    call5.enqueue(new Callback<AddCustomerResponse>() {
                        @Override
                        public void onResponse(Call<AddCustomerResponse> call, Response<AddCustomerResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(AddCustomerActivity.this, "Lead Create Successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(AddCustomerActivity.this, ProductActivity.class));
                                finish();
                            } else {
                                Toast.makeText(AddCustomerActivity.this, "try again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<AddCustomerResponse> call, Throwable t) {
                            Toast.makeText(AddCustomerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

    }
}