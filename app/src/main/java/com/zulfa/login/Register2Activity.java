package com.zulfa.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class Register2Activity extends AppCompatActivity {

    private EditText ETnama, ETemail, ETpassword, c_password;
    private Button register;
    private ProgressBar load;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        context = Register2Activity.this;

        ETnama = findViewById(R.id.ETnama);
        ETemail = findViewById(R.id.ETemail);
        ETpassword = findViewById(R.id.ETpassword);
        c_password = findViewById(R.id.ETconfirm);
        register = findViewById(R.id.btn_regis);
        load = findViewById(R.id.loading);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Regist() {

        load.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);

        final String nama = ETnama.getText().toString().trim();
        final String email = ETemail.getText().toString().trim();
        final String password = ETpassword.getText().toString().trim();

        if (ETnama.getText().toString().length() == 0) {
            Toast.makeText(Register2Activity.this, "Username Diperlukan", Toast.LENGTH_LONG).show();
            load.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
        } else if (ETemail.getText().toString().length() == 0){
            Toast.makeText(Register2Activity.this, "Email Diperlukan", Toast.LENGTH_LONG).show();
            load.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
        }else if (ETpassword.getText().toString().length() < 5) {
            Toast.makeText(Register2Activity.this, "Password Kurang Dari 5!", Toast.LENGTH_LONG).show();
            load.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                                response.contains(AppVar.REGISTER_SUCCESS);
                                gotoCourseActivity();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
                            load.setVisibility(View.GONE);
                            register.setVisibility(View.VISIBLE);
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put(AppVar.KEY_NAMA, nama);
                    params.put(AppVar.KEY_EMAIL, email);
                    params.put(AppVar.KEY_PASSWORD,password);
                    return params;
                }
            };

            Volley.newRequestQueue(this).add(stringRequest);
        }
    }

    private void gotoCourseActivity() {
        Intent intent = new Intent(context, Login2Activity.class);
        Toast.makeText(context, "Register Berhasil", Toast.LENGTH_LONG).show();
        load.setVisibility(View.GONE);
        register.setVisibility(View.VISIBLE);
        startActivity(intent);
        finish();
    }
}
