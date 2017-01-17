package com.example.keshar.cookbook;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    //log data
    final String log = "log_LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        //our inputs for login activity

        final EditText txt_password_enter = (EditText) findViewById(R.id.txt_pass_enter);
        final EditText txt_username_enter = (EditText) findViewById(R.id.txt_username_enter);
        final TextView tv_reg = (TextView) findViewById(R.id.tv_noacc);

        final Button btn_login = (Button) findViewById(R.id.btn_login);



        //this is the on click for users who do not have an account to register for one
        tv_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent reg_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(reg_intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(log,"Button clicked");

                final String username = txt_username_enter.getText().toString();
                final String password = txt_password_enter.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Log.v(log,"step 1 looking for responce");


                            if (success) {
                                Log.v(log,"step 2 success");

                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("age", age);
                                intent.putExtra("username", username);
                                LoginActivity.this.startActivity(intent);
                            } else {
                                Log.v(log,"step 3 login failed");

                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            Log.v(log,"step 4 JSONException");

                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}



