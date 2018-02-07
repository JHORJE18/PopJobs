package com.jhorje18.popjobs;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class  SplashActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    //Finals
    private final int DURACION_SPLASH = 1000;

    //Google APi
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    //Firebase
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*
        //Iniciaciones Firebase
        firebaseAuth = FirebaseAuth.getInstance();

        //Iniciaciones Google Api
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firebaseAuth.getCurrentUser() != null){
                    Abrir_Activity_Principal();
                }else{
                    Abrir_Acitivty_Login();
                }
            }
        }, DURACION_SPLASH);
        */
        startActivity(new Intent(this,Principal.class));
    }

    private void Abrir_Acitivty_Login() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }


    private void Abrir_Activity_Principal(){
        Intent i = new Intent(this, Principal.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(),"Error Realizando la conexion de GoogleApiClient",Toast.LENGTH_LONG).show();
    }
}
