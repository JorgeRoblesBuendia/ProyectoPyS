
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;


public class VentanaINICIO extends javax.swing.JFrame {

    private CargandoAnimacion animacion;
    
    public VentanaINICIO() {
        initComponents();
        animacion = new CargandoAnimacion();
        animacion.iniciarAnimacion(CargandoLABEL);
        cerrarYMostrarLogin();
        setLocationRelativeTo(null);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        NOMBRE_TITULO = new javax.swing.JLabel();
        WELCOME = new javax.swing.JLabel();
        CargandoLABEL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(255, 102, 102));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setMinimumSize(new java.awt.Dimension(860, 532));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 500));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("© Tigres Maiden");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 358, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 500, 179, -1));

        NOMBRE_TITULO.setFont(new java.awt.Font("Segoe UI", 3, 92)); // NOI18N
        NOMBRE_TITULO.setForeground(new java.awt.Color(255, 255, 255));
        NOMBRE_TITULO.setText("P&S");
        jPanel2.add(NOMBRE_TITULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, 80));

        WELCOME.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        WELCOME.setForeground(new java.awt.Color(255, 255, 255));
        WELCOME.setText("WELCOME TO...");
        jPanel2.add(WELCOME, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 140, -1));

        CargandoLABEL.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        CargandoLABEL.setForeground(new java.awt.Color(255, 255, 255));
        CargandoLABEL.setText("Powered by P&S...");
        jPanel2.add(CargandoLABEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 500, 160, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 532));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void iniciarAnimacion() {
        animacion = new CargandoAnimacion();
        animacion.iniciarAnimacion(CargandoLABEL);
    }

    
 
    private void cerrarYMostrarLogin() {
        // Esperar 15 segundos (15000 ms) y luego abrir la ventana de login
        Timer timer = new Timer(5000, e -> {
            animacion.detenerAnimacion(); // Detiene los puntos animados
            dispose(); // Cierra la ventana actual

            // Abre la ventana de iniciar sesión
            new VentanaLogin().setVisible(true);
        });
        timer.setRepeats(false); // Se ejecuta una sola vez
        timer.start();
    }
    
    
    
    
    
    

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaINICIO().setVisible(true);
            }
        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CargandoLABEL;
    private javax.swing.JLabel NOMBRE_TITULO;
    private javax.swing.JLabel WELCOME;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
