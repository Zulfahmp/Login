package com.zulfa.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login2Activity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private Button btn_login;
    private EditText ETemail, ETpassword;
    private AlertDialog.Builder builder;
    private TextView regis;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        context = Login2Activity.this;
        pDialog = new ProgressDialog(this);

        regis = findViewById(R.id.register_caption);
        ETemail = findViewById(R.id.email);
        ETpassword = findViewById(R.id.password);
        btn_login = findViewById(R.id.button);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login2Activity.this, Register2Activity.class));
            }
        });

    }

    private void login() {
        final String email = ETemail.getText().toString().trim();
        final String password = ETpassword.getText().toString().trim();
        pDialog.setMessage("Login Proses");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains(AppVar.LOGIN_SUCCESS)) {
                            hideDialog();
                            gotoCourseActivity();
                        } else {
                            hideDialog();
                            Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put(AppVar.KEY_EMAIL, email);
                params.put(AppVar.KEY_PASSWORD,password);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void gotoCourseActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        Toast.makeText(context, "Selamat Datang", Toast.LENGTH_LONG).show();
        startActivity(intent);
        finish();
    }

    private void showDialog(){
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideDialog(){
        if (!pDialog.isShowing())
            pDialog.show();
    }



}
