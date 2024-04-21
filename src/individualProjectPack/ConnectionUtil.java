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
    
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user,password);
    }
    
}
