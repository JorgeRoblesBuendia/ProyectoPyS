
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.*;

//Modificaciones de tabla 
import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author edwin
 */
public class Almacen extends javax.swing.JFrame {
    private int idProductoSeleccionado = -1;

    BaseDatos bd;DefaultTableModel m;
    boolean permisoEditar=false,permisoBorrar=false;
    /**
     * Creates new form Inventario
     */
    public Almacen() {
        initComponents();
        jMenuItem_Servicios.setText("<html><center>Reporte<br>de ventas</center></html>");
        btnCancelar.setVisible(false);
        setLocationRelativeTo(null);

       bd = new BaseDatos();
       String correoActual = VentanaLogin.correoUsuario;
        System.out.println("Correo obtenido: " + correoActual);
        JLabelCorreoMostrar.setText(VentanaLogin.correoUsuario);
        
        try {
            if (bd.conexion.isClosed()) {
                System.out.println("Noo!!!. Se cerro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } m=(DefaultTableModel) tblAlmacen.getModel();
        
      actualizarTabla();
      MostrarCmb();

    }
    
    /** Modificación de actualizarTabla para detectar caducidades */
    public void actualizarTabla() {
        ArrayList<String[]> datos = bd.mostrarAlmacen();
        if (datos.isEmpty()) return;
        m.setRowCount(0);
        ArrayList<String> proximosACaducar = new ArrayList<>();
        Date hoy = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoy);
        cal.add(Calendar.DAY_OF_YEAR, 7); // dentro de 7 días
        Date dentroDe7 = cal.getTime();

        for (String[] data : datos) {
            m.addRow(data);
        }
        
        // Instalar renderer para caducidad
        tblAlmacen.getColumnModel().getColumn(5).setCellRenderer(new CaducidadCellRenderer(dentroDe7, hoy));

        // Generar aviso de caducados o próximos a caducar
        for (String[] data : datos) {
            String nombre = data[1];
            try {
                Date fechaCad = new SimpleDateFormat("yyyy-MM-dd").parse(data[5]);
                if (fechaCad.before(hoy)) {
                    proximosACaducar.add(nombre + " (CADUCADO)");
                } else if (!fechaCad.after(dentroDe7)) {
                    proximosACaducar.add(nombre + " (vence el " + data[5] + ")");
                }
            } catch (Exception e) { /* ignore */ }
        }
        if (!proximosACaducar.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                String.join("\n", proximosACaducar),
                "Productos caducados o próximos a caducar",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private static class CaducidadCellRenderer extends DefaultTableCellRenderer {
        private final Date limiteProximo;
        private final Date hoy;
        private final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        CaducidadCellRenderer(Date limiteProximo, Date hoy) {
            this.limiteProximo = limiteProximo;
            this.hoy = hoy;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value != null) {
                try {
                    Date fecha = fmt.parse(value.toString());
                    if (fecha.before(hoy) || !fecha.after(limiteProximo)) {
                        c.setBackground(Color.YELLOW);
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                } catch (Exception e) {
                    c.setBackground(Color.WHITE);
                }
            } else {
                c.setBackground(Color.WHITE);
            }
            return c;
        }
    }

    private static class p {

        private static String FechaCa;

        public p() {
        }
    }

    
public class AlmacenCellRenderer extends DefaultTableCellRenderer {
    HashMap<String, Integer> stockMinimoProductos;

    public AlmacenCellRenderer(BaseDatos bd) {
        stockMinimoProductos = new HashMap<>();
        
        // Al construir el renderer, cargamos el stock mínimo de todos los productos
        ArrayList<String[]> productos = bd.mostrarProductos();
        for (String[] p : productos) {
            try {
                stockMinimoProductos.put(p[0], Integer.parseInt(p[2]));
            } catch (NumberFormatException e) {
                System.out.println("Error cargando stock mínimo para producto: " + p[0]);
            }
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        try {
            String nombreProducto = table.getValueAt(row, 1).toString(); // Columna 1 = nombre producto
            int cantidadActualFila = Integer.parseInt(table.getValueAt(row, 2).toString()); // cantidad de esa fila

            // SUMAR todas las cantidades de ese producto en la tabla
            int cantidadTotalProducto = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                String nombreFila = table.getValueAt(i, 1).toString();
                if (nombreFila.equals(nombreProducto)) {
                    cantidadTotalProducto += Integer.parseInt(table.getValueAt(i, 2).toString());
                }
            }

            Integer stockMinimo = stockMinimoProductos.get(nombreProducto);

            if (stockMinimo != null && cantidadTotalProducto < stockMinimo) {
                if (column == 1) { // Solo pintar la columna de nombre de producto
                    c.setBackground(Color.RED);
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
            } else {
                c.setBackground(Color.WHITE);
                c.setForeground(Color.BLACK);
            }
        } catch (Exception ex) {
            System.out.println("Error en renderer: " + ex.getMessage());
        }

        return c;
    }
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
        JLabelCorreoMostrar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCantidadIngreso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPrecioC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrecioV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        jdcFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        btnBuscarO = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlmacen = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem_Servicios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(860, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabelCorreoMostrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        JLabelCorreoMostrar.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(JLabelCorreoMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 210, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("GERENTE");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 70, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Fecha de vencimiento :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 207, -1, -1));
        jPanel1.add(txtCantidadIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 69, 111, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Cantidad de ingreso :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 72, -1, -1));
        jPanel1.add(txtPrecioC, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 113, 111, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Precio de compra :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 116, -1, -1));
        jPanel1.add(txtPrecioV, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 153, 111, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Precio de venta :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 156, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Producto :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 31, -1, -1));

        jPanel1.add(cmbProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(162, 28, 111, -1));
        jPanel1.add(jdcFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 207, 129, -1));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 96, -1, 260));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setBackground(new java.awt.Color(204, 0, 0));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sync.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(204, 0, 0));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 96, -1));

        btnBuscarO.setBackground(new java.awt.Color(204, 51, 0));
        btnBuscarO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarO.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search.png"))); // NOI18N
        btnBuscarO.setText("Buscar");
        btnBuscarO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarOActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarO, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, -1, -1));

        btnCancelar.setBackground(new java.awt.Color(204, 0, 0));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 820, 70));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAlmacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Numero", "Producto", "Stock", "Precio compra", "PrecioVenta", "Caducidad"
            }
        ));
        jScrollPane1.setViewportView(tblAlmacen);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 490, 250));

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 97, 510, 260));

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 35)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("GESTION ALMACEN");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 12, 880, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 470));

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

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png"))); // NOI18N
        jMenuItem5.setText("Empleados");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

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
               // TODO add your handling code here:
            JOptionPane.showMessageDialog(null, "Regresando al menú principal...");

        VentanaMenu v=new VentanaMenu();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
         Date fech = jdcFechaVencimiento.getDate();
    int pro = cmbProducto.getSelectedIndex();
    String cantidad = txtCantidadIngreso.getText().trim();
    String precioC = txtPrecioC.getText().trim();
    String preciov = txtPrecioV.getText().trim();

    // Validación visual
    boolean hayError = false;

    // Reseteo de colores
    txtCantidadIngreso.setBackground(Color.WHITE);
    txtPrecioC.setBackground(Color.WHITE);
    txtPrecioV.setBackground(Color.WHITE);
    jdcFechaVencimiento.getComponent(1).setBackground(Color.WHITE);
    cmbProducto.setBackground(Color.WHITE);

    if (cantidad.isEmpty()) {
        txtCantidadIngreso.setBackground(Color.red);
        hayError = true;
    }
    if (precioC.isEmpty()) {
        txtPrecioC.setBackground(Color.red);
        hayError = true;
    }
    if (preciov.isEmpty()) {
        txtPrecioV.setBackground(Color.red);
        hayError = true;
    }
    if (fech == null) {
        jdcFechaVencimiento.getComponent(1).setBackground(Color.red);
        hayError = true;
    }
    if (pro <= 0) {
        cmbProducto.setBackground(Color.red);
        hayError = true;
    }

    if (hayError) {
        JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos obligatorios.", "Campos requeridos", JOptionPane.WARNING_MESSAGE);

        new javax.swing.Timer(3000, e -> {
            txtCantidadIngreso.setBackground(Color.WHITE);
            txtPrecioC.setBackground(Color.WHITE);
            txtPrecioV.setBackground(Color.WHITE);
            jdcFechaVencimiento.getComponent(1).setBackground(Color.WHITE);
            cmbProducto.setBackground(Color.WHITE);
        }).start();
        return;
    }

    // Validar si la fecha es anterior a la actual
    Date fechaActual = new Date();
    if (fech.before(fechaActual)) {
        JOptionPane.showMessageDialog(this, "La fecha seleccionada ya pasó. Selecciona una fecha válida.", "Fecha inválida", JOptionPane.ERROR_MESSAGE);
        jdcFechaVencimiento.getComponent(1).setBackground(Color.PINK);

        new javax.swing.Timer(3000, e -> {
            jdcFechaVencimiento.getComponent(1).setBackground(Color.WHITE);
        }).start();
        return;
    }

    try {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = formatoFecha.format(fech);

        AlmacenC a = new AlmacenC(0, pro, Integer.parseInt(cantidad), Float.parseFloat(precioC), Float.parseFloat(preciov), fechaFormateada);
        if (bd.insertarAlmacen(a)) {
            JOptionPane.showMessageDialog(this, "Agregado con éxito.");
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar.");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Verifica que los valores numéricos estén correctamente escritos.", "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       String idRegistroStr = txtId.getText().trim();

    // Validación visual
    if (idRegistroStr.isEmpty()) {
        txtId.setBackground(Color.PINK);
        JOptionPane.showMessageDialog(this, "Introduce el ID del registro que quieres eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);

        new javax.swing.Timer(3000, e -> txtId.setBackground(Color.WHITE)).start();
        return;
    }

    int idRegistro;
    try {
        idRegistro = Integer.parseInt(idRegistroStr);
    } catch (NumberFormatException e) {
        txtId.setBackground(Color.PINK);
        JOptionPane.showMessageDialog(this, "ID inválido. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);

        new javax.swing.Timer(3000, e2 -> txtId.setBackground(Color.WHITE)).start();
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que quieres eliminar el registro #" + idRegistro + "?",
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        if (bd.eliminarAlmacen(idRegistro)) {
            JOptionPane.showMessageDialog(this, "Registro eliminado exitosamente del almacén.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
            limpiarCampos(); // Limpieza después de eliminación
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

public void limpiarCampos() {
    txtId.setText("");
    txtCantidadIngreso.setText("");
    txtPrecioC.setText("");
    txtPrecioV.setText("");
    jdcFechaVencimiento.setDate(null);
    cmbProducto.setSelectedIndex(0);
    cmbProducto.setEnabled(true); // 🔓 Activamos de nuevo el ComboBox
    idProductoSeleccionado = -1;  // Reiniciamos la variable
}

    
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
   String idRegistroStr = txtId.getText();
    
    if (idProductoSeleccionado == -1) {
        JOptionPane.showMessageDialog(this, 
            "Debes buscar primero un registro antes de actualizar.", 
            "Advertencia", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (idRegistroStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Introduce el ID del registro que quieres actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    if (txtCantidadIngreso.getText().isEmpty() || 
        txtPrecioC.getText().isEmpty() || 
        txtPrecioV.getText().isEmpty() || 
        jdcFechaVencimiento.getDate() == null || 
        cmbProducto.getSelectedIndex() <= 0) {
        
        JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos antes de actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que quieres actualizar el registro #" + idRegistroStr + "?", 
            "Confirmar actualización", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            int idRegistro = Integer.parseInt(idRegistroStr);
            int stock = Integer.parseInt(txtCantidadIngreso.getText());
            double precioCompra = Double.parseDouble(txtPrecioC.getText());
            double precioVenta = Double.parseDouble(txtPrecioV.getText());

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = formatoFecha.format(jdcFechaVencimiento.getDate());

            AlmacenC actualizado = new AlmacenC(idRegistro, idProductoSeleccionado, stock, precioCompra, precioVenta, fechaFormateada);

            if (bd.actualizarAlmacen(actualizado, idRegistro)) {
                JOptionPane.showMessageDialog(this, "Registro actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de números. Verifica que cantidades y precios sean correctos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    }//GEN-LAST:event_btnActualizarActionPerformed
private void mostrarLoading(String mensaje) {
    JOptionPane pane = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
    final javax.swing.JDialog dialog = pane.createDialog(this, "Espere...");

    // Crear un hilo que cierre automáticamente el diálogo después de unos milisegundos
    new Thread(() -> {
        try {
            Thread.sleep(1000); // Espera de 1 segundo (puedes ajustar el tiempo si quieres)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dialog.dispose();
    }).start();

    dialog.setModal(false); // No bloquear la ejecución principal
    dialog.setVisible(true);
}

    private void btnBuscarOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarOActionPerformed
        // ... búsqueda existente ...
        try {
            Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(p.FechaCa);
            if (fecha.before(new Date())) {
                jdcFechaVencimiento.setDate(null);
            }
        } catch (Exception e) {
            jdcFechaVencimiento.setDate(null);
        }
        
        
        String id = txtId.getText();
          
    
    if (id.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Introduce un ID para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    AlmacenC p = new AlmacenC();
    p = bd.buscarAlmacen(Integer.parseInt(id), p);
    
    if (p.idRegistro != 0) {
        txtPrecioC.setText(p.precioC + "");
        txtPrecioV.setText(p.precioV + "");
        txtCantidadIngreso.setText(p.stock + "");

        try {
            LocalDate f = LocalDate.parse(p.FechaCa);
            int anio = f.getYear();
            int mes = f.getMonthValue();
            int dia = f.getDayOfMonth();
            Date d = new Date(anio - 1900, mes - 1, dia);
            jdcFechaVencimiento.setDate(d);
        } catch (Exception e) {
            System.out.println("Error convirtiendo fecha: " + e.getMessage());
        }

        Producto prod = new Producto();
        prod = bd.buscarProductoPorId(p.idProducto, prod);

        if (prod.nombre != null) {
            for (int i = 0; i < cmbProducto.getItemCount(); i++) {
                if (cmbProducto.getItemAt(i).equals(prod.nombre)) {
                    cmbProducto.setSelectedIndex(i);
                    break;
                }
            }

            idProductoSeleccionado = p.idProducto; // 🔥 Guardamos el idProducto correcto

            int stockTotalProducto = bd.obtenerStockTotalProducto(prod.id);

            JOptionPane.showMessageDialog(this, 
                    "Producto encontrado:\n" +
                    "Nombre: " + prod.nombre + "\n" +
                    "Cantidad en este registro: " + p.stock + " unidades\n" +
                    "Cantidad total del producto: " + stockTotalProducto + " unidades\n" +
                    "Precio de venta: $" + p.precioV,
                    "Resultado de búsqueda",
                    JOptionPane.INFORMATION_MESSAGE);

            cmbProducto.setEnabled(false);
            btnCancelar.setVisible(true);// 🔒 Bloqueamos ComboBox para evitar error
        } else {
            JOptionPane.showMessageDialog(this, "Producto encontrado pero no se pudo recuperar el nombre.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró el registro en el almacén.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnBuscarOActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        
         limpiarCampos();
    btnCancelar.setVisible(false); // 🔥 Ocultar botón Cancelar otra vez
    JOptionPane.showMessageDialog(this, "Operación cancelada. Puedes continuar normalmente.", 
            "Operación cancelada", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnCancelarActionPerformed

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

        VetnanaGestionUsuarios v=new VetnanaGestionUsuarios();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem_ServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ServiciosActionPerformed
        // TODO add your handling code here:
        VentanaGanancias v=new VentanaGanancias();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem_ServiciosActionPerformed

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
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Almacen().setVisible(true);
            }
        });
    }
    public void MostrarCmb(){
        ArrayList<String[]>datos =bd.mostrarProductosAlmacen();
        cmbProducto.removeAllItems();
        if (datos.size() == 0) {
            cmbProducto.addItem("No hay datos disponibles");
            return;
        }
        // Recorrer los resultados y agregarlos al JComboBox
        cmbProducto.addItem("Selecciona un Producto"); 
        for (String[] data : datos) {
            // Por ejemplo, usar el primer campo como elemento (puedes ajustarlo según la lógica necesaria)
            cmbProducto.addItem(data[0]); // data[0] es el nombre, código o lo que se necesite mostrar
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelCorreoMostrar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscarO;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem_Servicios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFechaVencimiento;
    private javax.swing.JTable tblAlmacen;
    private javax.swing.JTextField txtCantidadIngreso;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPrecioC;
    private javax.swing.JTextField txtPrecioV;
    // End of variables declaration//GEN-END:variables
}
