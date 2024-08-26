/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.Medico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoController {
    
    private static final String ARCHIVO_MEDICOS = "C:\\Users\\Issac\\Documents\\NetBeansProjects\\Proyecto_Pacientes\\src\\Datos\\medicos.txt"; 
    // Método para registrar un médico en el archivo de texto
    public void registrarMedico(Medico medico) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_MEDICOS, true))) { // true para añadir al final del archivo
            bw.write(medico.getNombre() + "," + medico.getApellidos() + "," + medico.getCedula());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar el médico: " + e.getMessage());
        }
    }

    // Método para obtener la lista de médicos registrados desde el archivo de texto
    public List<Medico> obtenerMedicosRegistrados() {
        List<Medico> medicos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_MEDICOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) { // Verificar que la línea tenga los 3 datos esperados
                    medicos.add(new Medico(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener los médicos registrados: " + e.getMessage());
        }
        return medicos;
    }
}
