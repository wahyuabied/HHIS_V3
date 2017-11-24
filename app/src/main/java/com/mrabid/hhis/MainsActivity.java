package com.mrabid.hhis;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mrabid.hhis.Fragment.ProfilFragment;
import com.mrabid.hhis.Fragment.HomeFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainsActivity extends AppCompatActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_address);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Home");
        toolbar.setTitleTextColor(Color.WHITE);


        bottomBar = (BottomBar)findViewById(R.id.bottom_bar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            Fragment fragment = null;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(tabId == R.id.home){
                    fragment = new HomeFragment();
                    getSupportActionBar().setTitle("Home");
                    final Drawable upArrow = getResources().getDrawable(R.drawable.ic_home_black_40dp);
                    upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
                    getSupportActionBar().setDisplayUseLogoEnabled(true);
                }else if(tabId == R.id.people){
                    fragment = new ProfilFragment();
                    getSupportActionBar().setTitle("Profil");
                    final Drawable upArrow = getResources().getDrawable(R.drawable.ic_library_books_icon);
                    upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
                    getSupportActionBar().setDisplayUseLogoEnabled(true);
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frm_activity_main,fragment)
                        .commit();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_setting:
                Intent i = new Intent(MainsActivity.this,SettingActivity.class);
                startActivity(i);
        }
        return false;
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
    public void setIconTitle(int icon) {
        final Drawable upArrow = getResources().getDrawable(icon);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
