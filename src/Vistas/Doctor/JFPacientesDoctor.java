/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.Doctor;

import Clases.AntecedentePatologico;
import Clases.Consulta;
import Clases.HistoriaClinica;
import Clases.Medico;
import Clases.Paciente;
import Controladores.AntecedentePatologicoController;
import Controladores.ConsultaController;
import Controladores.HistoriasClinicasController;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class JFPacientesDoctor extends javax.swing.JFrame {

    /**
     * Creates new form JFPacientesDoctor
     */
    private Medico medicoActual;
    public JFPacientesDoctor(Medico medico) throws IOException {
        this.medicoActual = medico;
        initComponents();
        
        // DocumentListener para jTFBuscarCedula
        jTFBuscarCedula.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                refrescarTablaPacientesAsignados();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                refrescarTablaPacientesAsignados();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método generalmente no se usa para JTextField, pero lo incluimos por completitud
                refrescarTablaPacientesAsignados();
            }
        });

        // DocumentListener para jTFBuscarNombre
        jTFBuscarNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                refrescarTablaPacientesAsignados();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                refrescarTablaPacientesAsignados();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Este método generalmente no se usa para JTextField, pero lo incluimos por completitud
                refrescarTablaPacientesAsignados();
            }
        });
        refrescarTablaPacientesAsignados();
        
        jTFBuscarCedula.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    refrescarTablaAntecedentesPacientes();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    refrescarTablaAntecedentesPacientes();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    refrescarTablaAntecedentesPacientes();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jTFBuscarNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    refrescarTablaAntecedentesPacientes();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    refrescarTablaAntecedentesPacientes();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    refrescarTablaAntecedentesPacientes();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        refrescarTablaAntecedentesPacientes(); 
        
        jTFBuscarCedula.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                mostrarDatosPaciente();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                mostrarDatosPaciente();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                mostrarDatosPaciente();
            }
        });
        
        
        jTFBuscarCedula.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    mostrarHistorialClinico();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    mostrarHistorialClinico();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                try {
                    mostrarHistorialClinico();
                } catch (IOException ex) {
                    Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Llama a mostrarHistorialClinico() inicialmente para mostrar el historial al inicio (opcional)
        try {
            mostrarHistorialClinico();
        } catch (IOException ex) {
            Logger.getLogger(JFPacientesDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    private void refrescarTablaPacientesAsignados() {
        DefaultTableModel model = new DefaultTableModel();
        JTInformacionPaciente.setModel(model);
        String[] columnNames = {
            "Cédula", "Nombres", "Apellidos", "Género", "Correo", "Teléfono", "Dirección"
        };
        model.setColumnIdentifiers(columnNames);

        String cedulaBusqueda = jTFBuscarCedula.getText();
        String nombreBusqueda = jTFBuscarNombre.getText();
        List<Paciente> pacientes = new ArrayList<>();

        // Obtener todas las consultas
        List<Consulta> consultas = new ConsultaController().obtenerConsultasRegistradas();

        // Filtrar consultas por médico actual (asumiendo que tienes acceso al médico actual en esta clase)
        consultas = consultas.stream()
            .filter(consulta -> consulta.getMedico().getCedula().equals(medicoActual.getCedula()) &&
                    consulta.getMotivoConsulta().equals("null"))
            .collect(Collectors.toList());
        // Extraer pacientes de las consultas filtradas
        for (Consulta consulta : consultas) {
            Paciente paciente = consulta.getPaciente();
            if (paciente != null && 
                (cedulaBusqueda.isEmpty() || paciente.getCedula().equals(cedulaBusqueda)) &&
                (nombreBusqueda.isEmpty() || paciente.getNombre().toLowerCase().contains(nombreBusqueda.toLowerCase()))) {
                pacientes.add(paciente);
            }
        }

        // Llenar la tabla con los pacientes asignados al médico
        for (Paciente paciente : pacientes) {
            model.addRow(new Object[]{
                paciente.getCedula(),
                paciente.getNombre(),
                paciente.getApellidos(),
                paciente.getGenero(),
                paciente.getCorreoElectronico(),
                paciente.getTelefono(),
                paciente.getDireccion()
            });
        }
    }
    
    private void refrescarTablaAntecedentesPacientes() throws IOException {
        DefaultTableModel model = new DefaultTableModel();
        JTAntecedentePaciente.setModel(model);
        String[] columnNames = {
                "Cédula Paciente", "Nombre Paciente", "Código CIE", "Hallazgos", "Familiares con Enfermedad"
        };
        model.setColumnIdentifiers(columnNames);

        String cedulaBusqueda = jTFBuscarCedula.getText();
        String nombreBusqueda = jTFBuscarNombre.getText();

        // Obtener todas las consultas
        List<Consulta> consultas = new ConsultaController().obtenerConsultasRegistradas();

        // Filtrar consultas por médico actual
        consultas = consultas.stream()
            .filter(consulta -> 
                (consulta.getMedico().getCedula().equals(medicoActual.getCedula()))
            )
            .collect(Collectors.toList());

        // Obtener antecedentes de los pacientes en las consultas filtradas
        AntecedentePatologicoController antecedenteController = new AntecedentePatologicoController();
        for (Consulta consulta : consultas) {
            Paciente paciente = consulta.getPaciente();
            if (paciente != null) {
                List<AntecedentePatologico> antecedentesPaciente = antecedenteController.obtenerAntecedentesPorPaciente(paciente);
                for (AntecedentePatologico antecedente : antecedentesPaciente) {
                    if ((cedulaBusqueda.isEmpty() || paciente.getCedula().equals(cedulaBusqueda)) &&
                            (nombreBusqueda.isEmpty() || paciente.getNombre().toLowerCase().contains(nombreBusqueda.toLowerCase()))) {

                        // Formatear la lista de familiares
                        String familiares = antecedente.getFamiliaresConEnfermedad().stream()
                                .map(Paciente::getCedula) // Obtener solo las cédulas de los familiares
                                .collect(Collectors.joining(", "));

                        model.addRow(new Object[]{
                                paciente.getCedula(),
                                paciente.getNombre() + " " + paciente.getApellidos(),
                                antecedente.getEnfermedad(), // Código CIE
                                antecedente.getDescripcion(), // Hallazgos
                                familiares
                        });
                    }
                }
            }
        }
    }
    
    
    /////////////////////////////////////
    private void mostrarDatosPaciente() {
        String cedulaPaciente = jTFBuscarCedula.getText();

        HistoriasClinicasController historiasClinicasController = new HistoriasClinicasController();
            List<HistoriaClinica> historiasClinicas = historiasClinicasController.obtenerHistoriasClinicasPorCedula(cedulaPaciente);

            if (!historiasClinicas.isEmpty()) {
                // Seleccionar el último historial clínico
                HistoriaClinica ultimoHistorial = historiasClinicas.get(historiasClinicas.size() - 1);
                Paciente paciente = ultimoHistorial.getPaciente();

                // Mostrar los datos del paciente en los JTextFields
                jTextField1.setText(paciente.getCedula());
                jTextField2.setText(paciente.getNombre());
                jTextField3.setText(paciente.getApellidos());
                jTextField4.setText(paciente.getGenero());
                jTextField5.setText(paciente.getTelefono());

            } else {
                // Limpiar los JTextFields si no se encuentra el historial
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                // Opcional: Mostrar un mensaje indicando que no se encontró el historial
                JOptionPane.showMessageDialog(this, "No se encontró historial clínico para este paciente", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
    }
    
    private void mostrarHistorialClinico() throws IOException {
        String cedulaPaciente = jTFBuscarCedula.getText();

        HistoriasClinicasController historiasClinicasController = new HistoriasClinicasController();
        List<HistoriaClinica> historiasClinicas = historiasClinicasController.obtenerHistoriasClinicasPorCedula(cedulaPaciente);

        if (!historiasClinicas.isEmpty()) {
            // Seleccionar el último historial clínico
            HistoriaClinica ultimoHistorial = historiasClinicas.get(historiasClinicas.size() - 1);

            // Mostrar antecedentes patológicos en jTable1
            DefaultTableModel modelAntecedentes = new DefaultTableModel();
            jTable1.setModel(modelAntecedentes);
            String[] columnNamesAntecedentes = {"Código CIE", "Hallazgos"}; 
            modelAntecedentes.setColumnIdentifiers(columnNamesAntecedentes);

            for (AntecedentePatologico antecedente : ultimoHistorial.getAntecedentesPatologicos()) {
                modelAntecedentes.addRow(new Object[]{
                        antecedente.getEnfermedad(), // Código CIE
                        antecedente.getDescripcion() // Hallazgos
                });
            }

            // Mostrar consultas en jTable2
            crearTablaConsultas();
        } 
    }
    
    private void crearTablaConsultas() {
    DefaultTableModel model = new DefaultTableModel();
    String[] columnNames = {
        "Motivo de Consulta", "Temperatura", "Peso", "Presión Arterial", "Saturación O2", 
        "Hallazgos", "Código CIE", "Descripción Diagnóstico", "Medicación", "Posología"
    };
    model.setColumnIdentifiers(columnNames);

    String cedulaPaciente = jTFBuscarCedula.getText();

    // Obtener consultas del paciente usando ConsultaController
    List<Consulta> consultas = new ConsultaController().obtenerConsultasPorPaciente(cedulaPaciente); 

    for (Consulta consulta : consultas) {
        // Obtener los datos de cada atributo, manejando posibles valores nulos
        String motivoConsulta = consulta.getMotivoConsulta() != null ? consulta.getMotivoConsulta() : "";
        String temperatura = consulta.getSignosVitales() != null ? String.valueOf(consulta.getSignosVitales().getTemperatura()) : "";
        String peso = consulta.getSignosVitales() != null ? String.valueOf(consulta.getSignosVitales().getPeso()) : "";
        String presionArterial = consulta.getSignosVitales() != null ? String.valueOf(consulta.getSignosVitales().getPresionArterial()) : "";
        String saturacionO2 = consulta.getSignosVitales() != null ? String.valueOf(consulta.getSignosVitales().getSaturacionOxigeno()) : "";
        String hallazgos = consulta.getExamenFisico() != null ? consulta.getExamenFisico().getHallazgos() : "";
        String codigoCIE = consulta.getDiagnostico() != null ? consulta.getDiagnostico().getCodigoCIE() : "";
        String descripcionDiagnostico = consulta.getDiagnostico() != null ? consulta.getDiagnostico().getDescripcion() : "";
        String medicacion = consulta.getTratamiento() != null ? consulta.getTratamiento().getMedicacion() : "";
        String posologia = consulta.getTratamiento() != null ? consulta.getTratamiento().getPosologia() : "";

        model.addRow(new Object[]{
            motivoConsulta, temperatura, peso, presionArterial, saturacionO2, 
            hallazgos, codigoCIE, descripcionDiagnostico, medicacion, posologia
        });
    }

    // Asignar el modelo actualizado a la tabla existente jTable2
    jTable2.setModel(model); 
}
    
    
    // Método auxiliar para crear un JLabel con texto y valor
    private JLabel crearJLabel(String texto, String valor) {
        JLabel label = new JLabel("<html><b>" + texto + "</b> " + valor + "</html>");
        return label;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jPHistoriaClinica = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTInformacionPaciente = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTAntecedentePaciente = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jSPHistoriaClinica = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Apellidos = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        Apellidos1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        Apellidos2 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        Apellidos3 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTFBuscarCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFBuscarNombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(146, 10, 48));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Paciente");
        jPanel3.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        JTInformacionPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTInformacionPaciente);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPHistoriaClinica.addTab("Información", jPanel1);

        JTAntecedentePaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(JTAntecedentePaciente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPHistoriaClinica.addTab("Atecedentes", jPanel2);

        jLabel5.setText("Nombres");

        Apellidos.setText("Apellidos");

        Apellidos1.setText("Genero");

        Apellidos2.setText("Dirección");

        Apellidos3.setText("Telefono");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jLabel6.setText("CONSULTAS");

        jLabel8.setText("ANTECEDENTES");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(5, 5, 5)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Apellidos)
                                .addGap(5, 5, 5)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Apellidos1)
                                .addGap(5, 5, 5)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Apellidos2)
                                .addGap(5, 5, 5)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(223, 223, 223)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(Apellidos3)
                                .addGap(5, 5, 5)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Apellidos)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Apellidos1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Apellidos2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Apellidos3)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane6))
                .addGap(60, 60, 60))
        );

        jSPHistoriaClinica.setViewportView(jPanel7);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jSPHistoriaClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jSPHistoriaClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        jPHistoriaClinica.addTab("Historia Clinica", jPanel4);

        jLabel1.setText("Buscar Cédula:");

        jTFBuscarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFBuscarCedulaActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar Nombre:");

        jTFBuscarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFBuscarNombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTFBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTFBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPHistoriaClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTFBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPHistoriaClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MousePressed

    private void jTFBuscarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFBuscarCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFBuscarCedulaActionPerformed

    private void jTFBuscarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFBuscarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFBuscarNombreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFPacientesDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPacientesDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPacientesDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPacientesDoctor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new JFPacientesDoctor(medicoActual).setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Apellidos;
    private javax.swing.JLabel Apellidos1;
    private javax.swing.JLabel Apellidos2;
    private javax.swing.JLabel Apellidos3;
    private javax.swing.JTable JTAntecedentePaciente;
    private javax.swing.JTable JTInformacionPaciente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTabbedPane jPHistoriaClinica;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jSPHistoriaClinica;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTFBuscarCedula;
    private javax.swing.JTextField jTFBuscarNombre;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
