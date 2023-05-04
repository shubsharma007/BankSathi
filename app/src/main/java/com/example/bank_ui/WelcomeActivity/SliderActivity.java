package com.example.bank_ui.WelcomeActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

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
    OnBoardingPagerAdapter adapter;
    TextView[] dots;
    Animation animation;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySliderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // calling setDotIndicator and passing position jaha pe active dot ko set krna he
                setDotIndicator(position);
                if (position > 0) {
                    binding.backBtn.setVisibility(View.VISIBLE);
                } else {
                    binding.backBtn.setVisibility(View.INVISIBLE);
                }
                if (position == 2) {
                    binding.nextBtn.setText("Finish");
//                binding.nextBtn.setAnimation(animation);
                    binding.skipBtn.setVisibility(View.GONE);
                } else {
                    binding.nextBtn.setText("Next");
                    binding.skipBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };


        //btnAnimation
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.botton_animation);

        adapter = new OnBoardingPagerAdapter(this);
        // viewpager ko link kiya with adapter
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.addOnPageChangeListener(viewPagerListener);

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) > 0) {
                    binding.viewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });
        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItem(0) < 2) {
                    binding.viewPager.setCurrentItem(getItem(1), true);
                } else {
                    intent = new Intent(SliderActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        binding.skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SliderActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        setDotIndicator(0);

    }

    public void setDotIndicator(int position) {
        dots = new TextView[3];
        binding.dotIndicatorLinearLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#x2022", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#000000"));
            binding.dotIndicatorLinearLayout.addView(dots[i]);
        }
        dots[position].setText(Html.fromHtml("&#x2022", Html.FROM_HTML_MODE_LEGACY));
        dots[position].setTextColor(getResources().getColor(R.color.app_theme));
        dots[position].setTextSize(38);
    }

    private int getItem(int i) {
        return binding.viewPager.getCurrentItem() + i;
    }

}