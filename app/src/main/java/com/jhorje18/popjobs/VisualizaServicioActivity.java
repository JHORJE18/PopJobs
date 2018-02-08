package com.jhorje18.popjobs;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jhorje18.popjobs.Objetos.Servicio;

import java.util.Calendar;

public class VisualizaServicioActivity extends AppCompatActivity {

    private TextView nombre, Descripcion, precio, PVP, mapa, fecha;
    private ImageView imagen;
    private Button perfil, mensaje;
    String claveServicio;
    //private FrameLayout frameMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_servicio);

        //Recibir clave
        claveServicio = getIntent().getStringExtra("claveServicio");

        //nombre del servicio
        nombre =(TextView)findViewById(R.id.TextNombreServicio);
        imagen = (ImageView) findViewById(R.id.IdImage);
        Descripcion = (TextView) findViewById(R.id.TextDescripcion);
        precio = (TextView) findViewById(R.id.txtPecio);
        PVP = (TextView) findViewById(R.id.TextPVP);
        mapa = (TextView) findViewById(R.id.textMapa);
        //frameMapa = (FrameLayout) findViewById(R.id.textMapa);
        fecha = (TextView) findViewById(R.id.TxtFecha);
        perfil = (Button) findViewById(R.id.btnPerfil);
        mensaje = (Button) findViewById(R.id.btnMensaje);

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.menuEditar)
        {
            Intent intent = new Intent(VisualizaServicioActivity.this,NuevoServicioActivity.class);
            intent.putExtra("claveServicio",claveServicio);
            startActivity(intent);

        }
        if (id==R.id.menuCompartir) {

            Intent intCom = new Intent(Intent.ACTION_SEND);
            intCom.setType("text/plain");
            intCom.putExtra(Intent.EXTRA_TEXT, "Descargate la mejor app para ofercer servicios! http://popjobs.jhorje18.com");
            startActivity(Intent.createChooser(intCom, "Compartir con..."));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getMenuInflater().inflate(R.menu.visualizar_servicio,menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void CargarDatos(Servicio obj){
        nombre.setText(obj.getNombre());
        //mostart imagen
        Descripcion.setText(obj.getDescripcion());
        //mostart ubicacion
        PVP.setText(obj.getPrecio().toString());
        fecha.setText(obj.getFecha());

    }

}
