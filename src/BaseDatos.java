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
            String SQL="SELECT * FROM `Gerente` WHERE Correo = '"+correo+"' AND Contrasena = '"+contrasena+"'";
            cursor= transaccion.executeQuery(SQL);
            if(cursor.next()){
                A[0]=cursor.getString(3);
                A[1]=cursor.getString(4);
                if(A[0].equals(correo) && A[1].equals(contrasena))bandera=true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
      //Productoos
    /**
     * Inserta un producto en la base de datos.
     * 
     * @param p Producto que se desea insertar. Debe contener los valores necesarios
     *          como nombre, descripción, código de barras, precio de compra, precio de venta,
     *          stock, stock mínimo, id de categoría y id de proveedor.
     * @return true si la operación fue exitosa, false en caso de error (por ejemplo, una SQLException).
     * 
     * Este método construye una consulta SQL basada en los atributos del objeto Producto
     * proporcionado, reemplazando los valores correspondientes en la consulta y ejecutándola.
     */
    public boolean insertarProducto(Producto p) {
        try {
            /*
            INSERT INTO `Productos` (`idArticulo`, `Nombre`, `Descripcion`, `CodigoBarras`, `PrecioCompra`, `PrecioVenta`, `Stock`, `StockMinimo`, `IdCategoria`, `IdProveedor`) 
            VALUES (NULL, 'Producto', 'Descripción', 'Código de Barras', 'Precio de Compra', 'Precio de Venta', 'Stock', 'Stock Mínimo', 'Categoría', 'Proveedor');
            */
            String SQL = "INSERT INTO `Productos` (`Nombre`, `Descripcion`, `CodigoBarras`, `PrecioCompra`, `PrecioVenta`, `Stock`, `StockMinimo`, `IdCategoria`, `IdProveedor`) "
                       + "VALUES ('%Nombre%', '%Descripcion%', '%CodigoBarras%', '%PrecioCompra%', '%PrecioVenta%', '%Stock%', '%StockMinimo%', '%IdCategoria%', '%IdProveedor%');";

            SQL = SQL.replaceAll("%Nombre%", p.nombre);
            SQL = SQL.replaceAll("%Descripcion%", p.descripcion);
            SQL = SQL.replaceAll("%CodigoBarras%", p.codigoBarras);
            SQL = SQL.replaceAll("%PrecioCompra%", String.valueOf(p.precioCompra));
            SQL = SQL.replaceAll("%PrecioVenta%", String.valueOf(p.precioVenta));
            SQL = SQL.replaceAll("%Stock%", String.valueOf(p.stock));
            SQL = SQL.replaceAll("%StockMinimo%", String.valueOf(p.stockMinimo));
            SQL = SQL.replaceAll("%IdCategoria%", String.valueOf(p.idCategoria));
            SQL = SQL.replaceAll("%IdProveedor%", String.valueOf(p.idProveedor));

            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al insertar producto: " + ex.getMessage());
            return false;
        }
        System.out.println("Producto insertado exitosamente");
        return true;
    }
    /**
     * Busca un producto en la base de datos basado en su código de barras.
     * 
     * @param codigo Código de barras del producto que se desea buscar.
     * @return El valor entero asociado al producto (por ejemplo, su identificador)
     *         o 0 si no se encuentra el producto o ocurre un error.
     * 
     * Este método utiliza una consulta SQL para buscar un producto por su código de barras.
     * Si se encuentra un resultado, se extrae un entero desde el tercer campo (columna).
     */
    public int buscarProducto(String codigo){//validar login
        int cd=0;
        
        try {
            String SQL="SELECT * FROM `Productos` WHERE CodigoBarras LIKE '"+codigo+"'";
            cursor= transaccion.executeQuery(SQL);
            if(cursor.next()){
                cd=cursor.getInt(3);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cd;
    }
    /**
     * Busca un producto en la base de datos basado en su código de barras y asigna los valores encontrados
     * a un objeto Producto proporcionado.
     * 
     * @param codigo Código de barras del producto que se desea buscar.
     * @param p Objeto Producto que recibirá los valores encontrados en la base de datos.
     * @return El objeto Producto con los atributos actualizados si se encuentra el producto,
     *         o el mismo objeto con valores sin cambios si no se encuentra o ocurre un error.
     * 
     * Este método realiza una consulta SQL para buscar un producto por su código de barras.
     * Si encuentra un resultado, asigna los valores correspondientes al objeto Producto proporcionado.
     */
    public Producto buscarProducto(String codigo, Producto p) {
    //Producto producto = null; // Inicializamos el objeto como null.

    try {
        String SQL = "SELECT * FROM `Productos` WHERE CodigoBarras = '" + codigo+"'";
        cursor = transaccion.executeQuery(SQL);

        if (cursor.next()) {
            //p = new Producto(); 
            p.id = cursor.getInt("idArticulo");
            p.nombre = cursor.getString("Nombre");
            p.descripcion = cursor.getString("Descripcion");
            p.codigoBarras = cursor.getString("CodigoBarras");
            p.precioCompra = cursor.getDouble("PrecioCompra");
            p.precioVenta = cursor.getDouble("PrecioVenta");
            p.stock = cursor.getInt("Stock");
            p.stockMinimo = cursor.getInt("StockMinimo");
            p.idCategoria = cursor.getInt("IdCategoria");
            p.idProveedor = cursor.getInt("IdProveedor");
        }

    } catch (SQLException ex) {
        Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, "Error al buscar el producto", ex);
    }

        return p; 
    }

    public ArrayList<String[]> mostrarProductosCaja(){
        ArrayList <String[]> resultado= new ArrayList ();
        try {
            
            //"SELECT * FROM `Productos` WHERE idEmpresa= "+E
            String SQL="SELECT * FROM Productos";
            cursor= transaccion.executeQuery(SQL);
            if(cursor.next()){
                do{
                    String[] al = {
                        cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),  
                        cursor.getString(5),cursor.getString(6), cursor.getString(7),// cursor.getString(8),  
                        // cursor.getString(9),cursor.getString(10), cursor.getString(11)  
                        cursor.getString(8),cursor.getString(9),cursor.getString(10)
                    };
                    resultado.add(al);
                }while(cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public boolean eliminarProducto(String id){
        try {
            String SQL="DELETE FROM Productos WHERE `idProducto` ='%ID%' ";
            SQL = SQL.replaceAll("%ID%", id);
            transaccion.execute(SQL);
        } catch (SQLException ex) {
            return false;
        }
        return true;
        
    }
    public boolean actualizarProductos(String codigoBarras, String nombre, String descripcion, String cantidad, String precio) {
        try {
            
            String SQL = "UPDATE `Productos` SET `Cantidad` = Cantidad + %Cantidad%, `Nombre` = '%Nombre%', " +
                         "`Descripcion` = '%Descripcion%', `Precio` = '%Precio%' " +
                         "WHERE `CodigoBarras` = '%CodigoBarras%' ";

            // Reemplazar los marcadores con los valores correspondientes
            SQL = SQL.replaceAll("%Cantidad%", cantidad);
            SQL = SQL.replaceAll("%Nombre%", nombre);
            SQL = SQL.replaceAll("%Descripcion%", descripcion);
            SQL = SQL.replaceAll("%Precio%", precio);
            SQL = SQL.replaceAll("%CodigoBarras%", codigoBarras);

            // Ejecutar la consulta SQL
            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el producto: " + ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean actualizarProductos(Producto p, String campo, String nuevoValor) {
        try {
         
            String SQL = "UPDATE `Productos` SET `" + campo + "` = '%NuevoValor%' WHERE `CodigoBarras` = '%CodigoBarras%'";

            SQL = SQL.replaceAll("%NuevoValor%", nuevoValor);
            SQL = SQL.replaceAll("%CodigoBarras%", p.codigoBarras);

            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el producto: " + ex.getMessage());
            return false;
        }
        return true;
    }
public boolean actualizarProductos(Producto p) {
    try {
        /*String SQL = "UPDATE `Productos` SET " + "`nombre` = '" + p.nombre + "', " +"`descripcion` = '" + p.descripcion + "', " +
                     "`precioCompra` = " + p.precioCompra + ", " +"`precioVenta` = " + p.precioVenta + ", " +
                     "`stock` = " + p.stock + ", " +"`stockMinimo` = " + p.stockMinimo + ", " +
                     "`idCategoria` = " + p.idCategoria + ", " +"`idProveedor` = " + p.idProveedor + ", " +
                     "`codigoBarras` = '" + p.codigoBarras + "', " +"`fechaVencimiento` = '" + p.fechaVencimiento + "' " +
                     "WHERE `codigoBarras` = '" + p.codigoBarras + "'";*/
        String SQL = "UPDATE `Productos` SET " + "`nombre` = '" + p.nombre + "', " +"`descripcion` = '" + p.descripcion + "', " +
                     "`precioCompra` = " + p.precioCompra + ", " +"`precioVenta` = " + p.precioVenta + ", " +
                     "`stock` = " + p.stock + ", " +"`stockMinimo` = " + p.stockMinimo + ", " +
                     "`idCategoria` = " + p.idCategoria + ", " +"`idProveedor` = " + p.idProveedor + ", "+
                     "`codigoBarras` = '" + p.codigoBarras + "' " +
                     "WHERE `idProducto` = '" + p.id + "'";

        // Imprimir la consulta SQL para depuración
        System.out.println(SQL);

        // Ejecutar la consulta SQL
        transaccion.execute(SQL);
    } catch (SQLException ex) {
        System.out.println("Error al actualizar el producto: " + ex.getMessage());
        return false;
    }
    return true;
}



    
    
    //-----------------------------------------Empleado
    public boolean insertarEmpleado(Empleado e) {
        try {
            /*
            INSERT INTO `Empleados` (`idEmpleado`, `Nombre`, `Direccion`, `Telefono`, `Email`, `Puesto`) 
            VALUES (NULL, 'Nombre', 'Direccion', 'Telefono', 'Email', 'Puesto');
            */
            String SQL = "INSERT INTO `Empleados` (`idEmpleado`, `Nombre`, `Direccion`, `Telefono`, `Email`, `Puesto`) "
                       + "VALUES (NULL, '%Nombre%', '%Direccion%', '%Telefono%', '%Email%', '%Puesto%');";

            SQL = SQL.replaceAll("%Nombre%", e.nombre);
            SQL = SQL.replaceAll("%Direccion%", e.direccion);
            SQL = SQL.replaceAll("%Telefono%", e.telefono);
            SQL = SQL.replaceAll("%Email%", e.email);
            SQL = SQL.replaceAll("%Puesto%", e.puesto);

            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al insertar empleado: " + ex.getMessage());
            return false;
        }
        System.out.println("Empleado insertado exitosamente");
        return true;
        }
        public int buscarEmpleado(String email) { 
        int id = 0;

        try {
            String SQL = "SELECT * FROM `Empleados` WHERE Email LIKE '" + email + "'";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                id = cursor.getInt("idEmpleado");
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public Empleado buscarEmpleado(String email, Empleado e) {
        try {
            String SQL = "SELECT * FROM `Empleados` WHERE Email LIKE '" + email + "'";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                e.id = cursor.getInt("idEmpleado");
                e.nombre = cursor.getString("Nombre");
                e.direccion = cursor.getString("Direccion");
                e.telefono = cursor.getString("Telefono");
                e.email = cursor.getString("Email");
                e.puesto = cursor.getString("Puesto");
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, "Error al buscar el empleado", ex);
        }
        return e;
    }

    public boolean eliminarEmpleado(String email) {
        try {
            String SQL = "DELETE FROM `Empleados` WHERE `Email` = '%Email%'";
            SQL = SQL.replaceAll("%Email%", email);
            transaccion.execute(SQL);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    public boolean actualizarEmpleado(Empleado e, String campo, String nuevoValor) {
        try {
            /*
            Actualiza un campo específico de la tabla `Empleados` basado en el ID del empleado.
            */
            // Construir la consulta SQL de forma dinámica según el campo especificado
            String SQL = "UPDATE `Empleados` SET `" + campo + "` = '%NuevoValor%' WHERE `idEmpleado` = %IdEmpleado%";

            // Reemplazar los marcadores con los valores correspondientes
            SQL = SQL.replaceAll("%NuevoValor%", nuevoValor);
            SQL = SQL.replaceAll("%IdEmpleado%", String.valueOf(e.id));

            // Ejecutar la consulta SQL
            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el empleado: " + ex.getMessage());
            return false;
        }
        return true;
    }


    public ArrayList<String[]> mostrarEmpleados() {
        ArrayList<String[]> resultado = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM `Empleados`";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                do {
                    String[] datos = {cursor.getString("Nombre"), cursor.getString("Direccion"), 
                                      cursor.getString("Telefono"), cursor.getString("Email")};
                    resultado.add(datos);
                } while (cursor.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }


    
    
    //-----------------------------------ventaaaa
    
    

    
    
    
    
    
    //--------------------------------------Provedores
    public boolean insertarProveedor(Proveedores p) {
    try {
            String SQL = "INSERT INTO `Proveedores` (`idProveedor`, `Nombre`, `contacto`, `telefono`, `direccion`, `email`) " +
                         "VALUES (NULL, '%Nombre%', '%Contacto%', '%Telefono%', '%Direccion%', '%Email%');";
            SQL = SQL.replaceAll("%Nombre%", p.nombre);
            SQL = SQL.replaceAll("%Contacto%", p.contacto);
            SQL = SQL.replaceAll("%Telefono%", p.telefono);
            SQL = SQL.replaceAll("%Direccion%", p.direccion);
            SQL = SQL.replaceAll("%Email%", p.email);

            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al insertar proveedor: " + ex.getMessage());
            return false;
        }
        System.out.println("Proveedor insertado exitosamente");
        return true;
    }

    public int buscarProveedor(String email) {
        int id = -1;

        try {
            String SQL = "SELECT * FROM `Proveedores` WHERE `email` = '" + email + "'";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                id = cursor.getInt("idProveedor");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public Proveedores buscarProveedor(String email, Proveedores p) {
        try {
            String SQL = "SELECT * FROM `Proveedores` WHERE `email` LIKE '" + email + "'";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                p.id = cursor.getInt("idProveedor");
                p.nombre = cursor.getString("nombreEmpresa");
                p.contacto = cursor.getString("contacto");
                p.telefono = cursor.getString("telefono");
                p.direccion = cursor.getString("direccion");
                p.email = cursor.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, "Error al buscar el proveedor", ex);
        }
        return p;
    }

    public boolean eliminarProveedor(String email) {
        try {
            String SQL = "DELETE FROM `Proveedores` WHERE `email` = '%Email%'";
            SQL = SQL.replaceAll("%Email%", email);
            transaccion.execute(SQL);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    public boolean actualizarProveedor(Proveedores p, String campo, String nuevoValor) {
        try {
            String SQL = "UPDATE `Proveedores` SET `" + campo + "` = '%NuevoValor%' WHERE `idProveedor` = %IdProveedor%";
            SQL = SQL.replaceAll("%NuevoValor%", nuevoValor);
            SQL = SQL.replaceAll("%IdProveedor%", String.valueOf(p.id));

            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el proveedor: " + ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean actualizarProveedor(Proveedores p,int n) {
    try {
        String SQL = "UPDATE `Proveedores` SET " +
                     "`nombre` = '%NombreEmpresa%', " +
                     "`contacto` = '%Contacto%', " +
                     "`telefono` = '%Telefono%', " +
                     "`direccion` = '%Direccion%', " +
                     "`email` = '%Email%' " +
                     "WHERE `idProveedor` = %IdProveedor%";

        // Reemplazar los valores en la consulta
        SQL = SQL.replaceAll("%NombreEmpresa%", p.nombre);
        SQL = SQL.replaceAll("%Contacto%", p.contacto);
        SQL = SQL.replaceAll("%Telefono%", p.telefono);
        SQL = SQL.replaceAll("%Direccion%", p.direccion);
        SQL = SQL.replaceAll("%Email%", p.email);
        SQL = SQL.replaceAll("%IdProveedor%", String.valueOf(n));

        // Ejecutar la consulta
        transaccion.execute(SQL);
        System.out.println(SQL);
    } catch (SQLException ex) {
        System.out.println("Error al actualizar el proveedor: " + ex.getMessage());
        return false;
    }
    return true;
}


    public ArrayList<String[]> mostrarProveedores() {
        ArrayList<String[]> resultado = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM `Proveedores`";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                do {
                    String[] datos = {cursor.getString("nombre"),cursor.getString("telefono"), 
                                      cursor.getString("email"),cursor.getString("direccion")};
                    resultado.add(datos);
                } while (cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    
//--------------------------servicios
public boolean insertarServicio(Servicio s) {
    try {
        String SQL = "INSERT INTO `Servicios` (`idServicio`, `nombre`, `descripcion`, `precio`, `categoriaServicio`, `activo`) " +
                     "VALUES (NULL, '%Nombre%', '%Descripcion%', '%Precio%', '%CategoriaServicio%', '%Activo%');";
        SQL = SQL.replaceAll("%Nombre%", s.nombre);
        SQL = SQL.replaceAll("%Descripcion%", s.descripcion);
        SQL = SQL.replaceAll("%Precio%", String.valueOf(s.precio));
        SQL = SQL.replaceAll("%CategoriaServicio%", s.categoriaServicio);
        SQL = SQL.replaceAll("%Activo%", String.valueOf(s.activo));

        transaccion.execute(SQL);
        System.out.println(SQL);
    } catch (SQLException ex) {
        System.out.println("Error al insertar servicio: " + ex.getMessage());
        return false;
    }
    System.out.println("Servicio insertado exitosamente");
    return true;
}

public int buscarServicio(String nombre) {
    int id = -1;

    try {
        String SQL = "SELECT * FROM `Servicios` WHERE `nombre` LIKE '" + nombre + "'";
        cursor = transaccion.executeQuery(SQL);

        if (cursor.next()) {
            id = cursor.getInt("idServicio");
        }
    } catch (SQLException ex) {
        Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
    }
    return id;
}

public Servicio buscarServicio(String nombre, Servicio s) {
    try {
        String SQL = "SELECT * FROM `Servicios` WHERE `nombre` LIKE '" + nombre + "'";
        cursor = transaccion.executeQuery(SQL);

        if (cursor.next()) {
            s.idServicio = cursor.getInt("idServicio");
            s.nombre = cursor.getString("nombre");
            s.descripcion = cursor.getString("descripcion");
            s.precio = cursor.getDouble("precio");
            s.categoriaServicio = cursor.getString("categoriaServicio");
            s.activo = cursor.getInt("activo");
        }
    } catch (SQLException ex) {
        Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, "Error al buscar el servicio", ex);
    }
    return s;
}

public boolean eliminarServicio(String nombre) {
    try {
        String SQL = "DELETE FROM `Servicios` WHERE `nombre` = '%Nombre%'";
        SQL = SQL.replaceAll("%Nombre%", nombre);
        transaccion.execute(SQL);
    } catch (SQLException ex) {
        return false;
    }
    return true;
}

public boolean actualizarServicio(Servicio s, String campo, String nuevoValor) {
    try {
        String SQL = "UPDATE `Servicios` SET `" + campo + "` = '%NuevoValor%' WHERE `idServicio` = %IdServicio%";
        SQL = SQL.replaceAll("%NuevoValor%", nuevoValor);
        SQL = SQL.replaceAll("%IdServicio%", String.valueOf(s.idServicio));

        transaccion.execute(SQL);
        System.out.println(SQL);
    } catch (SQLException ex) {
        System.out.println("Error al actualizar el servicio: " + ex.getMessage());
        return false;
    }
    return true;
}
public boolean actualizarServicio(Servicio s,int s2) {
    try {
        String SQL = "UPDATE `Servicios` SET " +
                     "`nombre` = '%Nombre%', " +
                     "`descripcion` = '%Descripcion%', " +
                     "`precio` = %Precio%, " +
                     "`categoriaServicio` = '%CategoriaServicio%', " +
                     "`activo` = %Activo% " +
                     "WHERE `idServicio` = %IdServicio%";

        // Reemplazar los valores en la consulta
        SQL = SQL.replaceAll("%Nombre%", s.nombre);
        SQL = SQL.replaceAll("%Descripcion%", s.descripcion);
        SQL = SQL.replaceAll("%Precio%", s.precio+"");
        SQL = SQL.replaceAll("%CategoriaServicio%", s.categoriaServicio);
        SQL = SQL.replaceAll("%Activo%", String.valueOf(s.activo));
        SQL = SQL.replaceAll("%IdServicio%", String.valueOf(s2));

        // Ejecutar la consulta
        transaccion.execute(SQL);
        System.out.println(SQL);
    } catch (SQLException ex) {
        System.out.println("Error al actualizar el servicio: " + ex.getMessage());
        return false;
    }
    return true;
}
public ArrayList<String[]> mostrarServicios() {
    ArrayList<String[]> resultado = new ArrayList<>();
    try {
        String SQL = "SELECT * FROM `Servicios`";
        cursor = transaccion.executeQuery(SQL);

        if (cursor.next()) {
            do {
                String[] datos = {cursor.getString("nombre"), cursor.getString("descripcion"), 
                                  cursor.getBigDecimal("precio").toString(), cursor.getString("categoriaServicio"), 
                                  String.valueOf(cursor.getBoolean("activo"))};
                resultado.add(datos);
            } while (cursor.next());
        }
    } catch (SQLException ex) {
        Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
    }
    return resultado;
}
//---------------------------------categorias----------------------------------------------
    public boolean insertarCategoria(Categorias c) {
        try {
            String SQL = "INSERT INTO `Categorias` (`idCategoria`, `nombre`, `descripcion`) " +
                         "VALUES (NULL, '%Nombre%', '%Descripcion%');";
            SQL = SQL.replaceAll("%Nombre%", c.nombre);
            SQL = SQL.replaceAll("%Descripcion%", c.descripcion);

            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al insertar categoría: " + ex.getMessage());
            return false;
        }
        System.out.println("Categoría insertada exitosamente");
        return true;
    }

    public int buscarCategoria(String nombre) {
        int id = -1;

        try {
            String SQL = "SELECT * FROM `Categorias` WHERE `nombre` LIKE '" + nombre + "'";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                id = cursor.getInt("idCategoria");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public Categorias buscarCategoria(String nombre, Categorias c) {
        try {
            String SQL = "SELECT * FROM `Categorias` WHERE `nombre` LIKE '" + nombre + "'";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                c.id = cursor.getInt("idCategoria");
                c.nombre = cursor.getString("nombre");
                c.descripcion = cursor.getString("descripcion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, "Error al buscar la categoría", ex);
        }
        return c;
    }


    public boolean eliminarCategoria(String nombre) {
        try {
            String SQL = "DELETE FROM `Categorias` WHERE `nombre` = '%Nombre%'";
            SQL = SQL.replaceAll("%Nombre%", nombre);
            transaccion.execute(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al eliminar categoría: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean actualizarCategoria(Categorias c, String campo, String nuevoValor) {
        try {
            String SQL = "UPDATE `Categorias` SET `" + campo + "` = '%NuevoValor%' WHERE `idCategoria` = %IdCategoria%";
            SQL = SQL.replaceAll("%NuevoValor%", nuevoValor);
            SQL = SQL.replaceAll("%IdCategoria%", String.valueOf(c.id));

            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la categoría: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean actualizarCategoria(Categorias c, int idCategoria) {
        try {
            String SQL = "UPDATE `Categorias` SET " +
                         "`nombre` = '%Nombre%', " +
                         "`descripcion` = '%Descripcion%' " +
                         "WHERE `idCategoria` = %IdCategoria%";

            SQL = SQL.replaceAll("%Nombre%", c.nombre);
            SQL = SQL.replaceAll("%Descripcion%", c.descripcion);
            SQL = SQL.replaceAll("%IdCategoria%", String.valueOf(idCategoria));

            transaccion.execute(SQL);
            System.out.println(SQL);
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la categoría: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<String[]> mostrarCategorias() {
        ArrayList<String[]> resultado = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM `Categorias`";
            cursor = transaccion.executeQuery(SQL);

            if (cursor.next()) {
                do {
                    String[] datos = {
                        cursor.getString("idCategoria"),  // ID de la categoría
                        cursor.getString("nombre"),      // Nombre de la categoría
                        cursor.getString("descripcion")  // Descripción de la categoría
                    };
                    resultado.add(datos);
                } while (cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
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

    public Categorias() {
    }
    
}
class Producto {
    int id;
    String nombre ,descripcion;
    double precioCompra , precioVenta;
    int stock, stockMinimo,idCategoria,idProveedor;
    String codigoBarras;
    
    //fecha_vencimiento DATE,

    public Producto() {
    }

    public Producto(int id, String nombre, String descripcion, double precioCompra, double precioVenta, int stock, int stockMinimo, int idCategoria, int idProveedor, String codigoBarras) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.codigoBarras = codigoBarras;
    }
    
    
   
}
class Empleado{
    int id;
    String nombre,direccion,telefono,email,puesto;

    public Empleado(int id, String nombre, String direccion, String telefono, String email, String puesto) {
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

class Servicio {
    int idServicio;
    String nombre;
    String descripcion;
    double precio; // Para manejar valores decimales
    String categoriaServicio;
    int activo; // Para manejar el estado activo/inactivo

    // Constructor vacío
    public Servicio() {}

    // Constructor con parámetros
    public Servicio(int idServicio, String nombre, String descripcion, double precio, String categoriaServicio, int activo) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoriaServicio = categoriaServicio;
        this.activo = activo;
    }

}
