/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.RefundController;
import Model.Refund;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
 * @author Geeshanu Gayan
 */
public class RefundView extends javax.swing.JFrame {
    private RefundController refundController;
    private DefaultTableModel tableModel;

    public RefundView() {
        initComponents();
        refundController = new RefundController(this);
        tableModel = (DefaultTableModel) refundTable.getModel();
        setupTableModel();
        loadRefunds();
    }

    private void setupTableModel() {
        tableModel.setColumnIdentifiers(new String[]{
            "Refund ID", "Reservation ID", "Status"
        });
    }

    public void updateRefundTable(List<Refund> refunds) {
        tableModel.setRowCount(0); // Clear existing data
        for (Refund refund : refunds) {
            Object[] row = {
                refund.getRefundId(),
                refund.getReservationId(),
                refund.getStatus()
            };
            tableModel.addRow(row);
        }
    }

    private void loadRefunds() {
        refundController.loadRefunds();
    }

    @SuppressWarnings("unchecked")
    /**
     * Creates new form RefundView
     */
    
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backBtn6 = new javax.swing.JButton();
        loutBtn4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        refundTable = new javax.swing.JTable();
        refundApprove = new javax.swing.JButton();
        refundReject = new javax.swing.JButton();
        clearBtn2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backBtn6.setBorder(null);
        backBtn6.setContentAreaFilled(false);
        backBtn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtn6ActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 540, 30, 30));

        loutBtn4.setBorder(null);
        loutBtn4.setContentAreaFilled(false);
        loutBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loutBtn4ActionPerformed(evt);
            }
        });
        getContentPane().add(loutBtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 20, 30));

        refundTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "refundid", "reservationid", " status"
            }
        ));
        jScrollPane1.setViewportView(refundTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 860, -1));

        refundApprove.setBackground(new java.awt.Color(102, 102, 255));
        refundApprove.setForeground(new java.awt.Color(255, 255, 255));
        refundApprove.setText("Approve");
        refundApprove.setBorder(null);
        refundApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refundApproveActionPerformed(evt);
            }
        });
        getContentPane().add(refundApprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, 80, 40));

        refundReject.setBackground(new java.awt.Color(255, 0, 102));
        refundReject.setForeground(new java.awt.Color(255, 255, 255));
        refundReject.setText("Reject");
        refundReject.setBorder(null);
        refundReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refundRejectActionPerformed(evt);
            }
        });
        getContentPane().add(refundReject, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, 80, 40));

        clearBtn2.setBackground(new java.awt.Color(102, 0, 102));
        clearBtn2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        clearBtn2.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn2.setText("Clear");
        clearBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtn2ActionPerformed(evt);
            }
        });
        getContentPane().add(clearBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 540, 70, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/payment.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtn6ActionPerformed
        // TODO add your handling code here:
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtn6ActionPerformed

    private void loutBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loutBtn4ActionPerformed
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
    }//GEN-LAST:event_loutBtn4ActionPerformed

    private void refundApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refundApproveActionPerformed
        // TODO add your handling code here:

        int selectedRow = refundTable.getSelectedRow();
    if (selectedRow != -1) {
        int refundId = (int) refundTable.getValueAt(selectedRow, 0); 
        String currentStatus = (String) refundTable.getValueAt(selectedRow, 2); 
        
        if ("approved".equals(currentStatus)) {
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
            refundController.approveRefund(refundId);
        }
    } else {
        JOptionPane.showMessageDialog(this,
            "Please select a Refund to approve.",
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_refundApproveActionPerformed

    private void refundRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refundRejectActionPerformed
        // TODO add your handling code here:
         int selectedRow = refundTable.getSelectedRow();
    if (selectedRow != -1) {
        int refundId = (int) refundTable.getValueAt(selectedRow, 0); 
        String currentStatus = (String) refundTable.getValueAt(selectedRow, 2); 
        
        if ("rejected".equals(currentStatus)) {
            JOptionPane.showMessageDialog(this,
                "This refund is already rejected.",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to reject this refund?",
            "Confirm Rejection",
            JOptionPane.YES_NO_OPTION
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            refundController.rejectRefund(refundId);
        }
    } else {
        JOptionPane.showMessageDialog(this,
            "Please select a refund to reject.",
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_refundRejectActionPerformed

    private void clearBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtn2ActionPerformed
        // TODO add your handling code here:
         tableModel.setRowCount(0);
    }//GEN-LAST:event_clearBtn2ActionPerformed

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
            java.util.logging.Logger.getLogger(RefundView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RefundView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RefundView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RefundView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    
       java.awt.EventQueue.invokeLater(() -> {
            new RefundView().setVisible(true);
        });
   
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn6;
    private javax.swing.JButton clearBtn2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loutBtn4;
    private javax.swing.JButton refundApprove;
    private javax.swing.JButton refundReject;
    private javax.swing.JTable refundTable;
    // End of variables declaration//GEN-END:variables

}