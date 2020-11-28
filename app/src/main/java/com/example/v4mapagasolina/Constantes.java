package com.example.v4mapagasolina;

public class Constantes {
    public static final String TABLA_ESTUDIANTE="estudiante";
    public static final String Campo_ID="id";
    public static final String Campo_nombre="nombre";
    public static final String Campo_Empresa="empresa";
    public static final String Campo_Departamento="departamento";
    public static final String Campo_Municipio="municipio";
    public static final String Campo_Longitud="longitud";
    public static final String Campo_Latitud="latitud";


    // INTEGER,
    // public static final String CREAR_TABLA_USUARIO ="CREATE TABLE "+TABLA_ESTUDIANTE+"("+Campo_ID+" text primary key,"+Campo_nombre+" TEXT,"+Campo_programa+" TEXT)";

    public static final String CREAR_TABLA_USUARIO ="CREATE TABLE "+TABLA_ESTUDIANTE+"("
            +Campo_ID+" text primary key,"
            +Campo_nombre+" TEXT,"
            +Campo_Empresa+" TEXT,"
            +Campo_Departamento+" TEXT,"
            +Campo_Municipio+" TEXT,"
            +Campo_Longitud+" Real,"
            +Campo_Latitud+" Real);";

}
