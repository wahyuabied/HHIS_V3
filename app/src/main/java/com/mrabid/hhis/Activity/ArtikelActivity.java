package com.mrabid.hhis.Activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mrabid.hhis.Adapter.ArtikelAdapter;
import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Modal.Artikel;
import com.mrabid.hhis.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArtikelActivity extends AppCompatActivity {

    private ProgressDialog loading;
    RecyclerView rcyartikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_person_login_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Artikel");
        toolbar.setTitleTextColor(Color.WHITE);


        rcyartikel=(RecyclerView)findViewById(R.id.rcy_list_artikel);
        getData();
    }

    private void getData(){
        String url = AppConfig.ARTIKEL;

        loading = ProgressDialog.show(ArtikelActivity.this,"Please wait...","mencari...",false,false);
        JsonArrayRequest stringRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(ArtikelActivity.this,"Server Error or No Internet Connection",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ArtikelActivity.this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(JSONArray response){
        List<Artikel> artikel = new ArrayList<>();

        try {

            for (int i = 0; i < response.length(); i++) {
                JSONObject data = (JSONObject) response.getJSONObject(i);
                artikel.add(new Artikel(data.getString("judul"),(data.getString("deskripsi")),
                        data.getString("image"), (data.getString("abstrak"))));
            }

            rcyartikel.setHasFixedSize(true);
            rcyartikel.setLayoutManager(new LinearLayoutManager(ArtikelActivity.this));
            rcyartikel.setAdapter(new ArtikelAdapter(ArtikelActivity.this, artikel));

            loading.dismiss();


        } catch (JSONException e) {
            e.printStackTrace();
            loading.dismiss();
            Toast.makeText(ArtikelActivity.this, "CEK " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        //TxtHasil.setText("Tanggal Periksa:\t"+tgl_periksa+"\nnama pasien:\t" +namaPasien+ "\numur:\t"+ umur+"\nberat badan:\t" +beratBadan);
    }
}
