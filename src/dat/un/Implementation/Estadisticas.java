/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Date;

/**
 *
 * @author arthvrian
 */
public class Estadisticas {
    int id;
    int edad;
    Date fecha_diagnostico;
    String tipo_contagio;
    String sexo;
    String ciudad;
    Date fecha_sintomas;
    String ubicacion;   
    String estado;   
    String localidad;   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaDiagnostico() {
        return fecha_diagnostico;
    }

    public void setFechaDiagnostico(Date fecha_diagnostico) {
        this.fecha_diagnostico = fecha_diagnostico;
    }

    public String getTipoContagio() {
        return tipo_contagio;
    }

    public void setTipoContagio(String tipo_contagio) {
        this.tipo_contagio = tipo_contagio;
    }  

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }  

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaSintomas() {
        return fecha_sintomas;
    }

    public void setFechaSintomas(Date fecha_sintomas) {
        this.fecha_sintomas = fecha_sintomas;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }  

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }  

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
