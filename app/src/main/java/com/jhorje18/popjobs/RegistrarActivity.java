package com.jhorje18.popjobs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jhorje18.popjobs.Extra.Validadores;

import java.util.Calendar;

public class RegistrarActivity extends AppCompatActivity {

    private final int RESULT_SELECCIONAR_IMAGEN = 1;

    EditText TIEditText_Nombre_Apellidos,TIEditText_Nacimiento, TIEditText_Vivienda, TIEditText_Telefono;
    ImageView fotoPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        TIEditText_Nombre_Apellidos = (EditText) findViewById(R.id.TIEditText_Nombre_Apellidos);
        TIEditText_Vivienda = (EditText) findViewById(R.id.TIEditText_Vivienda);
        TIEditText_Nacimiento = (EditText) findViewById(R.id.TIEditText_Nacimiento);
        TIEditText_Telefono = (EditText) findViewById(R.id.TIEditText_Telefono);
        fotoPerfil = (ImageView) findViewById(R.id.imageViewFoto);

    }

    public void listenerNacimiento(View view) {
        Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrarActivity.this, DatePickerDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                TIEditText_Nacimiento.setText("" + i2 + "/" + (i1 + 1) + "/" + i);
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    public void listenerSeleccionarImagen(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //Se configura que tipo de archivos queremos (MIME data types)
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"), RESULT_SELECCIONAR_IMAGEN);
    }

    public void listenerCrearUsuario(View view) {
        String nombreApellidos = TIEditText_Nombre_Apellidos.getText().toString();
        String fechaNacimiento = TIEditText_Nacimiento.getText().toString();
        String direccion = TIEditText_Vivienda.getText().toString();
        int telefono = Integer.parseInt(TIEditText_Telefono.getText().toString());
        //String imagenKey = ;

        Validadores validadores = new Validadores();
        if(validadores.editNombreValido(nombreApellidos,TIEditText_Nombre_Apellidos) && validadores.editNacimientoValido(fechaNacimiento,this) && !validadores.vacio(direccion) && !TextUtils.isEmpty(TIEditText_Telefono.getText())) {
            //Se vuelve a la activity anterior
            Intent intent = getIntent();
            intent.putExtra("nombreApellidos",nombreApellidos);
            intent.putExtra("fechaNacimiento",fechaNacimiento);
            intent.putExtra("direccion",direccion);
            intent.putExtra("telefono",telefono);
            setResult(RESULT_OK,intent);
            finish();
        } else {
            Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        }
    }

    public void listenerCancelar(View view) {
        Intent intent=new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_SELECCIONAR_IMAGEN && resultCode == Activity.RESULT_OK) {
            Uri uriImagen = data.getData();
            fotoPerfil.setImageURI(uriImagen);
        }

    }

}
