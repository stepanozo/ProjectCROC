/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack.DAO;

import individualProjectPack.Exceptions.InvalidInsertException;
import individualProjectPack.TableClasses.Candidate;
import individualProjectPack.ConnectionUtil;
import java.sql.*;
import individualProjectPack.Hashing.MD5Hashing;
/**
 *
 * @author чтепоноза
 */
public class CandidateDAO {
    
    private CandidateDAO(){
       //Конструктор заглушка
    }
    
    public static Candidate createCandidate(Candidate candidate) throws InvalidInsertException{
        
        try (Statement statement = ConnectionUtil.getConnection().createStatement()) {
            boolean hasResult = statement.execute(String.format(
                     "SELECT * FROM Candidates " +
                             "WHERE name = '%s'",
                            candidate.getName()
                    ));
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()) //Если в результате есть хотя бы одна строка с таким именем, значит мы не можем добавить этого кандидата
                    throw new InvalidInsertException("Такой кандидат уже зарегистрирован: " + candidate.getName());

               statement.execute(String.format( "INSERT INTO Candidates VALUES ('%s', %d, '%s', '%s', '%s', 0); ",
                       candidate.getName(),
                       candidate.getYearOfBirth(),
                       candidate.getPlaceOfLiving(),
                       candidate.getParty(),
                       candidate.getInformation()
               ));
            }
        }catch (SQLException E){
            throw new InvalidInsertException("Не удалось добавить кандидата в таблицу");
        }
        return candidate;
    }
}
