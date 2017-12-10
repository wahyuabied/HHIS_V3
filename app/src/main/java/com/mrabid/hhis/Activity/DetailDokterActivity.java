package com.mrabid.hhis.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mrabid.hhis.R;

public class DetailDokterActivity extends AppCompatActivity {


    TextView nama,email,praktik,rumah,telepon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);

        nama =(TextView)findViewById(R.id.tv_detailDokter_namaDokter);
        email = (TextView)findViewById(R.id.tv_detailDokter_emailDokter);
        praktik = (TextView)findViewById(R.id.tv_detailDokter_alamatPraktik);
        rumah = (TextView)findViewById(R.id.tv_detailDokter_alamatRumah);
        telepon = (TextView)findViewById(R.id.tv_detailDokter_telpDokter);

        nama.setText("Dr."+getIntent().getStringExtra("nama_dokter").toString());
        email.setText(getIntent().getStringExtra("no_telp").toString());
        praktik.setText(getIntent().getStringExtra("alamat_praktik").toString());
        rumah.setText(getIntent().getStringExtra("alamat_rumah").toString());
        telepon.setText(getIntent().getStringExtra("no_telp").toString());

    }
}
