package com.example.bank_ui.ProfileActivity;

import static com.airbnb.lottie.L.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bank_ui.ApiInterface.ApiInterface;
import com.example.bank_ui.LoginActivity.LoginActivity;
import com.example.bank_ui.MainActivity;
import com.example.bank_ui.Model.SignUpResponse;
import com.example.bank_ui.R;
import com.example.bank_ui.Retrofit.RetrofitServices;
import com.example.bank_ui.databinding.ActivityProductBinding;
import com.example.bank_ui.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

import okhttp3.CertificatePinner;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String name, dob, profileImg, address, email, username, pinCode;
    int Id;
    ApiInterface apiInterface;
    Uri profileImage;
    String uripi = " ";
    Boolean dpImageBoolean = false;
    File PImg;
    private static final String TAG = "ProfileActivity";
    Call<SignUpResponse> updateCall;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            binding.userProfile.setImageURI(data.getData());
            profileImage = data.getData();
            uripi = getRealPathFromURI(profileImage);
            dpImageBoolean = true;
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);

        getSaved();
        if (isNetworkAvailable()) {
            getApi();
        } else {
            Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show();
        }
        //  sharedPreferences


        binding.btnBack.setOnClickListener(v -> {
            finish();
        });

        binding.userProfile.setOnClickListener(v -> {
            Intent imgIntent1 = new Intent(Intent.ACTION_PICK);
            imgIntent1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imgIntent1, 101);
        });
        binding.btnSave.setOnClickListener(v -> {
            if (isNetworkAvailable()) {
                name = binding.txtUserName.getText().toString();
                address = binding.txtAddress.getText().toString();
                email = binding.txtEmail.getText().toString();
                pinCode = binding.txtPin.getText().toString();
                dob = binding.txtDOB.getText().toString();

                PImg = new File(uripi);
                Log.d("fdlskfsdf", PImg.toString());
                RequestBody proImg = RequestBody.create(MediaType.parse("image/*"), PImg);
                MultipartBody.Part profile_img = MultipartBody.Part.createFormData("profileimg", PImg.getName(), proImg);

                Log.d(TAG, "onCreateU:" + uripi);
                Log.d(TAG, "onCreateP:" + PImg);
                Log.d(TAG, "onCreate:PI" + proImg);
                Log.d(TAG, "onCreate:PROIMAGE" + profile_img);
                RequestBody rName = RequestBody.create(MediaType.parse("text/plain"), name);
                RequestBody rEmail = RequestBody.create(MediaType.parse("text/plain"), email);
                RequestBody rAddress = RequestBody.create(MediaType.parse("text/plain"), address);
                RequestBody rPin = RequestBody.create(MediaType.parse("text/plain"), pinCode);
                RequestBody rDOB = RequestBody.create(MediaType.parse("text/plain"), dob);

                if (dpImageBoolean) {
                    updateCall = apiInterface.updateProfileResponse(Id, rName, rAddress, rDOB, rEmail, rPin, profile_img);

                } else {
                    updateCall = apiInterface.updateProfileResponseWithoutImage(Id, rName, rAddress, rDOB, rEmail, rPin);

                }
                binding.Linear.setVisibility(View.GONE);
                binding.loading.setVisibility(View.VISIBLE);
                updateCall.enqueue(new Callback<SignUpResponse>() {
                    @Override
                    public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                        if (response.isSuccessful()) {
                            binding.Linear.setVisibility(View.VISIBLE);
                            binding.loading.setVisibility(View.GONE);
                            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Log.d(TAG, "onResponseElse" + response.message());
                            binding.Linear.setVisibility(View.VISIBLE);
                            binding.loading.setVisibility(View.GONE);
                            Toast.makeText(ProfileActivity.this, "try again", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpResponse> call, Throwable t) {
                        Log.d(TAG, "Failure" + t.getMessage());
                        binding.Linear.setVisibility(View.VISIBLE);
                        binding.loading.setVisibility(View.GONE);
                        Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                Toast.makeText(this, "No Network Connection", Toast.LENGTH_SHORT).show();

            }
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

    private void getApi() {
        binding.Linear.setVisibility(View.GONE);
        binding.loading.setVisibility(View.VISIBLE);
        Call<SignUpResponse> getAllData = apiInterface.getAllData(Id);
        getAllData.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccessful()) {
                    binding.Linear.setVisibility(View.VISIBLE);
                    binding.loading.setVisibility(View.GONE);
                    SignUpResponse singleUser = response.body();
                    binding.txtName.setText(singleUser.getUsername());
                    Glide.with(ProfileActivity.this).load(singleUser.getProfileimg()).into(binding.userProfile);
                    binding.txtUserName.setText(singleUser.getFullname());
                    binding.txtDOB.setText(singleUser.getDateofbirth());
                    binding.txtEmail.setText(singleUser.getEmail());
                    binding.txtAddress.setText(singleUser.getAddress());
                    binding.txtPin.setText(singleUser.getPincode());
                } else {
                    binding.Linear.setVisibility(View.VISIBLE);
                    binding.loading.setVisibility(View.GONE);
                    Log.d("OnResponseElse", response.message());
                    Toast.makeText(ProfileActivity.this, "try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("OnFailure", t.getMessage());
                binding.Linear.setVisibility(View.VISIBLE);
                binding.loading.setVisibility(View.GONE);
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
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
//        editor.putString("address","");
        Id = sharedPreferences.getInt("id", 0);

//        name = sharedPreferences.getString("fullName", "");
//        binding.txtUserName.setText(name);
//
//        username = sharedPreferences.getString("userName", "");
//        binding.txtName.setText(username);
//
//        if (sharedPreferences.getString("profileImg", "").equals("")) {
//            profileImg = "";
//        } else {
//            profileImg = sharedPreferences.getString("profileImg", "");
//            Log.d("ProfileImage", profileImg);
//            Glide.with(ProfileActivity.this).load(profileImg).centerCrop().placeholder(R.drawable.profile).into(binding.userProfile);
//        }
//
//        if (sharedPreferences.getString("dob", "").equals("")) {
//            dob = "";
//            binding.txtDOB.setHint("update your date of birth");
//        } else {
//            dob = sharedPreferences.getString("dob", "");
//            binding.txtDOB.setText(dob);
//        }
//
//        if (sharedPreferences.getString("email", "").equals("")) {
//            email = "";
//            binding.txtEmail.setHint("update your email address");
//        } else {
//            email = sharedPreferences.getString("email", "");
//            binding.txtEmail.setText(email);
//        }
//        if (sharedPreferences.getString("address", "").equals("")) {
//            address = "";
//            binding.txtAddress.setHint("update your address");
//        } else {
//            address = sharedPreferences.getString("address", "");
//            binding.txtAddress.setText(address);
//        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}