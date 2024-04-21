/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package individualProjectPack.Frames;

import individualProjectPack.Elections;
import java.util.stream.Collectors;
import java.util.*;
import java.util.function.Predicate;
import individualProjectPack.TableClasses.*;
import java.time.LocalDateTime;


/**
 *
 * @author чтепоноза
 */
public class FilterFrame extends javax.swing.JFrame {

    
    private VoteFrame voteFrame;
    
    public VoteFrame getVoteFrame(){
        return voteFrame;
    }
    public void setVoteFrame(VoteFrame voteFrame){
        this.voteFrame = voteFrame;
    }
    /**
     * Creates new form FilterFrame
     */
    public FilterFrame() {
        setLocationRelativeTo(null);
        initComponents();
        Elections.setFilterFrame(this);
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
        ageComboBox = new javax.swing.JComboBox<>();
        ageCheckbox = new javax.swing.JCheckBox();
        ageTextField = new javax.swing.JTextField();
        partyCheckbox = new javax.swing.JCheckBox();
        partyTextField = new javax.swing.JTextField();
        cityCheckbox = new javax.swing.JCheckBox();
        cityTextField = new javax.swing.JTextField();
        applyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        ageComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "старше", "моложе" }));

        ageCheckbox.setText("По возрасту:");

        ageTextField.setText("возраст");

        partyCheckbox.setText("По партии");

        partyTextField.setText("партия");

        cityCheckbox.setText("По городу проживания");

        cityTextField.setText("город");

        applyButton.setText("Применить");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partyCheckbox)
                            .addComponent(cityCheckbox)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ageCheckbox)
                                .addGap(36, 36, 36)
                                .addComponent(ageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(partyTextField)
                            .addComponent(ageTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(cityTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(applyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageCheckbox)
                    .addComponent(ageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(partyCheckbox)
                    .addComponent(partyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityCheckbox)
                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(applyButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        voteFrame.enableAllButtons(true);
    }//GEN-LAST:event_formWindowClosed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed

        try{

            Predicate<Candidate> pred= candidate -> true;

            if(ageCheckbox.isSelected()){
                if(ageComboBox.getSelectedIndex()==0)
                    pred = pred.and(candidate -> LocalDateTime.now().getYear() - candidate.getYearOfBirth() > Integer.parseInt(ageTextField.getText()));
                else if(ageComboBox.getSelectedIndex() == 1)
                    pred = pred.and(candidate -> LocalDateTime.now().getYear() - candidate.getYearOfBirth() < Integer.parseInt(ageTextField.getText()));
            }

            if(partyCheckbox.isSelected())
                pred = pred.and(candidate -> Objects.equals(candidate.getParty(), partyTextField.getText()));
            if(cityCheckbox.isSelected())
                pred = pred.and(candidate -> Objects.equals(candidate.getPlaceOfLiving(), partyTextField.getText()));


            voteFrame.showCandidates(
                    Elections.getCandidates().stream().filter(pred)
                    .collect(Collectors.toCollection(HashSet::new)) //Здесь преобразуем в HashSet, который принимается методом showCandidates
                    );
            dispose();
        } catch(NumberFormatException e){
            InfoFrame infoFrame = new InfoFrame();
            infoFrame.setErrorLabel("Неверно введен возраст кандидата.");
        }
    }//GEN-LAST:event_applyButtonActionPerformed

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
            java.util.logging.Logger.getLogger(FilterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FilterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FilterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FilterFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FilterFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ageCheckbox;
    private javax.swing.JComboBox<String> ageComboBox;
    private javax.swing.JTextField ageTextField;
    private javax.swing.JButton applyButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox cityCheckbox;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox partyCheckbox;
    private javax.swing.JTextField partyTextField;
    // End of variables declaration//GEN-END:variables
}
