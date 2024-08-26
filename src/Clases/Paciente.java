/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.List;

/**
 *
 * @author Issac
 */
public class Paciente {
    private String nombre;
    private String apellidos;
    private String cedula; // Número de historia clínica
    private String genero;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private List<HistoriaClinica> historiasClinicas;

    public Paciente(String nombre, String apellidos, String cedula, String genero, String direccion, String telefono, String correoElectronico) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
