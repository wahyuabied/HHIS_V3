package com.mrabid.hhis;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.mrabid.hhis.Adapter.RecyclerKotaAdapter;
import com.mrabid.hhis.Adapter.RecyclerProvinsiAdapter;
import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.Modal.Kota;
import com.mrabid.hhis.Modal.Pasien;
import com.mrabid.hhis.Modal.Provinsi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NextSignUpActivity extends AppCompatActivity implements HelpersDialog {

    ProgressDialog progress;
    RequestQueue requestQueue;
    SharedPref sharedPref = new SharedPref(this) ;
    Gson gson;
    TextView prov,kab;
    String namaPasien,nik,email,password,golDar,jenKel,kabupaten;
    EditText no_Telp,ala_mat;
    Spinner gol_Dar,jen_Kel;
    Dialog dialogList,dialogKota;
    String d_id_provinsi,d_nama_provinsi,d_id_kota,d_nama_kota;
    ArrayList<Provinsi> provinsi = new ArrayList<>();
    ArrayList<Kota> kota = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_sign_up);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbars);
        Button button = (Button)findViewById(R.id.btn_RegisterSignUp);
        no_Telp = (EditText)findViewById(R.id.edt_noTelp_signUp);
        ala_mat= (EditText)findViewById(R.id.edt_alamat_signUp);
        gol_Dar = (Spinner)findViewById(R.id.spn_golDar_signUp);
        jen_Kel = (Spinner)findViewById(R.id.spn_jenKel_signUp);
        prov = (TextView) findViewById(R.id.btn_provinsi_signUP);
        kab = (TextView)findViewById(R.id.btn_kota_signUP);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("SignUp");
        toolbar.setTitleTextColor(Color.WHITE);

        gol_Dar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                golDar = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        jen_Kel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jenKel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----------------------------------Button Provinsi-------------------------------------//
        dialogList = new Dialog(NextSignUpActivity.this);
        dialogList.setContentView(R.layout.dialog_kota_propinsi);
        dialogList.setTitle("Provinsi");
        isiProvinsi();
        RecyclerView rvListTask = (RecyclerView) dialogList.findViewById(R.id.rcy_list_kota_prop);
        rvListTask.setHasFixedSize(true);
        rvListTask.setLayoutManager(new LinearLayoutManager(NextSignUpActivity.this));
        rvListTask.setAdapter(new RecyclerProvinsiAdapter(NextSignUpActivity.this,provinsi));

        prov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogList.show();
            }
        });


        //-----------------------------------Button Kabupaten-----------------------------------//
        dialogKota = new Dialog(NextSignUpActivity.this);
        dialogKota.setContentView(R.layout.dialog_kota_propinsi);
        dialogKota.setTitle("Kabupaten");
        isiKota();
        RecyclerView rvKota = (RecyclerView)dialogKota.findViewById(R.id.rcy_list_kota_prop);
        rvKota.setHasFixedSize(true);
        rvKota.setLayoutManager(new LinearLayoutManager(NextSignUpActivity.this));
        rvKota.setAdapter(new RecyclerKotaAdapter(NextSignUpActivity.this,kota));

        kab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogKota.show();;
            }
        });

        namaPasien = getIntent().getExtras().getString("namaPasien").toString();
        password = getIntent().getExtras().getString("password").toString();
        email = getIntent().getExtras().getString("email").toString();
        nik = getIntent().getExtras().getString("nik").toString();

        progress=new ProgressDialog(NextSignUpActivity.this);
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
                progress.show();
            }
        });

    }

    public void Signup(){
        requestQueue = Volley.newRequestQueue(NextSignUpActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = AppConfig.REGISTER;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        Log.d("Response", response);
                        ResponseSignUp posts =  gson.fromJson(response, ResponseSignUp.class);

                        if(posts.getStatus().equalsIgnoreCase("success")){
                            Intent i = new Intent(NextSignUpActivity.this, SignInActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(NextSignUpActivity.this, posts.getStatus(), Toast.LENGTH_SHORT).show();
                            Log.d("Response :",response);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                  params.put("nama_pasien", namaPasien);
                  params.put("alamat", ala_mat.getText().toString());
                  params.put("no_telp_pasien", no_Telp.getText().toString());
                  params.put("gol_darah", golDar);
                  params.put("jenis_kelamin", jenKel);
                  params.put("nik", nik);
                  params.put("id_kota", d_id_kota);
                  params.put("id_provinsi", d_id_provinsi);
                  params.put("email",email);
                  params.put("password",password);

                return params;
            }
        };
        requestQueue.add(postRequest);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void DismissDialog() {
        dialogList.dismiss();
    }

    @Override
    public void DismissDialogKota() {
        dialogKota.dismiss();
    }

    public class ResponseSignUp{
        private String status;
        private String message;
        private Pasien data;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Pasien getData() {
            return data;
        }

        public void setData(Pasien data) {
            this.data = data;
        }
    }

    public class ResponseKota{
        private ArrayList<Kota> datakota;

        public ArrayList<Kota> getDatakota() {
            return datakota;
        }

        public void setDatakota(ArrayList<Kota> datakota) {
            this.datakota = datakota;
        }
    }

    public void isiProvinsi(){

        provinsi.add(new Provinsi(11, "Aceh"));
        provinsi.add(new Provinsi(12, "Sumatera Utara"));
        provinsi.add(new Provinsi(13, "Sumatera Barat"));
        provinsi.add(new Provinsi(14, "Riau"));
        provinsi.add(new Provinsi(15, "Jambi"));
        provinsi.add(new Provinsi(16, "Sumatera Selatan"));
        provinsi.add(new Provinsi(17, "Bengkulu"));
        provinsi.add(new Provinsi(18, "Lampung"));
        provinsi.add(new Provinsi(19, "Kepulauan Bangka Belitung"));
        provinsi.add(new Provinsi(21, "Kepulauan Riau"));
        provinsi.add(new Provinsi(31, "DKI Jakarta"));
        provinsi.add(new Provinsi(32, "Jawa Barat"));
        provinsi.add(new Provinsi(33, "Jawa Tengah"));
        provinsi.add(new Provinsi(34, "DI Yogyakarta"));
        provinsi.add(new Provinsi(35, "Jawa Timur"));
        provinsi.add(new Provinsi(36, "Banten"));
        provinsi.add(new Provinsi(51, "Bali"));
        provinsi.add(new Provinsi(52, "Nusa Tenggara Barat"));
        provinsi.add(new Provinsi(53, "Nusa Tenggara Timur"));
        provinsi.add(new Provinsi(61, "Kalimantan Barat"));
        provinsi.add(new Provinsi(62, "Kalimantan Tengah"));
        provinsi.add(new Provinsi(63, "Kalimantan Selatan"));
        provinsi.add(new Provinsi(64, "Kalimantan Timur"));
        provinsi.add(new Provinsi(65, "Kalimantan Utara"));
        provinsi.add(new Provinsi(71, "Sulawesi Utara"));
        provinsi.add(new Provinsi(72, "Sulawesi Tengah"));
        provinsi.add(new Provinsi(73, "Sulawesi Selatan"));
        provinsi.add(new Provinsi(74, "Sulawesi Tenggara"));
        provinsi.add(new Provinsi(75, "Gorontalo"));
        provinsi.add(new Provinsi(76, "Sulawesi Barat"));
        provinsi.add(new Provinsi(81, "Maluku"));
        provinsi.add(new Provinsi(82, "Maluku Utara"));
        provinsi.add(new Provinsi(91, "Papua Barat"));
        provinsi.add(new Provinsi(92, "Papua"));
    }

    @Override
    public void getProvinsi(String id ,String nama) {
        d_id_provinsi = id;
        d_nama_provinsi = nama;
        prov.setText(nama);
    }

    @Override
    public void getKota(String id_kota, String nama_kota) {
        d_id_kota = id_kota;
        d_nama_kota = nama_kota;
        kab.setText(nama_kota);
    }

    public void isiKota(){
        requestQueue = Volley.newRequestQueue(NextSignUpActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = AppConfig.KOTA;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        Log.d("Response", response);
                        ResponseKota posts =  new ResponseKota();
                        try{
                            posts =  gson.fromJson(response, ResponseKota.class);
                        }catch(Exception e){
                            Toast.makeText(NextSignUpActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("Response",e.toString());
                        }
                        if(posts.getDatakota()!=null){
                            for(int i=0;i<posts.getDatakota().size();i++){
                                kota.add(new Kota
                                        (posts.getDatakota().get(i).getId_kab(),
                                                posts.getDatakota().get(i).getId_prov(),
                                                posts.getDatakota().get(i).getNama()));
                            }
                        }else{
                            Log.d("Response :",response);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                return params;
            }
        };
        requestQueue.add(postRequest);
    }

}
