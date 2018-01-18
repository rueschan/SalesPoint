/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Rubén Escalante
 */
public class FileManager {
    
//    private static final String fileName = "carta.txt";
//    private static File selected;
//    private static final String FILE_BEBIDAS = "bebidas.txt";
//    private static File bebidas;
//    private static final String FILE_ANTOJOS = "antojos.txt";
//    private static File antojos;
//    private static final String FILE_MENUS = "menus.txt";
//    private static File menus;
    
    public static ArrayList<String> readFile(FileTypes fileType) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        File selected = new File(fileName);
        
        ArrayList<String> salida = new ArrayList<>();
        
        try {
            if (selected.createNewFile()) {
                LogFileMannager.writeLog("Archivo " + fileName + " creado con exito.");
            } else {
                LogFileMannager.writeLog("Acceso a " + fileName + ".");
                
                BufferedReader br = new BufferedReader(new FileReader(selected));
                String line = br.readLine();
                String[] lineItems;
                while (line != null) {
                    lineItems = line.split(":");
                    salida.add(lineItems[0]);
                    
                    line = br.readLine();
                }
                br.close();
                LogFileMannager.writeLog("Datos leidos de " + fileName + 
                        ":" + salida.toString());
            }
        } catch (Exception e) {
            LogFileMannager.writeLog(e.getMessage());
        }
        return salida;
    }
    
}
