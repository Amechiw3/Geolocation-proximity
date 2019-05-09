package com.example.odm.webservice50.Tablas.Usuarios;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.odm.webservice50.Login.LoginActivity;
import com.example.odm.webservice50.Main.MainMenuActivity;
import com.example.odm.webservice50.R;
import com.example.odm.webservice50.Tablas.Broadcast.Nuevo_Broadcast_Activity;
import com.example.odm.webservice50.Tablas.chpublico.ChatPublicoFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class PerfilActivity extends AppCompatActivity {

    private TextView Usuario, Cuenta, Privacidad, Contraseña, Email;
    private String email;
    public String id_usu = "";
    //private FloatingActionButton fab;
    private com.melnykov.fab.FloatingActionButton fab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPerfil);
        setSupportActionBar(toolbar);

        // Initializing textview
        Usuario = (TextView)findViewById(R.id.Usuario);
        //Cuenta = (TextView)findViewById(R.id.Cuenta);
        Privacidad = (TextView)findViewById(R.id.Privacidad);
        Contraseña = (TextView)findViewById(R.id.Contraseña);
        Email = (TextView)findViewById(R.id.Email);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"Not Available");

        //Showing the current logged in email to textview
        Email.setText(email);

        Intent intent = getIntent();
        //id = intent.getStringExtra(Config.IDUSER_SHARED_PREF);

        //id = Email.getText().toString().trim();

        getUser();
        if(email == "")
        {
            CharSequence text = "Necesitas logearte";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent2 = new Intent(PerfilActivity.this, LoginActivity.class);
            startActivity(intent2);
        }

        fab = (com.melnykov.fab.FloatingActionButton) findViewById(R.id.fabPerfil);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent CHpublico = new Intent(PerfilActivity.this, ChatPublicoFragment.class);
                CHpublico.putExtra(Config.USER_ID, id);*/

                Intent intents = new Intent(PerfilActivity.this, UpdateUserActivity.class);
                intents.putExtra(Config.USER_ID, id_usu);
                startActivity(intents);

                /*Intent intent = new Intent(PerfilActivity.this, ChatPublicoFragment.class);
                intent.putExtra(Config.USER_ID, id);*/


            }
        });
    }

    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
        if(email == "")
        {
            CharSequence text = "Necesitas logearte";
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent2 = new Intent(PerfilActivity.this, LoginActivity.class);
            startActivity(intent2);
        }
        else
        {
            getUser();
        }
    }

    private void getUser() {
        class GetUser extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(PerfilActivity.this,"Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                showUser(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_USER, email);
                return s;
            }
        }
        GetUser gu = new GetUser();
        gu.execute();
    }

    private void showUser(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String ID = c.getString(Config.TAG_ID);
            String usuario = c.getString(Config.TAG_USUARIO);
            //String cuenta = c.getString(Config.TAG_CUENTA);
            String privacidad = c.getString(Config.TAG_PRIVACIDAD);
            String contraseña = c.getString(Config.TAG_PASSWORD);
            String email = c.getString(Config.TAG_EMAIL);

            id_usu = ID;
            Config.ID_USUARIOS = ID;
            Usuario.setText(usuario);
            //Cuenta.setText(cuenta);
            Privacidad.setText(privacidad);
            Contraseña.setText(contraseña);
            Email.setText(email);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //Logout function
    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");

                        email = "";

                        //Saving the sharedpreferences
                        editor.clear();
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.manu_perfil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        if (id == R.id.menuPrincipalPerfil)
        {
           /* Bundle bundle = new Bundle();
            bundle.putString(Config.USER_ID, id_usu);
            ChatPublicoFragment fragment = new ChatPublicoFragment();
            fragment.setArguments(bundle);*/

            Intent principal = new Intent(this, MainMenuActivity.class);
            principal.putExtra(Config.USER_ID, id_usu);
            startActivity(principal);
        }

        if(id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
