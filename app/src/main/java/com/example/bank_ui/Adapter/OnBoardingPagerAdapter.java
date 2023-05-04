package com.example.bank_ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bank_ui.Model.SliderModel;
import com.example.bank_ui.R;

import java.util.List;

public class OnBoardingPagerAdapter extends PagerAdapter {
    // context-us activity ka liya jaega viewpager he
    Context context;

    public OnBoardingPagerAdapter(Context context) {
        this.context = context;
    }

    // images and strings ka project me location
    int[] sliderAllImages = {R.drawable.slider_img1, R.drawable.slider_img2, R.drawable.slider_img1};

    // already override hua he jisme apne ko kitni entries he uski length de do
    @Override
    public int getCount() {
        return sliderAllImages.length;
    }

    // iska dhyan rkhna pdega alg se override krna pdega
    // jo objectaya he usko view me daal k return kr do
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }


    // isme apn ko saare views bnane he inflated view ko use kr k and usme set krna he cheeze positions k hisaab se
    // aakhri me container.addView kr k view pass kr do
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout_1, container, false);

        ImageView sliderImage = (ImageView) view.findViewById(R.id.imgSlider1);

        sliderImage.setImageResource(sliderAllImages[position]);

        container.addView(view);

        return view;
    }

    // us container ie the viewpager me se hta do previous position se related details ko
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
