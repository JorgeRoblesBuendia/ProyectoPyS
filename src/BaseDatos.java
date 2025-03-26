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
    Statement transaccion;//----
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





//clases de objetos por si se llega a usar 
class Proveedores {
    int id;
    String nombre,contacto,telefono,direccion,email;

    public Proveedores(int id, String nombre, String contacto, String telefono, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }
    
}
class Categorias {
    int id;
    String nombre,descripcion;

    public Categorias(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
}
class Productos {
    int id;
    String nombre ,descripcion;
    double precio_compra , precio_venta;
    int stock, stockMinimo,idCategoria,idProveedor;
    String codigoBarras;
    
    //fecha_vencimiento DATE,

    public Productos(int id, String nombre, String descripcion, double precio_compra, double precio_venta, int stock, int stockMinimo, int idCategoria, int idProveedor, String codigoBarras) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.codigoBarras = codigoBarras;
    }
}
class Empleados {
    int id;
    String nombre,direccion,telefono,email,puesto;

    public Empleados(int id, String nombre, String direccion, String telefono, String email, String puesto) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.puesto = puesto;
    }
    
}
class Ventas {
    int id;
    //fecha_hora DATETIME,
    int idCliente,idEmpleado;
    double total;
    String tipoPago;

    public Ventas(int id, int idCliente, int idEmpleado, double total, String tipoPago) {
        this.id = id;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.total = total;
        this.tipoPago = tipoPago;
    }

    
}
class DetallesVenta {
    int id,idVenta,idProducto,cantidad;
    double precioUnitario,subtotal;

    public DetallesVenta(int id, int idVenta, int idProducto, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
    
    
}
class Compras {
    int id;
//    fecha DATE,
    int idProveedor;
    double total;
    String estado;

    public Compras(int id, int idProveedor, double total, String estado) {
        this.id = id;
        this.idProveedor = idProveedor;
        this.total = total;
        this.estado = estado;
    }
}
class DetallesCompra {
    int id, idCompra,idProducto,cantidad;
    double precioUnitario,subtotal;

    public DetallesCompra(int id, int idCompra, int idProducto, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
    
}
class Inventario {
    int id,idProducto,cantidad;
    //fecha_movimiento DATETIME,
    String tipoMovimiento; // 'entrada' o 'salida'
    String motivo;

    public Inventario(int id, int idProducto, int cantidad, String tipoMovimiento, String motivo) {
        this.id = id;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.tipoMovimiento = tipoMovimiento;
        this.motivo = motivo;
    }
    

}
class Promociones {
    int id,idProducto;
    String descripcion;
    double descuento;
    //fecha_inicio DATE,
    //fecha_fin DATE,

    public Promociones(int id, int idProducto, String descripcion, double descuento) {
        this.id = id;
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.descuento = descuento;
    }
}
