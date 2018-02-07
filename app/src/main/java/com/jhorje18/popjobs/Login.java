package com.jhorje18.popjobs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
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
    private final static int ACTIVITY_REGISTRO = 3;

    //GoogleApi
    GoogleApiClient googleApiClient;
    SignInButton signInButtonGoogle;

    //Firebase
    DatabaseReference referenciaUsuarios;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Iniciaciones Firebase
        referenciaUsuarios = FirebaseDatabase.getInstance().getReference("usuarios");
        firebaseAuth = FirebaseAuth.getInstance();


        //Iniciaciones GoogleApi
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        signInButtonGoogle = findViewById(R.id.btn_SingIn_Google) ;
        signInButtonGoogle.setColorScheme(SignInButton.COLOR_DARK);
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
            case ACTIVITY_REGISTRO:
                switch (resultCode){
                    case RESULT_OK:
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;

        }
    }

    private void Test_Resultado_SingInGoogle(GoogleSignInResult result) {
        if(result.isSuccess()){
            Test_New_User(result);
        }else{
            Toast.makeText(getApplicationContext(), String.valueOf(result.getStatus().getStatusCode()), Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Login con Google Fallido", Toast.LENGTH_LONG).show();
        }
    }

    private void Abrir_Activity_Principal(final GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(getApplicationContext(), Principal.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Error Logeando con las credenciales de google en firebase", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void Abrir_Activity_Registrarse() {
      //  Intent i = new Intent(this, )
    }

    private void Test_New_User(final GoogleSignInResult result) {
        String idUser = result.getSignInAccount().getId();
        Query q = referenciaUsuarios.child(idUser);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Abrir_Activity_Principal(result.getSignInAccount());
                }else{
                    Abrir_Activity_Registrarse(); //En el activity recoger el la cuenta y generar credencial.
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
