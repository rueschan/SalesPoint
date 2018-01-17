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
    
    private static final String FILE_CARTA = "carta.txt";
    private static File carta;
    private static final String FILE_BEBIDAS = "bebidas.txt";
    private static File bebidas;
    private static final String FILE_MENUS = "menus.txt";
    private static File menus;
    
    public static ArrayList<String> readCarta() {
        carta = new File(FILE_CARTA);
        ArrayList<String> salida = new ArrayList<>();
        
        try {
            if (carta.createNewFile()) {
                LogFileMannager.writeLog("Archivo " + FILE_CARTA + " creado con exito.");
            } else {
                LogFileMannager.writeLog("Acceso a " + FILE_CARTA + ".");
                
                BufferedReader br = new BufferedReader(new FileReader(carta));
                String line = br.readLine();
                String[] lineItems;
                while (line != null) {
                    lineItems = line.split(":");
                    salida.add(lineItems[0]);
                    
                    line = br.readLine();
                }
                br.close();
                LogFileMannager.writeLog("Datos leidos de " + FILE_CARTA + 
                        ":" + salida.toString());
            }
        } catch (Exception e) {
            LogFileMannager.writeLog(e.getMessage());
        }
        return salida;
    }
    
}
