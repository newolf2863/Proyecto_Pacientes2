/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.List;
import java.util.stream.Collectors;

public class HistoriaClinica {
    private Paciente paciente;
    private List<AntecedentePatologico> antecedentesPatologicos;
    private List<Consulta> consultas;

    public HistoriaClinica(Paciente paciente, List<AntecedentePatologico> antecedentesPatologicos, List<Consulta> consultas) {
        this.paciente = paciente;
        this.antecedentesPatologicos = antecedentesPatologicos;
        this.consultas = consultas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public List<AntecedentePatologico> getAntecedentesPatologicos() {
        return antecedentesPatologicos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setAntecedentesPatologicos(List<AntecedentePatologico> antecedentesPatologicos) {
        this.antecedentesPatologicos = antecedentesPatologicos;
    }
    
    
}
