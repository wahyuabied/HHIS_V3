package com.mrabid.hhis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailRiwayatPasienActivity extends AppCompatActivity {

    TextView namaDokter,tglPeriksa,tinggiBadan,beratBadan,umur,no_telp,riwayat_kesehatan,diagnosa,keluhan,larangan,advis,perawatan,head,neck,thorax,abdomen,ekstremitas;
    TextView alamatPraktik,namaPasien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat_pasien);

        namaDokter = (TextView)findViewById(R.id.j_dokter);
        alamatPraktik = (TextView)findViewById(R.id.j_alamat_praktik);
        tglPeriksa = (TextView)findViewById(R.id.j_tgl);
        tinggiBadan = (TextView)findViewById(R.id.j_tbadan);
        beratBadan = (TextView)findViewById(R.id.j_bbadan);
        umur = (TextView)findViewById(R.id.j_umur);
        no_telp = (TextView)findViewById(R.id.j_telp);
        riwayat_kesehatan = (TextView)findViewById(R.id.j_riwayat);
        diagnosa = (TextView)findViewById(R.id.j_diagnosa);
        keluhan = (TextView)findViewById(R.id.j_keluhan);
        larangan = (TextView)findViewById(R.id.j_larangan);
        advis = (TextView)findViewById(R.id.j_advis);
        perawatan = (TextView)findViewById(R.id.j_perawatan);
        head = (TextView)findViewById(R.id.j_head);
        neck = (TextView)findViewById(R.id.j_neck);
        thorax = (TextView)findViewById(R.id.j_thorax);
        abdomen = (TextView)findViewById(R.id.j_abdomen);
        ekstremitas = (TextView)findViewById(R.id.j_ekstremitas);


        namaDokter.setText("Dr."+getIntent().getExtras().getString("nama_dokter"));
        tglPeriksa.setText(getIntent().getExtras().getString("tgl_periksa"));
        alamatPraktik.setText(getIntent().getExtras().getString("alamat_praktik"));
        tinggiBadan.setText(getIntent().getExtras().getString("tinggi_badan")+"Cm");
        beratBadan.setText(getIntent().getExtras().getString("berat_badan")+"Kg");
        umur.setText(getIntent().getExtras().getString("umur"));
        no_telp.setText(getIntent().getExtras().getString("no_telp_dokter"));
        riwayat_kesehatan.setText(getIntent().getExtras().getString("riwayat_penyakit_keluarga"));
        diagnosa.setText(getIntent().getExtras().getString("diagnosa"));
        keluhan.setText(getIntent().getExtras().getString("keluhan_utama"));
        larangan.setText(getIntent().getExtras().getString("larangan"));
        advis.setText(getIntent().getExtras().getString("advis"));
        perawatan.setText(getIntent().getExtras().getString("perawatan"));
        head.setText(getIntent().getExtras().getString("head"));
        neck.setText(getIntent().getExtras().getString("neck"));
        thorax.setText(getIntent().getExtras().getString("thorax"));
        abdomen.setText(getIntent().getExtras().getString("abdomen"));
        ekstremitas.setText(getIntent().getExtras().getString("ekstremitas"));

    }
}
