/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;

import java.sql.*;
/**
 *
 * @author чтепоноза
 */
public class ConnectionUtil {
    
    private static String url;
    private static String user;
    private static String password;
    
    private static Connection connection;
    
    public static String getUrl(){
        return url;
    }
    public static String getUser(){
        return user;
    }
    public static String getPassword(){
        return password;
    }
    
    public static void setUrl(String newUrl){
        url = newUrl;
    }
    public static void setUser(String newUser){
        user = newUser;
    }
    public static void setPassword(String newPassword){
        password = newPassword;
    }
    
    public static Connection getNewConnection() throws SQLException{
        connection.close();
        return DriverManager.getConnection(url, user,password);
    }
    
    public static Connection getConnection() throws SQLException{
        return connection;
    }
    
    public static void closeConnection() throws SQLException{
        connection.close();
    }
    
    public static void setConnection(Connection newConnection) throws SQLException{
        connection = newConnection;
    }
    
}
