/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack.DAO;

import individualProjectPack.Exceptions.*;
import individualProjectPack.TableClasses.User;
import individualProjectPack.ConnectionUtil;
import java.sql.*;
import individualProjectPack.Hashing.MD5Hashing;
import java.util.HashSet;
/**
 *
 * @author чтепоноза
 */
public class UserDAO {
    
    private UserDAO(){
       //Конструктор заглушка
    }
    
    public static User createUser(User user) throws InvalidInsertException{
        
        
        try (Statement statement = ConnectionUtil.getConnection().createStatement()) {
            boolean hasResult = statement.execute(String.format(
                     "SELECT * FROM Users WHERE login = '%s'",
                            user.getLogin()
                    ));
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()) //Если в результате есть хотя бы одна строка с таким логином, значит мы не можем добавить этого пользователя
                    throw new InvalidInsertException("Такой пользователь уже зарегистрирован: " + user.getLogin());

               statement.execute(String.format( "INSERT INTO Users VALUES ('%s', '%s', %b, %b); ",
                       user.getLogin(),
                       user.getPasswordHash(),
                       user.getVoted(),
                       user.getIsAdmin()
               ));
            } else throw new SQLException();
        }catch (SQLException E){
            throw new InvalidInsertException("Не удалось добавить пользователя в таблицу");
        }
        return user;
    }
    
     public static User createUserIfNotExists(User user) throws InvalidInsertException{
        
        
        try (Statement statement = ConnectionUtil.getConnection().createStatement()) {
            boolean hasResult = statement.execute(String.format(
                     "SELECT * FROM Users WHERE login = '%s'",
                            user.getLogin()
                    ));
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(!resultSet.next()){ //Если в результате есть хотя бы одна строка с таким логином, значит мы не можем добавить этого пользователя
                    statement.execute(String.format( "INSERT INTO Users VALUES ('%s', '%s', %b, %b); ",
                            user.getLogin(),
                            user.getPasswordHash(),
                            user.getVoted(),
                            user.getIsAdmin()
                    ));
                }
            } else throw new SQLException();
        }catch (SQLException E){
            throw new InvalidInsertException("Не удалось добавить пользователя в таблицу");
        }
        return user;
    }
    
    public static User findUser(String login) throws SQLException, NoSuchUserException{
        try (Statement statement = ConnectionUtil.getConnection().createStatement()) {
            boolean hasResult = statement.execute(String.format(
                    "SELECT * FROM Users WHERE login = '%s'",
                    login
            ));
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()){ //Если в результате есть хотя бы одна строка с таким логином, она нам и нужна
                    User userResult = new User(
                            resultSet.getString("login"),
                            resultSet.getString("passwordHash"),
                            resultSet.getBoolean("voted"),
                            resultSet.getBoolean("isAdmin")
                    );
                    return userResult;
                }

                throw new NoSuchUserException("Такого пользователя нет: " + login, login);
            }
            throw new SQLException();
        }
    }
    
        public static User updateUser(User user) throws SQLException, NoSuchUserException {

         try (Statement statement = ConnectionUtil.getConnection().createStatement()) {
            boolean hasResult = statement.execute(String.format(
                    "SELECT * FROM Users WHERE login = '%s'",
                    user.getLogin()
            ));
            if (hasResult) {
                ResultSet resultSet = statement.getResultSet();
                if(resultSet.next()) {//Если в результате есть хотя бы одна строка с таким номером, значит мы не можем обновить этого пользователя
                    statement.execute(String.format(
                            "UPDATE Users SET passwordHash = '%s', voted = %b, isAdmin = %b WHERE login = '%s'",
                            user.getPasswordHash(),
                            user.getVoted(),
                            user.getIsAdmin(),
                            user.getLogin()
                    ));
                    return user;
                }
                throw new NoSuchUserException("Такого пользователя нет: " + user.getLogin(), user.getLogin());
            }
            throw new SQLException();
        }
    }
    
    public static boolean successfulLogIn(String login, String password) throws SQLException{
        String hash = MD5Hashing.hashPassword(password);
       
       try (Statement statement = ConnectionUtil.getConnection().createStatement()) {
            statement.execute(String.format(
                     "SELECT * FROM Users WHERE login = '%s' AND passwordHash = '%s'" ,
                            login, hash
                    ));
            return statement.getResultSet().next(); //Возвращает true, если нашли 1 строку
        
        }
    }
    
    public static boolean checkIfAdmin(String login) throws SQLException{
       
        try (Statement statement = ConnectionUtil.getConnection().createStatement()){
            statement.execute(String.format(
                     "SELECT * FROM Users WHERE login = '%s' AND isAdmin = true" ,
                            login
                    ));
            return statement.getResultSet().next(); //Возвращает true, если нашли одну строчку
        }
    }
    
    
    
    public static void setAdminRights(String login, boolean adminRights) throws SQLException, NoSuchUserException, AlreadyAdminException, NotAdminException {
        User user = findUser(login);
        if(adminRights && user.getIsAdmin())
            throw new AlreadyAdminException("Пользователь " + login + " уже админ.", login);
        if(!adminRights && !user.getIsAdmin())
            throw new NotAdminException("Пользователь " + login + " уже не админ.", login);
        user.setIsAdmin(adminRights);
        updateUser(user);    
    }
    
    public static void forgetAllVotes() throws SQLException{
        
        try (Statement statement = ConnectionUtil.getConnection().createStatement()) {
            statement.execute(String.format(
                            "UPDATE Users SET voted = false " 
                    ));
        }
    }
    
    public static HashSet<User> getUsers() throws NoUsersException, SQLException{
           
         try (Statement statement = ConnectionUtil.getConnection().createStatement()) {
            boolean hasResult = statement.execute(
                    "SELECT * FROM Users "
            );
            if (hasResult) {
                HashSet<User> users = new HashSet();
                ResultSet resultSet = statement.getResultSet();
                boolean flg = false;
                while (resultSet.next()) {//Если в результате есть хотя бы одна строка, значит кандидаты есть.
                    flg = true;
                    users.add(new User(
                            resultSet.getString("login"),
                            resultSet.getString("passwordHash"),
                            resultSet.getBoolean("voted"),
                            resultSet.getBoolean("isAdmin")
                  
                    ));
                }
                if(!flg)
                    throw new NoUsersException("Пользователей нет.");
                else return users;
            }
            throw new SQLException();
        }
    }
    
}
