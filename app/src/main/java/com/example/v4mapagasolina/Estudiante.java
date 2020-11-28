package com.example.v4mapagasolina;

public class Estudiante {

    private String codigo;   // Integer codigo
    private String nombre;
    private String empresa;
    private String departamento;
    private String municipio;
    private double latitud;
    private double longitud;

    public Estudiante() {

    }

    public Estudiante(String codigo, String nombre, String empresa, String departamento, String municipio, double latitud, double longitud) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.empresa = empresa;
        this.departamento = departamento;
        this.municipio = municipio;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
