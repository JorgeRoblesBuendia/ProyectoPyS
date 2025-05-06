import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VentanaCerrarVenta extends javax.swing.JFrame {

    BaseDatos bd;
    private javax.swing.JTextField txtMontoPagado;
    private javax.swing.JLabel lblCambio;
    private javax.swing.JButton btnVender;
    private javax.swing.JButton btnGenerarTicket;

    public VentanaCerrarVenta() {
        initComponents();
        setLocationRelativeTo(null);
        bd = new BaseDatos();
        
        // Configurar el cálculo automático del cambio
        txtMontoPagado.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { calcularCambio(); }
            @Override
            public void removeUpdate(DocumentEvent e) { calcularCambio(); }
            @Override
            public void changedUpdate(DocumentEvent e) { calcularCambio(); }
        });
    }

    private void calcularCambio() {
        try {
            double total = 100.00; // Aquí deberías obtener el total de la venta
            double pagado = Double.parseDouble(txtMontoPagado.getText());
            double cambio = pagado - total;
            
            if(cambio >= 0) {
                lblCambio.setText(String.format("$%.2f", cambio));
            } else {
                lblCambio.setText("Pago insuficiente");
            }
        } catch (NumberFormatException e) {
            lblCambio.setText("Ingrese monto válido");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        JLabel titulo = new javax.swing.JLabel();
        JLabel lblMontoPagado = new javax.swing.JLabel();
        txtMontoPagado = new javax.swing.JTextField();
        JLabel lblCambioTitulo = new javax.swing.JLabel();
        lblCambio = new javax.swing.JLabel();
        btnVender = new javax.swing.JButton();
        btnGenerarTicket = new javax.swing.JButton();
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Tahoma", 3, 30));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("CERRAR VENTA");
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 920, -1));

        lblMontoPagado.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblMontoPagado.setText("Monto pagado por el cliente:");
        getContentPane().add(lblMontoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 300, 30));

        txtMontoPagado.setFont(new java.awt.Font("Tahoma", 0, 18));
        getContentPane().add(txtMontoPagado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 200, 30));

        lblCambioTitulo.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblCambioTitulo.setText("Cambio:");
        getContentPane().add(lblCambioTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 100, 30));

        lblCambio.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblCambio.setForeground(new java.awt.Color(0, 153, 0));
        lblCambio.setText("$0.00");
        getContentPane().add(lblCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 200, 30));

        btnVender.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnVender.setText("Vender");
        btnVender.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Venta realizada con éxito!");
            // Aquí iría la lógica para registrar la venta
        });
        getContentPane().add(btnVender, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 150, 40));

        btnGenerarTicket.setFont(new java.awt.Font("Tahoma", 1, 14));
        btnGenerarTicket.setText("Generar Ticket");
        btnGenerarTicket.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Generando ticket...");
            // Aquí iría la lógica para generar el ticket
        });
        getContentPane().add(btnGenerarTicket, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 150, 40));

        FONDO.setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().add(FONDO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 914, 500));

        // Configuración del menú (igual a la referencia)
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2849812_menu_multimedia_bars_media_icon.png")));
        jMenu1.setText("Menu");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/353416_home_icon.png")));
        jMenuItem1.setText("Regresar al menu principal");
        jMenuItem1.addActionListener(e -> {
            new VentanaMenu().setVisible(true);
            this.dispose();
        });
        jMenu1.add(jMenuItem1);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cliente.png")));
        jMenuItem6.setText("Empleados");
        jMenuItem6.addActionListener(e -> abrirVentana(VetnanaGestionUsuarios.class));
        jMenu1.add(jMenuItem6);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png")));
        jMenuItem3.setText("Categorias");
        jMenuItem3.addActionListener(e -> abrirVentana(VentanaGestionCategoria.class));
        jMenu1.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inventory.png")));
        jMenuItem4.setText("Inventario");
        jMenuItem4.addActionListener(e -> abrirVentana(Inventario.class));
        jMenu1.add(jMenuItem4);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/supplier.png")));
        jMenuItem2.setText("Proveedor");
        jMenuItem2.addActionListener(e -> abrirVentana(VentanaMenu.class));
        jMenu1.add(jMenuItem2);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/producto.png")));
        jMenuItem5.setText("Almacen");
        jMenuItem5.addActionListener(e -> abrirVentana(Almacen.class));
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);
        setJMenuBar(jMenuBar1);

        pack();
    }

    private void abrirVentana(Class<?> ventana) {
        try {
            JFrame frame = (JFrame) ventana.getDeclaredConstructor().newInstance();
            frame.setVisible(true);
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(VentanaCerrarVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new VentanaCerrarVenta().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JLabel FONDO;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
}