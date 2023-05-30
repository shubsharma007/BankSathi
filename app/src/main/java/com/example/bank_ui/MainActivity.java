package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.bank_ui.Fragment.HomeFragment;
import com.example.bank_ui.Fragment.LeadFragment;
import com.example.bank_ui.Fragment.MyTeamFragment;
import com.example.bank_ui.Fragment.RrferralFragment;
import com.example.bank_ui.NotificationActivity.Notification;
import com.example.bank_ui.ProfileActivity.ProfileActivity;
import com.example.bank_ui.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    int Id;
    String name;
    String profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSaved();

        binding.notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.notificationBtn.setBadgeValue(64);
                startActivity(new Intent(MainActivity.this, Notification.class));

            }
        });
        binding.userProfile.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        });

        getSupportFragmentManager().beginTransaction().add(R.id.constraintLayout, new HomeFragment()).commit();
        binding.txtHii.setVisibility(View.VISIBLE);
        binding.txtName.setVisibility(View.VISIBLE);
        binding.icQuestion.setVisibility(View.VISIBLE);
        binding.maintext.setVisibility(View.GONE);
        binding.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new HomeFragment()).commit();
                    binding.txtHii.setVisibility(View.VISIBLE);
                    binding.txtName.setVisibility(View.VISIBLE);
                    binding.icQuestion.setVisibility(View.VISIBLE);
                    binding.maintext.setVisibility(View.GONE);
                    break;

                case R.id.leads:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new LeadFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.maintext.setText("Leads");
                    break;

                case R.id.referral:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new RrferralFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.maintext.setText("Referral");
                    break;
                case R.id.myTeam:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new MyTeamFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.maintext.setText("My Team");
                    break;
            }
            return true;
        });

//        binding.cardView7.setOnClickListener(v -> {
//            Intent intent = new Intent(MainActivity.this, CreditActivity.class);
//            startActivity(intent);
//
//        });

    }

    private void getSaved() {
        SharedPreferences sharedPreferences = getSharedPreferences("youtubeSathi", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        editor.putString("fullName", fullName);
//        editor.putString("userName", username);
//        editor.putString("phoneNumber", phoneNumber);
//        editor.putString("password", password);
//        editor.putInt("id", response.body().getId());
//        editor.putString("profileImg", "");
//        editor.putString("email", "");
//        editor.putString("dob", "");
//        editor.putString("pinCode", "");

        Id = sharedPreferences.getInt("id", 0);
        name = sharedPreferences.getString("fullName", "");
        binding.txtName.setText(name);

        if (sharedPreferences.getString("profileImg", "").equals("")) {
            profileImg = "";
        } else {
            profileImg = sharedPreferences.getString("profileImg", "");
            Glide.with(MainActivity.this).load(profileImg).centerCrop().placeholder(R.drawable.profile).into(binding.userProfile);
        }

    }
}
