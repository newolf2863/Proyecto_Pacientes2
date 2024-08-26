/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;


public class Consulta {
    private String motivoConsulta;
    private SignosVitales signosVitales;
    private ExamenFisico examenFisico;
    private Diagnostico diagnostico;
    private Tratamiento tratamiento;
    private Medico medico;

    public Consulta(String motivoConsulta, SignosVitales signosVitales, ExamenFisico examenFisico, Diagnostico diagnostico, Tratamiento tratamiento, Medico medico) {
        this.motivoConsulta = motivoConsulta;
        this.signosVitales = signosVitales;
        this.examenFisico = examenFisico;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.medico = medico;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

}
