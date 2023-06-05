package com.example.bank_ui.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_ui.Adapter.CustomerAdapter;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.FragmentMyStatsBinding;

public class MyStatsFragment extends Fragment {
    FragmentMyStatsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyStatsBinding.inflate(inflater, container, false);

        binding.rvAddCustomer.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAddCustomer.setAdapter(new CustomerAdapter(getContext()));

        return binding.getRoot();
    }
}