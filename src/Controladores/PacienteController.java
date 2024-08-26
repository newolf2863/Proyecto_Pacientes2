/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.Consulta;
import Clases.HistoriaClinica;
import Clases.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteController {
    
    private static final String ARCHIVO_PACIENTES = "C:\\Users\\Issac\\Documents\\NetBeansProjects\\Proyecto_Pacientes\\src\\Datos\\pacientes.txt";

    // Método para registrar un paciente en el archivo de texto
    public void registrarPaciente(Paciente paciente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_PACIENTES, true))) { 
            bw.write(paciente.getNombre() + "," + 
                     paciente.getApellidos() + "," + 
                     paciente.getCedula() + "," + 
                     paciente.getGenero() + "," + 
                     paciente.getDireccion() + "," +
                     paciente.getTelefono() + "," +
                     paciente.getCorreoElectronico());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar el paciente: " + e.getMessage());
        }
    }

    // Método para obtener la lista de pacientes registrados desde el archivo de texto
    public List<Paciente> obtenerPacientesRegistrados() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PACIENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 7) { // Verificar que la línea tenga los 7 datos esperados
                    pacientes.add(new Paciente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener los pacientes registrados: " + e.getMessage());
        }
        return pacientes;
    }


    public void actualizarHistoriaClinica(String cedulaPaciente, Consulta nuevaConsulta) {
        // Implementación para actualizar la historia clínica de un paciente
    }

    public HistoriaClinica mostrarHistoriaClinica(String cedulaPaciente) {
        // Implementación para mostrar la historia clínica de un paciente
        return null;
        // Implementación para mostrar la historia clínica de un paciente
    }
}
