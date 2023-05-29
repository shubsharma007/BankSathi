package com.example.bank_ui.BottomSheets;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bank_ui.LoginActivity.LoginActivity;
import com.example.bank_ui.MainActivity;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.FragmentOTPBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class OtpVerification extends BottomSheetDialogFragment {
    FragmentOTPBinding binding;
    String mobileNumber;
    FirebaseAuth mAuth;
    String otpId;
    String otp;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOTPBinding.inflate(inflater, container, false);
        final ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");


        mAuth = FirebaseAuth.getInstance();
        String verificationId = getArguments().getString("verificationId");
        String number = getArguments().getString("number");
        MoveNumToNext();

        binding.textView2.setText("Enter OTP code send to your number\n+91 " + number);

        binding.confirmBtn.setOnClickListener(v -> {
            if (binding.otp1.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Otp", Toast.LENGTH_SHORT).show();

            } else if (binding.otp2.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Otp", Toast.LENGTH_SHORT).show();

            } else if (binding.otp3.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Otp", Toast.LENGTH_SHORT).show();

            } else if (binding.otp4.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Otp", Toast.LENGTH_SHORT).show();

            } else if (binding.otp5.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Otp", Toast.LENGTH_SHORT).show();
            } else if (binding.otp6.getText().toString().trim().isEmpty()) {
                Toast.makeText(getActivity(), "Enter Otp", Toast.LENGTH_SHORT).show();
            } else {
                dialog.show();
                otp = binding.otp1.getText().toString()
                        + binding.otp2.getText().toString()
                        + binding.otp3.getText().toString()
                        + binding.otp4.getText().toString()
                        + binding.otp5.getText().toString()
                        + binding.otp6.getText().toString();

                Log.e("verificationdata", verificationId);
                Log.e("AllOTp", otp);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);

                                    sharedPreferences = getActivity().getSharedPreferences(String.valueOf(R.string.sharedPreferenceName), Context.MODE_PRIVATE);
                                    editor = sharedPreferences.edit();

                                    editor.putBoolean("login", true);
                                    editor.apply();
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getActivity(), "OTP is not valid...", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        });
            }
        });
        return binding.getRoot();
    }


    private void MoveNumToNext() {

        binding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.otp2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.otp3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.otp4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.otp5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    binding.otp6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

}