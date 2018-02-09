package com.jhorje18.popjobs;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jhorje18.popjobs.Extra.Validadores;
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
    SimpleDateFormat sdf;
    String claveUsu;
    private final int RESULT_SELECCIONAR_IMAGEN_SERVICIO=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_servicio);

        //Recibe clave servicio
        String claveServicio = getIntent().getStringExtra("claveServicio");
        if (claveServicio != null){
            cargar(claveServicio);
        }

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


        bbdd = FirebaseDatabase.getInstance().getReference("USUARIOS");
        claveUsu = fba.getCurrentUser().getUid();
        bbddS = FirebaseDatabase.getInstance().getReference(("SERVICIOS"));

        guardarServicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                insertaNuevoServicio();
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

    private void cargar(String claveServicio) {

        //TODO Cargar información de la clave recibida
        DatabaseReference refservicio = FirebaseDatabase.getInstance().getReference("SERVICIOS").child(claveServicio);
        refservicio.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Servicio serv = dataSnapshot.getValue(Servicio.class);
                CargarDatos(serv);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void CargarDatos(Servicio serv) {

        nombreServicio.setText(serv.getNombre());
        //mostart imagen
        descripcionServicio.setText(serv.getDescripcion());
        //mostart ubicacion
        precioServicio.setText(serv.getPrecio().toString());
       // currentDateandTime.setText(obj.getFecha());
    }

    //Metodo que inserta nuevo servicio comprobando los datos introducidos por el usuario
    public void insertaNuevoServicio(){

        String nombre_servicio = nombreServicio.getText().toString();
        String descripcion_servicio = descripcionServicio.getText().toString();
        Float precio_servicio = Float.parseFloat(precioServicio.getText().toString());

        Validadores  v = new Validadores();
        if(v.editNombreValido(nombre_servicio,nombreServicio) && v.editDescripciónValida(descripcion_servicio,descripcionServicio) && !v.vacioPrecio(precio_servicio)){

            final String claveS = bbddS.push().getKey();
            sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            final String currentDateandTime = sdf.format(new Date());


            Servicio servicio = new Servicio(nombreServicio.getText().toString(), descripcionServicio.getText().toString(), categorias.getSelectedItem().toString(), precio_servicio,claveUsu,claveS,1,1, currentDateandTime,"");

            bbddS.child(claveS).setValue(servicio);
            Toast.makeText(NuevoServicioActivity.this, "Servicio añadido", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(NuevoServicioActivity.this, "Faltan datos por rellenar", Toast.LENGTH_SHORT).show();
        }
    }

    public void listenerSeleccionarImagenServicio(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //Se configura que tipo de archivos queremos (MIME data types)
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), RESULT_SELECCIONAR_IMAGEN_SERVICIO);
    }

    //Botones Menu Superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getMenuInflater().inflate(R.menu.servicio_edicion, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //Eventos Botones Menu Superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                finish();
                break;
            case R.id.mnServGuardar:
                insertaNuevoServicio();
                break;
            case R.id.mnServCancelar:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_SELECCIONAR_IMAGEN_SERVICIO && resultCode == Activity.RESULT_OK) {
            Uri uriImagen = data.getData();
            imagenServicio.setImageURI(uriImagen);
        }

    }


    public void onFragmentInteraction(Uri uri){}

}
