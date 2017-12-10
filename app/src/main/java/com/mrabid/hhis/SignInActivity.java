package com.mrabid.hhis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.mrabid.hhis.Activity.BeforeMainActivity;
import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.Modal.ResponseLogin;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    Button signIn;
    TextView signUp;
    EditText usernameLogin,passwordLogin;
    ProgressDialog progress;
    Gson gson;
    RequestQueue requestQueue;
    SharedPref sharedPref = new SharedPref(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbars);
        usernameLogin = (EditText)findViewById(R.id.edt_username_login);
        passwordLogin = (EditText)findViewById(R.id.edt_password_login);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_person_login_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("Login");
        toolbar.setTitleTextColor(Color.WHITE);

        progress=new ProgressDialog(SignInActivity.this);
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);

        signIn = (Button)findViewById(R.id.btn_login_login);
        signUp = (TextView)findViewById(R.id.txt_signup_login);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masuk = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(masuk);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                Login();

                    }
        });
    }
    public void Login(){
        requestQueue = Volley.newRequestQueue(SignInActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = AppConfig.LOGIN;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        ResponseLogin posts =  new ResponseLogin();
                        try{
                            posts =  gson.fromJson(response, ResponseLogin.class);
                        }catch(Exception e){
                            posts.setStatus("error");
                        }
                        if(posts.getStatus().equalsIgnoreCase("success")){
                            Intent i = new Intent(SignInActivity.this, MainsActivity.class);
                            String id = String.valueOf(posts.getData().getId());
                            String token = String.valueOf(posts.getData().getToken());
                            sharedPref.saveData("id",id);
                            sharedPref.saveData("token",token);
                            startActivity(i);
                        }else{
                            Toast.makeText(SignInActivity.this, "Wrong Password or Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Response", error.toString());
                        Toast.makeText(SignInActivity.this, "Cek paket data anda", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", usernameLogin.getText().toString());
                params.put("password", passwordLogin.getText().toString());
                return params;
            }

        };
        requestQueue.add(postRequest);
    }

    public void onBackPressed(){
        Intent i = new Intent(SignInActivity.this,BeforeMainActivity.class);
        startActivity(i);
        }
}
