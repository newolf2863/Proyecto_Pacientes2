/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.util.ArrayList;
import java.util.List;

import Clases.Consulta;

public class ConsultaController {
    
    private List<Consulta> consultas = new ArrayList<>(); // Lista para almacenar las consultas

    public void crearConsulta(Consulta consulta) {
        consultas.add(consulta); // Agregar la nueva consulta a la lista
        System.out.println("Consulta creada exitosamente."); // O cualquier otro mensaje de éxito
    }

    public List<Consulta> listaConsulta() {
        return consultas; // Devolver la lista de consultas
    }
}
