package com.mrabid.hhis.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.hhis.AboutActivity;
import com.mrabid.hhis.Activity.ArtikelActivity;
import com.mrabid.hhis.Activity.RiwayatPasienActivity;
import com.mrabid.hhis.GraphActivity;
import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.Modal.Pasien;
import com.mrabid.hhis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {


    ImageButton riwayat,artikel, graph,about;
    SharedPref sharedPref;
    RequestQueue requestQueue;
    ProgressDialog progress;
    Gson gson;
    ArrayList<Pasien> pasien = new ArrayList<>();
    CircleImageView image;
    TextView nama_profil;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        graph = (ImageButton) getActivity().findViewById(R.id.rlt_graph);
        artikel = (ImageButton) getActivity().findViewById(R.id.rlt_artikel);
        riwayat = (ImageButton) getActivity().findViewById(R.id.rlt_history);
        image = (CircleImageView)getActivity().findViewById(R.id.circle_profil_home);
        nama_profil = (TextView)getActivity().findViewById(R.id.tv_nama_home);
        about = (ImageButton)getActivity().findViewById(R.id.imgbtn_about_home);

        progress=new ProgressDialog(getActivity());
        progress.setMessage("Sending...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);

        progress.show();
        getProfil();

        artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ini Artikel", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ArtikelActivity.class));

            }
        });

        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RiwayatPasienActivity.class));
            }
        });

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GraphActivity.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    public void getProfil(){

        String id_user = sharedPref.loadData("id");
        requestQueue = Volley.newRequestQueue(getActivity());
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = AppConfig.PASIEN+id_user;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        HomeFragment.ResponseProfilHome posts = new ResponseProfilHome();
                        try{
                            posts =  gson.fromJson(response, HomeFragment.ResponseProfilHome.class);
                        }catch(Exception e){
                            posts.setStatus("error");
                        }
                        if(posts.getStatus().equalsIgnoreCase("sukses")){
                            Log.d("Response",posts.getData().get(0).getEmail().toString());
                            if(posts.getData().size()==0){
                                Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_SHORT).show();
                            }else {
                                //set text disini krena nilainya null ketika diatas
                                for(int i=0;i<posts.getData().size();i++){
                                    pasien.add(new Pasien(posts.getData().get(i).getId_pasien(),
                                            posts.getData().get(i).getId_riwayat(),
                                            posts.getData().get(i).getNama_pasien(),
                                            posts.getData().get(i).getUmur(),
                                            posts.getData().get(i).getJenis_kelamin(),
                                            posts.getData().get(i).getEmail(),
                                            posts.getData().get(i).getGolongan_darah(),
                                            posts.getData().get(i).getTinggi_badan(),
                                            posts.getData().get(i).getBerat_badan(),
                                            posts.getData().get(i).getNo_telp(),
                                            posts.getData().get(i).getAlamat(),
                                            posts.getData().get(i).getLarangan(),
                                            posts.getData().get(i).getDiagnosa(),
                                            posts.getData().get(i).getImage()));
                                }


                                Glide.with(getActivity()).load("http://hhis.tk/backend/web/"+pasien.get(0).getImage()).into(image);
                                nama_profil.setText(pasien.get(0).getNama_pasien().toString());

                            }
                        }else{
                            Toast.makeText(getActivity(), "Data tidak masuk", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response", error.toString());
                        Toast.makeText(getActivity(), "Cek paket data anda", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
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

    public class ResponseProfilHome{
        private String status;
        private ArrayList<Pasien> data;

        public ResponseProfilHome(String status, ArrayList<Pasien> data) {
            this.status = status;
            this.data = data;
        }

        public ResponseProfilHome() {
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ArrayList<Pasien> getData() {
            return data;
        }

        public void setData(ArrayList<Pasien> data) {
            this.data = data;
        }
    }
}
