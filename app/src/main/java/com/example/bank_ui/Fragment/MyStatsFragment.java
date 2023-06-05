package com.example.bank_ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.bank_ui.Adapter.CustomerAdapter;
import com.example.bank_ui.R;

import com.example.bank_ui.AddCustomerActivity;

import com.example.bank_ui.databinding.FragmentMyStatsBinding;

import java.util.Objects;

public class MyStatsFragment extends Fragment {
    FragmentMyStatsBinding binding;

    int cardId, Id;
    SharedPreferences sharedPreferences;
    String from;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyStatsBinding.inflate(inflater, container, false);

        sharedPreferences = getActivity().getSharedPreferences("bank", Context.MODE_PRIVATE);
        Id = sharedPreferences.getInt("id", 0);
        cardId = this.getArguments().getInt("Id", 0);
        from = getActivity().getIntent().getStringExtra("from");

        Log.d("IDDDDDDD", String.valueOf(cardId));

        binding.btnAddCustomer.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddCustomerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("Id", Id);
            bundle.putString("from", from);
            bundle.putInt("cardId", cardId);
            intent.putExtras(bundle);
            startActivity(intent);

        });


        binding.rvAddCustomer.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvAddCustomer.setAdapter(new CustomerAdapter(getContext()));

        return binding.getRoot();
    }
}