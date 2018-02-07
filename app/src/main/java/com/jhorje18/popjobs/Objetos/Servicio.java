package com.jhorje18.popjobs.Objetos;

import android.widget.ImageView;

import java.util.Date;

/**
 * Created by adri9ps on 6/2/18.
 */

public class Servicio {

    private String nombre;
    private String descripcion;
    private String categoria;
    private Float precio;
    private String claveS;
    private String claveUser;
    private float ubicacionX;
    private float ubicacionY;
    private String fecha;
    private String imagen;

    public Servicio(){

    }

    public Servicio(String nombre, String descripcion, String categoria, Float precio, String claveS, String claveUser, float ubicacionX, float ubicacionY, String fecha, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precio = precio;
        this.claveS = claveS;
        this.claveUser = claveUser;
        this.ubicacionX = ubicacionX;
        this.ubicacionY = ubicacionY;
        this.fecha = fecha;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getClaveS() {
        return claveS;
    }

    public void setClaveS(String claveS) {
        this.claveS = claveS;
    }

    public String getClaveUser() {
        return claveUser;
    }

    public void setClaveUser(String claveUser) {
        this.claveUser = claveUser;
    }

    public float getUbicacionX() {
        return ubicacionX;
    }

    public void setUbicacionX(float ubicacionX) {
        this.ubicacionX = ubicacionX;
    }

    public float getUbicacionY() {
        return ubicacionY;
    }

    public void setUbicacionY(float ubicacionY) {
        this.ubicacionY = ubicacionY;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
