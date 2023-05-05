package com.example.bank_ui.LoginActivity;

import static android.os.Build.VERSION_CODES.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bank_ui.BottomSheets.OtpVerification;
import com.example.bank_ui.MainActivity;
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

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    Intent intent;
    FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

//            OtpVerification otpFragment = new OtpVerification();
//            otpFragment.show(getSupportFragmentManager(), otpFragment.getTag());
//            intent.putExtra("mobile", binding.mobileEt.getText().toString());
            sendotp();
        });
    }

    private void sendotp() {

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Bundle bundle = new Bundle();
                bundle.putString("verificationId", verificationId);
                OtpVerification otpFragment = new OtpVerification();
                otpFragment.setArguments(bundle);
                Log.d("fdadfsdfas",verificationId);
                Toast.makeText(getApplicationContext(),"OTP Send Successfully...", Toast.LENGTH_SHORT).show();
                otpFragment.show(getSupportFragmentManager(), otpFragment.getTag());

            }
        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + binding.mobileEt.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}