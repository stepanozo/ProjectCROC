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
import java.util.stream.Stream;

public class Elections {
    
    private static volatile LocalDateTime dateTimeOfBegining;
    private static volatile LocalDateTime dateTimeOfEnding;
    
    private static HashSet<Candidate> candidates = new HashSet();
    private static VoteFrame voteFrame; //Храним эту форму здесь, чтобы после окончания выборов её закрыть и заменить на форму результатов выборов.
    
    public static VoteFrame getVoteFrame(){
        return voteFrame;
    }
    public static void setVoteFrame(VoteFrame newVoteFrame){
        voteFrame = newVoteFrame;
    }
    
    public static int getNumberOfCandidates(){
        return candidates.size();
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
    
    public static double percentageOfVotes(Candidate candidate) {
        int sum = candidates.stream()
                   .mapToInt(Candidate::getVotes) // Преобразуем каждого кандидата в число голосов
                   .sum(); // Суммируем все голоса

        if(sum == 0) return 0;
        return candidate.getVotes()* 100/ sum;
                
    }
    
    private Elections(){
        //Конструктор-заглушка
    }
      
}
