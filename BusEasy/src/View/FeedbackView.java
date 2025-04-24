/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package View;

import Controller.FeedbackController;
import Model.Feedback;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Geeshanu Gayan
 */
public class FeedbackView extends javax.swing.JFrame {

private FeedbackController feedbackController;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public FeedbackView() {
    initComponents();
    tableModel = (DefaultTableModel) feedbackTable.getModel();
    feedbackController = new FeedbackController();
    setupTableModel();
    viewFeedbacks();
}

    private void setupTableModel() {
        tableModel.setColumnIdentifiers(new String[]{
            "Feedback ID", "Reservation ID", "Message","Created At"
        });
    }
    
    private void viewFeedbacks() {
        DefaultTableModel model = (DefaultTableModel) feedbackTable.getModel();
        List<Feedback> feedbacks = feedbackController.getAllFeedbackDetails();
        model.setRowCount(0);

        for (Feedback feedback : feedbacks) {
            Vector<Object> row = new Vector<>();
            row.add(feedback.getFeedbackId());
            row.add(feedback.getReservationId());
            row.add(feedback.getMessage());
            row.add(feedback.getCreatedAt());
            model.addRow(row);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loutBtn2 = new javax.swing.JButton();
        backbtn4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        feedbackTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loutBtn2.setBorder(null);
        loutBtn2.setContentAreaFilled(false);
        loutBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loutBtn2ActionPerformed(evt);
            }
        });
        getContentPane().add(loutBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 10, 20));

        backbtn4.setBorder(null);
        backbtn4.setContentAreaFilled(false);
        backbtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtn4ActionPerformed(evt);
            }
        });
        getContentPane().add(backbtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 550, 20, 20));

        feedbackTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "feedbackid", "reservationid", "message", "created_at"
            }
        ));
        jScrollPane1.setViewportView(feedbackTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 860, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/feedbacks.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backbtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtn4ActionPerformed
        // TODO add your handling code here:
        
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtn4ActionPerformed

    private void loutBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loutBtn2ActionPerformed
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
    }//GEN-LAST:event_loutBtn2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    java.awt.EventQueue.invokeLater(() -> {
            new FeedbackView().setVisible(true);
        });
        
    }
    

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn4;
    private javax.swing.JTable feedbackTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loutBtn2;
    // End of variables declaration//GEN-END:variables

}