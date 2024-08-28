package Vistas.Secretaria;

import Vistas.Doctor.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import Clases.*;
import Vistas.Doctor.JFPacientesDoctor;
import Vistas.Doctor.JPConsulta;

public class JFMenuSecretaria extends javax.swing.JFrame {
    // Vistas

    // Variables para el manejo del mouse
    int xMouse, yMouse;

    // Paneles
    private JPanel[] clickedPanels = new JPanel[3];
    CardLayout contenido, contenido1;
    private Medico medico;
    ArrayList<Paciente> inventario = null;

    /**
     * Constructor de la clase JFMenuConductor.
     * Inicializa el formulario y carga los datos necesarios.
     * 
     * @param conductor El conductor asociado a la sesión actual.
     */
    public JFMenuSecretaria(Medico medico) {
        this.medico = medico;

        initComponents();
        // Configurar icono de la ventana
        setIconImage(new ImageIcon(getClass().getResource("/iconos/AjustesBest.png")).getImage());
        setLocationRelativeTo(null);

        // Mostrar la fecha y hora actual
        String fecha = "dd-MM-yyyy";
        Locale localM = null;
        String resultado;
        Date fechaYHora = new Date();
        resultado = mostrarFechaHora(fechaYHora, fecha, localM);
        txtDateLog.setText("Fecha\t: " + resultado);

        // Configuración del JFrame
        JFrame frame = new JFrame();
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        contenido = (CardLayout) (panelContent.getLayout());
        contenido.show(panelContent, "card1");

        // Inicializar los paneles del menú
        clickedPanels[0] = Clicked1;
        clickedPanels[1] = Clicked2;
        clickedPanels[2] = Clicked3;
        String nombresMedico = medico.getNombre();
        String nombreMedico = obtenerPrimerNombre(nombresMedico);
        desvanecer();
        jLInicio.setText("Bienvenido/a " + nombreMedico);
    }

    public String obtenerPrimerNombre(String nombres) {
        if (nombres == null || nombres.trim().isEmpty()) {
            return "";
        }
        String[] partes = nombres.split(" ");
        return partes[0];
    }
    
    JFrame activeForm = null;
    
    private void abrirFormHijo(JFrame formHijo) {

        // Verifica si hay un formulario activo y ocúltalo si es necesario
        if (activeForm != null) {
            if (activeForm.getClass() == formHijo.getClass()) {
                return;
            }
            activeForm.setVisible(false);
        }

        // Asigna el nuevo formulario activo
        activeForm = formHijo;

        // Configura el JFrame antes de hacerlo visible
        // Esto debe hacerse antes de llamar a setVisible(true)
        if (!formHijo.isUndecorated()) {
            // Usa un Frame de configuración para configurar el JFrame
            // Nota: Necesitamos un JFrame temporal para aplicar la configuración
            JFrame tempFrame = new JFrame();
            tempFrame.setUndecorated(true);
            tempFrame.dispose(); // Destruye el marco temporal
        }

        // Ajusta el tamaño y la ubicación del formulario hijo
        formHijo.setSize(JPGPanelContenedor.getSize());
        formHijo.setLocation(JPGPanelContenedor.getLocation());
        JPGPanelContenedor.setLayout(null);
        // Configura el tamaño y la posición del formulario hijo antes de agregarlo
        formHijo.setBounds(0, 0, JPGPanelContenedor.getWidth(), JPGPanelContenedor.getHeight());
        // Añade el contenido al panel contenedor
        JPGPanelContenedor.removeAll();
        JPGPanelContenedor.add(formHijo.getContentPane());
        JPGPanelContenedor.revalidate();
        JPGPanelContenedor.repaint();

    }

    public void desvanecer() {
        Clicked1.setVisible(true);
        Clicked2.setVisible(false);
        Clicked3.setVisible(false);
    }

