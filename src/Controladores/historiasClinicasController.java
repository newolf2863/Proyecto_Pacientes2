/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Clases.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriasClinicasController {
    private static final String ARCHIVO_HISTORIAS_CLINICAS = "C:\\Users\\Issac\\Documents\\NetBeansProjects\\Proyecto_Pacientes\\src\\Datos\\historias_clinicas.txt";

    // Método para registrar una historia clínica en el archivo de texto
    public void registrarHistoriaClinica(HistoriaClinica historiaClinica) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_HISTORIAS_CLINICAS, true))) {
            // Formato de guardado (ejemplo, puedes ajustarlo según tus necesidades):
            // paciente.cedula;antecedente1.codigoCIE,antecedente1.descripcion,...;consulta1.motivoConsulta,...
            bw.write(historiaClinica.getPaciente().getCedula() + ";");

            // Guardar antecedentes patológicos
            for (AntecedentePatologico antecedente : historiaClinica.getAntecedentesPatologicos()) {
                bw.write(antecedente.getEnfermedad() + ";" + antecedente.getDescripcion() + ";");
                if (antecedente.getFamiliaresConEnfermedad() != null) {
                    for (Paciente familiar : antecedente.getFamiliaresConEnfermedad()) {
                        bw.write(familiar.getCedula() + "-"); 
                    }
                }
                bw.write(";"); 
            }

            // Separador entre antecedentes y consultas
            bw.write("###;"); 
            
            // Guardar consultas
            for (Consulta consulta : historiaClinica.getConsultas()) {
                // Formato de guardado: paciente.cedula;medico.cedula;motivoConsulta;signosVitales;examenFisico;diagnostico;tratamiento
                bw.write(consulta.getPaciente().getCedula() + ";" +
                         consulta.getMedico().getCedula() + ";" +
                         consulta.getMotivoConsulta() + ";");

                // Manejar valores nulos para SignosVitales
                if (consulta.getSignosVitales() != null) {
                    bw.write(consulta.getSignosVitales().getTemperatura() + ";" +
                            consulta.getSignosVitales().getPeso() + ";" +
                            consulta.getSignosVitales().getPresionArterial() + ";" +
                            consulta.getSignosVitales().getSaturacionOxigeno() + ";");
                } else {
                    bw.write("null;null;null;null;"); 
                }

                // Manejar valores nulos para ExamenFisico
                if (consulta.getExamenFisico() != null) {
                    bw.write(consulta.getExamenFisico().getHallazgos() + ";");
                } else {
                    bw.write("null;"); 
                }

                // Manejar valores nulos para Diagnostico
                if (consulta.getDiagnostico() != null) {
                    bw.write(consulta.getDiagnostico().getCodigoCIE() + ";" +
                            consulta.getDiagnostico().getDescripcion() + ";");
                } else {
                    bw.write("null;null;"); 
                }

                // Manejar valores nulos para Tratamiento
                if (consulta.getTratamiento() != null) {
                    bw.write(consulta.getTratamiento().getMedicacion() + ";" +
                            consulta.getTratamiento().getPosologia());
                } else {
                    bw.write("null;null"); 
                }

                bw.write(";"); 
            }

            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar la historia clínica: " + e.getMessage());
        }
    }
    
