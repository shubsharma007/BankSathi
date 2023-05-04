package com.example.bank_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.example.bank_ui.databinding.ActivityProductDetailAddBinding;

public class ProductDetailAddActivity extends AppCompatActivity {

    ActivityProductDetailAddBinding binding;
    Dialog addDialog;
    private Integer[] images = {R.drawable.add_image};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         
        addDialog = new Dialog(this);




//        binding.img2.setOnClickListener(v -> {
//
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("text/plain");
//            intent.putExtra(Intent.EXTRA_TEXT, "Shubham Sharma");
//            startActivity(intent);
//        });
//        binding.img1.setOnClickListener(v -> {
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            Uri image = Uri.parse(MediaStore.Images.Media.EXTERNAL_CONTENT_URI + "/" + images);
//            intent.setType("image/jpeg/png");
//            intent.putExtra(Intent.EXTRA_STREAM, image);
//          //      startActivity(intent);
//               startActivity(Intent.createChooser(intent,"Share Image Using"));
//        });





        binding.img2.setOnClickListener(v -> {
            addDialog.setContentView(R.layout.add_layout);
            addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            addDialog.show();
        });
        binding.buttonBack.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ProductActivity.class));
            finish();
        });


    }
}