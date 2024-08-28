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
                    consulta.getMotivoConsulta() + ";" +
                    consulta.getSignosVitales().getTemperatura() + ";" +
                    consulta.getSignosVitales().getPeso() + ";" +
                    consulta.getSignosVitales().getPresionArterial() + ";" +
                    consulta.getSignosVitales().getSaturacionOxigeno() + ";" +
                    consulta.getExamenFisico().getHallazgos() + ";" +
                    consulta.getDiagnostico().getCodigoCIE() + ";" +
                    consulta.getDiagnostico().getDescripcion() + ";" +
                    consulta.getTratamiento().getMedicacion() + ";" +
                    consulta.getTratamiento().getPosologia());
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
                    SignosVitales signosVitales = new SignosVitales(
                            Double.parseDouble(datos[3]), 
                            Double.parseDouble(datos[4]), 
                            Double.parseDouble(datos[5]), 
                            Double.parseDouble(datos[6]));
                    ExamenFisico examenFisico = new ExamenFisico(datos[7]);
                    Diagnostico diagnostico = new Diagnostico(datos[8], datos[9]);
                    Tratamiento tratamiento = new Tratamiento(datos[10], datos[11]);

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
}
