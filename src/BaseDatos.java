import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jorge
 */
//jdbc:mysql://belbr9kwb1stmqbvm6si-mysql.services.clever-cloud.com:3306/belbr9kwb1stmqbvm6si?zeroDateTimeBehavior=CONVERT_TO_NULL
public class BaseDatos {
    
    Connection conexion;
    Statement transaccion;
    ResultSet cursor;
    
    
    
    
    public BaseDatos(Connection conexion, Statement transaccion, ResultSet cursor) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");            
            this.conexion = conexion;
            this.transaccion = transaccion;
            this.cursor = cursor;
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public BaseDatos() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://belbr9kwb1stmqbvm6si-mysql.services.clever-cloud.com:3306/belbr9kwb1stmqbvm6si?zeroDateTimeBehavior=CONVERT_TO_NULL",
                    "uakgprfg2wghdbl8","GbNuYm3g8kkcG1jRi14n");
            transaccion=conexion.createStatement();
            
            System.out.println("Nos conectamos con exito");
        }catch(SQLException e){
            System.out.println("Algo salio mal");
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insertarLogin(String n,String cor,String c){
        try {
            String SQL="INSERT INTO `Gerente` (`Nombre`,`Correo`, `Contrasena`) VALUES ('%Nom%', '%COR%','%CON%');";
            SQL=SQL.replaceAll("%Nom%",n);
            SQL=SQL.replaceAll("%COR%",cor);
            SQL=SQL.replaceAll("%CON%",c);
            transaccion.execute(SQL);
        } catch (SQLException ex) {return false;}
        System.out.println("insertamos");
        return true;
    }  
    public int getLogin(String correo, String contrasena){
        String A[] = new String[2];
        int idEmpleado = -1;
        try {
            //SELECT * FROM `Login` WHERE Correo like 'A' and Constrasena like 'A';
            String SQL="SELECT * FROM `Gerente` WHERE Correo LIKE '"+correo+"' AND Contrasena LIKE '"+contrasena+"'";
            cursor= transaccion.executeQuery(SQL);
            if(cursor.next()){
                idEmpleado = cursor.getInt(1);
                A[0]=cursor.getString(2);
                A[1]=cursor.getString(3);
                if(A[0].equals(correo) && A[1].equals(contrasena)){
                    idEmpleado = cursor.getInt(1);
                };
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idEmpleado;
    }
    public boolean buscarLogin(String correo, String contrasena){//validar login
        String A[] = new String[2];
        boolean bandera = false;
        try {
            String SQL="SELECT * FROM `Gerente` WHERE Correo LIKE '"+correo+"' AND Contrasena LIKE '"+contrasena+"'";
            cursor= transaccion.executeQuery(SQL);
            if(cursor.next()){
                A[0]=cursor.getString(2);
                A[1]=cursor.getString(3);
                if(A[0].equals(correo) && A[1].equals(contrasena))bandera=true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
}
