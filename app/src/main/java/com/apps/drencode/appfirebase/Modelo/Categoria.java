package com.apps.drencode.appfirebase.Modelo;

/**
 * Created by drenKoCode on 24/02/2018.
 */

public class Categoria {
    private String Nombre;
    private String Image;

    public Categoria(){
    }

    public Categoria(String nombre, String image){
        Nombre = nombre;
        Image = image;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
