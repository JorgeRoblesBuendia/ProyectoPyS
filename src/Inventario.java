import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author edwin
 */
public class Inventario extends javax.swing.JFrame {
    BaseDatos bd;DefaultTableModel m;

    /**
     * Creates new form Inventario
     */
    public Inventario() {
        initComponents();
        jMenuItem_Servicios.setText("<html><center>Reporte<br>de ventas</center></html>");
        setLocationRelativeTo(null);

        bd = new BaseDatos();
        try {
            if (bd.conexion.isClosed()) {
                System.out.println("Noo!!!. Se cerro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        m=(DefaultTableModel) tblInventario.getModel();
        MostrarCmb();MostrarCmbCat();actualizarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventario = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        JLabelCorreoMostrar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem_Servicios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(860, 550));
        setSize(new java.awt.Dimension(860, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(860, 500));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Descripcion", "Stock", "Codigo de barras", "Categoria", "Proveedor"
            }
        ));
        jScrollPane1.setViewportView(tblInventario);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 780, 210));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 810, 230));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setBackground(new java.awt.Color(204, 0, 0));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sync.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        btnBuscar.setBackground(new java.awt.Color(204, 0, 0));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search2.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(204, 0, 0));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo-producto.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 422, 810, 50));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("GESTION INVENTARIO");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 0, 860, -1));

        cmbCategoria.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jPanel4.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 190, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Categoria:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        cmbProveedor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedorActionPerformed(evt);
            }
        });
        jPanel4.add(cmbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 190, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Proveedor:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 120, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 380, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 70, -1));

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 380, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descripcion:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 140, -1));

        txtStock.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 140, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Stock minimo:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 130, -1));

        txtCodigoBarras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(txtCodigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, 140, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Codigo barras:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 150, -1));

        JLabelCorreoMostrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelCorreoMostrar.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(JLabelCorreoMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 24, 210, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("GERENTE");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(775, 0, 70, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 510));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2849812_menu_multimedia_bars_media_icon.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/353416_home_icon.png"))); // NOI18N
        jMenuItem1.setText("Regresar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png"))); // NOI18N
        jMenuItem4.setText("Empleados");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        jMenuItem3.setText("Categorias");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/supplier.png"))); // NOI18N
        jMenuItem2.setText("Proveedor");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png"))); // NOI18N
        jMenuItem5.setText("Almacen");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem_Servicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sales.png"))); // NOI18N
        jMenuItem_Servicios.setText("Reporte");
        jMenuItem_Servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ServiciosActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_Servicios);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
                    JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        VentanaMenu v=new VentanaMenu();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        
        //locaaaaal
        
        String nombre=txtNombre.getText(), descripcion=txtDescripcion.getText(), stock=txtStock.getText(), 
                codigoBarras=txtCodigoBarras.getText();
        int categoria = cmbCategoria.getSelectedIndex();
        int proveedor = cmbProveedor.getSelectedIndex();
        
        Categorias c=new Categorias(); Proveedores pr=new Proveedores();
        c=bd.buscarCategoria(cmbCategoria.getItemAt(categoria), c);
        pr=bd.buscarProvedor(cmbProveedor.getItemAt(proveedor), pr);
        
        
        Producto p = new Producto( nombre, 
        descripcion,      Integer.parseInt(stock),c.id,
    pr.id,codigoBarras);
        if(bd.insertarProducto(p)){
            JOptionPane.showMessageDialog(this, "Agregamos con exito");
        }else{
            JOptionPane.showMessageDialog(this, "Error al Registrar");
        }
        
                actualizarTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String nombreBuscar = txtCodigoBarras.getText();
        if (nombreBuscar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Introduce un codigo de barras para buscar.");
            return;
        }
        //LOCAAAAL
        Producto p=new Producto();
                p= bd.buscarProducto(nombreBuscar, p);
                txtNombre.setText(p.nombre);
               txtDescripcion.setText(p.descripcion);
               txtStock.setText(p.stockMinimo+"");
               txtCodigoBarras.setText(p.codigoBarras);
               
        for (int i = 0; i < cmbCategoria.getItemCount(); i++) { 
            if(cmbCategoria.getItemAt(i).equals(bd.buscarCategoria(p.idCategoria))){
                cmbCategoria.setSelectedIndex(i);
            } 
        }
        for (int i = 0; i < cmbProveedor.getItemCount(); i++) {
            if(cmbProveedor.getItemAt(i).equals(bd.buscarProveedor(p.idProveedor))){
                cmbProveedor.setSelectedIndex(i);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String nombre = txtNombre.getText();
    String descripcion = txtDescripcion.getText();
    String stock = txtStock.getText();
    String codigoBarras = txtCodigoBarras.getText().trim(); // ← Buscar por código de barras
    int categoria = cmbCategoria.getSelectedIndex();
    int proveedor = cmbProveedor.getSelectedIndex();

    // Verificamos que el código de barras no esté vacío
    if (codigoBarras.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Introduce un código de barras para actualizar.");
        return;
    }

    // Buscar la categoría y proveedor seleccionados
    Categorias c = new Categorias();
    Proveedores pr = new Proveedores();
    c = bd.buscarCategoria(cmbCategoria.getItemAt(categoria), c);
    pr = bd.buscarProvedor(cmbProveedor.getItemAt(proveedor), pr);

    // Buscar el producto por código de barras (no por nombre)
    Producto p = new Producto();
    p = bd.buscarProducto(codigoBarras, p);

    // Si no se encontró el producto
    if (p.id == 0) {
        JOptionPane.showMessageDialog(this, "No se encontró un producto con ese código de barras.");
        return;
    }

    // Actualizar los datos del producto
    p.descripcion = descripcion;
    p.nombre = nombre;
    p.stockMinimo = Integer.parseInt(stock);
    p.codigoBarras = codigoBarras;
    p.idCategoria = c.id;
    p.idProveedor = pr.id;

    // Enviar actualización a la base de datos
    if (bd.actualizarProductos(p)) {
        JOptionPane.showMessageDialog(this, "Actualizamos con éxito.");
    } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar el producto.");
    }

    actualizarTabla();
    limpiarCampo();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
     String codigoBarras = txtCodigoBarras.getText();

    if (codigoBarras.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Introduce un código de barras para borrar.");
        return;
    }

    Producto p = new Producto();
    p = bd.buscarProducto(codigoBarras, p); // ← usa el código de barras, no el nombre

    if (p.id == 0) {
        JOptionPane.showMessageDialog(this, "Producto no encontrado con ese código de barras.");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Deseas eliminar el producto: \"" + p.nombre + "\"?",
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        boolean exito = bd.eliminarProducto(String.valueOf(p.id));
        if (exito) {
            JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.");
            actualizarTabla();
            limpiarCampo();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto. ¿Está en Almacén u otra tabla?");
        }
    }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        // TODO add your handling code here:
        int index = cmbCategoria.getSelectedIndex();
    if (index <= 0) {
        // Si selecciona "Selecciona una Categoria" o no hay nada válido, mostrar todo
        actualizarTabla();
        return;
    }

    String categoriaSeleccionada = cmbCategoria.getItemAt(index);
    ArrayList<String[]> datos = bd.mostrarProductosPorCategoria(categoriaSeleccionada);

    // Limpiar la tabla
    int totalRenglones = m.getRowCount();
    for (int i = 0; i < totalRenglones; i++) {
        m.removeRow(0);
    }

    // Agregar los productos filtrados
    for (String[] fila : datos) {
        m.addRow(fila);
    }
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:

        VentanaGestionCategoria v=new VentanaGestionCategoria();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        VentanaProveedor v=new VentanaProveedor();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:

        Almacen v=new Almacen();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:

        VetnanaGestionUsuarios v=new VetnanaGestionUsuarios();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem_ServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ServiciosActionPerformed
        // TODO add your handling code here:
        VentanaGanancias v=new VentanaGanancias();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem_ServiciosActionPerformed

    private void cmbProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedorActionPerformed
        // TODO add your handling code here:
        String proveedor = cmbProveedor.getSelectedItem().toString();

    // Evitar aplicar el filtro si es la opción inicial
    if (proveedor.equals("Selecciona un Provedoor") || proveedor.contains("datos disponibles")) {
        actualizarTabla(); // Muestra todo
    } else {
        filtrarPorProveedor(proveedor);
    } 
    }//GEN-LAST:event_cmbProveedorActionPerformed

    public void filtrarPorProveedor(String proveedorSeleccionado) {
    ArrayList<String[]> datos = bd.mostrarProductosPorProveedor(proveedorSeleccionado);

    int totalRenglones = m.getRowCount();
    for (int i = 0; i < totalRenglones; i++) {
        m.removeRow(0);
    }

    for (String[] fila : datos) {
        m.addRow(fila);
    }
}

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }
    public void MostrarCmb(){
        ArrayList<String[]>datos =bd.mostrarProveedores();
        cmbProveedor.removeAllItems();
        if (datos.size() == 0) {
            cmbProveedor.addItem("No hay datos disponibles");
            return;
        }
        // Recorrer los resultados y agregarlos al JComboBox
        cmbProveedor.addItem("Selecciona un Provedoor"); 
        for (String[] data : datos) {
            // Por ejemplo, usar el primer campo como elemento (puedes ajustarlo según la lógica necesaria)
            cmbProveedor.addItem(data[0]); // data[0] es el nombre, código o lo que se necesite mostrar
        }
    }
    
    public void MostrarCmbCat(){
        ArrayList<String[]>datos =bd.mostrarCategorias();
        cmbCategoria.removeAllItems();
        if (datos.size() == 0) {
            cmbCategoria.addItem("No hay datos disponibles");
            return;
        }
        // Recorrer los resultados y agregarlos al JComboBox
        cmbCategoria.addItem("Selecciona una Categoria"); 
        for (String[] data : datos) {
            // Por ejemplo, usar el primer campo como elemento (puedes ajustarlo según la lógica necesaria)
            cmbCategoria.addItem(data[1]); // data[0] es el nombre, código o lo que se necesite mostrar
        }
    }
    public void actualizarTabla(){
        ArrayList<String[]>datos =bd.mostrarProductos();
        if(datos.size()==0)return;
        int totalRenglones=m.getRowCount();
        for (int i = 0; i <totalRenglones; i++) {//hh
            m.removeRow(0);
        }
        for (String[] data: datos) {
            m.addRow(data);
        }   
    }
    public void limpiarCampo(){
        txtNombre.setText("");
       txtDescripcion.setText("");
       txtStock.setText("");
       txtCodigoBarras.setText("");
        cmbCategoria.setSelectedIndex(0);
        cmbProveedor.setSelectedIndex(0);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelCorreoMostrar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem_Servicios;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventario;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
