/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residencia;

import ConexionEstablecida.Conexion;
import ConexionEstablecida.Tablas;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static residencia.Materias.res;
import static residencia.Materias.vp;

/**
 *
 * @author Cesar Leon
 */
public class Documentos extends javax.swing.JFrame {

    /**
     * Creates new form Documentos
     */
    public Documentos() {
        initComponents();
        datos();
        this.clave.setEnabled(false);
        this.lblclave.setEnabled(false);
        this.btnelininar.setEnabled(false);
        this.btnModificar.setEnabled(false);
    }
    String bd = "ProyectoResidencia";
    String login = "CrLeon";
    String password = "jake12345";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=" + bd + ";integratedSecutiry=false";
    Connection connection = null; 
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;
    FileInputStream Cargar_Archivo;
    DefaultTableModel Tabla;
    
    public void datos() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        res = Tablas.Consulta("select * from Documentacion");
        try {
            while (res.next()) {
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getString(5));
                v.add(res.getInt(6));
               
                modelo.addRow(v);
                jTable1.setModel(modelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
    }
    
    public void Acta() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Seleccionar Imagen de Entrada");
            int Open = chooser.showOpenDialog(null);
            if (Open == JFileChooser.APPROVE_OPTION) {
                File Archivo = chooser.getSelectedFile();
                Acta.setText(String.valueOf(Archivo));
                Image Imagen = getToolkit().getImage(Acta.getText());
                Imagen = Imagen.getScaledInstance(150, 160, Image.SCALE_SMOOTH);
                lblActa.setIcon(new ImageIcon(Imagen));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
    }
    public void Cerfiti() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Seleccionar Imagen de Entrada");
            int Open = chooser.showOpenDialog(null);
            if (Open == JFileChooser.APPROVE_OPTION) {
                File Archivo = chooser.getSelectedFile();
                Certificado.setText(String.valueOf(Archivo));
                Image Imagen = getToolkit().getImage(Certificado.getText());
                Imagen = Imagen.getScaledInstance(150, 160, Image.SCALE_SMOOTH);
                lblCertificado.setIcon(new ImageIcon(Imagen));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
        
    }
    public void INE() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Seleccionar Imagen de Entrada");
            int Open = chooser.showOpenDialog(null);
            if (Open == JFileChooser.APPROVE_OPTION) {
                File Archivo = chooser.getSelectedFile();
                INE.setText(String.valueOf(Archivo));
                Image Imagen = getToolkit().getImage(INE.getText());
                Imagen = Imagen.getScaledInstance(150, 160, Image.SCALE_SMOOTH);
                lbline.setIcon(new ImageIcon(Imagen));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }       
    }public void CD() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Seleccionar Imagen de Entrada");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
            int Open = chooser.showOpenDialog(null);
            if (Open == JFileChooser.APPROVE_OPTION) {
                File Archivo = chooser.getSelectedFile();
                ComprobanteD.setText(String.valueOf(Archivo));
                Image Imagen = getToolkit().getImage(ComprobanteD.getText());
                Imagen = Imagen.getScaledInstance(150, 160, Image.SCALE_SMOOTH);
                lblComprobante.setIcon(new ImageIcon(Imagen));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }       
    }
    public void alta(){
         try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {               
                ps = connection.prepareStatement("INSERT INTO Documentacion VALUES (?,?,?,?,?)");
                Cargar_Archivo = new FileInputStream(Acta.getText());
                ps.setBinaryStream(1, Cargar_Archivo);
                Cargar_Archivo = new FileInputStream(Certificado.getText());
                ps.setBinaryStream(2, Cargar_Archivo);
                Cargar_Archivo = new FileInputStream(INE.getText());
                ps.setBinaryStream(3, Cargar_Archivo);
                Cargar_Archivo = new FileInputStream(ComprobanteD.getText());
                ps.setBinaryStream(4, Cargar_Archivo);
                ps.setInt(5, Integer.parseInt(txtClave.getText().trim()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registro Realizado");
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error verifique los campos" + ex.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error verifique los campos" + ex.toString());
        } 
         datos();
    }     
       
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblActa = new javax.swing.JLabel();
        lblCertificado = new javax.swing.JLabel();
        lbline = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Certificado = new javax.swing.JLabel();
        ComprobanteD = new javax.swing.JLabel();
        Acta = new javax.swing.JLabel();
        btnelininar = new javax.swing.JButton();
        lblComprobante = new javax.swing.JLabel();
        INE = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        lblclave = new javax.swing.JLabel();
        clave = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblActa.setBackground(new java.awt.Color(153, 153, 255));
        lblActa.setForeground(new java.awt.Color(102, 102, 255));
        lblActa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCertificado.setBackground(new java.awt.Color(153, 153, 255));
        lblCertificado.setForeground(new java.awt.Color(102, 102, 255));
        lblCertificado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbline.setBackground(new java.awt.Color(153, 153, 255));
        lbline.setForeground(new java.awt.Color(102, 102, 255));
        lbline.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel4.setText("Calve de alumno");

        txtClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClaveActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Clave de archivo", "Acta de Nacimiento", "Certificado de Secundaria", "INE", "Compribante de Domicilio", "Clave del alumno"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Acta Nacimiento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Certificado");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("INE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Certificado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ComprobanteD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Acta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnelininar.setText("Eliminar");
        btnelininar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnelininarActionPerformed(evt);
            }
        });

        lblComprobante.setBackground(new java.awt.Color(153, 153, 255));
        lblComprobante.setForeground(new java.awt.Color(102, 102, 255));
        lblComprobante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        INE.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton5.setText("Comprobante Domicilio");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        lblclave.setText("Clave de Archivo");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jMenu1.setText("Opciones");

        jMenu2.setText("Eliminar un registro");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu2);

        jMenu3.setText("Modificar un registro");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Menu");

        jMenuItem1.setText("Menu Principal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4))
                    .addComponent(Acta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblActa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Certificado, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(lblCertificado, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(INE, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbline, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComprobanteD, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jButton3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnModificar)))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnelininar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblclave)
                                .addGap(18, 18, 18)
                                .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(74, 128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblCertificado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(lblActa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblComprobante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(INE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Acta, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Certificado, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComprobanteD, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5)
                        .addComponent(jButton3))
                    .addComponent(jButton2))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar)
                    .addComponent(btnelininar)
                    .addComponent(lblclave)
                    .addComponent(clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        alta();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Acta();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Cerfiti();// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        INE();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnelininarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnelininarActionPerformed
        Eliminar();
        this.lblclave.setEnabled(false);
        this.clave.setEnabled(false);
        this.btnelininar.setEnabled(false);  
    }//GEN-LAST:event_btnelininarActionPerformed

    public void Eliminar()
    {
        try {
            PreparedStatement ps;
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {
                String Delete = "DELETE FROM Documentacion WHERE IdDocumentacion= ?";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                ps = connection.prepareStatement(Delete);
                ps.setInt(1, Integer.parseInt(clave.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Eliminado Con  Exito");
                datos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error En Eliminar : " + ex.getMessage());
        }          
    }
    public void Actualizar()
    {
        try {
            PreparedStatement ps;
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {
                String Update = "update Documentacion set Acta = ?, certificado = ?, INE = ?, ComprobanteD = ?, IdAlumno = ? WHERE IdDocumentacion= ?";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                ps = connection.prepareStatement(Update);
                Cargar_Archivo = new FileInputStream(Acta.getText());
                ps.setBinaryStream(1, Cargar_Archivo);
                Cargar_Archivo = new FileInputStream(Certificado.getText());
                ps.setBinaryStream(2, Cargar_Archivo);
                Cargar_Archivo = new FileInputStream(INE.getText());
                ps.setBinaryStream(3, Cargar_Archivo);
                Cargar_Archivo = new FileInputStream(ComprobanteD.getText());
                ps.setBinaryStream(4, Cargar_Archivo);
                ps.setInt(5, Integer.parseInt(txtClave.getText().trim()));
                ps.setInt(6, Integer.parseInt(clave.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Actualizado Con  Exito");
                datos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al Modificar : " + ex.getMessage());
        }          
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        CD();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        try {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea Elinar un registro?",
                    "Registro de Documentos", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            String mensaje = "";
            switch (resp) {
                case 0:
                    mensaje = "Escriba la clave del archivo";
                    this.lblclave.setEnabled(true);
                    this.clave.setEnabled(true);
                    this.btnelininar.setEnabled(true);                  
                    break;
                case 1:
                    mensaje = "Ok";
                    break;
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error En Eliminar : " + e.getMessage());
        }
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        try {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea Modificar un registro?",
                    "Registro de Documentos", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            String mensaje = "";
            switch (resp) {
                case 0:
                    mensaje = "Escriba la clave del archivo y seleccione los ducumentos completos";
                    this.lblclave.setEnabled(true);
                    this.clave.setEnabled(true);
                    this.btnModificar.setEnabled(true);                  
                    break;
                case 1:
                    mensaje = "Ok";
                    break;
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error En Eliminar : " + e.getMessage());
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Actualizar();
        this.lblclave.setEnabled(false);
        this.clave.setEnabled(false);
        this.btnModificar.setEnabled(false); 
    }//GEN-LAST:event_btnModificarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        vp.setVisible(true);
        this.hide();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Documentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Documentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Acta;
    private javax.swing.JLabel Certificado;
    private javax.swing.JLabel ComprobanteD;
    private javax.swing.JLabel INE;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnelininar;
    private javax.swing.JTextField clave;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblActa;
    private javax.swing.JLabel lblCertificado;
    private javax.swing.JLabel lblComprobante;
    private javax.swing.JLabel lblclave;
    private javax.swing.JLabel lbline;
    private javax.swing.JTextField txtClave;
    // End of variables declaration//GEN-END:variables
}
