package com.jhorje18.popjobs;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Obtener detalles del Auth
                FirebaseUser userAuth = FirebaseAuth.getInstance().getCurrentUser();

                //Si la Auth ya se Inicio va al Activity Principal
                if (userAuth != null){
                    startActivity(new Intent(SplashActivity.this, Principal.class));
                    finish();
                } else {
                    Toast.makeText(SplashActivity.this, "No has Iniciado Sesión", Toast.LENGTH_SHORT).show();
                    //TODO Redirigir a la pantalla Inicio de Sesión
                    startActivity(new Intent(SplashActivity.this, Login.class));
                    finish();
                }

            }
        }, DURACION_SPLASH);
    }
}
