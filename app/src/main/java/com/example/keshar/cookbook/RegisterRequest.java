package com.example.keshar.cookbook;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mukul on 1/14/17.
 * makes request to register.php file
 * get's data back as string
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://cookbook-app.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name,int age,String gender,String username,String password, Response.Listener<String> listener){
        //no time for error lisener will leave null for now
        super(Method.POST,REGISTER_REQUEST_URL, listener, null);
        //hasmap with our data for register

        //name, age, gender, username, password
        params = new HashMap<>();
        params.put("name",name);
        params.put("age",age + "");
        params.put("gender",gender);
        params.put("username",username);
        params.put("password",password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


