/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_pacientes;

import Clases.*;
import Controladores.AntecedentePatologicoController;
import Controladores.*;
import Vistas.Doctor.JFMenuDoctor;
import java.util.List;

public class Proyecto_Pacientes {

    public static void main(String[] args) {
        
        PacienteController pacienteController = new PacienteController();
        ConsultaController consultaController = new ConsultaController();
        MedicoController medicoController = new MedicoController();

        //Crear el médico (asegúrate de que MedicoController tenga el método registrarMedico)
        Medico medico = new Medico("Dr. Juan", "Pérez", "123456789");
        medicoController.registrarMedico(medico);

        // Crear los pacientes (asegúrate de que PacienteController tenga el método registrarPaciente)
        Paciente paciente1 = new Paciente("María", "López", "987654321", "Femenino", "Calle A 123", "555-111-111", "maria@example.com");
        Paciente paciente2 = new Paciente("Carlos", "Gómez", "456123789", "Masculino", "Avenida B 456", "555-222-222", "carlos@example.com");
        pacienteController.registrarPaciente(paciente1);
        pacienteController.registrarPaciente(paciente2);

        // Crear los datos para las consultas
        SignosVitales signosVitales1 = new SignosVitales(36.5, 65.0, 120/80, 95.0);
        ExamenFisico examenFisico1 = new ExamenFisico("Garganta inflamada, fiebre leve");
        Diagnostico diagnostico1 = new Diagnostico("J02.9", "Faringitis aguda no especificada");
        Tratamiento tratamiento1 = new Tratamiento("Ibuprofeno", "400 mg cada 8 horas");

        SignosVitales signosVitales2 = new SignosVitales(37.0, 80.0, 130/90, 99.0);
        ExamenFisico examenFisico2 = new ExamenFisico("Dolor abdominal, náuseas");
        Diagnostico diagnostico2 = new Diagnostico("K29.7", "Gastritis, no especificada");
        Tratamiento tratamiento2 = new Tratamiento("Omeprazol", "20 mg una vez al día");

        // Crear las consultas
        Consulta consulta1 = new Consulta("Dolor de garganta y fiebre", signosVitales1, examenFisico1, diagnostico1, tratamiento1, paciente1, medico);
        Consulta consulta2 = new Consulta("Malestar estomacal", signosVitales2, examenFisico2, diagnostico2, tratamiento2, paciente2, medico);

        // Guardar las consultas en el archivo
        consultaController.guardarConsultaEnArchivo(consulta1);
        consultaController.guardarConsultaEnArchivo(consulta2);
        
        
//        //cargar el doctor
//        Medico medico = medicoController.buscarMedicoPorCedula("123456789");
        // Obtener la lista de consultas registradas
        List<Consulta> consultasRegistradas = consultaController.obtenerConsultasRegistradas();
        List<Paciente> pacientesResgistrados = pacienteController.obtenerPacientesRegistrados();
        AntecedentePatologicoController antecedenteController = new AntecedentePatologicoController();
        

        System.out.println("Paciente: " + pacientesResgistrados.get(0).getNombre());
        
        // Mostrar los detalles de cada consulta
        for (Consulta consulta : consultasRegistradas) {
    System.out.println("Paciente: " + consulta.getPaciente().getNombre() + " " + consulta.getPaciente().getApellidos());
    System.out.println("Médico: " + consulta.getMedico().getNombre() + " " + consulta.getMedico().getApellidos());
    System.out.println("Motivo de consulta: " + consulta.getMotivoConsulta());
    System.out.println("Signos vitales: " + 
            "Temperatura: " + consulta.getSignosVitales().getTemperatura()+ " " +
            "Peso: "+ consulta.getSignosVitales().getPeso() +" " +
            "Presion Arterial: "+ consulta.getSignosVitales().getPresionArterial() +" " +
            "Saturacion O2: "+consulta.getSignosVitales().getSaturacionOxigeno()); // Asegúrate de que SignosVitales tenga un método toString() adecuado
    System.out.println("Examen físico: " + consulta.getExamenFisico().getHallazgos());
    System.out.println("Diagnóstico: " + consulta.getDiagnostico().getCodigoCIE() + " - " + consulta.getDiagnostico().getDescripcion());
    System.out.println("Tratamiento: " + consulta.getTratamiento().getMedicacion() + " - " + consulta.getTratamiento().getPosologia());
    System.out.println("------------------------"); 
}
        
        AntecedentePatologico antecedente1_paciente1 = new AntecedentePatologico(diagnostico1.getCodigoCIE(), examenFisico1.getHallazgos(), paciente1);
        antecedenteController.registrarAntecedentePatologico(antecedente1_paciente1,null);

        Diagnostico diagnostico2_paciente1 = new Diagnostico("I10", "Hipertensión esencial (primaria)");
ExamenFisico examenFisico2_paciente1 = new ExamenFisico("Presión arterial elevada");
AntecedentePatologico antecedente2_paciente1 = new AntecedentePatologico(diagnostico2_paciente1.getCodigoCIE(), examenFisico2_paciente1.getHallazgos(), paciente1);
antecedenteController.registrarAntecedentePatologico(antecedente2_paciente1,null);


        AntecedentePatologico antecedente1_paciente2 = new AntecedentePatologico(diagnostico2.getCodigoCIE(), examenFisico2.getHallazgos(), paciente2);
antecedenteController.registrarAntecedentePatologico(antecedente1_paciente2,null);
             
        
        
        
        
List<AntecedentePatologico> antecedentes = antecedenteController.obtenerAntecedentesRegistrados();

for (AntecedentePatologico antecedente : antecedentes) {
    System.out.println("Paciente: " + antecedente.getPaciente().getNombre() + " " + antecedente.getPaciente().getApellidos());
    System.out.println("Código CIE: " + antecedente.getEnfermedad()); // Recuerda que 'enfermedad' ahora almacena el código CIE
    System.out.println("Hallazgos: " + antecedente.getDescripcion()); // Recuerda que 'descripcion' ahora almacena los hallazgos
    System.out.println("------------------------");
}

//     Paciente paciente = new Paciente("Juan", "Pérez", "123456789", "Masculino", "Calle Ejemplo 123", "1234567890", "juan@example.com");
//Paciente familiar = new Paciente("María", "Pérez", "987654321", "Femenino", "Otra Calle 456", "0987654321", "maria@example.com");
//Diagnostico diagnostico = new Diagnostico("J02.9", "Faringitis aguda, no especificada");
//ExamenFisico examenFisico = new ExamenFisico("Garganta inflamada, fiebre leve");
//Diagnostico diagnostico0 = new Diagnostico("J02.9", "Faringitis aguda, especificada");
//ExamenFisico examenFisico0 = new ExamenFisico("Garganta inflamada, fiebre");
//
//AntecedentePatologico antecedente = new AntecedentePatologico(diagnostico.getCodigoCIE(), examenFisico.getHallazgos(), paciente);
//AntecedentePatologico antecedente0 = new AntecedentePatologico(diagnostico0.getCodigoCIE(), examenFisico0.getHallazgos(), familiar);
//
//
//AntecedentePatologicoController controlador = new AntecedentePatologicoController();
//controlador.registrarAntecedentePatologico(antecedente, familiar); // Se pasa el familiar como argumento
//controlador.registrarAntecedentePatologico(antecedente0, null);

//        JFMenuDoctor menu = new JFMenuDoctor(medico);
//        menu.setVisible(true);
    }
    
    
}
