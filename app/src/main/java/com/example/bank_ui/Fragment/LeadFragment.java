package com.example.bank_ui.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bank_ui.R;
import com.example.bank_ui.databinding.FragmentLeadsBinding;

public class LeadFragment extends Fragment {
    FragmentLeadsBinding binding;

    public LeadFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLeadsBinding.inflate(inflater, container, false);


        FragmentTransaction transaction1 = getParentFragmentManager().beginTransaction();
        transaction1.replace(R.id.secondHome,new LeadsFragment1()).commit();


        binding.tvLead2.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.secondHome,new LeadsFragment2()).commit();
            binding.tvLead2.setBackgroundResource(R.drawable.bg_button);
            binding.tvLead2.setTextColor(getResources().getColor(R.color.white));
            binding.tvLead1.setBackgroundResource(R.drawable.bg_google);
            binding.tvLead1.setTextColor(getResources().getColor(R.color.black));
        });
        binding.tvLead1.setOnClickListener(v -> {
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.secondHome,new LeadsFragment1()).commit();
            binding.tvLead1.setBackgroundResource(R.drawable.bg_button);
            binding.tvLead1.setTextColor(getResources().getColor(R.color.white));
            binding.tvLead2.setBackgroundResource(R.drawable.bg_google);
            binding.tvLead2.setTextColor(getResources().getColor(R.color.black));
        });


        return binding.getRoot();
    }
}