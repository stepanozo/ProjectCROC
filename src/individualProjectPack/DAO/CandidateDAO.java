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
        
        try{
            Connection connection = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM Candidates WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, candidate.getName());
            boolean hasResult = statement.execute();
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()) //Если в результате есть хотя бы одна строка с таким именем, значит мы не можем добавить этого кандидата
                    throw new InvalidInsertException("Такой кандидат уже зарегистрирован: " + candidate.getName());
                sql = "INSERT INTO Candidates VALUES (?, ?, ?, ?, ?, 0); ";               
                statement = connection.prepareStatement(sql);
                statement.setString(1, candidate.getName());
                statement.setInt(2, candidate.getYearOfBirth());
                statement.setString(3, candidate.getPlaceOfLiving());
                statement.setString(4, candidate.getParty());
                statement.setString(5, candidate.getInformation());    
                statement.execute();
            } else throw new SQLException();
        }catch (SQLException E){
            throw new InvalidInsertException("Не удалось добавить кандидата в таблицу");
        }
        return candidate;
    }
    
    public static Candidate findCandidate(String name) throws SQLException, NoSuchCandidateException{

        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM Candidates WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        boolean hasResult = statement.execute();
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
    
    public static Candidate updateCandidate(Candidate candidate) throws SQLException, NoSuchCandidateException {

        Connection connection = ConnectionUtil.getConnection();
        String sql =  "SELECT * FROM Candidates WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, candidate.getName());
        boolean hasResult = statement.execute();
        if (hasResult) {
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()) {//Если в результате есть хотя бы одна строка с таким именем, значит мы не можем обновить этого кандидата
                sql = "UPDATE Candidates SET name = ?, yearOfBirth = ?, placeOfLiving = ?, party = ?, information = ?, votes = ? WHERE name = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, candidate.getName());
                statement.setInt(2, candidate.getYearOfBirth());
                statement.setString(3, candidate.getPlaceOfLiving());
                statement.setString(4, candidate.getParty());
                statement.setString(5, candidate.getInformation());
                statement.setInt(6, candidate.getVotes());
                statement.setString(7, candidate.getName());
                statement.execute();
                return candidate;
            }
            throw new NoSuchCandidateException("Такого кандидата нет: " + candidate.getName(), candidate.getName());
        }
        throw new SQLException();
    }
    
    public static void voteForCandidate(Candidate candidate) throws SQLException, NoSuchCandidateException {

        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM Candidates WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, candidate.getName());
        boolean hasResult = statement.execute();
        if (hasResult) {
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()) {//Если в результате есть хотя бы одна строка с таким именем, значит мы можем обновить этого кандидата
                sql = "UPDATE Candidates SET votes = votes + 1 WHERE name = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, candidate.getName());
                statement.execute();
            } else
                throw new NoSuchCandidateException("Такого кандидата нет: " + candidate.getName(), candidate.getName());
        } else
            throw new SQLException();
    }
    
    public static HashSet<Candidate> getCandidates() throws NoCandidatesException, SQLException{
           
        Connection connection = ConnectionUtil.getConnection();
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
