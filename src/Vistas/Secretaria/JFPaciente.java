/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas.Secretaria;

import Clases.Consulta;
import Clases.Medico;
import Clases.Paciente;
import Controladores.ConsultaController;
import Controladores.MedicoController;
import Controladores.PacienteController;
import Vistas.Doctor.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import validaciones.*;


public class JFPaciente extends javax.swing.JFrame {

    private boolean cedulaEsValida = false;
    private boolean nombreEsValido = false;
    private boolean apellidoEsValido = false;
    private boolean generoEsValido = false;
    private boolean correoEsValido = false;
    private boolean telefonoEsValido = false;
    private boolean direccionEsValida = false;
    
    //ClasesValidadoras
    ValidadorDeRegistros validarRegistroF = new ValidadorDeRegistros();
    ValidadorDeSwings validadorCheck = new ValidadorDeSwings();
    PacienteController pacienteController = new PacienteController();
    ConsultaController consultaController = new ConsultaController();
    
    
    public JFPaciente() {
        initComponents();
        
        jTFBusquedaCI.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                refrescarTablaPacientes();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                refrescarTablaPacientes();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                refrescarTablaPacientes();
            }
        });
        refrescarTablaPacientes();
        
        jTListaPacientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = jTListaPacientes.getSelectedRow();
                    if (selectedRow != -1) {
                        String cedula = jTListaPacientes.getValueAt(selectedRow, 0).toString(); // Asumiendo que la cédula está en la primera columna
                        Paciente paciente = pacienteController.buscarPacientePorCedula(cedula);

                        // Mostrar un JOptionPane con la información del paciente seleccionado
                        JOptionPane.showMessageDialog(null,
                                paciente.toString(), // Asegúrate de que Paciente tenga un método toString() adecuado
                                "Información del Paciente",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        jTListaConsultas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = jTListaConsultas.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener la consulta seleccionada (puedes ajustar esto según cómo almacenas los datos en la tabla)
                    Consulta consulta = consultaController.obtenerConsultasRegistradas().get(selectedRow); 

                    // Mostrar un JOptionPane con la información de la consulta seleccionada
                    JOptionPane.showMessageDialog(null,
                            consulta.toString(), // Asegúrate de que Consulta tenga un método toString() adecuado
                            "Información de la Consulta",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    });
        refrescarTablaConsultas(); 
    }   
    

    private void refrescarTablaPacientes() {
        DefaultTableModel model = new DefaultTableModel();
        jTListaPacientes.setModel(model);
        String[] columnNames = {
                "Cédula", "Nombres", "Apellidos", "Género", "Correo", "Teléfono", "Dirección"
        };
        model.setColumnIdentifiers(columnNames);

        String cedulaBusqueda = jTFBusquedaCI.getText();
        List<Paciente> pacientes;

        if (cedulaBusqueda.isEmpty()) {
            // Si el campo de búsqueda está vacío, mostrar todos los pacientes
            pacientes = pacienteController.obtenerPacientesRegistrados();
        } else {
            // Si hay texto en el campo de búsqueda, filtrar por cédula
            Paciente pacienteEncontrado = pacienteController.buscarPacientePorCedula(cedulaBusqueda);
            pacientes = (pacienteEncontrado != null) ? Arrays.asList(pacienteEncontrado) : new ArrayList<>();
        }

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
    
    private void refrescarTablaConsultas() {
        DefaultTableModel model = new DefaultTableModel();
        jTListaConsultas.setModel(model);
        String[] columnNames = {
                "Cédula Paciente", "Nombre Paciente", "Cédula Médico", "Nombre Médico" 
        };
        model.setColumnIdentifiers(columnNames);

        String cedulaBusqueda = jTFBusquedaCI.getText(); // Asumiendo que usas el mismo campo de búsqueda
        List<Consulta> consultas;

        if (cedulaBusqueda.isEmpty()) {
            // Si el campo de búsqueda está vacío, mostrar todas las consultas
            consultas = consultaController.obtenerConsultasRegistradas();
        } else {
            // Si hay texto en el campo de búsqueda, filtrar por cédula del paciente
            Consulta consultaEncontrada = consultaController.buscarConsultaPorCedulaPaciente(cedulaBusqueda);
            consultas = (consultaEncontrada != null) ? Arrays.asList(consultaEncontrada) : new ArrayList<>();
        }

        for (Consulta consulta : consultas) {
            model.addRow(new Object[]{
                    consulta.getPaciente().getCedula(),
                    consulta.getPaciente().getNombre(),
                    consulta.getMedico().getCedula(),
                    consulta.getMedico().getNombre()
            });
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLTipoCli = new javax.swing.JLabel();
        jTFCI = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jTFNombresR = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        jTFApellidos = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jTFTelefono = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jTFDireccion = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jTFCorreo = new javax.swing.JTextField();
        jCBGenero = new javax.swing.JComboBox<>();
        jLTipoCli1 = new javax.swing.JLabel();
        jBRregistrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTFBusquedaCI = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTListaPacientes = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFConMedicoCI = new javax.swing.JTextField();
        jTFConPacienteCI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTFConPacienteNombre = new javax.swing.JTextField();
        jTFConPacienteApellido = new javax.swing.JTextField();
        jTFConPacienteGenero = new javax.swing.JTextField();
        jTFConPacienteDir = new javax.swing.JTextField();
        jTFConPacienteTelefono = new javax.swing.JTextField();
        jTFConPacienteEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTFConMedicoNombre = new javax.swing.JTextField();
        jTFConMedicoApellido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jBCrearConsulta = new javax.swing.JButton();
        jBBuscarPaciente = new javax.swing.JButton();
        jBBuscarMedico = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTListaConsultas = new javax.swing.JTable();

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

        jLTipoCli.setText("CI");

        jTFCI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFCIFocusLost(evt);
            }
        });
        jTFCI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFCIKeyReleased(evt);
            }
        });

        jLabel66.setText("Nombres");

        jTFNombresR.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFNombresRFocusLost(evt);
            }
        });
        jTFNombresR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFNombresRKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFNombresRKeyTyped(evt);
            }
        });

        jLabel89.setText("Apellidos");

        jTFApellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFApellidosFocusLost(evt);
            }
        });
        jTFApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFApellidosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFApellidosKeyTyped(evt);
            }
        });

        jLabel87.setText("Genero");

        jLabel71.setText("Teléfono (0999999999)");

        jTFTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFTelefonoFocusLost(evt);
            }
        });
        jTFTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFTelefonoKeyReleased(evt);
            }
        });

        jLabel73.setText("Dirección");

        jTFDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFDireccionFocusLost(evt);
            }
        });
        jTFDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFDireccionKeyReleased(evt);
            }
        });

        jLabel88.setText("Correo");

        jTFCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTFCorreoFocusLost(evt);
            }
        });
        jTFCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFCorreoKeyReleased(evt);
            }
        });

        jCBGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccionar", "masculino", "femenino", " " }));

        jLTipoCli1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLTipoCli1.setText("REGISTRO PACIENTE");

        jBRregistrar.setText("Registrar");
        jBRregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRregistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel66)
                                    .addComponent(jLTipoCli)
                                    .addComponent(jLabel89)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTFNombresR, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                        .addComponent(jTFCI)
                                        .addComponent(jTFApellidos)))
                                .addGap(106, 106, 106)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73)
                                    .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel71)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTFCorreo)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(133, 133, 133)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLTipoCli1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jBRregistrar)))
                .addContainerGap(379, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLTipoCli1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLTipoCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFNombresR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel89)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jBRregistrar)
                .addGap(59, 59, 59))
        );

        jTabbedPane1.addTab("Registrar", jPanel1);

        jLabel1.setText("Busqueda CI:");

        jTListaPacientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTListaPacientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTFBusquedaCI, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFBusquedaCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Lista Pacientes", jPanel2);

        jPanel4.setEnabled(false);

        jLabel2.setText("CI Paciente");

        jLabel3.setText("CI Medico");

        jLabel4.setText("Nombres");

        jLabel5.setText("Apellidos");

        jLabel6.setText("Genero");

        jLabel7.setText("Dirección");

        jLabel8.setText("Telefono");

        jLabel9.setText("Email");

        jTFConPacienteNombre.setEnabled(false);

        jTFConPacienteApellido.setEnabled(false);

        jTFConPacienteGenero.setEnabled(false);

        jTFConPacienteDir.setEnabled(false);

        jTFConPacienteTelefono.setEnabled(false);

        jTFConPacienteEmail.setEnabled(false);

        jLabel10.setText("Nombres");

        jTFConMedicoNombre.setEnabled(false);

        jTFConMedicoApellido.setEnabled(false);

        jLabel11.setText("Apellidos");

        jBCrearConsulta.setText("Crear Consulta");
        jBCrearConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCrearConsultaActionPerformed(evt);
            }
        });

        jBBuscarPaciente.setText("Buscar Paciente");
        jBBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarPacienteActionPerformed(evt);
            }
        });

        jBBuscarMedico.setText("Buscar Medico");
        jBBuscarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarMedicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTFConPacienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTFConPacienteCI))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTFConPacienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTFConPacienteApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTFConPacienteGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(jTFConPacienteDir, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jBBuscarPaciente)
                                    .addComponent(jTFConPacienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jBCrearConsulta)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTFConMedicoApellido)
                        .addComponent(jTFConMedicoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                        .addComponent(jTFConMedicoCI))
                    .addComponent(jBBuscarMedico))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFConMedicoCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTFConPacienteCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFConPacienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTFConMedicoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTFConPacienteApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTFConMedicoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFConPacienteGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTFConPacienteDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFConPacienteTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTFConPacienteEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBBuscarPaciente)
                            .addComponent(jBBuscarMedico))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                        .addComponent(jBCrearConsulta)
                        .addGap(74, 74, 74))))
        );

        jTabbedPane1.addTab("Consulta", jPanel4);

        jTListaConsultas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTListaConsultas);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 74, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Lista Consultas", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MouseDragged

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel3MousePressed

    private void jTFCIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFCIFocusLost
        cedulaEsValida = validarRegistroF.camposDeRegistros(jTFCI, "cedula");
        validarRegistroF.hideTooltip();
    }//GEN-LAST:event_jTFCIFocusLost

    private void jTFCIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCIKeyReleased
        cedulaEsValida = validarRegistroF.camposDeRegistros(jTFCI, "cedula");
    }//GEN-LAST:event_jTFCIKeyReleased

    private void jTFNombresRFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFNombresRFocusLost
        nombreEsValido = validarRegistroF.camposDeRegistros(jTFNombresR, "nombre");
        validarRegistroF.hideTooltip();
    }//GEN-LAST:event_jTFNombresRFocusLost

    private void jTFNombresRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombresRKeyReleased
        nombreEsValido = validarRegistroF.camposDeRegistros(jTFNombresR, "nombre");
    }//GEN-LAST:event_jTFNombresRKeyReleased

    private void jTFNombresRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombresRKeyTyped
        int maxLength = 40; // Límite de longitud de caracteres
        if (jTFNombresR.getText().length() >= maxLength) {
            evt.consume();
        }
    }//GEN-LAST:event_jTFNombresRKeyTyped

    private void jTFApellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFApellidosFocusLost
        apellidoEsValido = validarRegistroF.camposDeRegistros(jTFApellidos, "nombre");
        validarRegistroF.hideTooltip();
    }//GEN-LAST:event_jTFApellidosFocusLost

    private void jTFApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFApellidosKeyReleased
        apellidoEsValido = validarRegistroF.camposDeRegistros(jTFApellidos, "nombre");
    }//GEN-LAST:event_jTFApellidosKeyReleased

    private void jTFApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFApellidosKeyTyped
        int maxLength = 64; // Límite de longitud de caracteres
        if (jTFApellidos.getText().length() >= maxLength) {
            evt.consume();
        }
    }//GEN-LAST:event_jTFApellidosKeyTyped

    private void jTFTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFTelefonoFocusLost
        telefonoEsValido = validarRegistroF.camposDeRegistros(jTFTelefono, "telefono");
        validarRegistroF.hideTooltip();
    }//GEN-LAST:event_jTFTelefonoFocusLost

    private void jTFTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTelefonoKeyReleased
        telefonoEsValido = validarRegistroF.camposDeRegistros(jTFTelefono, "telefono");
    }//GEN-LAST:event_jTFTelefonoKeyReleased

    private void jTFDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFDireccionFocusLost
        direccionEsValida = validarRegistroF.camposDeRegistros(jTFDireccion, "direccion");
        validarRegistroF.hideTooltip();
    }//GEN-LAST:event_jTFDireccionFocusLost

    private void jTFDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFDireccionKeyReleased
        direccionEsValida = validarRegistroF.camposDeRegistros(jTFDireccion, "direccion");
    }//GEN-LAST:event_jTFDireccionKeyReleased

    private void jTFCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFCorreoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCorreoFocusLost

    private void jTFCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCorreoKeyReleased

    private void jBRregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRregistrarActionPerformed
        // Obtener los datos del paciente desde los campos de texto y el combobox
        String cedula = jTFCI.getText();
        String nombres = jTFNombresR.getText();
        String apellidos = jTFApellidos.getText();
        String genero = (String) jCBGenero.getSelectedItem(); // Asegúrate de que jCBGenero es un JComboBox
        String correo = jTFCorreo.getText();
        String telefono = jTFTelefono.getText();
        String direccion = jTFDireccion.getText();

        ValidadorDeRegistros camposDeRegistros = new ValidadorDeRegistros();

        // Validar los datos (puedes usar la función camposDeRegistros si la tienes implementada)
        if (camposDeRegistros.camposDeRegistros(jTFCI, "cedula") && 
            camposDeRegistros.camposDeRegistros(jTFNombresR, "nombre") &&
            camposDeRegistros.camposDeRegistros(jTFApellidos, "apellidos") &&
            camposDeRegistros.camposDeRegistros(jCBGenero, "genero") &&
            camposDeRegistros.camposDeRegistros(jTFCorreo, "correoElectronico") &&
            camposDeRegistros.camposDeRegistros(jTFTelefono, "telefono") &&
            camposDeRegistros.camposDeRegistros(jTFDireccion, "direccion")) {

            // Crear un nuevo objeto Paciente
            Paciente nuevoPaciente = new Paciente(nombres, apellidos, cedula, genero, direccion, telefono, correo);

            // Registrar el paciente usando PacienteController
            PacienteController pacienteController = new PacienteController();
            pacienteController.registrarPaciente(nuevoPaciente);

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(this, "Paciente registrado exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos de texto después del registro (opcional)
            jTFCI.setText("");
            jTFNombresR.setText("");
            jTFApellidos.setText("");
            jCBGenero.setSelectedIndex(0); // Selecciona el primer elemento ("seleccionar")
            jTFCorreo.setText("");
            jTFTelefono.setText("");
            jTFDireccion.setText("");
        } 
        refrescarTablaPacientes();
    }//GEN-LAST:event_jBRregistrarActionPerformed

    private void jBBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarPacienteActionPerformed
        String cedula = jTFConPacienteCI.getText();

        Paciente paciente = pacienteController.buscarPacientePorCedula(cedula);
        
        if (paciente != null) {
            // Llenar los campos con los datos del paciente
            jTFConPacienteNombre.setText(paciente.getNombre());
            jTFConPacienteApellido.setText(paciente.getApellidos());
            jTFConPacienteGenero.setText(paciente.getGenero());
            jTFConPacienteDir.setText(paciente.getDireccion());
            jTFConPacienteTelefono.setText(paciente.getTelefono());
            jTFConPacienteEmail.setText(paciente.getCorreoElectronico());

        } else {
            // Mostrar mensaje de error si el paciente no se encuentra
            JOptionPane.showMessageDialog(this, "Paciente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);

            // Limpiar los campos (opcional)
            jTFConPacienteNombre.setText("");
            jTFConPacienteApellido.setText("");
            jTFConPacienteGenero.setText("");
            jTFConPacienteDir.setText("");
            jTFConPacienteTelefono.setText("");
            jTFConPacienteEmail.setText("");

        }
    }//GEN-LAST:event_jBBuscarPacienteActionPerformed

    private void jBBuscarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarMedicoActionPerformed
        String cedula = jTFConMedicoCI.getText();
        
        MedicoController medicoController = new MedicoController();
        Medico medico = medicoController.buscarMedicoPorCedula(cedula);
        if (medico != null) {
            // Llenar los campos con los datos del paciente
            jTFConMedicoNombre.setText(medico.getNombre());
            jTFConMedicoApellido.setText(medico.getApellidos());
        } else {
            // Mostrar mensaje de error si el paciente no se encuentra
            JOptionPane.showMessageDialog(this, "Medico no encontrado", "Error", JOptionPane.ERROR_MESSAGE);

            jTFConMedicoNombre.setText("");
            jTFConMedicoApellido.setText("");
        }
    }//GEN-LAST:event_jBBuscarMedicoActionPerformed

    private void jBCrearConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCrearConsultaActionPerformed
        
        String cedulaPaciente = jTFConPacienteCI.getText();
        String cedulaMedico = jTFConMedicoCI.getText();
        // Buscar el paciente y el médico por su cédula
        PacienteController pacienteController = new PacienteController();
        Paciente paciente = pacienteController.buscarPacientePorCedula(cedulaPaciente);

        MedicoController medicoController = new MedicoController();
        Medico medico = medicoController.buscarMedicoPorCedula(cedulaMedico);

        if (paciente != null && medico != null) {
            // Crear una nueva consulta con los datos del paciente y el médico, y el resto de atributos nulos
            Consulta nuevaConsulta = new Consulta(null, null, null, null, null, paciente, medico); 

            // Registrar la consulta usando ConsultaController
            ConsultaController consultaController = new ConsultaController();
            consultaController.guardarConsultaEnArchivo(nuevaConsulta);

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(this, "Consulta creada exitosamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);


        } else {
            // Mostrar mensaje de error si el paciente o el médico no se encuentran
            JOptionPane.showMessageDialog(this, "Paciente o médico no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        }
        refrescarTablaConsultas();
    }//GEN-LAST:event_jBCrearConsultaActionPerformed

    
    
    
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
            java.util.logging.Logger.getLogger(JFPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFPaciente jfPaciente = new JFPaciente(); 
                jfPaciente.setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBuscarMedico;
    private javax.swing.JButton jBBuscarPaciente;
    private javax.swing.JButton jBCrearConsulta;
    private javax.swing.JButton jBRregistrar;
    private javax.swing.JComboBox<String> jCBGenero;
    private javax.swing.JLabel jLTipoCli;
    private javax.swing.JLabel jLTipoCli1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFBusquedaCI;
    private javax.swing.JTextField jTFCI;
    private javax.swing.JTextField jTFConMedicoApellido;
    private javax.swing.JTextField jTFConMedicoCI;
    private javax.swing.JTextField jTFConMedicoNombre;
    private javax.swing.JTextField jTFConPacienteApellido;
    private javax.swing.JTextField jTFConPacienteCI;
    private javax.swing.JTextField jTFConPacienteDir;
    private javax.swing.JTextField jTFConPacienteEmail;
    private javax.swing.JTextField jTFConPacienteGenero;
    private javax.swing.JTextField jTFConPacienteNombre;
    private javax.swing.JTextField jTFConPacienteTelefono;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFNombresR;
    private javax.swing.JTextField jTFTelefono;
    private javax.swing.JTable jTListaConsultas;
    private javax.swing.JTable jTListaPacientes;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
