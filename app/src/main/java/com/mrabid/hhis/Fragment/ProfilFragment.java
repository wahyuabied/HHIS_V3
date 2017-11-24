package com.mrabid.hhis.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.Modal.Pasien;
import com.mrabid.hhis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfilFragment extends Fragment {

    RecyclerView rcyartikel;
    LinearLayout personalLinear,emergencyLinear,addressLinear,forbiddenLinear,privateLinear,myDoctorLinear;
    ImageButton personalInformationUp,personalInformationDown,
            emergencyContactUp,emergencyContactDown,
            addressInformationUp,addressInformationDown,
            forbiddenInformationUp,forbiddenInformationDown,
            privateInformationUp,privateInformationDown,
            mydoctorInformtionUp,mydoctorInformtionDown;

    ProgressDialog progress;
    Gson gson;
    RequestQueue requestQueue;
    SharedPref sharedPref ;

    TextView nama,umur,jenisKelamin,emailPasien,golonganDarah,tinggiBadan,beratBadan,phone,alamat,larangan,penyakit,username,password,email,namaDokter,noTelpDokter,alamatPraktik,alamatRumah;
    Pasien pasien = new Pasien();
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama = (TextView)getActivity().findViewById(R.id.txt_nama_profil);
        umur = (TextView)getActivity().findViewById(R.id.txt_umur_profil);
        jenisKelamin = (TextView)getActivity().findViewById(R.id.txt_jenisKel_profil);
        emailPasien = (TextView)getActivity().findViewById(R.id.txt_email_profil);
        golonganDarah = (TextView)getActivity().findViewById(R.id.txt_golongan_darah_profil);
        tinggiBadan = (TextView)getActivity().findViewById(R.id.txt_tinggi_badan_profil);
        beratBadan = (TextView)getActivity().findViewById(R.id.txt_berat_badan_profil);
        phone = (TextView)getActivity().findViewById(R.id.tv_phone_profil);
        alamat = (TextView)getActivity().findViewById(R.id.tv_address_information_profil);
        larangan = (TextView)getActivity().findViewById(R.id.tv_larangan_profil);
        penyakit = (TextView)getActivity().findViewById(R.id.tv_diagnosa_profil);
        username = (TextView)getActivity().findViewById(R.id.tv_username_profil);
        password = (TextView)getActivity().findViewById(R.id.tv_password_profil);

        personalInformationDown = (ImageButton)getActivity().findViewById(R.id.img_personalInformation_profil_down);
        personalInformationUp = (ImageButton)getActivity().findViewById(R.id.img_personalInformation_profil_up);
        emergencyContactDown = (ImageButton)getActivity().findViewById(R.id.img_emergencyContact_profil_down);
        emergencyContactUp = (ImageButton)getActivity().findViewById(R.id.img_emergencyContact_profil_up);
        addressInformationUp = (ImageButton)getActivity().findViewById(R.id.img_addressInformation_profil_up);
        addressInformationDown = (ImageButton)getActivity().findViewById(R.id.img_addressInformation_profil_down);
        forbiddenInformationDown = (ImageButton)getActivity().findViewById(R.id.img_forbiddenInformation_profil_down);
        forbiddenInformationUp = (ImageButton)getActivity().findViewById(R.id.img_forbiddenInformation_profil_up);
        privateInformationDown = (ImageButton)getActivity().findViewById(R.id.img_privateInformation_profil_down);
        privateInformationUp = (ImageButton)getActivity().findViewById(R.id.img_privateInformation_profil_up);
        mydoctorInformtionDown = (ImageButton)getActivity().findViewById(R.id.img_docterInformation_profil_down);
        mydoctorInformtionUp = (ImageButton)getActivity().findViewById(R.id.img_docterInformation_profil_up);
        personalLinear = (LinearLayout)getActivity().findViewById(R.id.lnr_detail_personal_information_profil);
        emergencyLinear = (LinearLayout)getActivity().findViewById(R.id.lnr_detail_emergency_contact_profil);
        addressLinear = (LinearLayout)getActivity().findViewById(R.id.lnr_detail_address_information_profil);
        forbiddenLinear = (LinearLayout)getActivity().findViewById(R.id.lnr_detail_forbidden_thing_profil);
        privateLinear = (LinearLayout)getActivity().findViewById(R.id.lnr_private_information_profil);
        myDoctorLinear = (LinearLayout)getActivity().findViewById(R.id.lnr_my_doctor_information_profil);

        personalInformationDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalInformationUp.setVisibility(View.VISIBLE);
                personalInformationDown.setVisibility(View.INVISIBLE);
                personalLinear.setVisibility(View.VISIBLE);
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_down);
                personalLinear.startAnimation(slideDown);
            }
        });
        personalInformationUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalInformationUp.setVisibility(View.INVISIBLE);
                personalInformationDown.setVisibility(View.VISIBLE);
                personalLinear.setVisibility(View.GONE);

            }
        });

        emergencyContactDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyContactUp.setVisibility(View.VISIBLE);
                emergencyContactDown.setVisibility(View.INVISIBLE);
                emergencyLinear.setVisibility(View.VISIBLE);
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_down);
                emergencyLinear.startAnimation(slideDown);
            }
        });
        emergencyContactUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emergencyContactUp.setVisibility(View.INVISIBLE);
                emergencyContactDown.setVisibility(View.VISIBLE);
                emergencyLinear.setVisibility(View.GONE);

            }
        });

        addressInformationDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressInformationUp.setVisibility(View.VISIBLE);
                addressInformationDown.setVisibility(View.INVISIBLE);
                addressLinear.setVisibility(View.VISIBLE);
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_down);
                addressLinear.startAnimation(slideDown);
            }
        });
        addressInformationUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressInformationUp.setVisibility(View.INVISIBLE);
                addressInformationDown.setVisibility(View.VISIBLE);
                addressLinear.setVisibility(View.GONE);

            }
        });

        forbiddenInformationDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forbiddenInformationUp.setVisibility(View.VISIBLE);
                forbiddenInformationDown.setVisibility(View.INVISIBLE);
                forbiddenLinear.setVisibility(View.VISIBLE);
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_down);
                forbiddenLinear.startAnimation(slideDown);
            }
        });
        forbiddenInformationUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forbiddenInformationUp.setVisibility(View.INVISIBLE);
                forbiddenInformationDown.setVisibility(View.VISIBLE);
                forbiddenLinear.setVisibility(View.GONE);
            }
        });

        privateInformationDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privateInformationUp.setVisibility(View.VISIBLE);
                privateInformationDown.setVisibility(View.INVISIBLE);
                privateLinear.setVisibility(View.VISIBLE);
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_down);
                privateLinear.startAnimation(slideDown);
            }
        });
        privateInformationUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privateInformationUp.setVisibility(View.INVISIBLE);
                privateInformationDown.setVisibility(View.VISIBLE);
                privateLinear.setVisibility(View.GONE);
            }
        });

        mydoctorInformtionDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydoctorInformtionUp.setVisibility(View.VISIBLE);
                mydoctorInformtionDown.setVisibility(View.INVISIBLE);
                myDoctorLinear.setVisibility(View.VISIBLE);
                Animation slideDown = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_down);
                myDoctorLinear.startAnimation(slideDown);
            }
        });
        mydoctorInformtionUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydoctorInformtionUp.setVisibility(View.INVISIBLE);
                mydoctorInformtionDown.setVisibility(View.VISIBLE);
                myDoctorLinear.setVisibility(View.GONE);
            }
        });


        progress=new ProgressDialog(getActivity());
        progress.setMessage("Looking for your profil....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);

        progress.show();

        getProfil();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profil_fragment, container, false);
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
                        ResponseProfil posts =  new ResponseProfil();
                        try{
                            posts =  gson.fromJson(response, ResponseProfil.class);
                        }catch(Exception e){
                            posts.setStatus("error");
                        }
                        if(posts.getStatus().equalsIgnoreCase("sukses")){
                            Toast.makeText(getActivity(), posts.getData().get(0).getEmail().toString(), Toast.LENGTH_SHORT).show();
                            Log.d("Response",posts.getData().get(0).getEmail().toString());
                            if(posts.getData().size()==0){
                                Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_SHORT).show();
                            }else {
                                //set text disini krena nilainya null ketika diatas
                               pasien = new Pasien(posts.getData().get(0).getId_pasien(),
                                        posts.getData().get(0).getNama_pasien(),
                                        posts.getData().get(0).getUmur(),
                                        posts.getData().get(0).getJenis_kelamin(),
                                        posts.getData().get(0).getEmail(),
                                        posts.getData().get(0).getGolongan_darah(),
                                        posts.getData().get(0).getBerat_badan(),
                                        posts.getData().get(0).getTinggi_badan(),
                                        posts.getData().get(0).getRiwayat_penyakit_keluarga(),
                                        posts.getData().get(0).getAlamat(),
                                        posts.getData().get(0).getLarangan(),
                                        posts.getData().get(0).getPemeriksaan_terbaru(),
                                        posts.getData().get(0).getDokter()
                                );
                                nama.setText(String.valueOf(pasien.getNama_pasien()));
                                umur.setText(String.valueOf(pasien.getUmur())+"Tahun, ");
                                jenisKelamin.setText(pasien.getJenis_kelamin());
                                emailPasien.setText(pasien.getEmail());
                                golonganDarah.setText(pasien.getGolongan_darah());
                                tinggiBadan.setText(String.valueOf(pasien.getTinggi_badan())+"Cm");
                                beratBadan.setText(String.valueOf(pasien.getBerat_badan())+"Kg");
                                alamat.setText(pasien.getAlamat());
                                penyakit.setText(pasien.getRiwayat_penyakit_keluarga());
                                username.setText("API Belum Lengkap");
                                password.setText("API Belum Lengkap");
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


    public class ResponseProfil{
        private String status;
        private ArrayList<Pasien> data;

        public ResponseProfil(String status, ArrayList<Pasien> data) {
            this.status = status;
            this.data = data;
        }

        public ResponseProfil() {
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