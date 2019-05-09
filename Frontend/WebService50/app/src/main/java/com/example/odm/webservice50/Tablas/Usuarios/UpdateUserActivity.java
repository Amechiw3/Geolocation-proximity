package com.example.odm.webservice50.Tablas.Usuarios;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.odm.webservice50.Login.LoginActivity;
import com.example.odm.webservice50.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UpdateUserActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextUsuario;
    //private EditText editTextCuenta;
    private Spinner Privacidad;
    private EditText editTextContraseña;
    private EditText editTextReContraseña;
    private EditText editTextEmail;

    private Button buttonUpdate;
    private Button buttonDelete;
    protected ArrayAdapter<CharSequence> adapter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        setToolBar();

        Intent intent = getIntent();
        id = intent.getStringExtra(Config.USER_ID);

        editTextUsuario = (EditText) findViewById(R.id.txtUserUpdate);
        //editTextCuenta = (EditText) findViewById(R.id.txtCuentaUpdate);
        Privacidad = (Spinner) findViewById(R.id.PrivacidadspinnerUpdate);
        editTextContraseña = (EditText) findViewById(R.id.txtPassUpdate);
        editTextReContraseña = (EditText) findViewById(R.id.txtRePassUpdate);
        editTextEmail = (EditText) findViewById(R.id.txtCorreoUpdate);

        adapter = ArrayAdapter.createFromResource(this, R.array.Privacidad,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Privacidad.setAdapter(adapter);

        buttonUpdate = (Button) findViewById(R.id.btnAceptarUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Usuario = editTextUsuario.getText().toString().trim();
                //final String Cuenta = editTextCuenta.getText().toString().trim();
                final String privacidad = Privacidad.getSelectedItem().toString().trim();
                final String Password = editTextContraseña.getText().toString().trim();
                final String repass = editTextReContraseña.getText().toString().trim();
                final String Email = editTextEmail.getText().toString().trim();

                class UpdateEmployee extends AsyncTask<Void,Void,String> {
                    //ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        //loading = ProgressDialog.show(UpdateUserActivity.this, "Updating...", "Wait...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        //loading.dismiss();
                        Toast.makeText(UpdateUserActivity.this, s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Void... params) {
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put(Config.KEY_USER_ID, id);
                        hashMap.put(Config.KEY_USER_USUARIO, Usuario);
                        //hashMap.put(Config.KEY_USER_CUENTA, Cuenta);
                        hashMap.put(Config.KEY_USER_PRIVACIDAD, privacidad);
                        hashMap.put(Config.KEY_USER_PASSWORD, Password);
                        hashMap.put(Config.KEY_USER_EMAIL, Email);

                        RequestHandler rh = new RequestHandler();

                        String s = rh.sendPostRequest(Config.URL_UPDATE_USER, hashMap);

                        return s;
                    }
                }

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                if(Usuario.isEmpty()  || Password.isEmpty() || repass.isEmpty() || Email.isEmpty()) {
                    CharSequence text = "Se necesita informacion para continuar con el registro";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    if(Password.length() < 8) {
                        CharSequence text = "La contraseña es demaciado corta";
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        if(Password.equals(repass)) {

                            UpdateEmployee ue = new UpdateEmployee();
                            ue.execute();
                            Intent intent2 = new Intent(UpdateUserActivity.this, PerfilActivity.class);
                            startActivity(intent2);
                        } else {
                            CharSequence text = "La contraseña no coinciden";
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }
                }

            }
        });


        buttonDelete = (Button) findViewById(R.id.btnCancelarUpdate);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(UpdateUserActivity.this, PerfilActivity.class);
                startActivity(intent2);
            }
        });
        //editTextId.setText(id);
        getEmployee();

    }

    public void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_my_toolbar);
        toolbar.setTitle("Update");
        setSupportActionBar(toolbar);
    }

    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(UpdateUserActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_USER_ID, id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);

            String Usuario = c.getString(Config.TAG_USUARIO);
            //String Cuenta = c.getString(Config.TAG_CUENTA);
            String Email = c.getString(Config.TAG_EMAIL);

            editTextUsuario.setText(Usuario);
            //editTextCuenta.setText(Cuenta);
            editTextEmail.setText(Email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
