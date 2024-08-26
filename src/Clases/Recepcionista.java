/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Issac
 */
public class Recepcionista {
    private String nombre;
    private String apellidos;
    private String cedula;

    public Recepcionista(String nombre, String apellidos, String cedula) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        
    }
    
    public void crearCita(Paciente paciente, Medico medico, String motivoConsulta) {
        
        System.out.println("Consulta creada exitosamente para " + paciente.getNombre() + " con el Dr. " + medico.getNombre());
    }

    public void asignarDoctor(Paciente paciente, Medico medico) {
        
        System.out.println("El Dr. " + medico.getNombre() + " ha sido asignado a " + paciente.getNombre());
    }
}
