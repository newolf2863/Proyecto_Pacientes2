/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Clases.*;

public class ConsultaController {
    
    private List<Consulta> consultas = new ArrayList<>(); // Lista para almacenar las consultas
    private static final String ARCHIVO_CONSULTAS = "C:\\Users\\Issac\\Documents\\NetBeansProjects\\Proyecto_Pacientes\\src\\Datos\\consultas.txt";
    
    // Método para guardar una consulta en el archivo
    public void guardarConsultaEnArchivo(Consulta consulta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CONSULTAS, true))) {
            // Formato de guardado: paciente.cedula,medico.cedula,motivoConsulta,signosVitales,examenFisico,diagnostico,tratamiento
            bw.write(consulta.getPaciente().getCedula() + ";" +
                    consulta.getMedico().getCedula() + ";" +
                    consulta.getMotivoConsulta()+";");
            // Manejar valores nulos para SignosVitales
            if (consulta.getSignosVitales() != null) {
                bw.write(consulta.getSignosVitales().getTemperatura() + ";" +
                        consulta.getSignosVitales().getPeso() + ";" +
                        consulta.getSignosVitales().getPresionArterial() + ";" +
                        consulta.getSignosVitales().getSaturacionOxigeno() + ";");
            } else {
                bw.write("null;null;null;null;"); // O cualquier otro valor que represente un valor nulo
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

            bw.newLine();
            
        } catch (IOException e) {
            System.err.println("Error al guardar la consulta: " + e.getMessage());
        }
    }

    // Método para obtener todas las consultas desde el archivo
    public List<Consulta> obtenerConsultasRegistradas() {
        List<Consulta> consultas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CONSULTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 12) { // Ajusta según la cantidad de datos que guardes por consulta
                    // Reconstruir los objetos necesarios a partir de los datos del archivo
                    Paciente paciente = new PacienteController().buscarPacientePorCedula(datos[0]); // Asegúrate de tener este método en PacienteController
                    Medico medico = new MedicoController().buscarMedicoPorCedula(datos[1]); // Asegúrate de tener estos métodos
                    
                    // Manejo de valores nulos para los demás atributos
                    SignosVitales signosVitales = null;
                    if (!datos[3].equals("null")) { 
                        signosVitales = new SignosVitales(
                                Double.parseDouble(datos[3]),
                                Double.parseDouble(datos[4]),
                                Double.parseDouble(datos[5]),
                                Double.parseDouble(datos[6]));
                    }

                    ExamenFisico examenFisico = null;
                    if (!datos[7].equals("null")) {
                        examenFisico = new ExamenFisico(datos[7]);
                    }

                    Diagnostico diagnostico = null;
                    if (!datos[8].equals("null") && !datos[9].equals("null")) {
                        diagnostico = new Diagnostico(datos[8], datos[9]);
                    }

                    Tratamiento tratamiento = null;
                    if (!datos[10].equals("null") && !datos[11].equals("null")) {
                        tratamiento = new Tratamiento(datos[10], datos[11]);
                    }

                    // Crear la consulta y agregarla a la lista
                    Consulta consulta = new Consulta(datos[2], signosVitales, examenFisico, diagnostico, tratamiento, paciente, medico);
                    consultas.add(consulta);
                }
                else{
                    System.out.println("datos leght"+datos.length);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener las consultas registradas: " + e.getMessage());
        }
        return consultas;
    }
    
    public Consulta buscarConsultaPorCedulaPaciente(String cedulaPaciente) {
        List<Consulta> consultas = obtenerConsultasRegistradas(); 
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().getCedula().equals(cedulaPaciente)) {
                return consulta;
            }
        }
        return null; 
    }
    
    public void editarConsulta(String cedulaPaciente, String cedulaMedico, 
            String motivoConsulta, SignosVitales signosVitales, ExamenFisico examenFisico,
            Diagnostico diagnostico, Tratamiento tratamiento) {
        List<Consulta> consultas = obtenerConsultasRegistradas();

        // Buscar la consulta existente para el paciente y médico, con otros atributos nulos
        Consulta consultaExistente = consultas.stream()
                .filter(c -> c.getPaciente().getCedula().equals(cedulaPaciente) &&
                             c.getMedico().getCedula().equals(cedulaMedico) &&
                             c.getMotivoConsulta().equals("null"))

                .findFirst()
                .orElse(null);

        if (consultaExistente != null) {
            // Eliminar la consulta existente
            consultas.remove(consultaExistente);

            // Buscar el paciente y el médico por su cédula (puedes optimizar esto si ya los tienes en memoria)
            Paciente paciente = new PacienteController().buscarPacientePorCedula(cedulaPaciente);
            Medico medico = new MedicoController().buscarMedicoPorCedula(cedulaMedico);

            if (paciente != null && medico != null) {
                // Crear una nueva consulta con los datos actualizados
                Consulta nuevaConsulta = new Consulta(motivoConsulta, signosVitales, examenFisico, diagnostico, tratamiento, paciente, medico);

//                // Agregar la nueva consulta a la lista
//                consultas.add(nuevaConsulta);

                // Guardar las consultas actualizadas en el archivo
                guardarConsultasEnArchivo(consultas);
                guardarConsultaEnArchivo(nuevaConsulta);

                System.out.println("Consulta editada exitosamente (se creó una nueva consulta con los datos actualizados).");
            } else {
                System.err.println("Error: Paciente o médico no encontrado. No se pudo editar la consulta.");
            }
        } else {
            // Buscar consulta con la misma cédula de paciente y médico, pero con atributos no nulos
            Consulta consultaConDatos = consultas.stream()
                    .filter(c -> c.getPaciente().getCedula().equals(cedulaPaciente) &&
                                 c.getMedico().getCedula().equals(cedulaMedico) &&
                                 (c.getMotivoConsulta() != null ||
                                  c.getSignosVitales() != null ||
                                  c.getExamenFisico() != null ||
                                  c.getDiagnostico() != null ||
                                  c.getTratamiento() != null))
                    .findFirst()
                    .orElse(null);

            if (consultaConDatos != null) {
                System.err.println("Error: Ya existe una consulta con datos para este paciente y médico. No se puede editar.");
            } else {
                System.err.println("Error: No se encontró ninguna consulta (ni siquiera vacía) para este paciente y médico.");
            }
        }
    }
    
    private void guardarConsultasEnArchivo(List<Consulta> consultas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CONSULTAS))) { // No usar 'true' para sobrescribir
            for (Consulta consulta : consultas) {
                bw.write(consulta.getPaciente().getCedula() + ";" +
                    consulta.getMedico().getCedula() + ";" +
                    consulta.getMotivoConsulta()+";");
                // Manejar valores nulos para SignosVitales
                if (consulta.getSignosVitales() != null) {
                    bw.write(consulta.getSignosVitales().getTemperatura() + ";" +
                            consulta.getSignosVitales().getPeso() + ";" +
                            consulta.getSignosVitales().getPresionArterial() + ";" +
                            consulta.getSignosVitales().getSaturacionOxigeno() + ";");
                } else {
                    bw.write("null;null;null;null;"); // O cualquier otro valor que represente un valor nulo
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

                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar las consultas: " + e.getMessage());
        }
    }

    public List<Consulta> obtenerConsultasPorPaciente(String cedulaPaciente) {
        List<Consulta> consultasPaciente = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CONSULTAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 12) { // Ajusta según la cantidad de datos que guardes por consulta
                    if (datos[0].equals(cedulaPaciente)) { // Verificar si la cédula coincide
                        // Reconstruir los objetos necesarios a partir de los datos del archivo
                        Medico medico = new MedicoController().buscarMedicoPorCedula(datos[1]); 

                        // Manejo de valores nulos para los demás atributos
                        SignosVitales signosVitales = null;
                        if (!datos[3].equals("null")) {
                            signosVitales = new SignosVitales(
                                    Double.parseDouble(datos[3]),
                                    Double.parseDouble(datos[4]),
                                    Double.parseDouble(datos[5]),
                                    Double.parseDouble(datos[6]));
                        }

                        ExamenFisico examenFisico = null;
                        if (!datos[7].equals("null")) {
                            examenFisico = new ExamenFisico(datos[7]);
                        }

                        Diagnostico diagnostico = null;
                        if (!datos[8].equals("null") && !datos[9].equals("null")) {
                            diagnostico = new Diagnostico(datos[8], datos[9]);
                        }

                        Tratamiento tratamiento = null;
                        if (!datos[10].equals("null") && !datos[11].equals("null")) {
                            tratamiento = new Tratamiento(datos[10], datos[11]);
                        }

                        // Buscar el paciente por su cédula (puedes optimizar esto si ya lo tienes en memoria)
                        Paciente paciente = new PacienteController().buscarPacientePorCedula(cedulaPaciente);

                        // Crear la consulta y agregarla a la lista
                        Consulta consulta = new Consulta(datos[2], signosVitales, examenFisico, diagnostico, tratamiento, paciente, medico);
                        consultasPaciente.add(consulta);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al obtener las consultas del paciente: " + e.getMessage());
        }
        return consultasPaciente;
    }
}
