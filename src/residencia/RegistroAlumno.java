/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package residencia;

import ConexionEstablecida.Conexion;
import ConexionEstablecida.Tablas;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static residencia.Horarios.res;


/**
 *
 * @author Cesar Leon
 */
public class RegistroAlumno extends javax.swing.JFrame {

    public static VentanaP vp = new VentanaP();
    String bd = "ProyectoResidencia";
    String login = "CrLeon";
    String password = "jake12345";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=" + bd + ";integratedSecutiry=false";
    Connection connection = null;

    public RegistroAlumno() {
        initComponents();
        Datos();   
        this.btnEliminar.setEnabled(false);
        this.btnModificar.setEnabled(false);
        tblAlumnos.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent Mouse_evt){
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if(Mouse_evt.getClickCount()==2){
                    txtID.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),0).toString());
                    txtNombre.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),1).toString());
                    txtApePat.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),2).toString());
                    txtApeMat.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),3).toString());
                    txtEdad.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),4).toString());                   
                    txtDireccion.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),6).toString());
                    txtNumExt.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),7).toString());
                    txtNumInt.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),8).toString());
                    txtCol.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),9).toString());
                    txtMuni.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),10).toString());
                    txtTelefono.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),11).toString());
                    txtCE.setText(tblAlumnos.getValueAt(tblAlumnos.getSelectedRow(),12).toString());
                   
                }
            }
        });
    }
    public void Datos() {
        DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
        modelo.setRowCount(0);
        res = Tablas.Consulta("select * from Alumno");
        try {
            while (res.next()) {               
                Vector v = new Vector();
                v.add(res.getInt(1));
                v.add(res.getString(2));
                v.add(res.getString(3));
                v.add(res.getString(4));
                v.add(res.getInt(5));
                v.add(res.getString(6));
                v.add(res.getString(7));
                v.add(res.getInt(8));
                v.add(res.getInt(9));
                v.add(res.getString(10));
                v.add(res.getString(11));
                v.add(res.getString(12));
                v.add(res.getString(13));              
                modelo.addRow(v);
                tblAlumnos.setModel(modelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
    }
    public void Actualizar(){
        try{
            PreparedStatement pst;
            connection = DriverManager.getConnection(url, login, password);
            if(connection!=null){
                String Actualizar = "update Alumno set NombreAlum = ?, ApePatAlum = ?,ApeMatAlum = ?,EdadAlum = ?,SexoAlum = ?,NombreCalle = ?,NumExt = ?,NumInt = ?,Colonia = ?,Municipio = ?,Telefono = ?,CE=? WHERE IdAlumno= ?";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                pst = connection.prepareStatement(Actualizar);
                pst.setString(1, (txtNombre.getText().trim()));
                pst.setString(2, txtApePat.getText().trim());
                pst.setString(3, txtApeMat.getText().trim());
                pst.setInt(4, Integer.parseInt(txtEdad.getText().trim()));
                if (rbtnM.isSelected() == true) {
                    pst.setString(5,"M");
                } else if (rbtnF.isSelected() == true) {
                     pst.setString(5,"F");
                }
                pst.setString(6, txtDireccion.getText().trim());
                pst.setInt(7, Integer.parseInt(txtNumExt.getText().trim()));
                pst.setInt(8, Integer.parseInt(txtNumInt.getText().trim()));
                pst.setString(9, txtCol.getText().trim());
                pst.setString(10, txtMuni.getText().trim());              
                pst.setString(11, txtTelefono.getText().trim());              
                pst.setString(12, txtCE.getText().trim());  
                pst.setInt(13, Integer.parseInt(txtID.getText().trim()));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Modificado Con  Exito");
                Datos();
            }         
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error" + ex.toString());
        }
    }

    public void registro(){
       
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {
                
                PreparedStatement pst = connection.prepareStatement("insert into Alumno values(?,?,?,?,?,?,?,?,?,?,?,?,?)");               
                pst.setInt(1, Integer.parseInt(txtID.getText().trim()));
                pst.setString(2, (txtNombre.getText().trim()));
                pst.setString(3, txtApePat.getText().trim());
                pst.setString(4, txtApeMat.getText().trim());
                pst.setInt(5, Integer.parseInt(txtEdad.getText().trim()));
                if (rbtnM.isSelected() == true) {
                    pst.setString(6,"M");
                } else if (rbtnF.isSelected() == true) {
                     pst.setString(6,"F");
                }
                pst.setString(7, txtDireccion.getText().trim());
                pst.setInt(8, Integer.parseInt(txtNumExt.getText().trim()));
                pst.setInt(9, Integer.parseInt(txtNumInt.getText().trim()));
                pst.setString(10, txtCol.getText().trim());
                pst.setString(11, txtMuni.getText().trim());              
                pst.setString(12, txtTelefono.getText().trim());              
                pst.setString(13, txtCE.getText().trim());              
               
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registro Realizado");
                txtNombre.setText("");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.toString());
        }     
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApePat = new javax.swing.JTextField();
        txtApeMat = new javax.swing.JTextField();
        rbtnM = new javax.swing.JRadioButton();
        rbtnF = new javax.swing.JRadioButton();
        txtDireccion = new javax.swing.JTextField();
        txtNumExt = new javax.swing.JTextField();
        txtNumInt = new javax.swing.JTextField();
        txtCol = new javax.swing.JTextField();
        txtMuni = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCE = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Deleted = new javax.swing.JMenuItem();
        Update = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alumnos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        jLabel1.setText("Matricula");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido Materno");

        jLabel4.setText("Apellido Paterno");

        jLabel5.setText("Sexo");

        jLabel6.setText("Direccion");

        jLabel7.setText("Numero Exterior");

        jLabel8.setText("Numero Inteiror");

        jLabel9.setText("Colonia");

        jLabel10.setText("Numero Telefonico");

        jLabel11.setText("Municipio");

        jLabel12.setText("Correo Electronico");

        buttonGroup1.add(rbtnM);
        rbtnM.setText("Masculino");

        buttonGroup1.add(rbtnF);
        rbtnF.setText("Femenino");

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre", "Apellido Paterno", "Apellido Materno", "Edad", "Sexo", "Dirección", "Numero E", "Numero Int", "Colonia", "Municipio", "Telefono", "Correo Electronico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAlumnos);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Salir");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel13.setText("Edad");

        btnModificar.setText("Modificar Datos");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(304, 304, 304)
                                        .addComponent(rbtnM)))
                                .addGap(18, 18, 18)
                                .addComponent(rbtnF)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtApePat, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(290, 290, 290)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEdad)
                                            .addComponent(txtApeMat, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))))
                                .addGap(162, 162, 162)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel9))
                                    .addGap(17, 17, 17)))
                            .addComponent(jLabel6))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumExt, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtDireccion)
                            .addComponent(txtNumInt)
                            .addComponent(txtCol)
                            .addComponent(txtMuni)
                            .addComponent(txtTelefono)
                            .addComponent(txtCE))
                        .addGap(64, 64, 64))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnGuardar)
                        .addGap(42, 42, 42)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar)
                        .addGap(52, 52, 52)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar)
                        .addGap(52, 52, 52))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtApeMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rbtnM)
                                        .addComponent(rbtnF))
                                    .addComponent(jLabel5)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtApePat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(28, 28, 28))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNumExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNumInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))))
                        .addGap(18, 18, 18)
                        .addComponent(txtMuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnModificar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Opciones");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        Deleted.setText("Eliminar Registro");
        Deleted.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeletedMouseClicked(evt);
            }
        });
        Deleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletedActionPerformed(evt);
            }
        });
        jMenu1.add(Deleted);

        Update.setText("Modificar Registro");
        Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UpdateMouseClicked(evt);
            }
        });
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        jMenu1.add(Update);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        registro();
        Datos();
        limpiar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tblAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAlumnosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblAlumnosMouseClicked
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            PreparedStatement ps;
            connection = DriverManager.getConnection(url, login, password);
            if(connection!=null){
                String Delete = "DELETE FROM Alumno WHERE IdAlumno= ?";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
                ps = connection.prepareStatement(Delete);
                ps.setInt(1,Integer.parseInt(txtID.getText())); 
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Eliminado Con  Exito");
                Datos();
            }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error En Eliminar : " + ex.getMessage());
        }
        this.btnEliminar.setEnabled(false);
        limpiar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        vp.setVisible(true);
        this.hide();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void DeletedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeletedMouseClicked
        
    }//GEN-LAST:event_DeletedMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
       
    }//GEN-LAST:event_jMenu1MouseClicked

    private void UpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateMouseClicked
        
    }//GEN-LAST:event_UpdateMouseClicked

    private void DeletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletedActionPerformed
        try {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea Eliminar un registro?",
                    "Registro de Materias", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            String mensaje = "";
            switch (resp) {
                case 0:                    
                    mensaje = "Escriba la clave del alumno a eliminar";
                    this.btnEliminar.setEnabled(true);
                    this.btnModificar.setEnabled(false);
                    break;
                case 1:
                    mensaje = "OK";
                    break;
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error En Eliminar : " + e.getMessage());
        }
    }//GEN-LAST:event_DeletedActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        try {
            int resp = JOptionPane.showConfirmDialog(null, "¿Desea Modificar un registro?",
                    "Registro de Materias", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            String mensaje = "";
            switch (resp) {
                case 0:                    
                    mensaje = "seleccione del registro al alumno que desea modificar";
                    this.btnEliminar.setEnabled(false);
                    this.btnModificar.setEnabled(true);
                    break;
                case 1:
                    mensaje = "OK";
                    break;
            }
            JOptionPane.showMessageDialog(null, mensaje);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error En Eliminar : " + e.getMessage());
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Actualizar();
    }//GEN-LAST:event_btnModificarActionPerformed
    
    public void limpiar(){
        txtID.setText(null);
        txtNombre.setText(null);                
        txtApePat.setText(null);
        txtApeMat.setText(null);
        txtEdad.setText(null);       
        txtDireccion.setText(null);
        txtNumExt.setText(null);
        txtNumInt.setText(null);
        txtCol.setText(null);
        txtMuni.setText(null);
        txtTelefono.setText(null);
        txtCE.setText(null);        
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
            java.util.logging.Logger.getLogger(RegistroAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroAlumno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistroAlumno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Deleted;
    private javax.swing.JMenuItem Update;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtnF;
    private javax.swing.JRadioButton rbtnM;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTextField txtApeMat;
    private javax.swing.JTextField txtApePat;
    private javax.swing.JTextField txtCE;
    private javax.swing.JTextField txtCol;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMuni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumExt;
    private javax.swing.JTextField txtNumInt;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
