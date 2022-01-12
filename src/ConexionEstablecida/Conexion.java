package ConexionEstablecida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/*
 *
 * @author Cesar Leon
 */
public class Conexion {
    
    static String bd = "ProyectoResidencia";
    static String login = "CrLeon";
    static String password = "jake12345";
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=" + bd+";integratedSecutiry=false";
    Connection connection = null;

    public void Conexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {
                JOptionPane.showMessageDialog(null,"Conexión a base de datos " + bd + " OK\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.toString());
        }
    }
    public Connection getConnection(){
        return connection;
    }
   
    public void desconectar(){
        try{
            System.out.println("Cerrando conexion");
            connection.close();
        }catch(Exception ex){}
    }
    public int Login(String User, String Pass){
        Integer resul=0;
        try{
            Statement ejecutor = connection.createStatement();
            //select password, tipo from Usuarios where nombre = 'Usr'
            ResultSet rs = ejecutor.executeQuery("Select '"+Pass+"' from Usuarios where nombre = '"+User+"'");
            
            if(rs.next())
            {              
                JOptionPane.showMessageDialog(null, "Bienvenido " +User.toString());
                resul =1;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Error verificar usuario/contraseña");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
        }
        return resul;
    }
    
}
