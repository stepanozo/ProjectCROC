/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package individualProjectPack;

import individualProjectPack.TableClasses.*;
import individualProjectPack.Exceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.stream.Stream;
/**
 *
 * @author чтепоноза
 */
public class FilesUtil {
    
    
    public static ArrayList<Candidate> getCandidatesFromFiles(String folderPath) throws NoSuchFolderException, UnableToReadFileException{
        File folder = new File(folderPath);
        if(!folder.exists())
            throw new NoSuchFolderException("Нет такой папки: " + folderPath, folderPath);
        File[] files = folder.listFiles();
        
        ArrayList<Candidate> candidates = new ArrayList();
        for(File file: files)
            candidates.add(Candidate.fromFile(file.getAbsolutePath()));
        return candidates;
    }
}
