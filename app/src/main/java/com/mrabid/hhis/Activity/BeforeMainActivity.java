package com.mrabid.hhis.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eftimoff.viewpagertransformers.FlipVerticalTransformer;
import com.mrabid.hhis.Adapter.SliderAdapter;
import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.R;
import com.mrabid.hhis.SignInActivity;

public class BeforeMainActivity extends AppCompatActivity {

    ViewPager viewPager;
    SliderAdapter sliderAdapter;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main);

        viewPager = (ViewPager)findViewById(R.id.vp_image_slider);
        sliderDotspanel = (LinearLayout)findViewById(R.id.sliderDots);
        signIn = (Button)findViewById(R.id.btn_signIn_BeforeMain);
        SharedPref sharedPref = new SharedPref(this);

        sliderAdapter = new SliderAdapter(BeforeMainActivity.this);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setPageTransformer(true, new FlipVerticalTransformer());
        dotscount = sliderAdapter.getCount();
        dots = new ImageView[dotscount];


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeforeMainActivity.this, SignInActivity.class));
            }
        });

        for(int i=0;i<dotscount;i++){
            dots[i] =new ImageView(BeforeMainActivity.this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(BeforeMainActivity.this.getApplicationContext(),R.drawable.nonactivedot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            sliderDotspanel.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(BeforeMainActivity.this.getApplicationContext(),R.drawable.activedot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<dotscount;i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(BeforeMainActivity.this.getApplicationContext(),R.drawable.nonactivedot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(BeforeMainActivity.this.getApplicationContext(),R.drawable.activedot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
