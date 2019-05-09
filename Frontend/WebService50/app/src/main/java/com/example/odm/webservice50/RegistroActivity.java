package com.example.odm.webservice50;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.odm.webservice50.Login.LoginActivity;
import com.example.odm.webservice50.Tablas.Usuarios.Config;
import com.example.odm.webservice50.Tablas.Usuarios.PerfilActivity;
import com.example.odm.webservice50.Tablas.Usuarios.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    Button BtnAceptar, BtnCancelar;
    EditText User, Pass, RePass, Email;
    //EditText Cuenta;
    Spinner Privacidad;
    protected ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setToolBar();

        User = (EditText)findViewById(R.id.txtUserRegistro);
        //Cuenta = (EditText)findViewById(R.id.txtCuentaRegistro);
        Pass = (EditText)findViewById(R.id.txtPassRegistro);
        RePass = (EditText)findViewById(R.id.txtRePassRegistro);
        Email = (EditText)findViewById(R.id.txtCorreoRegistro);
        Privacidad = (Spinner) findViewById(R.id.Privacidadspinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.Privacidad,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Privacidad.setAdapter(adapter);


        BtnAceptar = (Button)findViewById(R.id.btnAceptarRegistro);
        BtnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String user = User.getText().toString().trim();
                //final String cuenta = Cuenta.getText().toString().trim();
                final String privacidad = Privacidad.getSelectedItem().toString();
                final String pass = Pass.getText().toString().trim();
                String repass = RePass.getText().toString().trim();
                final String email = Email.getText().toString().trim();
                Context context = getApplicationContext();

                int duration = Toast.LENGTH_SHORT;
                if(user.isEmpty() || pass.isEmpty() || repass.isEmpty() || email.isEmpty()) {
                    CharSequence text = "Se necesita informacion para continuar con el registro";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    if(pass.length() < 8) {
                        CharSequence text = "La contraseña es demaciado corta";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        if(pass.equals(repass)) {
                            addUSUARIOS();
                            login();
                        } else {
                            CharSequence text = "La contraseña no coinciden";
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                }
            }
        });
        BtnCancelar = (Button)findViewById(R.id.btnCancelarRegistro);
        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent principal = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(principal);
            }
        });
    }

    private void login() {
        final String email    = Email.getText().toString().trim();
        final String password = Pass.getText().toString().trim();

        // Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // if we are getting success from server
                        if(response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                            // Creating a shared preference
                            SharedPreferences sharedPreferences = RegistroActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            //prueba();
                            //getEmployee();

                            //Starting profile activity
                            Intent intent = new Intent(RegistroActivity.this, PerfilActivity.class);
                            startActivity(intent);

                            /*Intent principal = new Intent(LoginActivity.this, MainMenuActivity.class);
                            startActivity(principal);*/
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            //Toast.makeText(RegistroActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                            startActivity(intent);
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

    private void addUSUARIOS() {
        final String user = User.getText().toString().trim();
        //final String cuenta = Cuenta.getText().toString().trim();
        final String privacidad = Privacidad.getSelectedItem().toString();
        final String pass = Pass.getText().toString().trim();
        final String email = Email.getText().toString().trim();

        class AddUSER extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(RegistroActivity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                Toast.makeText(RegistroActivity.this, s, Toast.LENGTH_LONG).show();
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_USER_USUARIO, user);
                //params.put(Config.KEY_USER_CUENTA, cuenta);
                params.put(Config.KEY_USER_PRIVACIDAD, privacidad);
                params.put(Config.KEY_USER_PASSWORD, pass);
                params.put(Config.KEY_USER_EMAIL, email);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }

        }
        AddUSER au = new AddUSER();
        au.execute();
    }

    public void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle("Registro");
        setSupportActionBar(toolbar);
    }
}
