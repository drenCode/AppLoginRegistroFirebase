package com.apps.drencode.appfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apps.drencode.appfirebase.Common.Common;
import com.apps.drencode.appfirebase.Modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Login extends AppCompatActivity {

    EditText edtCodigo,edtPass;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPass = (MaterialEditText)findViewById(R.id.edtPass);
        edtCodigo = (MaterialEditText)findViewById(R.id.edtCodigo);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        //AQUI INICIAMOS FIREBASE
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_usuario = database.getReference("Usuario");

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                final ProgressDialog mDialog = new ProgressDialog(Login.this);
                mDialog.setMessage("Espera Por Favor....");
                mDialog.show();

                tabla_usuario.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){

                        //COMPROBAMOS QUE EL USUARIO NO EXISTE EN LA BASE DE DATOS
                        if (dataSnapshot.child(edtCodigo.getText().toString()).exists()){



                             //COGEMOS LA INFORMACION DEL USUARIO
                            mDialog.dismiss();
                             Usuario usuario = dataSnapshot.child(edtCodigo.getText().toString()).getValue(Usuario.class);
                             if (usuario.getContraseña().equals(edtPass.getText().toString())){
                                 Intent homeIntent = new Intent(Login.this,Home.class);
                                 Common.currentUser = usuario;
                                 startActivity(homeIntent);
                                 finish();
                        }else{
                            Toast.makeText(Login.this, "Contraseña incorrecta!!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Login.this, "No existe el usuario", Toast.LENGTH_SHORT).show();
                    }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError){


                    }
                });
            }

        });
    }
}
