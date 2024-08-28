/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.List;
import java.util.stream.Collectors;

public class HistoriaClinica {
    private String cedulaPaciente;
    private List<AntecedentePatologico> antecedentesPatologicos;
    private List<Consulta> consultas;

    public HistoriaClinica(String cedulaPaciente, List<AntecedentePatologico> antecedentesPatologicos, List<Consulta> consultas) {
        this.cedulaPaciente = cedulaPaciente;
        this.antecedentesPatologicos = antecedentesPatologicos;
        this.consultas = consultas;
    }

    public String getCedulaPaciente() {
        return cedulaPaciente;
    }

    public void setCedulaPaciente(String cedulaPaciente) {
        this.cedulaPaciente = cedulaPaciente;
    }  
}
