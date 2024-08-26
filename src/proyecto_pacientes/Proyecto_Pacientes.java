/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_pacientes;

import Clases.Medico;
import Vistas.JFMenuDoctor;

/**
 *
 * @author Issac
 */
public class Proyecto_Pacientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Medico medico = new Medico("HOLA", "XD","15");
        
        JFMenuDoctor menu = new JFMenuDoctor(medico);
        menu.setVisible(true);
    }
    
}
