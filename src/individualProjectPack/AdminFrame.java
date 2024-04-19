/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package individualProjectPack;

import individualProjectPack.DAO.*;
import individualProjectPack.Exceptions.NoSuchUserException;
import individualProjectPack.TableClasses.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
/**
 *
 * @author чтепоноза
 */
public class AdminFrame extends javax.swing.JFrame {

    /**
     * Creates new form AdminFrame
     */
    public AdminFrame() {
        initComponents();
    }
    public void enableAllButtons(){
        newElectionsButton.setEnabled(true);
        voteButton.setEnabled(true);
        addAdminRightsButton.setEnabled(true);
        removeAdminRightsButton.setEnabled(true);
        exitButton.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        voteButton = new javax.swing.JButton();
        newElectionsButton = new javax.swing.JButton();
        addAdminRightsButton = new javax.swing.JButton();
        removeAdminRightsButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        voteButton.setText("Голосовать");
        voteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voteButtonActionPerformed(evt);
            }
        });

        newElectionsButton.setText("Новые выборы");
        newElectionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newElectionsButtonActionPerformed(evt);
            }
        });

        addAdminRightsButton.setText("Назначить админа");
        addAdminRightsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAdminRightsButtonActionPerformed(evt);
            }
        });

        removeAdminRightsButton.setText("Убрать админа");
        removeAdminRightsButton.setToolTipText("");
        removeAdminRightsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAdminRightsButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Выйти");
        exitButton.setToolTipText("");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(voteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeAdminRightsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addAdminRightsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newElectionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(voteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(newElectionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addAdminRightsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeAdminRightsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void disableAllButtons(){
        voteButton.setEnabled(false);
        newElectionsButton.setEnabled(false);
        addAdminRightsButton.setEnabled(false); 
        removeAdminRightsButton.setEnabled(false);
        exitButton.setEnabled(false);
    }
    
    public void notAdminAnymore(){
         InfoFrame errorFrame = new InfoFrame();
         errorFrame.setErrorLabel("У вас больше нет прав админа.");
         errorFrame.setVisible(true);
    }
    
    public void blockAdminButtons(){
        newElectionsButton.setEnabled(false);
        addAdminRightsButton.setEnabled(false); 
        removeAdminRightsButton.setEnabled(false);
    }
    
    private void newElectionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newElectionsButtonActionPerformed
        try{ //Проверим, что пользователь всё ещё админ
            if(UserDAO.checkIfAdmin(MainClass.getMyLogin())){
                NewElectionsFrame newElectionsForm = new NewElectionsFrame();
                newElectionsForm.setVisible(true);
                newElectionsForm.setAdminFrame(this);
                disableAllButtons();
            } else {
            blockAdminButtons();
            notAdminAnymore();
            }
        } catch (SQLException e){
                InfoFrame errorFrame = new InfoFrame();
                errorFrame.setErrorLabel("Произошла SQL-ошибка.");
                errorFrame.setVisible(true);
        }
           
    }//GEN-LAST:event_newElectionsButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        new LogInFrame().setVisible(true);
        dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void addAdminRightsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAdminRightsButtonActionPerformed
        
        try{ //Проверим, что пользователь всё ещё админ
            if(UserDAO.checkIfAdmin(MainClass.getMyLogin())){
                AddAdminRightsFrame addAdminRightsFrame = new AddAdminRightsFrame();
                addAdminRightsFrame.setVisible(true);
                addAdminRightsFrame.setAdminFrame(this);
                disableAllButtons();
            } else {
            blockAdminButtons();
            notAdminAnymore();
            }
        } catch (SQLException e){
                InfoFrame errorFrame = new InfoFrame();
                errorFrame.setErrorLabel("Произошла SQL-ошибка.");
                errorFrame.setVisible(true);
        }
        
       
    }//GEN-LAST:event_addAdminRightsButtonActionPerformed

    private void removeAdminRightsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAdminRightsButtonActionPerformed
        try{ //Проверим, что пользователь всё ещё админ
            if(UserDAO.checkIfAdmin(MainClass.getMyLogin())){
                RemoveAdminRightsFrame removeAdminRightsFrame = new RemoveAdminRightsFrame();
                removeAdminRightsFrame.setVisible(true);
                removeAdminRightsFrame.setAdminFrame(this);
                disableAllButtons();
            } else {
                blockAdminButtons();
                notAdminAnymore();
            }
        } catch (SQLException e){
                InfoFrame errorFrame = new InfoFrame();
                errorFrame.setErrorLabel("Произошла SQL-ошибка.");
                errorFrame.setVisible(true);
        }
        
        
        
    }//GEN-LAST:event_removeAdminRightsButtonActionPerformed

    private void voteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voteButtonActionPerformed
        try{
            if(SQLUtil.checkIfElectionsExist() &&
                LocalDateTime.now().isAfter(Elections.getDateTimeOfBegining()) )
            {
                if(LocalDateTime.now().isBefore(Elections.getDateTimeOfEnding())){
                    VoteFrame voteFrame = new VoteFrame();
                    voteFrame.setVisible(true);
                } else{
                    ElectionsResultFrame resultFrame = new ElectionsResultFrame();
                    resultFrame.setVisible(true);
                    }
                dispose();
            }
            else{
                InfoFrame infoFrame = new InfoFrame();
                infoFrame.setErrorLabel("Выборы в данный момент не проводятся");
                infoFrame.setVisible(true);
            }
        } catch(SQLException e){
                InfoFrame infoFrame = new InfoFrame();
                infoFrame.setErrorLabel("Произошла ошибка SQL.");
                infoFrame.setVisible(true);
        }
    }//GEN-LAST:event_voteButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addAdminRightsButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton newElectionsButton;
    private javax.swing.JButton removeAdminRightsButton;
    private javax.swing.JButton voteButton;
    // End of variables declaration//GEN-END:variables
}
