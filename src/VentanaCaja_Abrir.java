
import javax.swing.event.ChangeListener;

public class VentanaCaja_Abrir extends javax.swing.JFrame {

    BaseDatos bd;
    String correo="";

    public VentanaCaja_Abrir() {
        initComponents();
        configurarSpinners();
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
        JPN1000.setEnabled(false);
        
        String correoActual = VentanaLogin.correoUsuario;
        System.out.println("Correo obtenido: " + correoActual);
        correo=VentanaLogin.correoUsuario;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BtnAbrirCaja = new javax.swing.JButton();
        BtnCerrarCaja = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        NOMBRE_TITULO = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JlabelIniciarCaja = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JLDineroActual = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JLCierre = new javax.swing.JLabel();
        JPN20 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JPN50 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        JPN100 = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        JPN200 = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        JPN500 = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        JPN1000 = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        JPN2 = new javax.swing.JSpinner();
        JPN5 = new javax.swing.JSpinner();
        JPN10 = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        JPN_10 = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        JPN_20 = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        JPN_50 = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        JPN1 = new javax.swing.JSpinner();
        jLabel23 = new javax.swing.JLabel();
        JLEstatus = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnAbrirC = new javax.swing.JButton();
        txtI = new javax.swing.JTextField();
        btnCerrarC = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnAbrirCaja.setText("Abrir Caja");
        BtnAbrirCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAbrirCajaMouseClicked(evt);
            }
        });
        jPanel1.add(BtnAbrirCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        BtnCerrarCaja.setText("Cerrar Caja");
        BtnCerrarCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnCerrarCajaMouseClicked(evt);
            }
        });
        jPanel1.add(BtnCerrarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("$20");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 40, 20));

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

        JlabelIniciarCaja.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        JlabelIniciarCaja.setForeground(new java.awt.Color(255, 255, 255));
        JlabelIniciarCaja.setText("$0000.00");
        jPanel1.add(JlabelIniciarCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 130, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DINERO INICIAR EN CAJA:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 310, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DINERO ACTUAL EN CAJA:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 310, 30));

        JLDineroActual.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        JLDineroActual.setForeground(new java.awt.Color(255, 255, 255));
        JLDineroActual.setText("$0000.00");
        jPanel1.add(JLDineroActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 130, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DINERO CIERRE DE CAJA:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 310, 30));

        JLCierre.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        JLCierre.setForeground(new java.awt.Color(255, 255, 255));
        JLCierre.setText("$0000.00");
        jPanel1.add(JLCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 130, 30));
        jPanel1.add(JPN20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 70, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Anadir Dinero Disponible Para Iniciar Caja");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 390, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Billetes:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 80, -1));
        jPanel1.add(JPN50, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 70, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("$50");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 40, 20));
        jPanel1.add(JPN100, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 70, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("$100");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 50, 20));
        jPanel1.add(JPN200, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, 70, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("$200");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 50, 20));
        jPanel1.add(JPN500, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 210, 70, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("$500");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 50, 20));
        jPanel1.add(JPN1000, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 210, 70, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("$1000");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 60, 20));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("$10");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 50, 20));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("$2");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 40, 20));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("$5");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 40, 20));
        jPanel2.add(JPN2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 70, -1));
        jPanel2.add(JPN5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 70, -1));
        jPanel2.add(JPN10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 70, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Monedas:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 100, -1));
        jPanel2.add(JPN_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 70, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("$.10");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 40, 20));
        jPanel2.add(JPN_20, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 70, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("$.20");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 40, 20));
        jPanel2.add(JPN_50, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 70, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("$.50");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 50, 20));
        jPanel2.add(JPN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 70, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("$1");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 40, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 410, 250));

        JLEstatus.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        JLEstatus.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(JLEstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 180, 30));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("ESTATUS:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 110, 30));

        btnAbrirC.setText("Abrir");
        btnAbrirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirCActionPerformed(evt);
            }
        });
        jPanel1.add(btnAbrirC, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, -1, -1));
        jPanel1.add(txtI, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 270, -1));

        btnCerrarC.setText("Cerrar");
        jPanel1.add(btnCerrarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, -1, -1));

        jLabel14.setText("jLabel14");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 100, 40));

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

    private void BtnAbrirCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAbrirCajaMouseClicked
        JPN_10.setEnabled(true);
        JPN_20.setEnabled(true);
        JPN_50.setEnabled(true);
        JPN1.setEnabled(true);
        JPN2.setEnabled(true);
        JPN5.setEnabled(true);
        JPN10.setEnabled(true);
        JPN20.setEnabled(true);
        JPN50.setEnabled(true);
        JPN100.setEnabled(true);
        JPN200.setEnabled(true);
        JPN500.setEnabled(true);
        JPN1000.setEnabled(true);

        // Cambiar el texto del JLabel
        JLEstatus.setText("CAJA ABIERTA");


    }//GEN-LAST:event_BtnAbrirCajaMouseClicked

    private void BtnCerrarCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnCerrarCajaMouseClicked
        JLEstatus.setText("CAJA CERRADA");

        // 2️⃣ Calcular diferencia (ganancia)
        double diferencia = CajaGlobal.dineroAcumulado - CajaGlobal.dineroInicial;

        // 3️⃣ Mostrar diferencia en el JLabel
        JLCierre.setText(String.format("$%.2f", diferencia));

        // 4️⃣ Resetear variables
        CajaGlobal.dineroInicial = 0.0;
        CajaGlobal.dineroAcumulado = 0.0;

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
        JPN1000.setEnabled(false);

    }//GEN-LAST:event_BtnCerrarCajaMouseClicked

    private void btnAbrirCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirCActionPerformed
        // TODO add your handling code here:
        Empleado em=new Empleado();
        em=bd.buscarEmpleado(correo, em);
        
        bd.AbirCaja(em.id+"", Double.parseDouble(txtI.getText()));
        
    }//GEN-LAST:event_btnAbrirCActionPerformed

    // Método que configura los listeners
    public void configurarSpinners() {
        ChangeListener listener = e -> calcularTotalCaja();

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
        JPN1000.addChangeListener(listener);
    }

    public void calcularTotalCaja() {
        double total = 0;

        // Monedas
        total += ((Integer) JPN_10.getValue()) * 0.10;
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
        total += ((Integer) JPN1000.getValue()) * 1000;

        // Mostrar en el JLabel con formato
        CajaGlobal.dineroInicial = total; // 'total' es el dinero contado de billetes y monedas
        CajaGlobal.dineroAcumulado = CajaGlobal.dineroInicial; // acumulado empieza igual que inicial

        JlabelIniciarCaja.setText(String.format("$%.2f", total));
        JLDineroActual.setText(String.format("$%.2f", CajaGlobal.dineroAcumulado));

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
    private javax.swing.JButton BtnAbrirCaja;
    private javax.swing.JButton BtnCerrarCaja;
    private javax.swing.JLabel JLCierre;
    private javax.swing.JLabel JLDineroActual;
    private javax.swing.JLabel JLEstatus;
    private javax.swing.JSpinner JPN1;
    private javax.swing.JSpinner JPN10;
    private javax.swing.JSpinner JPN100;
    private javax.swing.JSpinner JPN1000;
    private javax.swing.JSpinner JPN2;
    private javax.swing.JSpinner JPN20;
    private javax.swing.JSpinner JPN200;
    private javax.swing.JSpinner JPN5;
    private javax.swing.JSpinner JPN50;
    private javax.swing.JSpinner JPN500;
    private javax.swing.JSpinner JPN_10;
    private javax.swing.JSpinner JPN_20;
    private javax.swing.JSpinner JPN_50;
    private javax.swing.JLabel JlabelIniciarCaja;
    private javax.swing.JLabel NOMBRE_TITULO;
    private javax.swing.JButton btnAbrirC;
    private javax.swing.JButton btnCerrarC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtI;
    // End of variables declaration//GEN-END:variables
}
