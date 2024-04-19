/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;
/**
 *
 * @author чтепоноза
 */
import java.sql.*;
import individualProjectPack.Exceptions.InvalidTableCreationException;

public class TableCreator {
    
    public TableCreator(){
        //Конструктор-заглушка, чтобы экземпляр класса случайно не был создан
    }
      
    public static void createUserTable() throws InvalidTableCreationException {
        try {
            Statement statement = ConnectionUtil.getConnection().createStatement();
            statement.execute(
                    "CREATE TABLE Users(" +
                            "                   login VARCHAR(255) NOT NULL," +
                            "                   passwordHash VARCHAR(255) NOT NULL, " +
                            "                   voted BOOLEAN NOT NULL, " +
                            "                   isAdmin BOOLEAN NOT NULL) ");
        } catch(SQLException e){
            throw new InvalidTableCreationException("Не удалось создать таблицу пользователей.");
        }
    }
    
    public static void createCandidateTable() throws InvalidTableCreationException {
        try {
            Statement statement = ConnectionUtil.getConnection().createStatement();
            statement.execute(
                    "CREATE TABLE Candidates(" +
                            "                   name VARCHAR(255) NOT NULL, " +
                            "                   yearOfBirth INT, " +
                            "                   placeOfLiving VARCHAR(255), " +
                            "                   party VARCHAR(255), " +
                            "                   information VARCHAR(1000),  "+
                            "                   votes INT);" ) ;
        } catch(SQLException e){
            throw new InvalidTableCreationException("Не удалось создать таблицу кандидатов.");
        }
    }
    
    public static void createElectionTimeTable() throws InvalidTableCreationException {
        try {
            Statement statement = ConnectionUtil.getConnection().createStatement();
            statement.execute(
                    "CREATE TABLE ElectionsTime(" +
                            "                   dateTimeOfBegining datetime NOT NULL," +
                            "                   dateTimeOfEnding datetime NOT NULL)");
                         
        } catch(SQLException e){
            throw new InvalidTableCreationException("Не удалось создать таблицу времени выборов.");
        }
    }
}
