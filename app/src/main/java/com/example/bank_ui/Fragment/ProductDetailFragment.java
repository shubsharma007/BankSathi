package com.example.bank_ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_ui.MainActivity;
import com.example.bank_ui.ProductActivity;
import com.example.bank_ui.ProductDetailAddActivity;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.FragmentProductDetailBinding;

public class ProductDetailFragment extends Fragment {
    FragmentProductDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }
}