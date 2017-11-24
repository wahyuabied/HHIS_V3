package com.mrabid.hhis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailArtikelActivity extends AppCompatActivity {

    TextView judul_,deskripsi_,sinopsis_;
    ImageView image_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_artikel);

        judul_=(TextView) findViewById(R.id.txt_MjdlArtikel);
        deskripsi_=(TextView) findViewById(R.id.txt_MdeskripsiArtikel);
        sinopsis_=(TextView) findViewById(R.id.txt_MsinopsisArtikel);
        image_=(ImageView)findViewById(R.id.img_Martikel);

        judul_.setText(getIntent().getExtras().getString("judul"));
        deskripsi_.setText(Html.fromHtml(getIntent().getExtras().getString("deskripsi")).toString());
        sinopsis_.setText(getIntent().getExtras().getString("abstrak"));
        Glide.with(this).load("http://hhis.tk/backend/web/"+getIntent().getExtras().getString("image")).into(image_);
    }
}
