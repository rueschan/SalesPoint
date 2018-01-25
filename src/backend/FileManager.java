/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "windows-1250"));
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
    
    public static void editFile(FileTypes fileType, DataTypes dataType, 
            String destiny, String data) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        String tempName = "temp.txt";

        File selected = new File(fileName);
        selected.renameTo(new File(tempName));
        File tempFile = new File(tempName);

        File nuevo = new File(fileName);
        BufferedReader br;
        FileWriter fw;
        
        try {
            
            br = new BufferedReader(new FileReader(tempFile));
            fw = new FileWriter(nuevo);
            
            String[] components;
            String line = br.readLine();
            while (line != null) {
                components = line.split(":");
                
                if (components[0].equals(destiny)) {
                    components[dataType.ordinal()] = data;
                    line = components[0] + ":" + components[1] + ":" + components[2];
                }
                
                fw.write(line + "\n");
                line = br.readLine();
            }
            fw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addToFile(FileTypes fileType, String[] data) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        File selected = new File(fileName);
        
        try {
            FileWriter fw = new FileWriter(selected);
            
            fw.append(data[0] + ":" + data[1] + ":" + data[2]);
            fw.close();
            
        } catch (IOException ex) {
            LogFileMannager.writeLog("EXCEPTION >> " + ex.getMessage());
        }
    }
    
}