//    // Método para obtener el historial clínico de un paciente por su cédula
//    public HistoriaClinica obtenerHistoriaClinicaPorCedula(String cedulaPaciente) {
//        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_HISTORIAS_CLINICAS))) {
//            String linea;
//            while ((linea = br.readLine()) != null) {
//                String[] datos = linea.split(";");
//                if (datos[0].equals(cedulaPaciente)) {
//                    // Reconstruir la historia clínica a partir de los datos del archivo
//                    Paciente paciente = new PacienteController().buscarPacientePorCedula(cedulaPaciente);
//
//                    // Obtener antecedentes patológicos
//                    List<AntecedentePatologico> antecedentes = new ArrayList<>();
//                    int i = 1;
//                    while (i < datos.length && !datos[i].isEmpty()) { // Leer antecedentes hasta encontrar una cadena vacía
//                        String codigoCIE = datos[i++];
//                        String hallazgos = datos[i++];
//                        List<Paciente> familiares = new ArrayList<>();
//
//                        // Leer familiares hasta encontrar el siguiente antecedente o el inicio de las consultas
//                        while (i < datos.length && !datos[i].isEmpty() && !datos[i].contains(",")) {
//                            String cedulaFamiliar = datos[i++];
//                            Paciente familiar = new PacienteController().buscarPacientePorCedula(cedulaFamiliar);
//                            if (familiar != null) {
//                                familiares.add(familiar);
//                            }
//                        }
//
//                        AntecedentePatologico antecedente = new AntecedentePatologico(codigoCIE, hallazgos, paciente);
//                        antecedente.setFamiliaresConEnfermedad(familiares);
//                        antecedentes.add(antecedente);
//                        i++; // Saltar el separador vacío después de la lista de familiares o el último antecedente
//                    }
//
//                    // Obtener consultas
//                    List<Consulta> consultas = new ArrayList<>();
//                    MedicoController medicoController = new MedicoController();
//                    while (i < datos.length && !datos[i].isEmpty()) { // Leer consultas hasta el final de la línea
//                        String[] datosConsulta = datos[i].split(",");
//                        if (datosConsulta.length == 12) {
//                            Medico medico = medicoController.buscarMedicoPorCedula(datosConsulta[1]);
//                            SignosVitales signosVitales = new SignosVitales(
//                                    Double.parseDouble(datosConsulta[3]),
//                                    Double.parseDouble(datosConsulta[4]),
//                                    Double.parseDouble(datosConsulta[5]),
//                                    Double.parseDouble(datosConsulta[6]));
//                            ExamenFisico examenFisico = new ExamenFisico(datosConsulta[7]);
//                            Diagnostico diagnostico = new Diagnostico(datosConsulta[8], datosConsulta[9]);
//                            Tratamiento tratamiento = new Tratamiento(datosConsulta[10], datosConsulta[11]);
//
//                            Consulta consulta = new Consulta(datosConsulta[2], signosVitales, examenFisico, diagnostico, tratamiento, paciente, medico);
//                            consultas.add(consulta);
//                        }
//                        i++;
//                    }
//
//                    return new HistoriaClinica(paciente, antecedentes, consultas);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Error al obtener la historia clínica: " + e.getMessage());
//        }
//        return null; // Historia clínica no encontrada
//    }
//
//    public List<HistoriaClinica> obtenerHistoriasClinicasPorCedula(String cedulaPaciente) {
//        List<HistoriaClinica> historiasClinicas = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_HISTORIAS_CLINICAS))) {
//            String linea;
//            while ((linea = br.readLine()) != null) {
//                String[] datos = linea.split(";");
//                if (datos[0].equals(cedulaPaciente)) {
//                    // Reconstruir la historia clínica a partir de los datos del archivo
//                    Paciente paciente = new PacienteController().buscarPacientePorCedula(cedulaPaciente);
//
//                    // Obtener antecedentes patológicos
//                    List<AntecedentePatologico> antecedentes = new ArrayList<>();
//                    int i = 1;
//                    while (i < datos.length && !datos[i].isEmpty()) {
//                        String codigoCIE = datos[i++];
//                        String hallazgos = datos[i++];
//                        List<Paciente> familiares = new ArrayList<>();
//
//                        while (i < datos.length && !datos[i].isEmpty() && !datos[i].contains(",")) {
//                            String cedulaFamiliar = datos[i++];
//                            Paciente familiar = new PacienteController().buscarPacientePorCedula(cedulaFamiliar);
//                            if (familiar != null) {
//                                familiares.add(familiar);
//                            }
//                        }
//
//                        AntecedentePatologico antecedente = new AntecedentePatologico(codigoCIE, hallazgos, paciente);
//                        antecedente.setFamiliaresConEnfermedad(familiares);
//                        antecedentes.add(antecedente);
//                        i++; 
//                    }
//
//                    // Obtener consultas
//                    List<Consulta> consultas = new ArrayList<>();
//                    MedicoController medicoController = new MedicoController();
//                    while (i < datos.length && !datos[i].isEmpty()) {
//                        String[] datosConsulta = datos[i].split(",");
//                        if (datosConsulta.length == 12) {
//                            Medico medico = medicoController.buscarMedicoPorCedula(datosConsulta[1]);
//                            SignosVitales signosVitales = new SignosVitales(
//                                    Double.parseDouble(datosConsulta[3]),
//                                    Double.parseDouble(datosConsulta[4]),
//                                    Double.parseDouble(datosConsulta[5]),
//                                    Double.parseDouble(datosConsulta[6]));
//                            ExamenFisico examenFisico = new ExamenFisico(datosConsulta[7]);
//                            Diagnostico diagnostico = new Diagnostico(datosConsulta[8], datosConsulta[9]);
//                            Tratamiento tratamiento = new Tratamiento(datosConsulta[10], datosConsulta[11]);
//
//                            Consulta consulta = new Consulta(datosConsulta[2], signosVitales, examenFisico, diagnostico, tratamiento, paciente, medico);
//                            consultas.add(consulta);
//                        }
//                        i++;
//                    }
//
//                    // Agregar la historia clínica reconstruida a la lista
//                    historiasClinicas.add(new HistoriaClinica(paciente, antecedentes, consultas));
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Error al obtener las historias clínicas del paciente: " + e.getMessage());
//        }
//        return historiasClinicas;
//    }
    
    // Método para obtener el historial clínico de un paciente por su cédula (último historial)
    public HistoriaClinica obtenerHistoriaClinicaPorCedula(String cedulaPaciente) {
        List<HistoriaClinica> historias = obtenerHistoriasClinicasPorCedula(cedulaPaciente);
        if (!historias.isEmpty()) {
            return historias.get(historias.size() - 1); // Devolver el último historial
        }
        return null; // No se encontró ningún historial
    }

    // Método para obtener todas las historias clínicas de un paciente por su cédula
    public List<HistoriaClinica> obtenerHistoriasClinicasPorCedula(String cedulaPaciente) {
        List<HistoriaClinica> historiasClinicas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_HISTORIAS_CLINICAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos[0].equals(cedulaPaciente)) {
                    Paciente paciente = new PacienteController().buscarPacientePorCedula(cedulaPaciente);

                    // Obtener antecedentes patológicos
                    List<AntecedentePatologico> antecedentes = new ArrayList<>();
                    int i = 1;
                    while (i < datos.length && !datos[i].equals("###")) { // Leer hasta el separador "###"
                        String codigoCIE = datos[i++];
                        String hallazgos = datos[i++];
                        List<Paciente> familiares = new ArrayList<>();

                        while (i < datos.length && !datos[i].isEmpty() && !datos[i].equals("###")) {
                            String cedulaFamiliar = datos[i++];
                            Paciente familiar = new PacienteController().buscarPacientePorCedula(cedulaFamiliar);
                            if (familiar != null) {
                                familiares.add(familiar);
                            }
                        }

                        AntecedentePatologico antecedente = new AntecedentePatologico(codigoCIE, hallazgos, paciente);
                        antecedente.setFamiliaresConEnfermedad(familiares);
                        antecedentes.add(antecedente);
                        i++; // Saltar el separador vacío o "###"
                    }

                    // Obtener consultas (saltando el separador "###")
                    List<Consulta> consultas = new ArrayList<>();
                    MedicoController medicoController = new MedicoController();
                    i++; // Saltar el separador "###"
                    while (i < datos.length && !datos[i].isEmpty()) {
                        String[] datosConsulta = datos[i].split(",");
                        if (datosConsulta.length == 12) {
                            // ... (Reconstruir objetos de consulta, igual que antes) ...
                        }
                        i++;
                    }

                    // Agregar la historia clínica reconstruida a la lista
                    historiasClinicas.add(new HistoriaClinica(paciente, antecedentes, consultas));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener las historias clínicas del paciente: " + e.getMessage());
        }
        return historiasClinicas;
    }
    
}
