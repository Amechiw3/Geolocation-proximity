package com.example.odm.webservice50.Tablas.chprivado;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.odm.webservice50.R;
import com.example.odm.webservice50.Tablas.Usuarios.Config;
import com.example.odm.webservice50.Tablas.Usuarios.RequestHandler;
import com.example.odm.webservice50.Tablas.chpublico.Nuevo_chat_publico_Activity;
import com.example.odm.webservice50.Tablas.comentarios.ComentariosActivity;
import com.example.odm.webservice50.Tablas.mensajes.MensajesActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatPrivadoFragment extends Fragment{

    public static ChatPrivadoFragment newInstance() {
        ChatPrivadoFragment fragment = new ChatPrivadoFragment();
        return fragment;
    }

    public ChatPrivadoFragment() {
    }

    private ListView listViewCHPR;
    private String JSON_STRINGCHPR;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chatprivado, container, false);

        listViewCHPR = (ListView) rootView.findViewById(R.id.listViewCHPrivado);
        listViewCHPR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent principal;
                principal = new Intent(getContext().getApplicationContext(), MensajesActivity.class);
                HashMap<String, String> map = (HashMap)adapterView.getItemAtPosition(i);
                String id_chpr = map.get(Config.TAG_ID_CHPR).toString();
                principal.putExtra(Config.CHPR_ID, id_chpr);
                startActivity(principal);

            }
        });

        fab = (FloatingActionButton) rootView.findViewById(R.id.fabChatPrivado);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Chat Publico", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent principal;
                principal = new Intent(getContext().getApplicationContext(), Nuevo_chat_privado_Activity.class);
                startActivity(principal);
            }
        });

        getJSON();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getJSON();
    }

    private void showCHPrivado(){
        JSONObject jsonObject;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRINGCHPR);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_CHPR);

            for(int i = 0; i < result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String ID = jo.getString(Config.TAG_ID_CHPR);
                String titulo_chpr = jo.getString(Config.TAG_TITULO_CHPR);
                String DE = jo.getString(Config.TAG_DE);
                String PARA = jo.getString(Config.TAG_PARA);

                HashMap<String,String> employees = new HashMap<>();

                employees.put(Config.TAG_ID_CHPR, ID);
                employees.put(Config.TAG_TITULO_CHPR, titulo_chpr);
                employees.put(Config.TAG_DE, DE);
                employees.put(Config.TAG_PARA, PARA);


                list.add(employees);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(getActivity(),
                list,
                R.layout.list_item_chpr,
                new String[] {Config.TAG_ID_CHPR, Config.TAG_TITULO_CHPR, Config.TAG_DE, Config.TAG_PARA},
                new int[] {R.id.tvIDCHPRLabel, R.id.tvTituloCHPR, R.id.tvUsuarioCHPR, R.id.tvUsuario2CHPR });

        listViewCHPR.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(getActivity(),"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //loading.dismiss();
                JSON_STRINGCHPR = s;
                showCHPrivado();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_CHPrivado, Config.ID_USUARIOS);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
}
