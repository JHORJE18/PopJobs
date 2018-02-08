package com.jhorje18.popjobs;

import android.app.Activity;
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

    private final int RESULT_SELECCIONAR_IMAGEN_SERVICIO = 1;

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
        sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");


        bbdd = FirebaseDatabase.getInstance().getReference("USUARIOS");

<<<<<<< HEAD
        claveUsu = fba.getCurrentUser().getUid();
        bbddS = FirebaseDatabase.getInstance().getReference(("SERVICIOS"));


=======
>>>>>>> 5538b98bbe780b2c295e4f2fa4c283136ae5d415
        guardarServicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

<<<<<<< HEAD
                crearNuevoServicio();
=======
                        bbddS.child(claveS).setValue(servicio);
                        Toast.makeText(NuevoServicioActivity.this, "Servicio añadido", Toast.LENGTH_SHORT).show();

                        //Cerramos resultado
                        finish();

                    }

                }
>>>>>>> 5538b98bbe780b2c295e4f2fa4c283136ae5d415
            }
        });


        //Relleno del spinner con las categorías seleccionadas
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

<<<<<<< HEAD
    //Método que valida los datos introducidos por el usuario y si son correctos añade un nuevo servicio.
    public void crearNuevoServicio(){

        String nombre_Servicio = nombreServicio.getText().toString();
        String descripcion_Servicio = descripcionServicio.getText().toString();
        Float precio_Servicio= Float.parseFloat(precioServicio.getText().toString());

        Validadores v = new Validadores();

        if(v.editNombreValido(nombre_Servicio,nombreServicio) && v.editDescripcionValida(descripcion_Servicio,descripcionServicio) && !v.vacioPrecio(precio_Servicio)){

            final String claveS = bbddS.push().getKey();
            String currentDateandTime = sdf.format(new Date());


            Servicio servicio = new Servicio(nombreServicio.getText().toString(), descripcionServicio.getText().toString(), categorias.getSelectedItem().toString(), precio_Servicio,claveUsu,claveS,1,1, currentDateandTime,"");

            bbddS.child(claveS).setValue(servicio);
            Toast.makeText(NuevoServicioActivity.this, "Servicio añadido", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(NuevoServicioActivity.this, "Faltan datos por rellenar", Toast.LENGTH_SHORT).show();


        }

    }
    //Selección de imagen de servicio
    public void listenerSeleccionarImagenServicio(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //Se configura que tipo de archivos queremos (MIME data types)
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), RESULT_SELECCIONAR_IMAGEN_SERVICIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_SELECCIONAR_IMAGEN_SERVICIO && resultCode == Activity.RESULT_OK) {
            Uri uriImagen = data.getData();
            imagenServicio.setImageURI(uriImagen);
        }

    }


=======
>>>>>>> 5538b98bbe780b2c295e4f2fa4c283136ae5d415
    public void onFragmentInteraction(Uri uri){}

}
