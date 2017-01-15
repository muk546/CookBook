package com.example.keshar.cookbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

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

    }
}
