package com.jhorje18.popjobs.Objetos;

/**
 * Created by JHORJE on 5/2/18.
 */

public class Usuario {

    //Variables
    private String uid;
    private String nombreApellidos;
    private String fechaNacimiento;
    private String direccion;
    private String correo;
    private String imagenKey;
    private int telefono;

    public Usuario() {

    }

    public Usuario(String uid, String nombreApellidos, String fechaNacimiento, String direccion, String correo, String imagenKey, int telefono) {
        this.uid = uid;
        this.nombreApellidos = nombreApellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.correo = correo;
        this.imagenKey = imagenKey;
        this.telefono = telefono;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagenKey() {
        return imagenKey;
    }

    public void setImagenKey(String imagenKey) {
        this.imagenKey = imagenKey;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
