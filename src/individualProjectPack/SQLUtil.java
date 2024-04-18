/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;
import individualProjectPack.Hashing.MD5Hashing;
import java.sql.*;
import individualProjectPack.ConnectionUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import individualProjectPack.Exceptions.InvalidInsertException;
import individualProjectPack.Exceptions.NoElectionsException;
/**
 *
 * @author чтепоноза
 */
public class SQLUtil {
    private SQLUtil(){
        //Конструктор-заглушка
    }
    
    public static void newTimeOfElections(LocalDateTime beginTime, LocalDateTime endTime) throws InvalidInsertException{
        
        try (Statement statement = ConnectionUtil.getConnection().createStatement()){
                statement.execute(
                     "TRUNCATE TABLE ElectionsTime "      
                    );
                statement.execute(String.format( "INSERT INTO ElectionsTime VALUES ('%s', '%s'); ",
                       beginTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                       endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
               ));
        }catch (SQLException E){
            throw new InvalidInsertException("Не удалось добавить новое время выборов");
        }
    }
    
    public static boolean checkIfElectionsExist() throws SQLException{
        try (Statement statement = ConnectionUtil.getConnection().createStatement()){
            statement.execute(
                     "SELECT * FROM ElectionsTime"
            );
            ResultSet result = statement.getResultSet();
                              
            return result.next();
        }
    }
    
    public static LocalDateTime getBeginingTime() throws NoElectionsException, SQLException{
        if(!checkIfElectionsExist())
            throw new NoElectionsException("Попытка получить начало несуществующих выборов");
        
        try (Statement statement = ConnectionUtil.getConnection().createStatement()){
            statement.execute(
                     "SELECT dateTimeOfBegining FROM ElectionsTime"
            );
            ResultSet result = statement.getResultSet();
                              
            return  LocalDateTime.parse(result.getString("dateTimeOfBegining"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
    
    public static LocalDateTime getEndingTime() throws NoElectionsException, SQLException{
        if(!checkIfElectionsExist())
            throw new NoElectionsException("Попытка получить конец несуществующих выборов");
        
        try (Statement statement = ConnectionUtil.getConnection().createStatement()){
            statement.execute(
                     "SELECT dateTimeOfEnding FROM ElectionsTime"
            );
            ResultSet result = statement.getResultSet();
                              
            return  LocalDateTime.parse(result.getString("dateTimeOfEnding"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
