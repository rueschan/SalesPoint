/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rubén Escalante
 */

public class MemoryFile {
    
    private static final String PATHNAME = "memory.txt";
    
    // Agregar variables de cantidades a calcular de manera cotidiana
    private static float total;
    private static String[] cadenaSalida = {String.valueOf(total)};
    
    public static void save() {
        FileWriter fw;
        
        try {
            fw = new FileWriter(PATHNAME);
            
            String registro = buildRegister();
            fw.write(registro);
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // Se debe agregar cada variable 
    private static String buildRegister() {
        String salida = "";
        for (String val : cadenaSalida) {
            salida += val + ",";
        }
        return salida;
    }
    
    public static void load() {
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(PATHNAME));
            
            assignValues(br.readLine());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void assignValues(String registro) {
        String[] valores = registro.split(",");
        
        // Se cambia segun los valores que existan
        total = Float.parseFloat(valores[0]);
    }
    
    public static void reset() {
        
        // Se cambia segun los valores que existan
        total = 0;
        
        save();
    }
    
    public static boolean isMemoryFile() throws IOException {
        try {
            new FileReader(PATHNAME);
            
            return true;
        } catch (Exception e) {
            FileWriter fw = new FileWriter(PATHNAME);
            fw.close();
            
            return false;
        }
    }
}
