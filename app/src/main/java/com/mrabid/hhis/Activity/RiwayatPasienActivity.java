package com.mrabid.hhis.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.hhis.Adapter.RiwayatPasienAdapter;
import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.Modal.RiwayatPasien;
import com.mrabid.hhis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiwayatPasienActivity extends AppCompatActivity {

    RecyclerView recyclerViewRiwayatPasien;
    ArrayList<RiwayatPasien> listRiwayatPasien = new ArrayList<>();
    Gson gson;
    RequestQueue requestQueue;
    TextView gagal;
    ProgressDialog progressDialog;
    SharedPref sharedPref = new SharedPref(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pasien);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_riwayat_pasien);
        recyclerViewRiwayatPasien = (RecyclerView) findViewById(R.id.rcy_riwayat_pasien);
        gagal= (TextView)findViewById(R.id.tv_gagal_riwayat);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_riwayat_icon);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("My History");
        toolbar.setTitleTextColor(Color.WHITE);

        progressDialog = new ProgressDialog(RiwayatPasienActivity.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgress(0);
        progressDialog.setCanceledOnTouchOutside(false);

        isiRiwayat();
    }

    public void isiRiwayat() {
        progressDialog = ProgressDialog.show(RiwayatPasienActivity.this,"Please wait...","loading...",false,false);
        requestQueue = Volley.newRequestQueue(RiwayatPasienActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String id;
        id = sharedPref.loadData("id");

        String url = AppConfig.RIWAYAT + id;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();
                        ResponseRiwayat posts = new ResponseRiwayat();
                        try {
                            posts = gson.fromJson(response, ResponseRiwayat.class);
                        } catch (Exception e) {
                            posts.setStatus("error");
                        }
                        if (posts.getStatus().equalsIgnoreCase("sukses")) {
                            if(posts.getData().size()==0){
                                Toast.makeText(RiwayatPasienActivity.this, "Anda tidak memiliki riwayat penyakit", Toast.LENGTH_SHORT).show();
                                gagal.setText("Maaf tidak ada data");
                                gagal.setVisibility(View.VISIBLE);
                            }else {
                                for (int i = 0; i < posts.getData().size(); i++) {
                                    listRiwayatPasien.add(new RiwayatPasien(posts.getData().get(i).getId_riwayat(),
                                            posts.getData().get(i).getId_pasien(),
                                            posts.getData().get(i).getNama_pasien(),
                                            posts.getData().get(i).getNama_dokter(),
                                            posts.getData().get(i).getNo_telp_dokter(),
                                            posts.getData().get(i).getAlamat_praktik(),
                                            String.valueOf(posts.getData().get(i).getUmur()),
                                            String.valueOf(posts.getData().get(i).getBerat_badan()),
                                            String.valueOf(posts.getData().get(i).getTinggi_badan()),
                                            posts.getData().get(i).getRiwayat_penyakit_keluarga(),
                                            posts.getData().get(i).getKeluhan_utama(),
                                            posts.getData().get(i).getDiagnosa(),
                                            posts.getData().get(i).getLarangan(),
                                            posts.getData().get(i).getTgl_periksa(),
                                            posts.getData().get(i).getPerawatan(),
                                            posts.getData().get(i).getAdvis(),
                                            posts.getData().get(i).getHead(),
                                            posts.getData().get(i).getNeck(),
                                            posts.getData().get(i).getThorax(),
                                            posts.getData().get(i).getAbdomen(),
                                            posts.getData().get(i).getEkstremitas()
                                    ));
                                }
                                recyclerViewRiwayatPasien.setHasFixedSize(true);
                                recyclerViewRiwayatPasien.setLayoutManager(new LinearLayoutManager(RiwayatPasienActivity.this));
                                recyclerViewRiwayatPasien.setAdapter(new RiwayatPasienAdapter(RiwayatPasienActivity.this, listRiwayatPasien));
                            }
                        } else {
                            Toast.makeText(RiwayatPasienActivity.this, "Maaf server sedang dalam masalah", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response", error.toString());
                        Toast.makeText(RiwayatPasienActivity.this, "Cek paket data anda", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        requestQueue.add(postRequest);
    }

    public class ResponseRiwayat{
        private String status;
        private ArrayList<RiwayatPasien> data;

        public ResponseRiwayat() {
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ArrayList<RiwayatPasien> getData() {
            return data;
        }

        public void setData(ArrayList<RiwayatPasien> data) {
            this.data = data;
        }
    }
}
