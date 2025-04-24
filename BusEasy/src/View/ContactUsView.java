/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.ContactUs;
import Controller.ContactUsController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Geeshanu Gayan
 */
public class ContactUsView extends javax.swing.JFrame {
    private ContactUsController contactUsController;
    private DefaultTableModel tableModel;

    public ContactUsView() {
        initComponents();
        
        // Initialize the controller
        contactUsController = new ContactUsController();
        
        // Initialize the table model
        tableModel = (DefaultTableModel) contactTable.getModel();
        setupTableModel();
        // Display messages
        displayMessages();
  
    }
    
    private void setupTableModel() {
        tableModel.setColumnIdentifiers(new String[]{
            "Contact ID", "Name", "Email","Message","Created At"
        });
    }
    
    /**
     * Populates the table with data from a list of ContactUs objects.
     * @param contactList List of ContactUs objects to display in the table.
     */
    
    private void displayMessages(){
        try {
            List<ContactUs> contactList = contactUsController.getAllContactUsDetails();
            tableModel.setRowCount(0); // Clear existing rows

            for (ContactUs contact : contactList) {
                Vector<Object> row = new Vector<>();
                row.add(contact.getContactId());
                row.add(contact.getEmail());
                row.add(contact.getName());
                row.add(contact.getMessage());
                row.add(contact.getCreatedAt());
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading contact messages: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    /**
     * Provides access to the refresh button for event handling.
     * @return The refresh button component.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backBtn2 = new javax.swing.JButton();
        logoutBtn2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        contactTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backBtn2.setBorder(null);
        backBtn2.setContentAreaFilled(false);
        backBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtn2ActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 550, 20, 20));

        logoutBtn2.setBackground(new java.awt.Color(153, 0, 255));
        logoutBtn2.setBorder(null);
        logoutBtn2.setContentAreaFilled(false);
        logoutBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtn2ActionPerformed(evt);
            }
        });
        getContentPane().add(logoutBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 30, 30));

        contactTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "contactid", "name", "email", "created_at"
            }
        ));
        jScrollPane1.setViewportView(contactTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 830, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/contactUs.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtn2ActionPerformed
        // TODO add your handling code here:
        AdminDashboard dashboard = new AdminDashboard();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtn2ActionPerformed

    private void logoutBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtn2ActionPerformed
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
    }//GEN-LAST:event_logoutBtn2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
       java.awt.EventQueue.invokeLater(() -> {
            new ContactUsView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn2;
    private javax.swing.JTable contactTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutBtn2;
    // End of variables declaration//GEN-END:variables

}