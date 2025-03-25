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
}
