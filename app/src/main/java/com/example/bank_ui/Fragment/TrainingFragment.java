package com.example.bank_ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_ui.ProductActivity;
import com.example.bank_ui.ProductDetailAddActivity;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.FragmentTrainingBinding;

public class TrainingFragment extends Fragment {
    FragmentTrainingBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrainingBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }
}