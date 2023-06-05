package com.example.bank_ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.Fragment.HomeFragment;
import com.example.bank_ui.Fragment.LeadFragment;
import com.example.bank_ui.Fragment.MyTeamFragment;
import com.example.bank_ui.Fragment.RrferralFragment;
import com.example.bank_ui.Model.LoginResponse;
import com.example.bank_ui.Model.SignUpResponse;
import com.example.bank_ui.NotificationActivity.Notification;
import com.example.bank_ui.ProfileActivity.ProfileActivity;
import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static final int NOTIFICATION = 357;
    int Id;
    String name;
    String profileImg, deviceToken, aayaToken;
    ApiInterface apiInterface;

    private static final String TAG = "MainTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

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
        binding.icToolTip.setVisibility(View.GONE);
        binding.maintext.setVisibility(View.GONE);
        binding.notificationBtn.setVisibility(View.VISIBLE);
        binding.navigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new HomeFragment()).commit();
                    binding.txtHii.setVisibility(View.VISIBLE);
                    binding.txtName.setVisibility(View.VISIBLE);
                    binding.notificationBtn.setVisibility(View.VISIBLE);
                    binding.icToolTip.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.VISIBLE);
                    binding.maintext.setVisibility(View.GONE);
                    break;

                case R.id.leads:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new LeadFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.icToolTip.setVisibility(View.GONE);
                    binding.notificationBtn.setVisibility(View.VISIBLE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.maintext.setText("Leads");
                    break;

                case R.id.referral:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new RrferralFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.notificationBtn.setVisibility(View.VISIBLE);
                    binding.icToolTip.setVisibility(View.GONE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.maintext.setText("Referral");
                    break;
                case R.id.myTeam:
                    getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new MyTeamFragment()).commit();
                    binding.txtHii.setVisibility(View.GONE);
                    binding.txtName.setVisibility(View.GONE);
                    binding.icQuestion.setVisibility(View.GONE);
                    binding.maintext.setVisibility(View.VISIBLE);
                    binding.icToolTip.setVisibility(View.VISIBLE);
                    binding.notificationBtn.setVisibility(View.GONE);
                    binding.maintext.setText("My Team");
                    break;
            }
            return true;
        });

        binding.icToolTip.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TeamDashboardActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSaved();
        getNotification();
    }

    private void getToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                        Toast.makeText(this, "Error generating device token", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Get new FCM registration token
                    deviceToken = task.getResult();
                    // Log and toast
                    Log.d("ToKeN", deviceToken);
                    Log.d("ToKeNAAya", aayaToken);

                    if (!deviceToken.equals(aayaToken)) {
                        Toast.makeText(this, "DevicesToken", Toast.LENGTH_SHORT).show();
                        Call<SignUpResponse> updateToken = apiInterface.updateToken(Id, deviceToken);

                        updateToken.enqueue(new Callback<SignUpResponse>() {
                            @Override
                            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                                if (response.isSuccessful()) {
                                    Log.d(TAG, "TOKEN UPDATED");
                                } else {
//                                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<SignUpResponse> call, Throwable t) {
//                                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d(TAG, t.getMessage());
                            }
                        });
                    }else {
                        Toast.makeText(this, "DevicesTokenElse", Toast.LENGTH_SHORT).show();
                    }


//                    Toast.makeText(MainActivity.this, "your token is " + token, Toast.LENGTH_SHORT).show();
                });
    }


    private void getNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestRuntimePermissionFunc("notification");
        } else {
            getToken();
//            Toast.makeText(MainActivity.this, Build.VERSION.SDK_INT + " requires no notification permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestRuntimePermissionFunc(String str) {
        if (str.equals("notification")) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                getToken();
//                Toast.makeText(this, "notification permission already granted", Toast.LENGTH_SHORT).show();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.POST_NOTIFICATIONS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("this permission is required for this and this")
                        .setTitle("notification required")
                        .setCancelable(false)
                        .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION);
                            }
                        })
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION);
            }
        }
    }


    private void getSaved() {
        SharedPreferences sharedPreferences = getSharedPreferences("bank", MODE_PRIVATE);
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
//        editor.putString("address", "");

        Id = sharedPreferences.getInt("id", 0);
        name = sharedPreferences.getString("fullName", "");
        Log.d(TAG + "NAME", name);
        Log.d(TAG + "ID", String.valueOf(Id));
        binding.txtName.setText(name);
        aayaToken = sharedPreferences.getString("deviceToken", "");

        if (sharedPreferences.getString("profileImg", "").equals("")) {
            profileImg = "";
        } else {
            profileImg = sharedPreferences.getString("profileImg", "");
            Log.d(TAG + "PROFILE", profileImg);
            Glide.with(MainActivity.this).load(profileImg).centerCrop().placeholder(R.drawable.profile).into(binding.userProfile);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NOTIFICATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("this feature is unavailable , now open settings ")
                        .setTitle("notification to chaiye")
                        .setCancelable(false)
                        .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                requestRuntimePermissionFunc("notification");
            }
        }
    }

}
