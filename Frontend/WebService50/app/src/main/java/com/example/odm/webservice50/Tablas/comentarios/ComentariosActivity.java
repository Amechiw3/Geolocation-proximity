package com.example.odm.webservice50.Tablas.comentarios;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.odm.webservice50.R;
import com.example.odm.webservice50.Tablas.Usuarios.Config;
import com.example.odm.webservice50.Tablas.Usuarios.RequestHandler;
import com.example.odm.webservice50.Tablas.chpublico.Nuevo_chat_publico_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ComentariosActivity extends AppCompatActivity {

    private String id;
    TextView etMensaje;
    TextView etUsuario;
    EditText etComentario;
    FloatingActionButton fab;
    private Button Enviar;

    private ListView listViewCOM;
    private String JSON_STRINGCOM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        Intent intent = getIntent();
        id = intent.getStringExtra(Config.CHPU_ID);
        //editTextUsuario = (EditText) findViewById(R.id.txtUserUpdate);

        etMensaje = (TextView) findViewById(R.id.mensajeComentarios);
        etUsuario = (TextView) findViewById(R.id.usuarioComentarios);
        etComentario = (EditText) findViewById(R.id.etxComentario);
        Enviar = (Button) findViewById(R.id.btnEnviarCHPU);

        listViewCOM = (ListView) findViewById(R.id.listViewComentarios);
        listViewCOM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Comentario = etComentario.getText().toString().trim();

                if(Comentario.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Se necesita informacion para realizar un comentario";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    addComentario();
                    etComentario.setText("");
                    getJSON();
                    getCHPublico();
                }
            }
        });

        /*
        fab = (FloatingActionButton) findViewById(R.id.fabComentariosNuevos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Comentario = etComentario.getText().toString().trim();

                if(Comentario.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Se necesita informacion para realizar un comentario";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    addBroadcast();
                    getJSON();
                    getEmployee();
                }
            }
        });
        */
        getCHPublico();
        getJSON();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(); return dateFormat.format(date); }
    private void addComentario() {
        final String Fecha = getDateTime();
        final String Comentario = etComentario.getText().toString().trim();
        final String id_CHPU = Config.ID_CHPublico;
        final String id_USU = Config.ID_USUARIOS;

        class AddComentario extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(ComentariosActivity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                Toast.makeText(ComentariosActivity.this, s, Toast.LENGTH_LONG).show();
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_COM_FECHA, Fecha);
                params.put(Config.KEY_COM_CONT, Comentario);
                params.put(Config.KEY_COM_IDCHPU, id_CHPU);
                params.put(Config.KEY_COM_IDUSUF, id_USU);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_Comentario, params);
                return res;
            }

        }
        AddComentario au = new AddComentario();
        au.execute();
    }

    private void getCHPublico(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(ComentariosActivity.this, "Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                showCHPublico(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_CHPublico, id);
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }
    private void showCHPublico(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_CHPU);
            JSONObject c = result.getJSONObject(0);

            String ID = c.getString(Config.TAG_ID_CHPU);
            String Mensaje = c.getString(Config.TAG_USUARIO);
            String Usuario = c.getString(Config.TAG_CONT_CHPU);

            etMensaje.setText(Mensaje);
            etUsuario.setText(Usuario);
            Config.ID_CHPublico = ID;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showComentarios(){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRINGCOM);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_COM);

            if(result.length() > 0) {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String ID = jo.getString(Config.TAG_ID_COM);
                    String Fecha = jo.getString(Config.TAG_FECHA_COM);
                    String cont_com = jo.getString(Config.TAG_CONT_COM);
                    String usuario = jo.getString(Config.TAG_USUARIO);


                    HashMap<String, String> employees = new HashMap<>();

                    employees.put(Config.TAG_ID_COM, ID);
                    employees.put(Config.TAG_FECHA_COM, Fecha);
                    employees.put(Config.TAG_USUARIO, usuario);
                    employees.put(Config.TAG_CONT_COM, cont_com);

                    list.add(employees);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(ComentariosActivity.this,
                list,
                R.layout.list_item_comentarios,
                new String[] {Config.TAG_ID_COM, Config.TAG_FECHA_COM, Config.TAG_USUARIO, Config.TAG_CONT_COM},
                new int[] {R.id.tvIDCOM, R.id.tvFechaCOM, R.id.tvUsuario, R.id.tvComentario});

        listViewCOM.setAdapter(adapter);
    }
    private void getJSON() {
        class GetJSON extends AsyncTask<Void,Void,String> {

            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(ComentariosActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                JSON_STRINGCOM = s;
                showComentarios();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_Comentario, id);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
