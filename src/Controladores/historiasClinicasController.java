/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class historiasClinicasController {
    private static final String ARCHIVO_HISTORIAS_CLINICAS = "C:\\Users\\Issac\\Documents\\NetBeansProjects\\Proyecto_Pacientes\\src\\Datos\\historias_clinicas.txt";

    // Método para registrar una historia clínica en el archivo de texto
    public void registrarHistoriaClinica(HistoriaClinica historiaClinica) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_HISTORIAS_CLINICAS, true))) {
            // Formato de guardado (ejemplo, puedes ajustarlo según tus necesidades):
            // paciente.cedula;antecedente1.codigoCIE,antecedente1.descripcion,...;consulta1.motivoConsulta,...
            bw.write(historiaClinica.getPaciente().getCedula() + ";");

            // Guardar antecedentes patológicos
            for (AntecedentePatologico antecedente : historiaClinica.getAntecedentesPatologicos()) {
                bw.write(antecedente.getEnfermedad() + "," + antecedente.getDescripcion() + ",");
                if (antecedente.getFamiliaresConEnfermedad() != null) {
                    for (Paciente familiar : antecedente.getFamiliaresConEnfermedad()) {
                        bw.write(familiar.getCedula() + "-"); 
                    }
                }
                bw.write(";"); 
            }

            // Guardar consultas
            for (Consulta consulta : historiaClinica.getConsultas()) {
                // ... (Guarda los datos de la consulta de forma similar a como lo hiciste en ConsultaController)
                bw.write(";"); // Separador entre consultas
            }

            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar la historia clínica: " + e.getMessage());
        }
    }
    
    // Método para obtener el historial clínico de un paciente por su cédula
    public HistoriaClinica obtenerHistoriaClinicaPorCedula(String cedulaPaciente) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_HISTORIAS_CLINICAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(cedulaPaciente)) {
                    // Reconstruir la historia clínica a partir de los datos del archivo
                    Paciente paciente = new PacienteController().buscarPacientePorCedula(cedulaPaciente);
                    
                    // Obtener antecedentes patológicos
                    List<AntecedentePatologico> antecedentes = new ArrayList<>();
                    for (int i = 1; i < datos.length - 1; i++) { // Asumiendo que el último elemento son las consultas
                        String[] datosAntecedente = datos[i].split(",");
                        if (datosAntecedente.length >= 2) {
                            String codigoCIE = datosAntecedente[0];
                            String hallazgos = datosAntecedente[1];
                            List<Paciente> familiares = new ArrayList<>();
                            if (datosAntecedente.length > 2) {
                                String[] cedulasFamiliares = datosAntecedente[2].split("-");
                                for (String cedulaFamiliar : cedulasFamiliares) {
                                    if (!cedulaFamiliar.isEmpty()) {
                                        Paciente familiar = new PacienteController().buscarPacientePorCedula(cedulaFamiliar);
                                        if (familiar != null) {
                                            familiares.add(familiar);
                                        }
                                    }
                                }
                            }
                            AntecedentePatologico antecedente = new AntecedentePatologico(codigoCIE, hallazgos, paciente);
                            antecedente.setFamiliaresConEnfermedad(familiares);
                            antecedentes.add(antecedente);
                        }
                    }

                    // Obtener consultas (debes implementar la lógica para reconstruir las consultas)
                    List<Consulta> consultas = new ArrayList<>();
                String consultasData = datos[datos.length - 1]; // Asumiendo que las consultas están en el último elemento
                String[] consultasSeparadas = consultasData.split(";"); // Separar cada consulta

                MedicoController medicoController = new MedicoController(); 

                for (String consultaData : consultasSeparadas) {
                    String[] datosConsulta = consultaData.split(",");
                    if (datosConsulta.length == 11) { // Ajusta según la cantidad de datos guardados por consulta
                        // Reconstruir los objetos necesarios
                        Medico medico = medicoController.buscarMedicoPorCedula(datosConsulta[1]);
                        SignosVitales signosVitales = new SignosVitales(
                                Double.parseDouble(datosConsulta[3]),
                                Double.parseDouble(datosConsulta[4]),
                                Double.parseDouble(datosConsulta[5]),
                                Double.parseDouble(datosConsulta[6]));
                        ExamenFisico examenFisico = new ExamenFisico(datosConsulta[7]);
                        Diagnostico diagnostico = new Diagnostico(datosConsulta[8], datosConsulta[9]);
                        Tratamiento tratamiento = new Tratamiento(datosConsulta[10], datosConsulta[11]);

                        // Crear la consulta y agregarla a la lista
                        Consulta consulta = new Consulta(datosConsulta[2], signosVitales, examenFisico, diagnostico, tratamiento, paciente, medico);
                        consultas.add(consulta);
                    }
                }

                return new HistoriaClinica(paciente, antecedentes, consultas);
                    
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener la historia clínica: " + e.getMessage());
        }
        return null; // Historia clínica no encontrada
    }
}
