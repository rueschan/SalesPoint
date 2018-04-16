/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rubén Escalante
 */
public class LogFileMannager {
    
    private static File logger;
    private static String fileName = "System" + File.separator + "log.txt";  
    
    public static boolean writeLog(String log) {
        if (isLogFile()) {
            
            FileWriter fw;
        
            try {
                fw = new FileWriter(logger, true);

                fw.write(DateManager.getCompleteDate() + " :: " + log + "\r\n");
                fw.close();
                
                System.out.println("Log añadido... > " + log);
                return true;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;
    }
    
    private static boolean isLogFile() {
        logger = new File(fileName);
        
        try {
            if (logger.createNewFile()) {
                System.out.println("Archivo " + fileName + " creado con exito.");
            } else {
                System.out.println("El archivo " + fileName + " ya existe.");
            }
            return true;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}
