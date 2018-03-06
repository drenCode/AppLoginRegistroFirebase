package com.apps.drencode.appfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.apps.drencode.appfirebase.Interfaz.ItemClickListener;
import com.apps.drencode.appfirebase.Modelo.Elementos;
import com.apps.drencode.appfirebase.ViewHolder.ElementosViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ListaElementos extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference ListaElementos;

    String categoryId = "";

    FirebaseRecyclerAdapter<Elementos, ElementosViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_elementos);

        //FIREBASE
        database = FirebaseDatabase.getInstance();
        ListaElementos = database.getReference("Elementos");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_elemento);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //COGEMOS INTENT DESDE AQUI
        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId != null) {
            cargarListaElementos(categoryId);
        }
    }

    private void cargarListaElementos(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Elementos, ElementosViewHolder>(Elementos.class,
                R.layout.elemento_item,
                ElementosViewHolder.class,
                ListaElementos.orderByChild("MenuId").equalTo(categoryId) //ES LO MISMO QUE: SELECT * FROM ELEMENTOS WHERE MENUID =
        ) {
            @Override
            protected void populateViewHolder(ElementosViewHolder viewHolder, Elementos modelo, int position) {
                viewHolder.nombre_elemento.setText(modelo.getNombre());
                Picasso.with(getBaseContext()).load(modelo.getImage())
                        .into(viewHolder.elemento_image);

                final Elementos local = modelo;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(ListaElementos.this, "" + local.getNombre(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        Log.d("TAG",""+adapter.getItemCount() );
        recyclerView.setAdapter(adapter);
    }
}