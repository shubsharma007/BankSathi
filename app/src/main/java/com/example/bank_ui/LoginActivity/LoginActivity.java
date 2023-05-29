package com.example.bank_ui.LoginActivity;

import static android.os.Build.VERSION_CODES.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.BottomSheets.OtpVerification;
import com.example.bank_ui.MainActivity;
import com.example.bank_ui.Model.LoginResponse;
import com.example.bank_ui.R;
import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.WelcomeActivity.SliderActivity;
import com.example.bank_ui.WelcomeActivity.Splash;
import com.example.bank_ui.databinding.ActivityLoginBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    Intent intent;
    FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";
    String number;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ApiInterface apiInterface;
    String mobile, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);


        //  sharedPreferences
        sharedPreferences = this.getSharedPreferences(String.valueOf(com.example.bank_ui.R.string.sharedPreferenceName), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mAuth = FirebaseAuth.getInstance();

        binding.clickForSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, Signup.class);
//                intent.putExtra("mobile",binding.mobileEt.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        binding.loginBtn.setOnClickListener(view -> {
            if (binding.mobileEt.getText().toString().trim().isEmpty()) {
                binding.mobileEt.setError("Enter phone number");
                binding.mobileEt.requestFocus();
            } else if (binding.mobileEt.getText().toString().trim().length() < 10) {
                binding.mobileEt.setError("Enter Correct Mobile Number");
                binding.mobileEt.requestFocus();
            } else if (binding.passwordEt.getText().toString().trim().isEmpty()) {
                binding.passwordEt.setError("Enter Password");
                binding.passwordEt.requestFocus();
            } else if (binding.passwordEt.getText().toString().trim().length() < 4) {
                binding.passwordEt.setError("Enter password min 4 words");
                binding.passwordEt.requestFocus();
            } else {
                mobile = binding.mobileEt.getText().toString();
                password = binding.passwordEt.getText().toString();

                if (isNetworkAvailable()) {
                    ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("please wait...");
                    progressDialog.show();

                    Call<LoginResponse> call = apiInterface.postLoginResponse(mobile, password);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                editor.putBoolean("login",true);
                                editor.apply();
                                progressDialog.dismiss();
                                finish();
                                Toast.makeText(LoginActivity.this, "Login Successful...", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Number and password incorrect...", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
//            sendotp();

                } else {
                    Toast.makeText(LoginActivity.this, "Check Your Internet Connection...", Toast.LENGTH_SHORT).show();

                }
            }


        });
    }

    //    private void sendotp() {
//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
//            }
//
//            @Override
//            public void onVerificationFailed(@NonNull FirebaseException e) {
//                progressDialog.dismiss();
//                Toast.makeText(LoginActivity.this, "Please Enter Correct Number...", Toast.LENGTH_SHORT).show();
////                Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String verificationId,
//                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
//                Bundle bundle = new Bundle();
//                bundle.putString("number", number);
//                bundle.putString("verificationId", verificationId);
//                OtpVerification otpFragment = new OtpVerification();
//                progressDialog.dismiss();
//                otpFragment.setArguments(bundle);
//                Log.d("fdadfsdfas", verificationId);
//                Toast.makeText(getApplicationContext(), "OTP Send Successfully...", Toast.LENGTH_SHORT).show();
//                otpFragment.setCancelable(false);
//                otpFragment.show(getSupportFragmentManager(), otpFragment.getTag());
//
//            }
//        };
//
//        number = binding.mobileEt.getText().toString().trim();
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber("+91" + number)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(this)                 // (optional) Activity for callback binding
//                        // If no activity is passed, reCAPTCHA verification can not be used.
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}