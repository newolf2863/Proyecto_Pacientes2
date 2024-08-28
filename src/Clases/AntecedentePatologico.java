/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.List;


public class AntecedentePatologico {
    private String enfermedad;
    private String descripcion; 
    private Paciente paciente;
    private List<Paciente> familiaresConEnfermedad; 

   public AntecedentePatologico(String codigoCIE, String hallazgosExamenFisico, Paciente paciente) {
        this.enfermedad = codigoCIE;
        this.descripcion = hallazgosExamenFisico;
        this.paciente = paciente;
        this.familiaresConEnfermedad = new ArrayList<>(); 
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }  

    public Paciente getPaciente() {
        return paciente;
    }
       
    public List<Paciente> getFamiliaresConEnfermedad() {
        return familiaresConEnfermedad;
    }

    public void setFamiliaresConEnfermedad(List<Paciente> familiaresConEnfermedad) {
        this.familiaresConEnfermedad = familiaresConEnfermedad;
    }

    // Método para agregar un familiar a la lista
    public void agregarFamiliarConEnfermedad(Paciente familiar) {
        this.familiaresConEnfermedad.add(familiar);
    }
}
