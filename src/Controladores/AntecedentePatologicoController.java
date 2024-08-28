/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AntecedentePatologicoController {
    private static final String ARCHIVO_ANTECEDENTES = "C:\\Users\\Issac\\Documents\\NetBeansProjects\\Proyecto_Pacientes\\src\\Datos\\antecedentes.txt";

    public void registrarAntecedentePatologico(AntecedentePatologico antecedente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ANTECEDENTES, true))) {
            // Formato de guardado: paciente.cedula,codigoCIE,descripcionDiagnostico,hallazgosExamenFisico
            bw.write(antecedente.getPaciente().getCedula() + ";" +
                    antecedente.getEnfermedad() + ";" + // Ahora almacena el codigoCIE
                    antecedente.getDescripcion());      // Ahora almacena los hallazgos del examen físico
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar el antecedente patológico: " + e.getMessage());
        }
    }
    
    public List<AntecedentePatologico> obtenerAntecedentesRegistrados() {
        List<AntecedentePatologico> antecedentes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ANTECEDENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 3) { 
                    String cedulaPaciente = datos[0];
                    String codigoCIE = datos[1];
                    String hallazgosExamenFisico = datos[2];

                    // Buscar el paciente por su cédula (asegúrate de tener este método en PacienteController)
                    Paciente paciente = new PacienteController().buscarPacientePorCedula(cedulaPaciente);

                    if (paciente != null) {
                        // Crear el antecedente y agregarlo a la lista
                        AntecedentePatologico antecedente = new AntecedentePatologico(codigoCIE, hallazgosExamenFisico, paciente);
                        antecedentes.add(antecedente);
                    } else {
                        System.err.println("Error al cargar antecedente: Paciente no encontrado.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener los antecedentes registrados: " + e.getMessage());
        }
        return antecedentes;
    }
}
