package com.example.odm.webservice50.Tablas.mensajes;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MensajesActivity extends AppCompatActivity {


    private String id;
    private TextView etTitulo;
    private EditText etMensaje;
    private Button enviar;


    private FloatingActionButton fab;

    private ListView listViewMSG;
    private String JSON_STRINGMSG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);

        Intent intent = getIntent();
        id = intent.getStringExtra(Config.CHPR_ID);
        //editTextUsuario = (EditText) findViewById(R.id.txtUserUpdate);

        etMensaje = (EditText) findViewById(R.id.etxMensajes);

        etTitulo = (TextView) findViewById(R.id.TituloMensajes);

        listViewMSG = (ListView) findViewById(R.id.listViewMensajes);
        listViewMSG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        enviar = (Button) findViewById(R.id.btnEnviarCHPR);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Comentario = etMensaje.getText().toString().trim();

                if(Comentario.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Se necesita informacion para realizar un mensaje";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    addMensaje();
                    getJSON();
                    getChatPrivado();
                    etMensaje.setText("");
                }

            }
        });
        /*
        fab = (FloatingActionButton) findViewById(R.id.fabMensajesNuevos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Comentario = etMensaje.getText().toString().trim();

                if(Comentario.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence text = "Se necesita informacion para realizar un mensaje";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    addBroadcast();
                    getJSON();
                    getEmployee();
                    etMensaje.setText("");
                }
            }
        });
        */

        getChatPrivado();
        getJSON();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(); return dateFormat.format(date); }

    private void addMensaje() {
        final String Fecha = getDateTime();
        final String Comentario = etMensaje.getText().toString().trim();
        final String id_CHPU = Config.ID_CHPrivado;
        final String id_USU = Config.ID_USUARIOS;

        class AddBroadCast extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(MensajesActivity.this, "Adding...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                Toast.makeText(MensajesActivity.this, s, Toast.LENGTH_LONG).show();
            }

            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_MSG_FECHA, Fecha);
                params.put(Config.KEY_MSG_CONT, Comentario);
                params.put(Config.KEY_MSG_IDCHPRF, id_CHPU);
                params.put(Config.KEY_MSG_IDUSUF, id_USU);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD_Mensaje, params);
                return res;
            }

        }
        AddBroadCast au = new AddBroadCast();
        au.execute();
    }

    private void getChatPrivado(){
        class GetChatPrivado extends AsyncTask<Void,Void,String> {
            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(MensajesActivity.this, "Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                showDatos(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_CHPrivadoOnce, id);
                return s;
            }
        }
        GetChatPrivado ge = new GetChatPrivado();
        ge.execute();
    }

    private void showDatos(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_CHPR);
            JSONObject c = result.getJSONObject(0);

            String ID = c.getString(Config.TAG_ID_CHPR);
            String Mensaje = c.getString(Config.TAG_TITULO_CHPR);

            etTitulo.setText(Mensaje);
            Config.ID_CHPrivado = ID;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showComentarios(){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRINGMSG);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_COM);

            if(result.length() > 0) {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String Fecha = jo.getString(Config.TAG_FECHA_MSG);
                    String cont_msg = jo.getString(Config.TAG_CONT_MSG);
                    String usuario = jo.getString(Config.TAG_USUARIO);


                    HashMap<String, String> mensaje = new HashMap<>();

                    mensaje.put(Config.TAG_FECHA_MSG, Fecha);
                    mensaje.put(Config.TAG_USUARIO, usuario);
                    mensaje.put(Config.TAG_CONT_MSG, cont_msg);

                    list.add(mensaje);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(MensajesActivity.this,
                list,
                R.layout.list_item_mensajes,
                new String[] {Config.TAG_FECHA_MSG, Config.TAG_USUARIO, Config.TAG_CONT_MSG},
                new int[] {R.id.tvFechaMSG, R.id.tvUsuario, R.id.tvComentario});

        listViewMSG.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void,Void,String> {

            //ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(MensajesActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                JSON_STRINGMSG = s;
                showComentarios();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_Mensaje, id);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
