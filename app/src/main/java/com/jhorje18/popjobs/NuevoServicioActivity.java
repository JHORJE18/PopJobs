package com.jhorje18.popjobs;

import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jhorje18.popjobs.Objetos.Servicio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NuevoServicioActivity extends AppCompatActivity implements FragmentMapaServicio.OnFragmentInteractionListener{

    private EditText nombreServicio,descripcionServicio,precioServicio;
    private Spinner categorias;
    private String categoria;
    private ImageView imagenServicio;
    private FragmentMapaServicio fragmentMapa;
    private Button guardarServicio;
    private Button cancelarServicio;
    FirebaseUser user;
    DatabaseReference bbdd;
    DatabaseReference bbddS;
    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_servicio);

        //Vista
        nombreServicio = (EditText) findViewById(R.id.txtNombreServicio);
        descripcionServicio = (EditText) findViewById(R.id.txtDescripcionServicio);
        precioServicio = (EditText) findViewById(R.id.txtPrecioServicio);
        categorias = (Spinner) findViewById(R.id.spinnerCategoriasServicio);
        imagenServicio = (ImageView) findViewById(R.id.imageViewServicio);
        //fragmentMapa = (FragmentMapaServicio) findViewById(R.id.fragmentMapa);
        guardarServicio = (Button) findViewById(R.id.btnGuardarNuevoServicio);
        cancelarServicio = (Button) findViewById(R.id.btnSalir);

        fba = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        final String currentDateandTime = sdf.format(new Date());

        bbdd = FirebaseDatabase.getInstance().getReference("USUARIOS");
        final String claveUsu = fba.getCurrentUser().getUid();
        bbddS = FirebaseDatabase.getInstance().getReference(("SERVICIOS"));

        guardarServicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (nombreServicio.getText().toString().isEmpty() || descripcionServicio.getText().toString().isEmpty() ||
                        precioServicio.getText().toString().isEmpty()|| categorias.toString().isEmpty()) {
                    Toast.makeText(NuevoServicioActivity.this, "Faltan datos por rellenar", Toast.LENGTH_SHORT).show();
                } else {
                    boolean valido = true;

                    if (valido) {

                        Float precioEnFloat= Float.parseFloat(precioServicio.getText().toString());
                        final String claveS = bbddS.push().getKey();


                        Servicio servicio = new Servicio(nombreServicio.getText().toString(), descripcionServicio.getText().toString(), categorias.getSelectedItem().toString(), precioEnFloat,claveUsu,claveS,1,1, currentDateandTime,imagenServicio);

                        bbddS.child(claveS).setValue(servicio);
                        Toast.makeText(NuevoServicioActivity.this, "Servicio añadido", Toast.LENGTH_SHORT).show();


                    }

                }
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categorias.setAdapter(adapter);


        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                 categoria = (String) adapterView.getItemAtPosition(pos);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cancelarServicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onFragmentInteraction(Uri uri){}

}
