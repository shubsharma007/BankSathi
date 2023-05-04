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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        binding.cardView7.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreditActivity.class);
            startActivity(intent);

        });
        return binding.getRoot();

    }
}