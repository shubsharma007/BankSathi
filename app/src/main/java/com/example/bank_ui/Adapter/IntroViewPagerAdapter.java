package com.example.bank_ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bank_ui.Model.SliderModel;
import com.example.bank_ui.R;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {


    Context sContext;
    List<SliderModel> sListScreen;

    public IntroViewPagerAdapter(Context sContext, List<SliderModel> sListScreen) {
        this.sContext = sContext;
        this.sListScreen = sListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) sContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.slider_layout_1, null);

        ImageView imgSlider1 = layoutScreen.findViewById(R.id.imgSlider1);

        imgSlider1.setImageResource(sListScreen.get(position).getImg());

        container.addView(layoutScreen);

        return layoutScreen;


    }

    @Override
    public int getCount() {
        return sListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
