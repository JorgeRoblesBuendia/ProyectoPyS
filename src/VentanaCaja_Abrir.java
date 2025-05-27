
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;

public class VentanaCaja_Abrir extends javax.swing.JFrame {

    BaseDatos bd;
    String correo="";
    boolean e=false;
    
    public VentanaCaja_Abrir() {
        initComponents();
        bd=new BaseDatos();
        
        try {
            if(bd.conexion.isClosed()){
                System.out.println("Noo!!!. Se cerro");
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(VentanaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        configurarSpinners();
        /*
        JlabelIniciarCaja.setText("$" + CajaGlobal.dineroInicial);

        CajaGlobal.dineroAcumulado = CajaGlobal.dineroInicial;       // 👀 Acumulado empieza igual que inicial
        JLDineroActual.setText(String.format("$%.2f", CajaGlobal.dineroAcumulado));

        JPN_10.setEnabled(false);
        JPN_20.setEnabled(false);
        JPN_50.setEnabled(false);
        JPN1.setEnabled(false);
        JPN2.setEnabled(false);
        JPN5.setEnabled(false);
        JPN10.setEnabled(false);
        JPN20.setEnabled(false);
        JPN50.setEnabled(false);
        JPN100.setEnabled(false);
        JPN200.setEnabled(false);
        JPN500.setEnabled(false);
        JPN1000.setEnabled(false);*/
        
        String correoActual = VentanaLogin.correoUsuario;
        System.out.println("Correo obtenido: " + correoActual);
        correo=VentanaLogin.correoUsuario;
        e=bd.ObtenerEstado();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NOMBRE_TITULO = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAbrirC = new javax.swing.JButton();
        txtI = new javax.swing.JTextField();
        btnCerrarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCaja = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NOMBRE_TITULO.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        NOMBRE_TITULO.setForeground(new java.awt.Color(255, 255, 255));
        NOMBRE_TITULO.setText("P&S");
        jPanel1.add(NOMBRE_TITULO, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VENTA INICIAR CAJA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 24, 860, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("EMPLEADO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 80, 30));

        btnAbrirC.setText("Abrir");
        btnAbrirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCActionPerformed(evt);
            }
        });
        jPanel1.add(btnAbrirC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 110, -1));
        jPanel1.add(txtI, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 110, -1));

        btnCerrarC.setText("Cerrar");
        btnCerrarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarCActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 110, -1));

        tblCaja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Estado", "Inicio", "Total", "Diferencia"
            }
        ));
        jScrollPane1.setViewportView(tblCaja);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 700, 460));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2849812_menu_multimedia_bars_media_icon.png"))); // NOI18N
        jMenu1.setText("Menu");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/353416_home_icon.png"))); // NOI18N
        jMenuItem1.setText("Regresar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/2124221_search_document_app_essential_icon.png"))); // NOI18N
        jMenuItem2.setText("Consultar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:

        VentanaMenuEmpleado v = new VentanaMenuEmpleado();
        v.bd = bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        VentanaConsultaEmpleado v = new VentanaConsultaEmpleado();
        v.bd = bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void btnAbrirCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCActionPerformed
        // TODO add your handling code here:
        
        Empleado em=new Empleado();
        em=bd.buscarEmpleado(correo, em);
        if(e){
            JOptionPane.showMessageDialog(this, "El estado es Verdadero existe caja");
        }else{
            bd.AbirCaja(em.id+"", Double.parseDouble(txtI.getText()));
            JOptionPane.showMessageDialog(this, "Exito");
            e=!e;
        }
        
        
    }//GEN-LAST:event_btnAbrirCActionPerformed

    private void btnCerrarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarCActionPerformed
        if(e){
            bd.CerrarCaja();
            JOptionPane.showMessageDialog(this, "Exito");
            e=!e;
        }else{
            JOptionPane.showMessageDialog(this, "No hay caja abierta");
        }
    }//GEN-LAST:event_btnCerrarCActionPerformed

    // Método que configura los listeners
    public void configurarSpinners() {
        //ChangeListener listener = e -> calcularTotalCaja();

        /*
        JPN_10.addChangeListener(listener);
        JPN_20.addChangeListener(listener);
        JPN_50.addChangeListener(listener);
        JPN1.addChangeListener(listener);
        JPN2.addChangeListener(listener);
        JPN5.addChangeListener(listener);
        JPN10.addChangeListener(listener);
        JPN20.addChangeListener(listener);
        JPN50.addChangeListener(listener);
        JPN100.addChangeListener(listener);
        JPN200.addChangeListener(listener);
        JPN500.addChangeListener(listener);
        JPN1000.addChangeListener(listener);*/
    }

    public void calcularTotalCaja() {
        double total = 0;

        // Monedas
        /*total += ((Integer) JPN_10.getValue()) * 0.10;
        total += ((Integer) JPN_20.getValue()) * 0.20;
        total += ((Integer) JPN_50.getValue()) * 0.50;
        total += ((Integer) JPN1.getValue()) * 1;
        total += ((Integer) JPN2.getValue()) * 2;
        total += ((Integer) JPN5.getValue()) * 5;
        total += ((Integer) JPN10.getValue()) * 10;

        // Billetes
        total += ((Integer) JPN20.getValue()) * 20;
        total += ((Integer) JPN50.getValue()) * 50;
        total += ((Integer) JPN100.getValue()) * 100;
        total += ((Integer) JPN200.getValue()) * 200;
        total += ((Integer) JPN500.getValue()) * 500;
        total += ((Integer) JPN1000.getValue()) * 1000;*/

        // Mostrar en el JLabel con formato
        CajaGlobal.dineroInicial = total; // 'total' es el dinero contado de billetes y monedas
        CajaGlobal.dineroAcumulado = CajaGlobal.dineroInicial; // acumulado empieza igual que inicial

        //JlabelIniciarCaja.setText(String.format("$%.2f", total));
        //JLDineroActual.setText(String.format("$%.2f", CajaGlobal.dineroAcumulado));

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaCaja_Abrir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCaja_Abrir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCaja_Abrir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCaja_Abrir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCaja_Abrir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NOMBRE_TITULO;
    private javax.swing.JButton btnAbrirC;
    private javax.swing.JButton btnCerrarC;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCaja;
    private javax.swing.JTextField txtI;
    // End of variables declaration//GEN-END:variables
}
