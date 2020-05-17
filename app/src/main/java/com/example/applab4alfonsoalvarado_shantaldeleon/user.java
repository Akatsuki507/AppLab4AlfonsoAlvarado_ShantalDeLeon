package com.example.applab4alfonsoalvarado_shantaldeleon;

public class user {

    public String email;
    public String pass;
    public String nombre;
    public String cedula;
    public String rol;
    public boolean origen; // SI ES DE SHARED PREFENCE ES TRUE, SI NO NO

    public user(String email, String pass, String nombre, String cedula, String rol, boolean origen){
        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
        this.cedula = cedula;
        this.rol = rol;
        this.origen = origen;
    }
}
