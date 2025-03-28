/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//package com.mycompany.interfaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author g_ste
 */
public class VentanaConsulta extends javax.swing.JFrame {
    BaseDatos bd;
    DefaultTableModel m;
    
    /**
     * Creates new form VentanaConsulta
     */
    public VentanaConsulta() {
        initComponents();
        
        bd=new BaseDatos();
        try {
            if(bd.conexion.isClosed()){
                System.out.println("Noo!!!. Se cerro");
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(VentanaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        m=(DefaultTableModel) tblProductos.getModel();
        actualizarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoB = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        Regresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jLabel1.setText("CONSULTAS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        txtCodigoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBActionPerformed(evt);
            }
        });
        getContentPane().add(txtCodigoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, -1));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 680, 270));

        Regresar.setText("Regresar");
        Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarActionPerformed(evt);
            }
        });
        getContentPane().add(Regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
         bd.buscarProducto(txtCodigoB.getText());
        actualizarTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtCodigoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBActionPerformed
       /*txtCodigoB.getDocument().addDocumentListener(new DocumentListener() {
    public void insertUpdate(DocumentEvent e) {
        actualizarTabla();
    }

    public void removeUpdate(DocumentEvent e) {
        actualizarTabla();
    }

    public void changedUpdate(DocumentEvent e) {
        actualizarTabla();
    }
});*/

    }//GEN-LAST:event_txtCodigoBActionPerformed

    private void RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarActionPerformed
        // TODO add your handling code here:
        VentanaMenuEmpleado v=new VentanaMenuEmpleado();
        v.bd=bd;
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    
    DefaultTableModel modeloTabla = new DefaultTableModel(
    new String[]{"Código de Barras", "Nombre", "Cantidad", "Precio"}, 0
);
    
    /*
     public void actualizarTabla() {
    String criterio = txtCodigoB.getText().trim();

    String query = "SELECT codigoBarras, nombre, stock, precioVenta FROM Productos " +
                   "WHERE nombre LIKE ? OR codigoBarras LIKE ?";

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setString(1, "%" + criterio + "%");
        pst.setString(2, "%" + criterio + "%");

        ResultSet rs = pst.executeQuery();

        // Limpiar tabla
        DefaultTableModel modelo = (DefaultTableModel) tblMostrar.getModel();
        modelo.setRowCount(0);

        while (rs.next()) {
            Object[] fila = {
                rs.getString("codigoBarras"),
                rs.getString("nombre"),
                rs.getInt("stock"),
                rs.getBigDecimal("precioVenta")
            };
            modelo.addRow(fila);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar productos: " + ex.getMessage());
    }
}*/

     
     
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
            java.util.logging.Logger.getLogger(VentanaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaConsulta().setVisible(true);
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Regresar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCodigoB;
    // End of variables declaration//GEN-END:variables
}
