package com.example.bank_ui.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.BottomSheets.OtpVerification;
import com.example.bank_ui.MainActivity;
import com.example.bank_ui.Model.SignUpResponse;
import com.example.bank_ui.R;
import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.databinding.ActivitySignupBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {
    ActivitySignupBinding binding;
    Intent intent;
    ApiInterface apiInterface;
    String fullName, username, phoneNumber, password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //  sharedPreferences
        sharedPreferences = this.getSharedPreferences("bank", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.clickForLogin.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.fullNameEt.getText().toString().isEmpty()) {
                    binding.fullNameEt.setError("please enter full name");
                    binding.fullNameEt.requestFocus();
                } else if (binding.usernameEt.getText().toString().isEmpty()) {
                    binding.usernameEt.setError("please enter username");
                    binding.usernameEt.requestFocus();

                } else if (binding.mobileEt.getText().toString().isEmpty()) {
                    binding.mobileEt.setError("please enter mobile number");
                    binding.mobileEt.requestFocus();
                } else if (binding.mobileEt.getText().toString().length() < 10) {
                    binding.mobileEt.setError("please enter correct mobile number");
                    binding.mobileEt.requestFocus();
                } else if (binding.passwordEt.getText().toString().isEmpty()) {
                    binding.passwordEt.setError("please enter password");
                    binding.passwordEt.requestFocus();
                } else {
                    fullName = binding.fullNameEt.getText().toString();
                    username = binding.usernameEt.getText().toString();
                    phoneNumber = binding.mobileEt.getText().toString();
                    password = binding.passwordEt.getText().toString();
                    if (isNetworkAvailable()) {
                        binding.loadingCard.setVisibility(View.VISIBLE);

                        Call<SignUpResponse> call = apiInterface.postSignUpResponse(fullName, username, phoneNumber, password, true);
                        call.enqueue(new Callback<SignUpResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                                if (response.isSuccessful()) {
                                    binding.loadingCard.setVisibility(View.GONE);
                                    Toast.makeText(Signup.this, "SignUp Successfull...", Toast.LENGTH_SHORT).show();
                                    editor.putBoolean("login", true);

                                    editor.putString("deviceToken", "");
                                    editor.putString("fullName", fullName);
                                    editor.putString("userName", username);
                                    editor.putString("phoneNumber", phoneNumber);
                                    editor.putInt("id", response.body().getId());
                                    editor.putString("profileImg", "");
                                    editor.putString("email", "");
                                    editor.putString("dob", "");
                                    editor.putString("pinCode", "");
                                    editor.putString("address", "");
                                    editor.apply();
                                    Log.d("IdGeneratedIs", String.valueOf(response.body().getId()));

                                    startActivity(new Intent(Signup.this, MainActivity.class));
                                    finish();
                                } else {
                                    binding.loadingCard.setVisibility(View.GONE);
                                    Log.d("OnResponseElse", response.message());
                                    Toast.makeText(Signup.this, "OnResponseElse", Toast.LENGTH_SHORT).show();


//                                    if (response.body().getPhoneno() != null && !response.body().getPhoneno().isEmpty()){
//                                        if (phoneNumber.equals(response.body().getPhoneno())) {
//                                            Toast.makeText(Signup.this, "user with this phone number already exists.", Toast.LENGTH_SHORT).show();
//
//                                        }
//                                        else if (username.equals(response.body().getUsername())) {
//                                            Toast.makeText(Signup.this, "A user with that username already exists", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                                binding.loadingCard.setVisibility(View.GONE);
                                Log.d("OnFailure", t.getMessage());
                                Toast.makeText(Signup.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                    } else {
                        Toast.makeText(Signup.this, "Check Your Internet Connection...", Toast.LENGTH_SHORT).show();

                    }

//                    OtpVerification otpFragment = new OtpVerification();
//                    otpFragment.show(getSupportFragmentManager(), otpFragment.getTag());
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}