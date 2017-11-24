package com.mrabid.hhis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.mrabid.hhis.Activity.BeforeMainActivity;
import com.mrabid.hhis.Helper.SharedPref;

public class SplashActivity extends AppCompatActivity {

        private static int SPLASH_TIME_OUT = 3000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            final SharedPref sharedPref= new SharedPref(this);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(sharedPref.loadData("id")!=""){
                        startActivity(new Intent(SplashActivity.this,MainsActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this,BeforeMainActivity.class));
                    }
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }

