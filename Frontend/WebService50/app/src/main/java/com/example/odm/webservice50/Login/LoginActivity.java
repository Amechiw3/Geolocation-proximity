package com.example.odm.webservice50.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.util.HashMap;
import java.util.Map;

import com.example.odm.webservice50.MySingleton;
import com.example.odm.webservice50.R;
import com.example.odm.webservice50.RegistroActivity;
import com.example.odm.webservice50.Tablas.Usuarios.Config;
import com.example.odm.webservice50.Tablas.Usuarios.PerfilActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements Response.Listener<String> {

    Button btnLogin;
    EditText TxtUser, TxtPass;
    private String USERemail;
    int C;
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TxtUser = (EditText) findViewById(R.id.txtUser);
        TxtPass = (EditText) findViewById(R.id.txtPass);

        USERemail = TxtUser.getText().toString().trim();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = TxtUser.getText().toString().trim();
                String pass = TxtPass.getText().toString().trim();

                if(( user.isEmpty() ) || (pass.isEmpty())) {
                    Context context = getApplicationContext();
                    CharSequence text = "Se necesita informacion para continuar";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    C++;
                    if(C >= 3)
                    {
                        Intent principal = new Intent(LoginActivity.this, RegistroActivity.class);
                        startActivity(principal);
                    }

                }
                else {
                    //Intent principal = new Intent(LoginActivity.this, MainMenuActivity.class);
                    //startActivity(principal);
                    login();
                }
            }
        });

        TextView registro = (TextView) findViewById(R.id.linkSignup);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent principal = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(principal);
            }
        });


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Acti......on", null).show();

                Intent principal = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(principal);            }
        });*/
    }

    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if(loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
            startActivity(intent);
        }
    }

    private void login() {
        final String email    = TxtUser.getText().toString().trim();
        final String password = TxtPass.getText().toString().trim();

        // Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // if we are getting success from server
                        if(response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                            // Creating a shared preference
                            SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();


                            //Starting profile activity
                            Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
                            startActivity(intent);

                            /*Intent principal = new Intent(LoginActivity.this, MainMenuActivity.class);
                            startActivity(principal);*/
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_USER_EMAIL, email);
                params.put(Config.KEY_USER_PASSWORD, password);

                //returning parameter
                return params;
            }
        };
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    final String TAG = this.getClass().getSimpleName();

    @Override
    public void onResponse(String response) {
        Log.d(TAG, response);
    }
}
