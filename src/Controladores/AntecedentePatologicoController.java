/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.AntecedentePatologico;
import Clases.Paciente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AntecedentePatologicoController {
    private static final String ARCHIVO_ANTECEDENTES = "C:\\Users\\Issac\\Documents\\NetBeansProjects\\Proyecto_Pacientes\\src\\Datos\\antecedentes.txt";

    public void registrarAntecedentePatologico(AntecedentePatologico antecedente, Paciente familiar) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ANTECEDENTES, true))) {
            // Formato de guardado: paciente.cedula;codigoCIE;hallazgosExamenFisico;familiar.cedula (si existe)
            bw.write(antecedente.getPaciente().getCedula() + ";" +
                    antecedente.getEnfermedad() + ";" + 
                    antecedente.getDescripcion() + ";");

            if (familiar != null) {
                bw.write(familiar.getCedula()); 
            }

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
  
    public List<AntecedentePatologico> obtenerAntecedentesPorPaciente(Paciente paciente) throws IOException {
        List<AntecedentePatologico> antecedentesPaciente = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_ANTECEDENTES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 3) { // Asegurarse de tener al menos los datos básicos
                    String cedulaPacienteArchivo = datos[0];
                    String codigoCIE = datos[1];
                    String hallazgosExamenFisico = datos[2];

                    // Verificar si la cédula del paciente en el archivo coincide con la del paciente proporcionado
                    if (cedulaPacienteArchivo.equals(paciente.getCedula())) {
                        List<Paciente> familiares = new ArrayList<>();
                        if (datos.length > 3) { 
                            String[] cedulasFamiliares = datos[3].split("-");
                            for (String cedulaFamiliar : cedulasFamiliares) {
                                if (!cedulaFamiliar.isEmpty()) {
                                    Paciente familiar = new PacienteController().buscarPacientePorCedula(cedulaFamiliar);
                                    if (familiar != null) {
                                        familiares.add(familiar);
                                    }
                                }
                            }
                        }

                        AntecedentePatologico antecedente = new AntecedentePatologico(codigoCIE, hallazgosExamenFisico, paciente);
                        antecedente.setFamiliaresConEnfermedad(familiares);
                        antecedentesPaciente.add(antecedente);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener los antecedentes del paciente: " + e.getMessage());
            throw e; // Relanzar la excepción para que pueda ser manejada en un nivel superior si es necesario
        }
        return antecedentesPaciente;
    }
        
}
