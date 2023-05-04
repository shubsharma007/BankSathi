package com.example.bank_ui.BottomSheets;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_ui.MainActivity;
import com.example.bank_ui.databinding.FragmentOTPBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class OtpVerification extends BottomSheetDialogFragment {
    FragmentOTPBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOTPBinding.inflate(inflater,container,false);

        binding.btnLogin.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        });
        return binding.getRoot();
    }
}