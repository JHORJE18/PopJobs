package com.jhorje18.popjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jhorje18.popjobs.Objetos.Servicio;
import com.jhorje18.popjobs.Objetos.Usuario;

import java.util.ArrayList;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    //Vista elements
    ListView listaPrincipalView;

    //Variables
    private Button b;
    DatabaseReference refServicios;
    GoogleApiClient googleApiClient;
    ArrayList<String> listaServicios;
    ArrayList<String> claveServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Vista
        b=(Button)findViewById(R.id.button2) ;
        listaPrincipalView = (ListView) findViewById(R.id.listPrincipal);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this,SegundaActivity.class);
                //startActivity(intent);
                Intent intent = new Intent(Principal.this,VisualizaServicioActivity.class);
                startActivity(intent);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Principal.this,NuevoServicioActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Iniciaciones Google Api
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //Iniciamos Referencias y ArrayLists
        refServicios = FirebaseDatabase.getInstance().getReference("SERVICIOS");
        listaServicios = new ArrayList<String>();
        claveServicios = new ArrayList<String>();

        //TODO Carga servicios almacenados en FireBase
        cargarServicios();

        //Añadimos evento al entrar a un servicio
        listaPrincipalView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Obtenemos el número del elemento que va a mostrar
                Intent mostrar = new Intent(Principal.this, VisualizaServicioActivity.class);
                mostrar.putExtra("claveServicio", claveServicios.get(position));
                startActivity(mostrar);
            }
        });
    }

    //Servicios que mostrara
    private void cargarServicios() {
        refServicios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Creamos adaptados y limpiamos listas
                ArrayAdapter<String> adaptador;
                listaServicios.clear();
                claveServicios.clear();

                //Obtenemos Servicios
                for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                    Servicio servicioTEMP = datasnapshot.getValue(Servicio.class);

                    listaServicios.add(servicioTEMP.getNombre());
                    claveServicios.add(datasnapshot.getKey());
                }

                adaptador = new ArrayAdapter<String>(Principal.this, android.R.layout.simple_list_item_1, listaServicios);
                listaPrincipalView.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Cerrar Sesión
        if (id == R.id.barr_logout){
            FirebaseAuth.getInstance().signOut();

            googleApiClient.connect();
            googleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                @Override
                public void onConnected(@Nullable Bundle bundle) {
                    if(googleApiClient.isConnected()){
                        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                if(status.isSuccess()){
                                    Toast.makeText(getApplicationContext(), "Deslogueado correctamente de google.", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Problemas deslogeando de google.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onConnectionSuspended(int i) {
                    Log.d("#TEMP", "Google API Client Connection Suspended");
                }
            });
            startActivity(new Intent(Principal.this, SplashActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //TODO Establecer acciones botones

        //Boton Mis servicios
        if (id ==R.id.nav_misServicios)
            startActivity(new Intent(this,VisualizaServicioActivity.class));
        //Boton Nuevo Servicio
        if (id == R.id.nav_nuevoServicio){
            startActivity(new Intent(this, NuevoServicioActivity.class));
        }

        //Boton compartir
        if (id == R.id.nav_compartir){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Descargate la mejor app para ofercer servicios! http://popjobs.jhorje18.com");
            startActivity(Intent.createChooser(intent, "Compartir con..."));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
