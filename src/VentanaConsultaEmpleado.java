
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author jorge
 */
public class VentanaConsultaEmpleado extends javax.swing.JFrame {
    BaseDatos bd;
    DefaultTableModel m;
    /**
     * Creates new form VentanaConsultaEmpleado
     */
    public VentanaConsultaEmpleado() {
        initComponents();
        setLocationRelativeTo(null);

        bd=new BaseDatos();
        String correoActual = VentanaLogin.correoUsuario;
        System.out.println("Correo obtenido: " + correoActual);
        JLabelCorreoMostrar.setText(VentanaLogin.correoUsuario);
        
        try {
            if(bd.conexion.isClosed()){
                System.out.println("Noo!!!. Se cerro");
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(VentanaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        m=(DefaultTableModel) tblProductos.getModel();
        actualizarTabla();//MostrarCmbCat();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        txtCodigoB = new javax.swing.JTextField();
        cmbCategoria = new javax.swing.JComboBox<>();
        NOMBRE_TITULO = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JLabelCorreoMostrar = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consultar Productos");
        setPreferredSize(new java.awt.Dimension(860, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 850, -1));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo de barras", "Categoria", "Producto", "Cantidad", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 620, 320));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 640, 340));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setBackground(new java.awt.Color(204, 0, 0));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search2.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 160, -1));

        txtCodigoB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtCodigoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBActionPerformed(evt);
            }
        });
        jPanel3.add(txtCodigoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 160, 30));

        cmbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Categorias", "A-Z", "Z-A" }));
        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });
        jPanel3.add(cmbCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 160, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, 340));

        NOMBRE_TITULO.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        NOMBRE_TITULO.setForeground(new java.awt.Color(255, 255, 255));
        NOMBRE_TITULO.setText("P&S");
        jPanel1.add(NOMBRE_TITULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 10, -1, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("EMPLEADO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 80, 30));

        JLabelCorreoMostrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelCorreoMostrar.setForeground(new java.awt.Color(255, 255, 255));
        JLabelCorreoMostrar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(JLabelCorreoMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 220, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 854, 470));

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

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/17219_cash_cashbox_machine_payment_register_icon.png"))); // NOI18N
        jMenuItem2.setText("Caja");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
         Producto p = new Producto(); // Creamos un objeto Producto vacío
    p = bd.buscarProducto(txtCodigoB.getText(), p);
    
    /*if (p != null && p.id != 0) {
        System.out.println("Producto encontrado: " + p.nombre);
        
    } else {
        System.out.println("No se encontro el producto.");
    }
    */
    
    
    if(cmbCategoria.getSelectedIndex()==0){
        p = bd.buscarProducto(txtCodigoB.getText(), p);
    
        if (p != null && p.id != 0) {
        System.out.println("Producto encontrado: " + p.nombre);
        
        } else {
            System.out.println("No se encontro el producto.");
        }
    }else if(cmbCategoria.getSelectedIndex()==1){
        actualizarTablaOrd(true);
    }else if(cmbCategoria.getSelectedIndex()==2){
        actualizarTablaOrd(false);
    }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtCodigoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBActionPerformed
        
    }//GEN-LAST:event_txtCodigoBActionPerformed
public void actualizarTablaOrdNombre(boolean orden) {
    ArrayList<String[]> datos = bd.mostrarProductosCajaOrdCategoria(orden);
    if (datos.isEmpty()) return;

    // Limpiar tabla
    int totalRenglones = m.getRowCount();
    for (int i = 0; i < totalRenglones; i++) {
        m.removeRow(0);
    }

    // Agregar datos nuevos
    for (String[] data : datos) {
        m.addRow(data);
    }
}

public void actualizarTablaOrdCategoria(boolean orden) {
    ArrayList<String[]> datos = bd.mostrarProductosCajaOrdCategoria(orden);
    if (datos.isEmpty()) return;

    int totalRenglones = m.getRowCount();
    for (int i = 0; i < totalRenglones; i++) {
        m.removeRow(0);
    }

    for (String[] data : datos) {
        m.addRow(data);
    }
}


    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
                // TODO add your handling code here:
                    JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        VentanaMenuEmpleado v=new VentanaMenuEmpleado();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        VentanaCaja v=new VentanaCaja();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
int selectedIndex = cmbCategoria.getSelectedIndex();
    switch (selectedIndex) {
        case 0: // "Categorias"
            actualizarTablaOrdCategoria(true); // A-Z por categoría
            break;
        case 1: // "A-z"
            actualizarTablaOrdNombre(true); // A-Z por nombre
            break;
        case 2: // "z-a"
            actualizarTablaOrdNombre(false); // Z-A por nombre
            break;
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoriaActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaConsultaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaConsultaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaConsultaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaConsultaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaConsultaEmpleado().setVisible(true);
            }
        });
    }
    public void actualizarTabla(){
        ArrayList<String[]>datos =bd.mostrarProductosCaja();
        if(datos.size()==0)return;
        int totalRenglones=m.getRowCount();
        for (int i = 0; i <totalRenglones; i++) {
            m.removeRow(0);
        }
        for (String[] data: datos) {
            m.addRow(data);
        }
    }
    
    public void actualizarTablaOrd(boolean a){
        ArrayList<String[]>datos =bd.mostrarProductosCajaOrdCat(a);
        if(datos.size()==0)return;
        int totalRenglones=m.getRowCount();
        for (int i = 0; i <totalRenglones; i++) {
            m.removeRow(0);
        }
        for (String[] data: datos) {
            m.addRow(data);
        }
    }
    
        /*public void MostrarCmbCat(){
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
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelCorreoMostrar;
    private javax.swing.JLabel NOMBRE_TITULO;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCodigoB;
    // End of variables declaration//GEN-END:variables
}
