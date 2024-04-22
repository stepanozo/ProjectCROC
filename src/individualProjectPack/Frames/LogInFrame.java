/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individualProjectPack.Frames;

import individualProjectPack.ConnectionUtil;
import java.sql.*;
import individualProjectPack.DAO.*;
import individualProjectPack.Elections;
import individualProjectPack.MainClass;
import individualProjectPack.SQLUtil;
import java.time.LocalDateTime;
/**
 *
 * @author student
 */
public class LogInFrame extends javax.swing.JFrame {

    
    private boolean mustCloseConnection;
    
    /**
     * Creates new form logInFrame
     */
    public LogInFrame() {
        mustCloseConnection = true;
        setLocationRelativeTo(null);
        initComponents();
        connectionErrorLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wrongPasswordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        registrationButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        loginField = new javax.swing.JTextField();
        connectionErrorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        loginButton.setText("Войти");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registrationButton.setText("Регистрация");
        registrationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrationButtonActionPerformed(evt);
            }
        });

        passwordLabel.setText("Введите пароль:");

        loginLabel.setText("Введите логин:");

        loginField.setText("Ваш логин");

        connectionErrorLabel.setText("Произошла ошибка. Выполните вход ещё раз.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectionErrorLabel)
                    .addComponent(wrongPasswordLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordLabel)
                            .addComponent(loginLabel))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loginButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(registrationButton))
                            .addComponent(passwordField)
                            .addComponent(loginField))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addGap(18, 18, 18)
                .addComponent(wrongPasswordLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton)
                    .addComponent(registrationButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(connectionErrorLabel)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrationButtonActionPerformed
        RegistrationFrame registrationFrame = new RegistrationFrame();
        registrationFrame.setVisible(true);
        registrationButton.setEnabled(false);
        loginButton.setEnabled(false);
        passwordField.setEnabled(false);
        loginField.setEnabled(false);
        registrationFrame.setLoginFrame(this);
    }//GEN-LAST:event_registrationButtonActionPerformed

    public void showConnectionErrorMessage(){
        connectionErrorLabel.setVisible(true);
    }
    
    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
       try{
           String password = String.valueOf(passwordField.getPassword());
           String login = loginField.getText();
           if(isLoginCorrect(login)){
               if(isPasswordCorrect(password)){
                    if(UserDAO.successfulLogIn(login, password)){

                        MainClass.setMyLogin(login);
                        //Проверим, обычный это пользователь или админ
                        if(UserDAO.checkIfAdmin(login)){
                            AdminFrame adminFrame = new AdminFrame();
                            adminFrame.setVisible(true);
                            mustCloseConnection = false;
                            dispose();
                        }else{ 
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
                                mustCloseConnection = false;
                                dispose();
                            }
                            else{
                                MainClass.showInfoFrame("Выборы в данный момент не проводятся.");
                            }
                        }
                    }
                    wrongPasswordLabel.setText("Неверный логин или пароль");
               } else  wrongPasswordLabel.setText("Некорректный пароль");
           } else  wrongPasswordLabel.setText("Некорректный логин");
       } catch(SQLException e){
           wrongPasswordLabel.setText("Ошибка входа.");
       }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        try{
            if(mustCloseConnection)
                ConnectionUtil.closeConnection();
        } catch (SQLException e) {MainClass.showInfoFrame("Не удалось закрыть соединение");}
    }//GEN-LAST:event_formWindowClosed

    private boolean isLoginCorrect(String login){
        return login.matches("[a-zA-Z0-9_]+") && login.length() >=5;
    }
    private boolean isPasswordCorrect(String password){
        return password.matches("[a-zA-Z0-9_]+") && password.length() >=5;
    }
    
    public void enableRegistrationButton(){
        registrationButton.setEnabled(true);
    }
    public void enableLoginButton(){
        loginButton.setEnabled(true);
    }
    public void enableLoginField(){
        loginField.setEnabled(true);
    }
    public void enablePasswordField(){
        passwordField.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel connectionErrorLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JTextField loginField;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton registrationButton;
    private javax.swing.JLabel wrongPasswordLabel;
    // End of variables declaration//GEN-END:variables
}
