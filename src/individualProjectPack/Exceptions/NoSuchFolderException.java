/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack.Exceptions;

/**
 *
 * @author чтепоноза
 */
public class NoSuchFolderException extends Exception {
    String path;
    public NoSuchFolderException(String message, String path){
        super(message);
        this.path = path;
    }
}
