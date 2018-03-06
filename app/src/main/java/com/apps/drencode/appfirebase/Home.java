package com.apps.drencode.appfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.drencode.appfirebase.Common.Common;
import com.apps.drencode.appfirebase.Interfaz.ItemClickListener;
import com.apps.drencode.appfirebase.Modelo.Categoria;
import com.apps.drencode.appfirebase.Modelo.Elementos;
import com.apps.drencode.appfirebase.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference categoria;

    TextView txtNombreCompleto;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Categoria,MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);


        //INICIAMOS FIREBASE
        database = FirebaseDatabase.getInstance();
        categoria = database.getReference("Categoria");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tienda online desabilitada", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //CONFIGURAMOS EL NOMBRE DEL USUARIO
        View headerView = navigationView.getHeaderView(0);
        txtNombreCompleto = (TextView)headerView.findViewById(R.id.txtNombreCompleto);
        txtNombreCompleto.setText(Common.currentUser.getNombre());

        //MENU DE CARGA
        recycler_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadmenu();
    }

    private void loadmenu(){
        adapter = new FirebaseRecyclerAdapter<Categoria, MenuViewHolder>(Categoria.class,R.layout.menu_item,MenuViewHolder.class,categoria) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Categoria modelo, int position) {
                viewHolder.txtNombreMenu.setText(modelo.getNombre());
                Picasso.with(getBaseContext()).load(modelo.getImage())
                      .into(viewHolder.imageView);                                              // COMENTADO POR QUE NO CARGA LAS IMAGENES DE LA BASE DE DATOS
                final Categoria clickItem = modelo;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                       Toast.makeText(Home.this, ""+clickItem.getNombre(), Toast.LENGTH_SHORT).show();

                        //COGEMOS LA ID DE LA CATEGORIA Y LA ENVIAMOS A LA NUEVA ACTIVIDAD
                 //       Intent ListaElementos = new Intent(Home.this, Elementos.class);
                        //PORQUE LA ID DE LA CATEGORIA ES LA KEY, ASI QUE TENEMOS QUE COGER LA KEY DEL ELEMENTO
                 //      ListaElementos.putExtra("CategoryId",adapter.getRef(position).getKey());
                 //      startActivity(ListaElementos);
                    }
                });

            }
        };
        recycler_menu.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu){

        }else if(id == R.id.nav_cambios){

        }else if(id == R.id.nav_perfil){

        }else if(id == R.id.nav_log_out){
        //LOGOUT
            Intent Login = new Intent(Home.this, Login.class);
            Login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(Login);

        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
