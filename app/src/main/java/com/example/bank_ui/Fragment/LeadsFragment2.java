package com.example.bank_ui.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_ui.R;
import com.example.bank_ui.databinding.FragmentLeads2Binding;

public class LeadsFragment2 extends Fragment {
    FragmentLeads2Binding binding;

    public  LeadsFragment2(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLeads2Binding.inflate(inflater,container,false);


        return binding.getRoot();
    }
}