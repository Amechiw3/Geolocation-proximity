package com.example.odm.webservice50.Tablas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.odm.webservice50.Main.MainMenuActivity;
import com.example.odm.webservice50.R;
import com.example.odm.webservice50.Tablas.Usuarios.Config;

public class Acerca_de_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAcercade);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.manu_acercade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuPrincipalAcercade)
        {
            Intent principal = new Intent(this, MainMenuActivity.class);
            startActivity(principal);
        }
        return super.onOptionsItemSelected(item);
    }

}
