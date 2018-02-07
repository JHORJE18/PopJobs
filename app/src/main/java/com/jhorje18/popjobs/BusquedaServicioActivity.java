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
import android.widget.Spinner;

public class BusquedaServicioActivity extends AppCompatActivity implements  FragmentMapaServicio.OnFragmentInteractionListener{

    private EditText nombreServicioBusqueda;
    private String categoriaBusqueda;
    private Spinner spinnerBusqueda;
    private Fragment fm;
    private Button enviarBusqueda;
    private Button cancelarBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_servicio);

        nombreServicioBusqueda = (EditText) findViewById(R.id.nombreServicioBusqueda);
        spinnerBusqueda = (Spinner) findViewById(R.id.spinnerBusqueda);
        //fm = (Fragment) findViewById(R.id.fragmentMapaBusqueda);
        enviarBusqueda = (Button) findViewById(R.id.btnBuscar);
        cancelarBusqueda = (Button) findViewById(R.id.btnCancelarBusqueda);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBusqueda.setAdapter(adapter);


        spinnerBusqueda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                categoriaBusqueda = (String) adapterView.getItemAtPosition(pos);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        enviarBusqueda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(nombreServicioBusqueda.getText().toString().isEmpty() || categoriaBusqueda.toString().isEmpty()){

                }
            }



        });

        enviarBusqueda.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void onFragmentInteraction(Uri uri){}

}
