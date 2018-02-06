package com.jhorje18.popjobs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.*;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //Finals
    private final static int GOOGLE_SINGIN_INTENT = 4;

    //GoogleApi
    GoogleApiClient googleApiClient;
    SignInButton signInButtonGoogle;

    //Firebase
    DatabaseReference referenciaUsuarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Iniciaciones Firebase
        referenciaUsuarios = FirebaseDatabase.getInstance().getReference("usuarios");

        //Iniciaciones GoogleApi
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        signInButtonGoogle = (SignInButton) findViewById(R.id.btn_SingIn_Google) ;
        signInButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(i, GOOGLE_SINGIN_INTENT);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case GOOGLE_SINGIN_INTENT:
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                Test_Resultado_SingInGoogle(result);
             break;
        }
    }

    private void Test_Resultado_SingInGoogle(GoogleSignInResult result) {
        if(result.isSuccess()){
            Test_New_User(result.getSignInAccount().getId());
        }else{
            Toast.makeText(getApplicationContext(), String.valueOf(result.getStatus().getStatusCode()), Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Login con Google Fallido", Toast.LENGTH_LONG).show();
        }
    }

    private void Abrir_Activity_Principal() {
        Intent i = new Intent(this, Principal.class);
        startActivity(i);
        finish();
    }

    private void Test_New_User(String idUser) {
        Query q = referenciaUsuarios.child(idUser);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Abrir_Activity_Principal();
                }else{
                    //Abrir_Activity_Registrarse
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getApplicationContext(), connectionResult.getErrorMessage(), Toast.LENGTH_LONG).show();
    }
}
