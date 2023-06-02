package com.example.bank_ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_ui.CreditActivity;
import com.example.bank_ui.MainActivity;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;

//    String CC, DA, BA, CL, PL, ITR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        cardClickListener();

        return binding.getRoot();

    }

    private void cardClickListener() {
        binding.creditCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreditActivity.class);
            intent.putExtra("from", "CC");
            startActivity(intent);
        });
        binding.DemetCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreditActivity.class);
            intent.putExtra("from", "DA");
            startActivity(intent);
        });
        binding.bankCArd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreditActivity.class);
            intent.putExtra("from", "BA");
            startActivity(intent);
        });
        binding.lineCard.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), CreditActivity.class);
//            intent.putExtra("from", "CL");
//            startActivity(intent);
        });
        binding.loanCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreditActivity.class);
            intent.putExtra("from", "PL");
            startActivity(intent);
        });
        binding.itrCard.setOnClickListener(v -> {
//            Intent intent = new Intent(getActivity(), CreditActivity.class);
//            intent.putExtra("from", "ITR");
//            startActivity(intent);
        });
    }
}