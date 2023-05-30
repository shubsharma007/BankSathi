package com.example.bank_ui.ProfileActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.bank_ui.LoginActivity.LoginActivity;
import com.example.bank_ui.MainActivity;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.ActivityProductBinding;
import com.example.bank_ui.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String name, dob, profileImg, address, email, username;
    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSaved();
        //  sharedPreferences


        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        binding.txtLogout.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Logout");
            dialog.setCancelable(false);
            dialog.setMessage("Are you sure");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    FirebaseAuth.getInstance().signOut();
                    editor.putBoolean("login", false);
                    editor.apply();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();

        });
    }

    private void getSaved() {
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.sharedPreferenceName), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
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
        binding.txtUserName.setText(name);

        username = sharedPreferences.getString("userName", "");
        binding.txtName.setText(username);

        if (sharedPreferences.getString("profileImg", "").equals("")) {
            profileImg = "";
        } else {
            profileImg = sharedPreferences.getString("profileImg", "");
            Glide.with(ProfileActivity.this).load(profileImg).centerCrop().placeholder(R.drawable.profile).into(binding.userProfile);
        }

        if (sharedPreferences.getString("dob", "").equals("")) {
            dob = "";
            binding.txtDOB.setHint("update your date of birth");
        } else {
            dob = sharedPreferences.getString("dob", "");
            binding.txtDOB.setText(dob);
        }

        if (sharedPreferences.getString("email", "").equals("")) {
            email = "";
            binding.txtEmail.setHint("update your email address");
        } else {
            email = sharedPreferences.getString("email", "");
            binding.txtEmail.setText(email);
        }

    }
}