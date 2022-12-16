package com.example.bank_ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_ui.MainActivity;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.FragmentOTPBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class OTPFragment extends BottomSheetDialogFragment {
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