    private void cambiarSeccionMenu(int seccionIndex) {
        for (int i = 0; i < clickedPanels.length; i++) {
            clickedPanels[i].setVisible(i == seccionIndex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Home = new javax.swing.JPanel();
        PanelHome = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        menuPaciente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        menuLogout = new javax.swing.JLabel();
        Clicked1 = new javax.swing.JPanel();
        Clicked2 = new javax.swing.JPanel();
        Clicked3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuHistorial = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();
        JPGPanelContenedor = new javax.swing.JPanel();
        jLInicio = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        txtDateLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SistemaBestColor");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        Home.setLayout(new java.awt.CardLayout());

        PanelHome.setBackground(new java.awt.Color(245, 245, 245));
        PanelHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel69.setText("Menu");
        jPanel3.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        PanelHome.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 20));

        jPanel1.setBackground(new java.awt.Color(41, 39, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuPaciente.setBackground(new java.awt.Color(41, 39, 40));
        menuPaciente.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuPaciente.setForeground(new java.awt.Color(255, 255, 255));
        menuPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/administracion.png"))); // NOI18N
        menuPaciente.setText("Consulta Paciente");
        menuPaciente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPacienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPacienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPacienteMouseExited(evt);
            }
        });
        jPanel1.add(menuPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 210, 52));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Version 0.1");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 740, -1, -1));

        menuLogout.setBackground(new java.awt.Color(41, 39, 40));
        menuLogout.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuLogout.setForeground(new java.awt.Color(255, 255, 255));
        menuLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Sign_Out_32px.png"))); // NOI18N
        menuLogout.setText("     Cerrar sesión");
        menuLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuLogoutMouseExited(evt);
            }
        });
        jPanel1.add(menuLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 210, 52));

        Clicked1.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked1Layout = new javax.swing.GroupLayout(Clicked1);
        Clicked1.setLayout(Clicked1Layout);
        Clicked1Layout.setHorizontalGroup(
            Clicked1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked1Layout.setVerticalGroup(
            Clicked1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 10, 52));

        Clicked2.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked2Layout = new javax.swing.GroupLayout(Clicked2);
        Clicked2.setLayout(Clicked2Layout);
        Clicked2Layout.setHorizontalGroup(
            Clicked2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked2Layout.setVerticalGroup(
            Clicked2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 10, 52));

        Clicked3.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked3Layout = new javax.swing.GroupLayout(Clicked3);
        Clicked3.setLayout(Clicked3Layout);
        Clicked3Layout.setHorizontalGroup(
            Clicked3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked3Layout.setVerticalGroup(
            Clicked3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 10, 52));

        jPanel13.setBackground(new java.awt.Color(146, 10, 48));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Menu_32px.png"))); // NOI18N
        jButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton3.setContentAreaFilled(false);
        jPanel13.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 80, 50));

        jLabel1.setBackground(new java.awt.Color(178, 8, 55));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Doctor");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel13.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 130, -1));

        jLabel2.setBackground(new java.awt.Color(178, 8, 55));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("2024");
        jPanel13.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 120, -1));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 220, 60));

        menuHistorial.setBackground(new java.awt.Color(41, 39, 40));
        menuHistorial.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuHistorial.setForeground(new java.awt.Color(255, 255, 255));
        menuHistorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Bar_Chart_32px.png"))); // NOI18N
        menuHistorial.setText(" Historial Paciente");
        menuHistorial.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menuHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHistorialMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuHistorialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuHistorialMouseExited(evt);
            }
        });
        jPanel1.add(menuHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 210, 52));

        PanelHome.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 770));

        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/power_24dp.png"))); // NOI18N
        btnExit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnExit.setContentAreaFilled(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        PanelHome.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 20, 35, 35));

        panelContent.setLayout(new java.awt.CardLayout());

        JPGPanelContenedor.setBackground(new java.awt.Color(245, 245, 245));
        JPGPanelContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLInicio.setFont(new java.awt.Font("Tahoma", 0, 64)); // NOI18N
        jLInicio.setForeground(new java.awt.Color(102, 102, 102));
        jLInicio.setText("Gestión de Pacientes");
        JPGPanelContenedor.add(jLInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        panelContent.add(JPGPanelContenedor, "card1");

        PanelHome.add(panelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 1180, 690));

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(51, 51, 51));
        txtID.setText("Logged  :");
        PanelHome.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 290, -1));

        txtDateLog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDateLog.setForeground(new java.awt.Color(51, 51, 51));
        txtDateLog.setText("Date      :");
        PanelHome.add(txtDateLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 37, 310, 20));

        Home.add(PanelHome, "home");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnExitActionPerformed
        getToolkit().beep();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de cerrar la aplicacion?", "Warning",
                dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }// GEN-LAST:event_btnExitActionPerformed

    private void menuPacienteMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuPacienteMouseEntered
        menuPaciente.setBackground(Color.decode("#333333"));
    }// GEN-LAST:event_menuPacienteMouseEntered

    private void menuPacienteMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuPacienteMouseExited
        menuPaciente.setBackground(Color.decode("#292728"));
        menuPaciente.setOpaque(true);
    }// GEN-LAST:event_menuPacienteMouseExited

    private void menuPacienteMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuPacienteMouseClicked
        contenido.show(panelContent, "card1");
        cambiarSeccionMenu(0);
        menuPaciente.setBackground(Color.decode("#494848"));
        JFrame ventanaPaciente = new JFPacientesDoctor();
        abrirFormHijo(ventanaPaciente);
        jLInicio.setText("Paquetes");
    }// GEN-LAST:event_menuPacienteMouseClicked

    private void menuLogoutMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuLogoutMouseClicked
        cambiarSeccionMenu(6);
        getToolkit().beep();
        int dialogButton = JOptionPane.YES_NO_OPTION;
