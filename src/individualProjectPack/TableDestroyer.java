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
import individualProjectPack.Exceptions.*;

public class TableDestroyer {
    
    public TableDestroyer(){
        //Конструктор-заглушка, чтобы экземпляр класса случайно не был создан
    }
      
    public static void dropUserTable() throws InvalidTableDestroyException {
        try (Connection connection = DriverManager.
                getConnection(
                        ConnectionUtil.getUrl(),
                        ConnectionUtil.getUser(),
                        ConnectionUtil.getPassword())) {
            Statement statement = connection.createStatement();
            statement.execute(
                    "DROP TABLE Users");
        } catch(SQLException e){
            throw new InvalidTableDestroyException("Не удалось удалить старую таблицу пользователей.");
        }
    }
    
    public static void dropCandidateTable() throws InvalidTableDestroyException {
        try (Connection connection = DriverManager.
                getConnection(
                        ConnectionUtil.getUrl(),
                        ConnectionUtil.getUser(),
                        ConnectionUtil.getPassword())) {
            Statement statement = connection.createStatement();
            statement.execute(
                    "DROP TABLE Candidates");
        } catch(SQLException e){
            throw new InvalidTableDestroyException("Не удалось удалить старую таблицу кандидатов.");
        }
    }
    
    public static void dropElectionTimeTable() throws InvalidTableDestroyException {
        try (Connection connection = DriverManager.
                getConnection(
                        ConnectionUtil.getUrl(),
                        ConnectionUtil.getUser(),
                        ConnectionUtil.getPassword())) {
            Statement statement = connection.createStatement();
            statement.execute(
                    "DROP TABLE ElectionsTime");
        } catch(SQLException e){
            throw new InvalidTableDestroyException("Не удалось удалить старую таблицу расписания.");
        }
    }
    
    public static void dropAllTable() throws InvalidTableDestroyException{
        dropUserTable();
        dropCandidateTable();
        dropElectionTimeTable();
    }
}
