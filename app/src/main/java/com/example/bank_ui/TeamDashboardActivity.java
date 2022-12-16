package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bank_ui.databinding.ActivityTeamDashboardBinding;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class TeamDashboardActivity extends AppCompatActivity {

    ActivityTeamDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
      



    }
}