//        if (SessionManager.getInstance().isCambiarSesion()) { // Accede a cambiarSesion a través del Singleton
//            int dialogResult = JOptionPane.showConfirmDialog(null, "¿Estás seguro/a que quieres salir de esta cuenta?",
//                    "Warning", dialogButton);
//            if (dialogResult == JOptionPane.YES_OPTION) {
//                // Crea una instancia del JFIngresar
//                JFIngresar ingresarFrame = new JFIngresar();
//                ingresarFrame.setVisible(true); // Muestra el JFIngresar
//
//                // Cierra todas las ventanas abiertas, excepto la nueva ventana ingresarFrame
//                Window[] windows = Window.getWindows(); // Obtiene todas las ventanas abiertas
//                for (Window window : windows) {
//                    if (window != ingresarFrame) { // Cierra todas las ventanas menos la nueva
//                        window.dispose();
//                    }
//                }
//                dispose(); // Cierra el JFrame actual si es necesario (es opcional si ya has cerrado todas
//                           // las demás ventanas)
//            }
//        } else {
//            String mensaje = "Tienes una factura pendiente.";
//            String titulo = "¡Aviso Crítico!";
//            JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
//        }
    }// GEN-LAST:event_menuLogoutMouseClicked

    private void menuLogoutMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuLogoutMouseEntered
        menuLogout.setBackground(Color.decode("#333333"));
        menuLogout.setOpaque(true);
    }// GEN-LAST:event_menuLogoutMouseEntered

    private void menuLogoutMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuLogoutMouseExited
        menuLogout.setBackground(Color.decode("#292728"));
        menuLogout.setOpaque(true);
    }// GEN-LAST:event_menuLogoutMouseExited

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel3MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }// GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jPanel3MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }// GEN-LAST:event_jPanel3MouseDragged


    public boolean verificarContra(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password.matches(regex);
    }


    public Boolean[] cambiarValoresVerdad(Boolean[] valores) {
        for (int i = 0; i < valores.length; i++) {
            valores[i] = true;
        }
        return valores;
    }

    private void menuHistorialMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuHistorialMouseClicked
        contenido.show(panelContent, "card1");
        cambiarSeccionMenu(1);
        menuPaciente.setBackground(Color.decode("#494848"));
        JFrame ventanaConsulta = new JPConsulta();
        abrirFormHijo(ventanaConsulta);
        jLInicio.setText("Paquetes");
    }// GEN-LAST:event_menuHistorialMouseClicked

    private void menuHistorialMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuHistorialMouseEntered
        menuHistorial.setBackground(Color.decode("#333333"));
        menuHistorial.setOpaque(true);
    }// GEN-LAST:event_menuHistorialMouseEntered

    private void menuHistorialMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_menuHistorialMouseExited
        menuHistorial.setBackground(Color.decode("#292728"));
        menuHistorial.setOpaque(true);
    }// GEN-LAST:event_menuHistorialMouseExited


    private static String mostrarFechaHora(Date fechaYHora, String formato, Locale local) {
        String fechaString;
        SimpleDateFormat formateador;
        if (local == null) {
            formateador = new SimpleDateFormat(formato);
        } else {
            formateador = new SimpleDateFormat(formato, local);
        }

        fechaString = formateador.format(fechaYHora);
        return fechaString;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clicked1;
    private javax.swing.JPanel Clicked2;
    private javax.swing.JPanel Clicked3;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel JPGPanelContenedor;
    private javax.swing.JPanel PanelHome;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel menuHistorial;
    private javax.swing.JLabel menuLogout;
    private javax.swing.JLabel menuPaciente;
    private javax.swing.JPanel panelContent;
    private javax.swing.JLabel txtDateLog;
    private javax.swing.JLabel txtID;
    // End of variables declaration//GEN-END:variables

}
