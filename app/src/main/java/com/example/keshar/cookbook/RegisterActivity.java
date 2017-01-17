package com.example.keshar.cookbook;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mukul on 1/15/17.
 */



public class RegisterActivity extends AppCompatActivity {

    //log data
    final String log = "log_RegisterActvity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etAge = (EditText) findViewById(R.id.txt_age);
        final EditText etName = (EditText) findViewById(R.id.txt_name);
        final EditText etUsername = (EditText) findViewById(R.id.txt_username);
        final EditText etPassword = (EditText) findViewById(R.id.txt_pass);
        final Button bRegister = (Button) findViewById(R.id.btn_reg);

        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.v(log,"button clicked");

                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Log.v(log,"step 1");

                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                Log.v(log,"step 2");

                            } else {
                                //user name already taken or server is down
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed (username already taken!)")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                Log.v(log,"step 3 (failure)");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v(log,"step 4 (failure)JSONException");

                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}