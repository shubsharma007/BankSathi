package com.example.bank_ui.WelcomeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.bank_ui.Adapter.OnBoardingPagerAdapter;
import com.example.bank_ui.LoginActivity.LoginActivity;
import com.example.bank_ui.Model.SliderModel;
import com.example.bank_ui.R;
import com.example.bank_ui.databinding.ActivitySliderBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SliderActivity extends AppCompatActivity {
    ActivitySliderBinding binding;
    private ViewPager screenPager;
    OnBoardingPagerAdapter introViewPagerAdapter;
    int position = 0;
    Animation btnAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySliderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        //btnAnimation
        btnAni = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.botton_animation);

        //Make The Activity On Full Screen 

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //when This Activity Is About Launch We Need To Check id its Opened Before Or Not
        if (restorePrefData()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        //Fill List Screen
        List<SliderModel> sList = new ArrayList<>();
        sList.add(new SliderModel(R.drawable.slider_img1));
        sList.add(new SliderModel(R.drawable.slider_img2));

        // setup ViewPager
        screenPager = findViewById(R.id.viewPager);
        introViewPagerAdapter = new OnBoardingPagerAdapter(this, sList);
        screenPager.setAdapter(introViewPagerAdapter);

        //setup tabLayout with ViewPager
        binding.tabIndicator.setupWithViewPager(screenPager);

        binding.tvSkip.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
        binding.tvGetStart.setOnClickListener(v -> {

            startActivity(new Intent(this, LoginActivity.class));

            savePrefsDAta();
            finish();
        });
        binding.tvNext.setOnClickListener(v -> {

            position = screenPager.getCurrentItem();
            if (position < sList.size()) {
                position++;
                screenPager.setCurrentItem(position);

            }
            if (position == sList.size() - 1) {

                loadLastScreen();

            }
        });
        binding.tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == sList.size() - 1) {
                    loadLastScreen();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;

    }

    private void savePrefsDAta() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }

    private void loadLastScreen() {

        binding.tvNext.setVisibility(View.INVISIBLE);
        binding.tvGetStart.setVisibility(View.VISIBLE);
        binding.tabIndicator.setVisibility(View.INVISIBLE);

        //SetUp Animation
        binding.tvGetStart.setAnimation(btnAni);

    }
}