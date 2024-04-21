/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack.DAO;

import individualProjectPack.Exceptions.InvalidInsertException;
import individualProjectPack.TableClasses.Candidate;
import individualProjectPack.ConnectionUtil;
import individualProjectPack.Exceptions.*;
import java.sql.*;
import java.util.HashSet;

/**
 *
 * @author чтепоноза
 */
public class CandidateDAO {
    
    private CandidateDAO(){
       //Конструктор заглушка
    }
    
    public static Candidate createCandidate(Candidate candidate) throws InvalidInsertException{
        
        try (Connection connection = DriverManager.
                getConnection(
                        ConnectionUtil.getUrl(),
                        ConnectionUtil.getUser(),
                        ConnectionUtil.getPassword())) {
            Statement statement = connection.createStatement();
            boolean hasResult = statement.execute(String.format(
                     "SELECT * FROM Candidates WHERE name = '%s'",
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
            } else throw new SQLException();
        }catch (SQLException E){
            throw new InvalidInsertException("Не удалось добавить кандидата в таблицу");
        }
        return candidate;
    }
    
     public static Candidate findCandidate(String name) throws SQLException, NoSuchCandidateException{
        try (Connection connection = DriverManager.
                getConnection(
                        ConnectionUtil.getUrl(),
                        ConnectionUtil.getUser(),
                        ConnectionUtil.getPassword())) {
            Statement statement = connection.createStatement();
            boolean hasResult = statement.execute(String.format(
                    "SELECT * FROM Candidates WHERE name = '%s'",
                    name
            ));
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()){ //Если в результате есть хотя бы одна строка с таким логином, она нам и нужна
                    Candidate candidateResult = new Candidate(
                            resultSet.getString("name"),
                            resultSet.getInt("yearOfBirth"),
                            resultSet.getString("placeOfLiving"),
                            resultSet.getString("party"),
                            resultSet.getString("information"),
                            resultSet.getInt("votes")
                    );
                    return candidateResult;
                }

                throw new NoSuchCandidateException("Такого кандидата нет: " + name, name);
            }
            throw new SQLException();
        }
    }
    
    public static Candidate updateCandidate(Candidate candidate) throws SQLException, NoSuchCandidateException {

        try (Connection connection = DriverManager.
                getConnection(
                        ConnectionUtil.getUrl(),
                        ConnectionUtil.getUser(),
                        ConnectionUtil.getPassword())) {
            Statement statement = connection.createStatement();
            boolean hasResult = statement.execute(String.format(
                    "SELECT * FROM Candidates WHERE name = '%s'",
                    candidate.getName()
            ));
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()) {//Если в результате есть хотя бы одна строка с таким именем, значит мы не можем обновить этого кандидата
                    statement.execute(String.format(
                            "UPDATE Candidates SET name = '%s', yearOfBirth = '%s', placeOfLiving = '%s', party = '%s', information = '%s', votes = %d WHERE name = '%s'",
                            candidate.getName(),
                            candidate.getYearOfBirth(),
                            candidate.getPlaceOfLiving(),
                            candidate.getParty(),
                            candidate.getInformation(),
                            candidate.getVotes(),
                            candidate.getName()
                    ));
                    return candidate;
                }
                throw new NoSuchCandidateException("Такого кандидата нет: " + candidate.getName(), candidate.getName());
            }
            throw new SQLException();
        }
    }
    
    public static void voteForCandidate(Candidate candidate) throws SQLException, NoSuchCandidateException {

        try (Connection connection = DriverManager.
                getConnection(
                        ConnectionUtil.getUrl(),
                        ConnectionUtil.getUser(),
                        ConnectionUtil.getPassword())) {
            Statement statement = connection.createStatement();
            boolean hasResult = statement.execute(String.format(
                     "SELECT * FROM Candidates WHERE name = '%s'",
                    candidate.getName()
            ));
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()) {//Если в результате есть хотя бы одна строка с таким именем, значит мы можем обновить этого кандидата
                    statement.execute(String.format(
                            "UPDATE Candidates SET votes = votes + 1 WHERE name = '%s'",
                            candidate.getName()
                    ));
                } else
                    throw new NoSuchCandidateException("Такого кандидата нет: " + candidate.getName(), candidate.getName());
            } else
                throw new SQLException();
        }
    }
    
    public static HashSet<Candidate> getCandidates() throws NoCandidatesException, SQLException{
           
        try (Connection connection = DriverManager.
                getConnection(
                        ConnectionUtil.getUrl(),
                        ConnectionUtil.getUser(),
                        ConnectionUtil.getPassword())) {
            Statement statement = connection.createStatement();
            boolean hasResult = statement.execute(
                    "SELECT * FROM Candidates "
            );
            if (hasResult) {
                HashSet<Candidate> candidates = new HashSet();
                ResultSet resultSet = statement.getResultSet();
                boolean flg = false;
                while (resultSet.next()) {//Если в результате есть хотя бы одна строка, значит кандидаты есть.
                    flg = true;
                    candidates.add(new Candidate(
                            resultSet.getString("name"),
                            resultSet.getInt("yearOfBirth"),
                            resultSet.getString("placeOfLiving"),
                            resultSet.getString("party"),
                            resultSet.getString("information"),
                            resultSet.getInt("votes")
                    ));
                }
                if(!flg)
                    throw new NoCandidatesException("Кандидатов нет.");
                else return candidates;
            }
            throw new SQLException();
        }
    }
    
}
