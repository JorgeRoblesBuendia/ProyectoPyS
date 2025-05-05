
import java.awt.Image;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaGanancias extends javax.swing.JFrame {

    BaseDatos bd;
    private javax.swing.JTable tblGanancias;
    private javax.swing.JScrollPane jScrollPane1;

    public VentanaGanancias() {
        initComponents();
        bd = new BaseDatos();
        setLocationRelativeTo(null);
        try {
            if (bd.conexion.isClosed()) {
                System.out.println("Noo!!!. Se cerro");
            }
        } catch (SQLException ex) {

            Logger.getLogger(VentanaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        setImagenEscalada(FONDO, "/imagenes/Fondo.jpg");
        String correoActual = VentanaLogin.correoUsuario;
        System.out.println("Correo obtenido: " + correoActual);
        JLabelCorreoMostrar.setText(VentanaLogin.correoUsuario);

        tblGanancias = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblTotalGanancias = new javax.swing.JLabel();
        lblTotalProductos = new javax.swing.JLabel();

// Configurar tabla
        tblGanancias.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID Producto", "Cantidad", "Subtotal", "Fecha Venta"
                }
        ));
        jScrollPane1.setViewportView(tblGanancias);

// Agregar tabla a la ventana
        

// Label de productos
        lblTotalProductos.setText("Total Productos Vendidos: 0");
        

// Label de ganancias
        lblTotalGanancias.setText("Ganancias Totales: $0.00");
        

        // 1. Agrega tabla y scrollpane
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 800, 250));

// 2. Agrega labels de ganancias y productos
        getContentPane().add(lblTotalProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 400, 30));
        getContentPane().add(lblTotalGanancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 400, 30));

// 3. Hasta el FINAL, agrega el fondo
        getContentPane().add(FONDO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 914, 500));

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                cargarGanancias();
            }
        });

    }

    private void cargarGanancias() {
       javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(
        new Object[][]{},
        new String[]{
            "ID Producto", "Cantidad", "Subtotal de venta", "Precio Compra", "Ganancia/Unidad", "Ganancia Total", "Fecha Venta"
        }
    );
    tblGanancias.setModel(modelo);

    double totalVendidos = 0.0;
    double totalGanancias = 0.0;
    int totalProductos = 0;

    try {
        String sql = "SELECT dv.idProducto, dv.cantidad, dv.subtotal, v.fechaHora, a.precioCompra " +
                     "FROM DetallesVenta dv " +
                     "INNER JOIN Ventas v ON dv.idVenta = v.idVenta " +
                     "INNER JOIN Almacen a ON dv.idProducto = a.idProducto";

        java.sql.PreparedStatement pst = bd.conexion.prepareStatement(sql);
        java.sql.ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int idProducto = rs.getInt("idProducto");
            int cantidad = rs.getInt("cantidad");
            double subtotal = rs.getDouble("subtotal");
            double precioCompra = rs.getDouble("precioCompra");
            java.sql.Timestamp fecha = rs.getTimestamp("fechaHora");

            double gananciaUnidad = (subtotal / cantidad) - precioCompra;
            double gananciaTotal = gananciaUnidad * cantidad;

            modelo.addRow(new Object[]{
                idProducto,
                cantidad,
                String.format("$%.2f", subtotal),
                String.format("$%.2f", precioCompra),
                String.format("$%.2f", gananciaUnidad),
                String.format("$%.2f", gananciaTotal),
                fecha.toString()
            });

            totalVendidos += subtotal;
            totalGanancias += gananciaTotal;
            totalProductos += cantidad;
        }

        // Mostramos por separado lo vendido y lo ganado
        lblTotalGanancias.setText(String.format("Ganancias netas: $%.2f", totalGanancias));
        lblTotalProductos.setText("Total productos vendidos: " + totalProductos);

        // Puedes agregar un tercer JLabel si quieres mostrar esto por separado
        JOptionPane.showMessageDialog(this, String.format("Total vendido (ingresos brutos): $%.2f", totalVendidos));

    } catch (SQLException ex) {
        Logger.getLogger(VentanaGanancias.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error al cargar las ganancias: " + ex.getMessage());
    }
    }

    private void setImagenEscalada(JLabel label, String ruta) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        Image image = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        JLabelCorreoMostrar = new javax.swing.JLabel();
        lblTotalProductos = new javax.swing.JLabel();
        lblTotalGanancias = new javax.swing.JLabel();
        FONDO = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("REPORTE DE VENTAS");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 920, -1));

        JLabelCorreoMostrar.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        JLabelCorreoMostrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(JLabelCorreoMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 210, 20));

        lblTotalProductos.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        getContentPane().add(lblTotalProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 210, 40));

        lblTotalGanancias.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        getContentPane().add(lblTotalGanancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 210, 40));

        FONDO.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(FONDO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 914, 500));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2849812_menu_multimedia_bars_media_icon.png"))); // NOI18N
        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/353416_home_icon.png"))); // NOI18N
        jMenuItem1.setText("Regresar al menu principal");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png"))); // NOI18N
        jMenuItem6.setText("Empleados");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        jMenuItem3.setText("Categorias");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inventory.png"))); // NOI18N
        jMenuItem4.setText("Inventario");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

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

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:

        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        VentanaMenu v = new VentanaMenu();
        v.bd = bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        VentanaGestionCategoria v=new VentanaGestionCategoria();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        Inventario v=new Inventario();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        VentanaMenu v=new VentanaMenu();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        Almacen v=new Almacen();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        VetnanaGestionUsuarios v=new VetnanaGestionUsuarios();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaGanancias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaGanancias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaGanancias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaGanancias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaGanancias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FONDO;
    private javax.swing.JLabel JLabelCorreoMostrar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JLabel lblTotalGanancias;
    private javax.swing.JLabel lblTotalProductos;
    // End of variables declaration//GEN-END:variables
}
