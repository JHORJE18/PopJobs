package com.jhorje18.popjobs;

import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class NuevoServicioActivity extends AppCompatActivity implements FragmentMapaServicio.OnFragmentInteractionListener{

    private EditText nombreServicio,descripcionServicio,precioServicio;
    private Spinner categorias;
    private ImageView imagenServicio;
    private Fragment fragmentMapa;
    private Button guardarServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_servicio);

        nombreServicio = (EditText) findViewById(R.id.txtNombreServicio);
        descripcionServicio = (EditText) findViewById(R.id.txtDescripcionServicio);
        precioServicio = (EditText) findViewById(R.id.txtPrecioServicio);
        categorias = (Spinner) findViewById(R.id.spinnerCategoriasServicio);
        //fragmentMapa = (Fragment) findViewById(R.id.fragmentMapa);
        guardarServicio = (Button) findViewById(R.id.btnGuardarNuevoServicio);
    }

    public void onFragmentInteraction(Uri uri){}

}
