/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;

import individualProjectPack.Frames.LogInFrame;
import individualProjectPack.Frames.InfoFrame;
import individualProjectPack.DAO.CandidateDAO;
import java.sql.*;
import individualProjectPack.Exceptions.*;
import individualProjectPack.DAO.UserDAO;
import individualProjectPack.TableClasses.*;
import java.time.LocalDateTime;

/**
 *
 * @author чтепоноза
 */
public class MainClass {
    
   
    
    private static String myLogin;
    private static Thread waiterThread;
    
    public static String getMyLogin(){
        return myLogin;
    }
    public static void setMyLogin(String login){
        myLogin = login;
    }
    public static Thread getWaiterThread(){
        return waiterThread;
    }
    public static void setWaiterThread(Thread thread){
        waiterThread = thread;
    }
    
    
    public static void clearTables(){
        //Это обычно не нужно. Тут мы просто криво созданные таблицы удаляем. Удалить потом.
        try{
            TableDestroyer.dropAllTable();
        } catch(InvalidTableDestroyException e){
            InfoFrame errorFrame = new InfoFrame();
            errorFrame.setErrorLabel(e.getMessage());
            errorFrame.setVisible(true);
        }
    }
    
    public static void showInfoFrame(String string){
            InfoFrame errorFrame = new InfoFrame();
            errorFrame.setErrorLabel(string);
            errorFrame.setVisible(true);
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
            String url = "jdbc:h2:file:./database/myDatabase";
            String user = "stepanozo";
            String password = "stepanozo";
            
            ConnectionUtil.setUrl(url);
            ConnectionUtil.setUser(user);
            ConnectionUtil.setPassword(password);
            ConnectionUtil.setConnection(DriverManager.getConnection(url, user,password));
            TableCreator.createUserTable();
            UserDAO.createUserIfNotExists(User.hashAndCreate("admin", "admin", true, true));
            TableCreator.createCandidateTable();
            TableCreator.createElectionTimeTable();
            
            if(SQLUtil.checkIfElectionsExist()){
                Elections.setTimeOfBegining(SQLUtil.getBeginingTime());
                Elections.setTimeOfEnding(SQLUtil.getEndingTime());
                Elections.setCandidates(CandidateDAO.getCandidates());
                
                if(Elections.getDateTimeOfEnding().isAfter(LocalDateTime.now())){
                    //Теперь запустим ожидание конца выборов.
                    waiterThread = new Thread(Waiter.getInstance());
                    waiterThread.start();
                }
                
            }
            new LogInFrame().setVisible(true);
        } catch (
               InvalidTableCreationException |
               NoElectionsException |
               NoCandidatesException |
               InvalidInsertException e){
            showInfoFrame(e.getMessage());
        } catch (SQLException e){
           showInfoFrame("SQL-ошибка.");
        }     
    }
}
