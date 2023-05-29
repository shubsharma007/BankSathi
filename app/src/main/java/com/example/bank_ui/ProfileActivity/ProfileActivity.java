package com.example.bank_ui.ProfileActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.bank_ui.LoginActivity.LoginActivity;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.ActivityProductBinding;
import com.example.bank_ui.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //  sharedPreferences
        sharedPreferences = this.getSharedPreferences(String.valueOf(R.string.sharedPreferenceName), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

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
}