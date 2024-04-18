/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack.TableClasses;

import individualProjectPack.Exceptions.*;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
/**
 *
 * @author чтепоноза
 */
public class Candidate {
    private final String name;
    private final int yearOfBirth;
    private final String placeOfLiving;
    private final String party;
    private final String information;
    private final int votes;
    
    public String getName(){
        return name;
    }
    public int getYearOfBirth(){
        return yearOfBirth;
    }
    public String getPlaceOfLiving() {
        return placeOfLiving;
    }
    public String getParty(){
        return party;
    }
    public String getInformation(){
        return information;
    }
    public int getVotes(){
        return votes;
    } 
    public Candidate(String name, int yearOfBirth, String placeOfLiving, String party, String information, int votes){
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.placeOfLiving = placeOfLiving;
        this.party = party;
        this.information = information;
        this.votes = votes;
    }
    
    public static Candidate fromFile(String path) throws UnableToReadFileException{
        
        File file = new File(path);
        if(!file.exists())
            throw new UnableToReadFileException("Такого файла не существует: ", new File(path).getName());
        
        try( BufferedReader r = new BufferedReader(new FileReader(path))){
            String name = r.readLine();
            int yearOfBirth = Integer.parseInt(r.readLine());
            String placeOfLiving = r.readLine();
            String party = r.readLine();
            String information = r.readLine();
            return new Candidate(name, yearOfBirth, placeOfLiving, party, information, 0);
        }
        catch (Exception e){
            throw new UnableToReadFileException("Не удалось прочитать кандидата из файла: " + file.getName(), file.getName());
        }
    }
    
}
