/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;

import individualProjectPack.Frames.ElectionsResultFrame;
import individualProjectPack.Frames.InfoFrame;
import individualProjectPack.DAO.CandidateDAO;
import java.time.LocalDateTime;
import individualProjectPack.Exceptions.NoCandidatesException;
import java.sql.SQLException;

/**
 *
 * @author чтепоноза
 */
public class Waiter implements Runnable {
    
    private static long delay = 1L;
    private static volatile boolean  exit = false;

    private static Waiter waiter = new Waiter();
    
    public static Waiter getInstance(){
        return waiter;
    }
    
    public static boolean getExit(){
        return exit;
    }
    
    public static void setExit(boolean value){
        exit = value;
    }
    
    private Waiter(){
        //Конструктор заглушка
    }
    
    private void waitForTime(LocalDateTime dateTime){
        //Здесь ждём какого-то времени, ничего не делаем
        while(LocalDateTime.now().isBefore(dateTime) && !exit){
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e){
                MainClass.showInfoFrame("Действующие выборы остановлены.");
            }
        }
    }
    
    
    @Override
    public void run() {
        waitForTime(Elections.getDateTimeOfEnding()); //Сначала ждём конца выборов
        try{
            Elections.setCandidates(CandidateDAO.getCandidates());
            if(Elections.getVoteFrame() != null){
                new ElectionsResultFrame().setVisible(true);
                Elections.getVoteFrame().setMustCloseConnection(false);
                Elections.getVoteFrame().dispose();
                if(Elections.getCandidateFrame() != null)
                    Elections.getCandidateFrame().dispose();
                if(Elections.getFilterFrame() != null)
                    Elections.getFilterFrame().dispose();
            }

        } catch(NoCandidatesException e){
            MainClass.showInfoFrame(e.getMessage());
        } catch(SQLException e){
            MainClass.showInfoFrame("Ошибка SQL");
        }
    }
}
