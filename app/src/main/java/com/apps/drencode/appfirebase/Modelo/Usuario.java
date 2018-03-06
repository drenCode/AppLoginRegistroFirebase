package com.apps.drencode.appfirebase.Modelo;

/**
 * Created by drenKoCode on 24/02/2018.
 */

public class Usuario {
    private String Nombre;
    private String Contraseña;

    public Usuario() {

    }

    public Usuario(String nombre, String contraseña) {
        Nombre = nombre;
        Contraseña = contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
