package com.mrabid.hhis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    EditText namaPasien,password,nik,email;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbars);
        namaPasien = (EditText)findViewById(R.id.edt_namaPasien_signUp);
        password = (EditText)findViewById(R.id.edt_password_signUp);
        nik = (EditText)findViewById(R.id.edt_nik_signUp);
        email = (EditText)findViewById(R.id.edt_email_signUp);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle("SignUp");
        toolbar.setTitleTextColor(Color.WHITE);

        signUp = (Button)findViewById(R.id.btn_RegisterSignUp);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SignUpActivity.this,NextSignUpActivity.class);
                i.putExtra("namaPasien",namaPasien.getText().toString());
                i.putExtra("password",password.getText().toString());
                i.putExtra("nik",nik.getText().toString());
                i.putExtra("email",email.getText().toString());
                startActivity(i);

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


}
