/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.PaymentController;
import Model.Payment;
import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Geeshanu Gayan
 */
public class PaymentView extends javax.swing.JFrame {
    private PaymentController paymentController;
    private DefaultTableModel tableModel;

    public PaymentView() {
        initComponents();
        setupTableModel();
        paymentController = new PaymentController(this);
        paymentController.loadPayments();
}
       
private void setupTableModel() {
        tableModel = (DefaultTableModel) paymentTable.getModel();
        tableModel.setColumnIdentifiers(new String[]{
            "Payment ID", "Reservation ID", "Amount", "Payment Date", 
            "Payment Method", "Status"
        });
    }


    public void updatePaymentTable(List<Payment> payments) {
        tableModel.setRowCount(0);
        
        for (Payment payment : payments) {
            String status = payment.getStatus();
            if (status == null) {
                status = "pending";
            }
            
            tableModel.addRow(new Object[]{
                payment.getPaymentId(),
                payment.getReservationId(),
                payment.getAmount().toString(),
                payment.getPaymentDate(),
                payment.getPaymentMethod(),
                status
            });
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        paymentTable = new javax.swing.JTable();
        paymentApprove = new javax.swing.JButton();
        paymentReject = new javax.swing.JButton();
        backBtn5 = new javax.swing.JButton();
        loutBtn3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        paymentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "paymentid", "reservationid", "amount", "payment_date", "payment_method"
            }
        ));
        jScrollPane1.setViewportView(paymentTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 850, 390));

        paymentApprove.setBackground(new java.awt.Color(102, 102, 255));
        paymentApprove.setForeground(new java.awt.Color(255, 255, 255));
        paymentApprove.setText("Approve");
        paymentApprove.setBorder(null);
        paymentApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentApproveActionPerformed(evt);
            }
        });
        getContentPane().add(paymentApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 520, 80, 30));

        paymentReject.setBackground(new java.awt.Color(255, 0, 0));
        paymentReject.setForeground(new java.awt.Color(255, 255, 255));
        paymentReject.setText("Reject");
        paymentReject.setBorder(null);
        paymentReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentRejectActionPerformed(evt);
            }
        });
        getContentPane().add(paymentReject, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 520, 80, 30));

        backBtn5.setBorder(null);
        backBtn5.setContentAreaFilled(false);
        backBtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtn5ActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 550, 20, 20));

        loutBtn3.setBorder(null);
        loutBtn3.setContentAreaFilled(false);
        loutBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loutBtn3ActionPerformed(evt);
            }
        });
        getContentPane().add(loutBtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, 20, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/refund.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loutBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loutBtn3ActionPerformed
        // TODO add your handling code here:
             int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            this.dispose(); // Close the current window
            AdminLogin loginView = new AdminLogin(); // Open the login screen
            loginView.setVisible(true);
        }
    }//GEN-LAST:event_loutBtn3ActionPerformed

    private void backBtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtn5ActionPerformed
        // TODO add your handling code here:
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtn5ActionPerformed

    private void paymentApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentApproveActionPerformed
     int selectedRow = paymentTable.getSelectedRow();
        if (selectedRow != -1) {
            int paymentId = (int) paymentTable.getValueAt(selectedRow, 0);
            String currentStatus = (String) paymentTable.getValueAt(selectedRow, 5);
            
            if ("completed".equals(currentStatus)) {
                JOptionPane.showMessageDialog(this,
                    "This payment is already approved.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to approve this payment?",
                "Confirm Approval",
                JOptionPane.YES_NO_OPTION
            );
            
            if (choice == JOptionPane.YES_OPTION) {
                paymentController.approvePayment(paymentId);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Please select a payment to approve.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_paymentApproveActionPerformed

    private void paymentRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentRejectActionPerformed
        // TODO add your handling code here:
     int selectedRow = paymentTable.getSelectedRow();
        if (selectedRow != -1) {
            int paymentId = (int) paymentTable.getValueAt(selectedRow, 0);
            String currentStatus = (String) paymentTable.getValueAt(selectedRow, 5);
            
            if ("rejected".equals(currentStatus)) {
                JOptionPane.showMessageDialog(this,
                    "This payment is already rejected.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to reject this payment?",
                "Confirm Rejection",
                JOptionPane.YES_NO_OPTION
            );
            
            if (choice == JOptionPane.YES_OPTION) {
                paymentController.rejectPayment(paymentId);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Please select a payment to reject.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_paymentRejectActionPerformed

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(() -> {
            new PaymentView().setVisible(true);
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn5;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loutBtn3;
    private javax.swing.JButton paymentApprove;
    private javax.swing.JButton paymentReject;
    private javax.swing.JTable paymentTable;
    // End of variables declaration//GEN-END:variables

}