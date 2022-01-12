/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residencia;

import ConexionEstablecida.Conexion;
import ConexionEstablecida.Tablas;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static residencia.Materias.res;


/**
 *
 * @author Cesar Leon
 */
public class RegistroPagos extends javax.swing.JFrame {

    /**
     * Creates new form RegistroPagos
     */
    public RegistroPagos() {
        initComponents();
        datos();
        this.btnEliminar.setEnabled(false);
        this.lblclave.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.txtId.setEnabled(false);
        tbl.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent Mouse_evt){
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();               
                int row = table.rowAtPoint(point);
                if(Mouse_evt.getClickCount()==2){
                    txtId.setText(tbl.getValueAt(tbl.getSelectedRow(),0).toString());
                    String img = tbl.getValueAt(tbl.getSelectedRow(),1).toString();
                    Image foto = getToolkit().getImage(img);
                    foto = foto.getScaledInstance(150, 160, Image.SCALE_DEFAULT);                  
                    lblFoto.setIcon(new ImageIcon(foto));
                    txtfecha.setText(tbl.getValueAt(tbl.getSelectedRow(),2).toString());
                    txtRazon.setText(tbl.getValueAt(tbl.getSelectedRow(),3).toString());
                    txtClave.setText(tbl.getValueAt(tbl.getSelectedRow(),4).toString());
                }
            }
        });
    }
    Conexion con = new Conexion();  
    PreparedStatement ps = null;
    Statement st = null;
    ResultSet rs = null;
    FileInputStream Cargar_Archivo;
    DefaultTableModel Tabla;
    Connection cn = con.getConnection();
    
    public void datos() {
        DefaultTableModel modelo = (DefaultTableModel) tbl.getModel();
        modelo.setRowCount(0);
        res = Tablas.Consulta("select * from pagos");
        try {
            while (res.next()) {
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getInt(5));
               
                modelo.addRow(v);
                tbl.setModel(modelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtRazon = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        lblLocalizador = new javax.swing.JLabel();
        jdate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtClave = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblclave = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Fecha de pago");

        jLabel1.setText("Razon de pago");

        jButton1.setText("Foto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Clave de Pago", "Comprobante", "Fecha de Pago", "Razon de pago", "Clave de Alumno"
            }
        ));
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblFoto.setBackground(new java.awt.Color(204, 204, 255));
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLocalizador.setBackground(new java.awt.Color(204, 204, 255));
        lblLocalizador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jdateMouseClicked(evt);
            }
        });

        jLabel2.setText("Clave del alumno");

        txtfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaActionPerformed(evt);
            }
        });

        jButton3.setText("Registro de fecha");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblclave.setText("Clave");

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

        jMenu3.setText("Actualizar un registro");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(33, 33, 33)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtRazon, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtClave)
                                        .addGap(138, 138, 138))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblclave, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(lblLocalizador, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblLocalizador, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar)
                            .addComponent(lblclave)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar))
                        .addGap(16, 16, 16)))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fil = new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
        chooser.addChoosableFileFilter(fil);
        chooser.setDialogTitle("Seleccionar Imagen de Entrada");
        int Open = chooser.showOpenDialog(null);
        if (Open == JFileChooser.APPROVE_OPTION) {
            File Archivo = chooser.getSelectedFile();
            lblLocalizador.setText(String.valueOf(Archivo));
            Image Imagen = getToolkit().getImage(lblLocalizador.getText());
            Imagen = Imagen.getScaledInstance(150, 160, Image.SCALE_DEFAULT);
            lblFoto.setIcon(new ImageIcon(Imagen));
        }      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        

    }//GEN-LAST:event_tblMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String bd = "ProyectoResidencia";
        String login = "CrLeon";
        String password = "jake12345";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + bd + ";integratedSecutiry=false";
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {

                PreparedStatement pst = connection.prepareStatement("insert into pagos values(?,?,?,?)");
                Cargar_Archivo = new FileInputStream(lblLocalizador.getText().trim());
                pst.setBinaryStream(1, Cargar_Archivo); 
                pst.setString(2, txtfecha.getText().trim());
                pst.setString(3, txtRazon.getText().trim());
                pst.setInt(4, Integer.parseInt(txtClave.getText()));
                pst.executeUpdate();
              
                JOptionPane.showMessageDialog(null, "Registro Realizado");
                
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.toString());
        }
        datos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaActionPerformed

    private void jdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdateMouseClicked

    }//GEN-LAST:event_jdateMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                Fecha();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        try {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar un registro?",
                    "Registro de Materias", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            String mensaje = "";
            switch (resp) {
                case 0:
                    mensaje = "Escriba la clave del registro a eliminar";
                    this.btnEliminar.setEnabled(true);
                    this.lblclave.setEnabled(true);
                    this.txtId.setEnabled(true);
                    break;
                case 1:
                    mensaje = "OK";
                    break;
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error En Eliminar : " + e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseClicked
    public void Limpiar(){
        txtId.setText(null);
        txtfecha.setText(null);
        txtRazon.setText(null);
        txtClave.setText(null);
    }
    public void eliminar(){            
        
        String bd = "ProyectoResidencia";
        String login = "CrLeon";
        String password = "jake12345";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + bd + ";integratedSecutiry=false";
        Connection connection = null;
        try {
            
            PreparedStatement ps;
            connection = DriverManager.getConnection(url, login, password);
            if(connection!=null){
                String Delete = "DELETE FROM pagos WHERE IdPago= ?";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                ps = connection.prepareStatement(Delete);
                ps.setInt(1,Integer.parseInt(txtId.getText())); 
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Eliminado Con  Exito");
                datos();
            }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error En Eliminar : " + ex.getMessage());
        }
    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        Limpiar();
        this.btnEliminar.setEnabled(false);
        this.lblclave.setEnabled(false);
        this.txtId.setEnabled(false);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
         try {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea Modificar un registro?",
                    "Registro de Materias", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            String mensaje = "";
            switch (resp) {
                case 0:
                    mensaje = "Escriba la clave del registro y los datos completos";
                    this.btnModificar.setEnabled(true);
                    this.lblclave.setEnabled(true);
                    this.txtId.setEnabled(true);
                    break;
                case 1:
                    mensaje = "OK";
                    break;
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar : " + e.getMessage());
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Actualizar();
        this.btnModificar.setEnabled(false);
        this.lblclave.setEnabled(false);
        this.txtId.setEnabled(false);
        Limpiar();
    }//GEN-LAST:event_btnModificarActionPerformed

    public void Actualizar()
    {
        String bd = "ProyectoResidencia";
        String login = "CrLeon";
        String password = "jake12345";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=" + bd + ";integratedSecutiry=false";
        Connection connection = null;
        try {
            
            PreparedStatement ps;
            connection = DriverManager.getConnection(url, login, password);
            if(connection!=null){
                String Update = "update pagos set Comprobante = ?, FechaPago = ?, RazonPago = ?, IdAlumno = ? WHERE IdPago= ?";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                ps = connection.prepareStatement(Update);
                Cargar_Archivo = new FileInputStream(lblLocalizador.getText().trim());
                ps.setBinaryStream(1, Cargar_Archivo); 
                ps.setString(2, txtfecha.getText().trim());
                ps.setString(3, txtRazon.getText().trim());
                ps.setInt(4, Integer.parseInt(txtClave.getText()));
                ps.setInt(5,Integer.parseInt(txtId.getText())); 
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Actualizado Con  Exito");
                datos();
            }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error al Actualizar: " + ex.getMessage());
        }
    }
    public void Fecha(){
        try{
            String dia = Integer.toString(jdate.getCalendar().get(Calendar.DAY_OF_MONTH));
            String mes = Integer.toString(jdate.getCalendar().get(Calendar.MONTH) + 1);
            String year = Integer.toString(jdate.getCalendar().get(Calendar.YEAR));
            String fecha = (year + "-" + mes + "-" + dia);
            txtfecha.setText(fecha);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Seleccione una fecha valida");
        }  
    }
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
            java.util.logging.Logger.getLogger(RegistroPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroPagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdate;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblLocalizador;
    private javax.swing.JLabel lblclave;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtRazon;
    private javax.swing.JTextField txtfecha;
    // End of variables declaration//GEN-END:variables
}
