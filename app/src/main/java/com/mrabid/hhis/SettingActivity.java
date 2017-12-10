package com.mrabid.hhis;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.mrabid.hhis.Activity.BeforeMainActivity;
import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Helper.SharedPref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {

    Button logout;
    SharedPref sharedPref;
    RelativeLayout email,password,nama,telepon,alamat;
    private TextView tv_nik,tv_email,tv_pass,tv_nama,tv_telepon,tv_alamat;
    CircleImageView image;
    ProgressDialog progress;
    Gson gson;
    RequestQueue requestQueue;
    private ArrayList<Setting> dataSetting = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Setting");
        toolbar.setTitleTextColor(Color.WHITE);

        email= (RelativeLayout) findViewById(R.id.rlt_profEmail);
        password = (RelativeLayout)findViewById(R.id.rlt_profPassword);
        nama = (RelativeLayout)findViewById(R.id.rlt_Nama);
        telepon = (RelativeLayout)findViewById(R.id.rlt_profTelepon);
        alamat = (RelativeLayout) findViewById(R.id.rlt_proAlamat);
        logout = (Button)findViewById(R.id.btn_logout);
        image = (CircleImageView)findViewById(R.id.img_profil_setting);

        tv_nik = (TextView)findViewById(R.id.txt_nik);
        tv_email = (TextView)findViewById(R.id.txt_emailSeting);
        tv_pass = (TextView)findViewById(R.id.txt_PasswordSetting);
        tv_nama = (TextView)findViewById(R.id.txt_Nama_Setting);
        tv_telepon = (TextView)findViewById(R.id.txt_TeleponSetting);
        tv_alamat = (TextView)findViewById(R.id.txt_AlamatSetting);


//================================================getting_data================================================//

        progress=new ProgressDialog(SettingActivity.this);
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);

        requestQueue = Volley.newRequestQueue(SettingActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String id_user = sharedPref.loadData("id");

        String url = AppConfig.PROFILSETTING+id_user;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        ResponseSetting posts =  new ResponseSetting();
                        try{
                            posts =  gson.fromJson(response, ResponseSetting.class);
                        }catch(Exception e){
                            posts.setStatus("error");
                        }
                        if(posts.getStatus().equalsIgnoreCase("sukses")){
                            for(int i=0;i<posts.getData().size();i++){
                                dataSetting.add(new Setting(
                                        posts.getData().get(i).getId_pasien(),
                                        posts.getData().get(i).getNik(),
                                        posts.getData().get(i).getEmail(),
                                        posts.getData().get(i).getPassword(),
                                        posts.getData().get(i).getNama_pasien(),
                                        posts.getData().get(i).getNo_telp(),
                                        posts.getData().get(i).getAlamat(),
                                        posts.getData().get(i).getImage()));
                            }
                            tv_nik.setText(String.valueOf(dataSetting.get(0).getNik()));
                            tv_email.setText(dataSetting.get(0).getEmail()+"");
                            tv_pass.setText(dataSetting.get(0).getPassword()+"");
                            tv_nama.setText(dataSetting.get(0).getNama_pasien()+"");
                            tv_telepon.setText(dataSetting.get(0).getNo_telp()+"");
                            tv_alamat.setText(dataSetting.get(0).getAlamat()+"");
                            Glide.with(SettingActivity.this).load("http://hhis.tk/backend/web/"+dataSetting.get(0).getImage()).into(image);
                        }else{
                            Toast.makeText(SettingActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response", error.toString());
                        Toast.makeText(SettingActivity.this, "Cek paket data anda", Toast.LENGTH_SHORT).show();
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

//===============================///============================================================================//

        setClick();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SettingActivity.this,BeforeMainActivity.class);
                sharedPref.saveData("id","");
                startActivity(i);
            }
        });

    }

    public void setClick(){
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(SettingActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.text_input_password, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(SettingActivity.this);
                alertDialogBuilderUserInput.setView(mView);
                TextView tittle = (TextView)mView.findViewById(R.id.dialogTitle);
                tittle.setText("Edit Email");
                final EditText emailDialog = (EditText) mView.findViewById(R.id.userInputDialog);
                emailDialog.setText(sharedPref.loadData("username")+"");
                alertDialogBuilderUserInput.setCancelable(false)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(SettingActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.text_input_password, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(SettingActivity.this);
                alertDialogBuilderUserInput.setView(mView);
                TextView tittle = (TextView)mView.findViewById(R.id.dialogTitle);
                tittle.setText("Edit Password");
                final EditText passDialog = (EditText) mView.findViewById(R.id.userInputDialog);
                passDialog.setInputType(InputType.TYPE_CLASS_TEXT);
                passDialog.setText(sharedPref.loadData("password")+"");
                alertDialogBuilderUserInput.setCancelable(false)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });

        nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(SettingActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.text_input_password, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(SettingActivity.this);
                alertDialogBuilderUserInput.setView(mView);
                TextView tittle = (TextView)mView.findViewById(R.id.dialogTitle);
                tittle.setText("Edit Nama");
                final EditText namaDialog = (EditText) mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderUserInput.setCancelable(false)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });

        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(SettingActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.text_input_password, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(SettingActivity.this);
                alertDialogBuilderUserInput.setView(mView);
                TextView tittle = (TextView)mView.findViewById(R.id.dialogTitle);
                tittle.setText("Edit Alamat");
                final EditText alamatDialog = (EditText) mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderUserInput.setCancelable(false)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });

        telepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(SettingActivity.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.text_input_password, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(SettingActivity.this);
                alertDialogBuilderUserInput.setView(mView);
                TextView tittle = (TextView)mView.findViewById(R.id.dialogTitle);
                tittle.setText("Edit No Telepon");
                final EditText teleponDialog = (EditText) mView.findViewById(R.id.userInputDialog);
                teleponDialog.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertDialogBuilderUserInput.setCancelable(false)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                            }
                        })
                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
            }
        });
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

    public class ResponseSetting{
        private String status;
        private ArrayList<Setting> data;

        public ResponseSetting(String status, ArrayList<Setting> data) {
            this.status = status;
            this.data = data;
        }

        public ResponseSetting() {
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public ArrayList<Setting> getData() {
            return data;
        }

        public void setData(ArrayList<Setting> data) {
            this.data = data;
        }
    }

    public class Setting{
        private int id_pasien;
        private long nik;
        private String email;
        private String password;
        private String nama_pasien;
        private String no_telp;
        private String alamat;
        private String image;

        public Setting(int id_pasien, long nik, String email, String password, String nama_pasien, String no_telp, String alamat, String image) {
            this.id_pasien = id_pasien;
            this.nik = nik;
            this.email = email;
            this.password = password;
            this.nama_pasien = nama_pasien;
            this.no_telp = no_telp;
            this.alamat = alamat;
            this.image = image;
        }

        public Setting() {
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getId_pasien() {
            return id_pasien;
        }

        public void setId_pasien(int id_pasien) {
            this.id_pasien = id_pasien;
        }

        public long getNik() {
            return nik;
        }

        public void setNik(long nik) {
            this.nik = nik;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNama_pasien() {
            return nama_pasien;
        }

        public void setNama_pasien(String nama_pasien) {
            this.nama_pasien = nama_pasien;
        }

        public String getNo_telp() {
            return no_telp;
        }

        public void setNo_telp(String no_telp) {
            this.no_telp = no_telp;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }
    }
}
