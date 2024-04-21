/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;

import individualProjectPack.Frames.ElectionsResultFrame;
import individualProjectPack.Frames.InfoFrame;
import individualProjectPack.DAO.CandidateDAO;
import java.time.LocalDateTime;
import individualProjectPack.Elections;
import individualProjectPack.Exceptions.NoCandidatesException;
import java.sql.SQLException;

/**
 *
 * @author чтепоноза
 */
public class Waiter implements Runnable {
    
    private long delay = 1L;

    private static Waiter waiter = new Waiter();
    
    public static Waiter getInstance(){
        return waiter;
    }
    
    private Waiter(){
        //Конструктор заглушка
    }
    
    private void waitForTime(LocalDateTime dateTime){
        //Здесь ждём какого-то времени, ничего не делаем
        while(LocalDateTime.now().isBefore(dateTime)){
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e){
                throw new RuntimeException(); //Возможно здесь можно что-то умнее придумать, чем рантайм исключение
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
                Elections.getVoteFrame().dispose();
                if(Elections.getCandidateFrame() != null)
                    Elections.getCandidateFrame().dispose();
                if(Elections.getFilterFrame() != null)
                    Elections.getFilterFrame().dispose();
            }

        } catch(NoCandidatesException e){
            InfoFrame infoFrame = new InfoFrame();
            infoFrame.setErrorLabel(e.getMessage());
            infoFrame.setVisible(true);
        } catch(SQLException e){
            InfoFrame infoFrame = new InfoFrame();
            infoFrame.setErrorLabel("Ошибка SQL.");
            infoFrame.setVisible(true);
        }
    }
}
