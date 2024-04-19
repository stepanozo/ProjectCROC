/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;

/**
 *
 * @author чтепоноза
 */
import java.time.LocalDateTime;
import java.util.HashSet;
import individualProjectPack.TableClasses.Candidate;

public class Elections {
    
    private static LocalDateTime dateTimeOfBegining;
    private static LocalDateTime dateTimeOfEnding;
    private static int numberOfCandidates;
    
    private static HashSet<Candidate> candidates;
    
    
    public static int getNumberOfCandidates(){
        return numberOfCandidates;
    }
    public static void setNumberOfCandidates(int newNumberOfCandidates){
        numberOfCandidates = newNumberOfCandidates;
    }
    
    public static LocalDateTime getDateTimeOfBegining(){
        return dateTimeOfBegining;
    }
    
    public static LocalDateTime getDateTimeOfEnding(){
        return dateTimeOfEnding;
    }
     
    public static void setTimeOfBegining(LocalDateTime newDateTimeOfBegining){
        dateTimeOfBegining = newDateTimeOfBegining;
    }
    
    public static void setTimeOfEnding(LocalDateTime newDateTimeOfEnding){
        dateTimeOfEnding = newDateTimeOfEnding;
    }
    
    public static void addCandidate(Candidate candidate){
        candidates.add(candidate);
    }
    
    public static void setCandidates(HashSet<Candidate> newCandidates){
        candidates = newCandidates;
    }
    
    public static void deleteAllCandidates(){
        candidates.clear();
    }
    
    public static HashSet<Candidate>  getCandidates(){
        return candidates;
    }
      
}
