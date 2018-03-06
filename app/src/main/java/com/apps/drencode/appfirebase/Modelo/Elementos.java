package com.apps.drencode.appfirebase.Modelo;

/**
 * Created by drenKoCode on 24/02/2018.
 */

public class Elementos {
   private String Nombre,Image,Descripcion,Precio,Descuento,MenuId;


    public Elementos() {
    }

    public Elementos(String nombre, String image, String descripcion, String precio, String descuento, String menuId) {
        Nombre = nombre;
        Image = image;
        Descripcion = descripcion;
        Precio = precio;
        Descuento = descuento;
        MenuId = menuId;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getDescuento() {
        return Descuento;
    }

    public void setDescuento(String descuento) {
        Descuento = descuento;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
