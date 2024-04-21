/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package individualProjectPack.Frames;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import individualProjectPack.Exceptions.*;
import individualProjectPack.TableClasses.*;
import individualProjectPack.DAO.CandidateDAO;
import individualProjectPack.DAO.UserDAO;
import individualProjectPack.Elections;
import individualProjectPack.FilesUtil;
import individualProjectPack.MainClass;
import individualProjectPack.SQLUtil;
import individualProjectPack.Waiter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
/**
 *
 * @author чтепоноза
 */
public class NewElectionsFrame extends javax.swing.JFrame {

    
    private AdminFrame adminFrame;
    
    public void setAdminFrame(AdminFrame adminFrame){
        this.adminFrame = adminFrame;
    }
    /**
     * Creates new form newElectionsForm
     */
    public NewElectionsFrame() {
        setLocationRelativeTo(null);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newElectionsLabel = new javax.swing.JLabel();
        candidateFolderPathField = new javax.swing.JTextField();
        timeBeginField = new javax.swing.JTextField();
        timeEndField = new javax.swing.JTextField();
        timeBeginLabel = new javax.swing.JLabel();
        timeEndLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        formatLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        newElectionsLabel.setText("Укажите путь к папке с текстовыми файлами кандидатов");
        newElectionsLabel.setToolTipText("");

        candidateFolderPathField.setText("Путь к папке");

        timeBeginField.setText("Начало выборов");

        timeEndField.setText("Конец выборов");

        timeBeginLabel.setText("Время начала выборов");

        timeEndLabel.setText("Время конца выборов");

        startButton.setText("Запустить выборы");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        formatLabel.setText("Вводите время в формате ГГГГ-ММ-ДД чч:мм:сс");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formatLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(newElectionsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(candidateFolderPathField)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(timeBeginField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(timeBeginLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(timeEndLabel)
                                .addComponent(timeEndField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(newElectionsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(candidateFolderPathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeBeginLabel)
                    .addComponent(timeEndLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeBeginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeEndField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(formatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    public void blockAdminButtons(){
        startButton.setEnabled(false);
    }
    
    
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
          
        try{ //Проверим, что пользователь всё ещё админ
           if(UserDAO.checkIfAdmin(MainClass.getMyLogin())){
            
                LocalDateTime beginTime =  LocalDateTime.parse(timeBeginField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime endTime = LocalDateTime.parse(timeEndField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                if(endTime.isAfter(beginTime)){
                    
                    
                    if(SQLUtil.checkIfElectionsExist()){ //Здесь мы должны сгрузить в файл информацию о предыдущих выборах, если они были

                        if(SQLUtil.getEndingTime().isBefore(LocalDateTime.now())){
                            //TODO ЗДЕСЬ СДЕЛАТЬ ВЫГРУЗКУ ДАННЫХ В ФАЙЛ
                            savePreviousElections();
                        }
                    }
                    

                    SQLUtil.newTimeOfElections(beginTime, endTime);
                    Elections.setTimeOfBegining(beginTime);
                    Elections.setTimeOfEnding(endTime);
                
                    ArrayList<Candidate> candidates = FilesUtil.getCandidatesFromFiles(candidateFolderPathField.getText());
                    Elections.deleteAllCandidates();
                    UserDAO.forgetAllVotes();
                    SQLUtil.newCandidateTable();
                    for(Candidate candidate: candidates){
                        CandidateDAO.createCandidate(candidate); //Заполняем таблицу кандидатов
                        Elections.addCandidate(candidate);
                    }
                    
                    //Теперь запустим ожидание конца выборов.
                    MainClass.setWaiterThread(new Thread(Waiter.getInstance()));
                    MainClass.getWaiterThread().start();
                    
                    
                    dispose();
                }   
                else{
                    InfoFrame errorFrame = new InfoFrame();
                    errorFrame.setErrorLabel("Окончание выборов должно быть позже начала.");
                    errorFrame.setVisible(true);
                }
            }
            else {
                blockAdminButtons();
                adminFrame.blockAdminButtons();
                adminFrame.notAdminAnymore();
            }
        } catch (DateTimeParseException e){
           InfoFrame errorFrame = new InfoFrame();
           errorFrame.setErrorLabel("Неверно введена дата.");
           errorFrame.setVisible(true);
        }
        catch (InvalidInsertException |
                NoSuchFolderException |
                UnableToReadFileException | 
                TooManyCandidatesException |
                InvalidTableDestroyException |
                NoElectionsException | 
                NoCandidatesException |
                NoUsersException e){
           InfoFrame errorFrame = new InfoFrame();
           errorFrame.setErrorLabel(e.getMessage());
           errorFrame.setVisible(true);
        }catch (SQLException e){
                InfoFrame errorFrame = new InfoFrame();
                errorFrame.setErrorLabel("Произошла SQL-ошибка.");
                errorFrame.setVisible(true);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        adminFrame.enableAllButtons();
    }//GEN-LAST:event_formWindowClosed

    
    private void savePreviousElections() throws SQLException, NoElectionsException, NoCandidatesException, NoUsersException{
        File filePath = new File("Save");
        filePath.mkdir();
        
        int i = 1;
        File file = new File(filePath + "\\Elections" + i +".txt");
        while(file.exists()){
            file = new File(filePath + "\\Election" + i +".txt");
            i++;
        }
        try(FileWriter writer = new FileWriter(file.getPath())) {;
            
            writer.write("Начало выборов: " + SQLUtil.getBeginingTime().toString() + "\n");
            writer.append("Конец выборов: " + SQLUtil.getEndingTime().toString() + "\n");
            writer.append("Результаты выборов: \n");
            
            HashSet<Candidate> candidates = CandidateDAO.getCandidates();
 
            for(Candidate candidate : candidates)
                writer.append(candidate.getName() + " - " + Elections.percentageOfVotes(candidate, candidates) + "% голосов \n");
            
          
            HashSet<User> users = UserDAO.getUsers();
            
            int sumVotes = users.stream()
                   .mapToInt(user -> (user.getVoted()) ? 1 : 0) 
                   .sum(); 
            writer.append("Всего человек проголосовало " +sumVotes); 
            writer.append('\n');
            
            writer.append("Явка составила: " + sumVotes* 100/users.size() + " %\n");
            
            writer.append("\nПриходили на выборы:\n ");
            for(User user : users)
                if(user.getVoted())
                    writer.append(user.toString() + "\n");
             
            writer.append("\n\nУчаствовавшие кандидаты: \n\n");
            for(Candidate candidate : candidates) {
                writer.append(candidate.toString() + "\n\n");
            }
            writer.append("\n");
             
             
            
            
            
            writer.flush();
        } catch (IOException e) {
            InfoFrame errorFrame = new InfoFrame();
           errorFrame.setErrorLabel("Не получилось создать файл");
           errorFrame.setVisible(true);
        } 
    }
    
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
            java.util.logging.Logger.getLogger(NewElectionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewElectionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewElectionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewElectionsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewElectionsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField candidateFolderPathField;
    private javax.swing.JLabel formatLabel;
    private javax.swing.JLabel newElectionsLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField timeBeginField;
    private javax.swing.JLabel timeBeginLabel;
    private javax.swing.JTextField timeEndField;
    private javax.swing.JLabel timeEndLabel;
    // End of variables declaration//GEN-END:variables
}
