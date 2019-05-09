package com.example.odm.webservice50.Tablas.chprivado;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.odm.webservice50.Main.MainMenuActivity;
import com.example.odm.webservice50.R;
import com.example.odm.webservice50.Tablas.Usuarios.Config;
import com.example.odm.webservice50.Tablas.Usuarios.RequestHandler;
import com.example.odm.webservice50.Tablas.chpublico.ChatPublicoFragment;

import java.util.HashMap;

public class Nuevo_chat_privado_Activity extends AppCompatActivity {

    EditText IDUsuario, Titulo;
    private Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_chat_privado);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarChatPrivado);
        setSupportActionBar(toolbar);

        IDUsuario = (EditText) findViewById(R.id.etxIDUsuario);
        Titulo = (EditText) findViewById(R.id.etxTituloCHPR);
        crear = (Button) findViewById(R.id.btnCrearCHPR);
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IDUSUARIO = IDUsuario.getText().toString().trim();
                String titulo = Titulo.getText().toString().trim();

                if(titulo.isEmpty() || IDUSUARIO.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Se necesita informacion para continuar";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    addCHPrivado();
                    //addCHPrivadoRE();

                    Intent principal = new Intent(Nuevo_chat_privado_Activity.this, MainMenuActivity.class);
                    startActivity(principal);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu_chat_privado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAcceptarChPri) {
            //calling logout method when the logout button is clicked

            String IDUSUARIO = IDUsuario.getText().toString().trim();
            String titulo = Titulo.getText().toString().trim();

            if(titulo.isEmpty() || IDUSUARIO.isEmpty()) {
                Context context = getApplicationContext();
                CharSequence text = "Se necesita informacion para continuar";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else {
                addCHPrivado();

                Intent principal = new Intent(this, MainMenuActivity.class);
                startActivity(principal);
            }

        }
        if (id == R.id.menuPrincipalChPri)
        {
            Intent principal = new Intent(this, MainMenuActivity.class);
            startActivity(principal);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addCHPrivado() {
        final String form_id_usuf = Config.ID_USUARIOS;
        final String to_id_usuf = IDUsuario.getText().toString().trim();
        final String titulo_chpr = Titulo.getText().toString().trim();


        class AddCHPrivado extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(Nuevo_chat_privado_Activity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                Toast.makeText(Nuevo_chat_privado_Activity.this, s, Toast.LENGTH_LONG).show();
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_CHPR_FORM_ID_USUF, form_id_usuf);
                params.put(Config.KEY_CHPR_TO_ID_USUF, to_id_usuf);
                params.put(Config.KEY_CHPR_TITULO_CHPR, titulo_chpr);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_CHPrivadoPr, params);
                return res;
            }

        }
        AddCHPrivado au = new AddCHPrivado();
        au.execute();
    }

}
