/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.List;


public class Recepcionista {
    private String nombre;
    private String apellidos;
    private String cedula;
    private List<Consulta> citasCreadas = new ArrayList<>();

    public Recepcionista(String nombre, String apellidos, String cedula) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        
    }
    
    // Método para asignar una cita (paciente a un médico)
    public void asignarCita(Paciente paciente, Medico medico, 
            String motivoConsulta) {
        // Crear una nueva consulta
        Consulta nuevaConsulta = new Consulta(motivoConsulta, null, null, null, 
        null, paciente, medico); 

        // Agregar la consulta a la lista de citas creadas
        citasCreadas.add(nuevaConsulta);

        // Imprimir mensaje de confirmación (puedes personalizarlo)
        System.out.println("Cita creada exitosamente para " + paciente.getNombre() + " con el Dr. " + medico.getNombre());
    }

    // Método para ver todas las citas creadas
    public List<Consulta> verCitasCreadas() {
        return citasCreadas; // Devuelve una copia de la lista para evitar modificaciones externas
    }
}
