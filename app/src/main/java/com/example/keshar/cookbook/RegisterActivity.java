package com.example.keshar.cookbook;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //our inputs for register activity
        final EditText txt_age = (EditText) findViewById(R.id.txt_age);
        final EditText txt_name = (EditText) findViewById(R.id.txt_name);
        final EditText txt_gender = (EditText) findViewById(R.id.txt_gender);
        final EditText txt_username = (EditText) findViewById(R.id.txt_username);
        final EditText txt_password = (EditText) findViewById(R.id.txt_pass);

        final Button btn_register = (Button) findViewById(R.id.btn_reg);


        //register button onclick

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("test","test");
                final String name = txt_name.getText().toString();
                final int age = Integer.parseInt(txt_age.getText().toString());
                final String gender = txt_gender.getText().toString();
                final String username = txt_username.getText().toString();
                final String password = txt_password.getText().toString();

                Response.Listener<String> responseListener  = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                                //if it fails
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed, Please try again in a little while...")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();;
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest register_request = new RegisterRequest(name, age, gender, username, password, responseListener );
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(register_request);

            }
        });





    }
}
