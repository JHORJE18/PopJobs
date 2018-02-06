package com.jhorje18.popjobs;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class VisualizaServicioActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_servicio);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.menuEditar)
        {
            Intent intent = new Intent(VisualizaServicioActivity.this,NuevoServicioActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getMenuInflater().inflate(R.menu.visualizar_servicio,menu);
        return super.onCreateOptionsMenu(menu);
    }




}
