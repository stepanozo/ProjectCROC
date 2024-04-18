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
    
    private static Connection connection;
    
    public static Connection getConnection(){
        return connection;
    }
    
    public static void setConnection(Connection setConnection){
        connection = setConnection;
    }
   
}
