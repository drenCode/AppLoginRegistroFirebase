package com.apps.drencode.appfirebase;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.apps.drencode.appfirebase.Modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Registro extends AppCompatActivity {
    MaterialEditText edtCodigo, edtNombre,edtPass;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtNombre = (MaterialEditText)findViewById(R.id.edtNombre);
        edtPass= (MaterialEditText)findViewById(R.id.edtPass);
        edtCodigo = (MaterialEditText)findViewById(R.id.edtCodigo);

        btnRegistro = (Button)findViewById(R.id.btnRegistro);
        //AQUI INICIAMOS FIREBASE
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_usuario = database.getReference("Usuario");

        btnRegistro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                final ProgressDialog mDialog = new ProgressDialog(Registro.this);
                mDialog.setMessage("Espera Por Favor....");
                mDialog.show();

                tabla_usuario.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtCodigo.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(Registro.this, "Codigo ya registrado", Toast.LENGTH_SHORT).show();
                        }else{
                            mDialog.dismiss();
                            Usuario usuario = new Usuario(edtNombre.getText().toString(),edtPass.getText().toString());
                            tabla_usuario.child(edtCodigo.getText().toString()).setValue(usuario);
                            Toast.makeText(Registro.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
            });

    }
}
