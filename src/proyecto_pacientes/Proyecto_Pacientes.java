/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_pacientes;

import Clases.Medico;
import Controladores.MedicoController;
import Vistas.JFMenuDoctor;
import java.util.List;

public class Proyecto_Pacientes {

    public static void main(String[] args) {
        MedicoController medicoController = new MedicoController();

// Registrar un médico
//Medico nuevoMedico = new Medico("Ana", "López", "369258147");
//medicoController.registrarMedico(nuevoMedico);

// Obtener la lista de médicos registrados
List<Medico> medicosRegistrados = medicoController.obtenerMedicosRegistrados();
for (Medico medico : medicosRegistrados) {
    System.out.println(medico.obtenerDatosMedico()); 
}
        Medico medico = new Medico("HOLA", "XD","15");
        
        JFMenuDoctor menu = new JFMenuDoctor(medico);
        menu.setVisible(true);
    }
    
    
}
