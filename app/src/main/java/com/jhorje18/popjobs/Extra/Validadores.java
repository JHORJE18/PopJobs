package com.jhorje18.popjobs.Extra;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

/**
 * Created by JHORJE on 7/2/18.
 */

public class Validadores {

    //Llamar al metodo del dato que se quiere validar con su valor

    //Validar Nombre
    public boolean editNombreValido(String txt, EditText editTXT){
        //Comprueba longitud
        if (txt.length() < 3){
            editTXT.setError("Este campo requiere una longitud mínima de 3 caracteres");
            return false;
        }
        if (txt.length() > 20){
            editTXT.setError("Este campo no puede contener más de 20 caracteres");
            return false;
        }

        //Comprueba que solo es texto
        if (!txt.matches("([a-z]|[A-Z]|\\s)+")){
            editTXT.setError("Este campo solo puede contener texto");
            return false;
        }

        //Validado correctamente
        return true;
    }

    //Validar Apedillos
    public boolean editApedilloValido(String txt, EditText editTXT){
        //Comprueba longitud
        if (txt.length() < 3){
            editTXT.setError("Este campo requiere una longitud mínima de 3 caracteres");
            return false;
        }
        if (txt.length() > 50){
            editTXT.setError("Este campo no puede contener más de 50 caracteres");
            return false;
        }

        //Comprueba que solo es texto
        if (!txt.matches("([a-z]|[A-Z]|\\s)+")){
            editTXT.setError("Este campo solo puede contener texto");
            return false;
        }

        //Validado correctamente
        return true;
    }

    //Validar Fecha nacimiento
    public boolean editNacimientoValido(String txt, Context cntx){
        //Preparamos Dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(cntx);
        builder.setMessage("Selecciona una fecha válida")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });

        if (vacio(txt)){
            builder.show();
            return false;
        }

        //Validado corectamente
        return true;
    }

    //Validar Descripcion Servicio
    public boolean editDescripciónValida(String txt, EditText editTXT){
        //Comprueba longitud
        if (txt.length() < 3){
            editTXT.setError("Este campo requiere una longitud mínima de 3 caracteres");
            return false;
        }
        if (txt.length() > 20){
            editTXT.setError("Este campo no puede contener más de 100 caracteres");
            return false;
        }

        //Comprueba que solo es texto
        if (!txt.matches("([a-z]|[A-Z]|\\s)+")){
            editTXT.setError("Este campo solo puede contener texto");
            return false;
        }

        //Validado correctamente
        return true;
    }

    public boolean vacio(String txt){
        if (txt.isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    public boolean vacioPrecio(Float precio){
        if (precio.toString().isEmpty()){
            return true;
        } else {
            return false;
        }
    }
}
