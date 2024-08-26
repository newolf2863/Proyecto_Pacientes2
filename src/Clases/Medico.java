/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.List;


public class Medico {
    private String nombre;
    private String apellidos;
    private String cedula;
    
    private ArrayList<Paciente> pacientesAsignados = new ArrayList<>();
    
    public Medico(String nombre, String apellidos, String cedula) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
    }
    
    public String obtenerDatosMedico() {
        return "Nombre: " + nombre + "\n" +
               "Apellidos: " + apellidos + "\n" +
               "Cédula: " + cedula;
    }    

    // Método para consultar los pacientes asignados al médico
    public List<Paciente> consultarPacientesAsignados() {
        return pacientesAsignados; // Devuelve una copia de la lista para evitar modificaciones externas
    }

    // Método para asignar un paciente al médico
    public void asignarPaciente(Paciente paciente) {
        pacientesAsignados.add(paciente);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
}
