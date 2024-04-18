/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;

import java.sql.*;
import individualProjectPack.Exceptions.*;
import individualProjectPack.DAO.UserDAO;
import individualProjectPack.TableClasses.User;

/**
 *
 * @author чтепоноза
 */
public class MainClass {
    
   
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
            java.util.logging.Logger.getLogger(LogInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogInFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        
       try{
           Connection connection = DriverManager.
                   getConnection("jdbc:h2:/test", "sa", "");
               // getConnection("jdbc:h2:mem:~/test", "sa", "");
           
            ConnectionUtil.setConnection(connection);
            
            try{
                TableCreator.createUserTable();
                UserDAO.createUser(User.hashAndCreate("admin", "admin", true, true));
                TableCreator.createCandidateTable();
                TableCreator.createElectionTimeTable();
            } catch(InvalidTableCreationException e){
                //Тут ничего не делаем, т.к. если не получилось создать таблицы, значит они уже есть.
                //Но вообще по-хорошему надо как-то сделать проверку на существование таблиц.
            }
            
            if(SQLUtil.checkIfElectionsExist()){
                Elections.setTimeOfBegining(SQLUtil.getBeginingTime());
                Elections.setTimeOfBegining(SQLUtil.getEndingTime());
            }
            new LogInFrame().setVisible(true);
       } catch (
               InvalidInsertException |
               NoElectionsException e){
           ErrorFrame errorFrame = new ErrorFrame();
           errorFrame.setErrorLabel(e.getMessage());
           errorFrame.setVisible(true);
       } catch (SQLException e){
           new ErrorFrame().setVisible(true);
       }
        
        
        
    }
    
}
