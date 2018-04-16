/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Rubén Escalante
 */

public class MemoryFile {
    
    private static final String PATHNAME = "memory.txt";
    
    // Agregar variables de cantidades a calcular de manera cotidiana
    private static String date;
    private static Double total;
    private static ArrayList<Object> memoryData = new ArrayList<>();
    public static final int DATE_ID = 0;
    public static final int TOTAL_ID = 1;

    public static void addMemoryData(Object data) {
        memoryData.add(data);
    }
    
    public static void removeMemoryData(Object data) {
        memoryData.remove(data);
    }
    
    public static void resetMemoryData() {
        memoryData.clear();
    }
    
    public static ArrayList<Object> getData() {
        ArrayList<Object> salida = new ArrayList<>(memoryData.size() - 2);
        for (int i = 2; i < memoryData.size() - 2; i++) {
            salida.add(memoryData.get(i));
        }
        return salida;
    }
    
    public static void addTotal(double value) {
        total += value;
    }

    public static Double getTotal() {
        return total;
    }
    
    public static String getDate() {
        return date;
    }
    
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
        for (Object data : memoryData) {
            
            if (data instanceof String) {
                salida += (String)data;
                
            } else if (data instanceof Inventario) {
                Inventario nuevo = (Inventario)data;
                salida += nuevo.getName();
                
            }
            salida += ",";
        }
        return salida;
        
    }
    
    public static void load() {
        reset();
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
        for (int i = 0; i < valores.length; i++) {
            memoryData.add(valores[i]);
        }
        
        // Se cambia segun los valores que existan
        date = valores[DATE_ID];
        memoryData.add(DATE_ID, date);
        total = Double.valueOf(valores[TOTAL_ID]);
        memoryData.add(TOTAL_ID, total);
    }
    
    public static void reset() {
        
        // Se cambia segun los valores que existan
        total = 0.0;
        date = "<Not defined>";
        memoryData.clear();
        
        save();
    }
    
    public static boolean isMemoryFile() throws IOException {
        try {
            FileReader fr = new FileReader(PATHNAME);
            BufferedReader br = new BufferedReader(fr);
            String date = br.readLine().split(",")[DATE_ID];
            
            if (date.equals(DateManager.getDay())) {
                load();
                return true;
            }
            return false;
        
        } catch (FileNotFoundException e) {
            FileWriter fw = new FileWriter(PATHNAME);
            fw.append(DateManager.getDay() + ",");
            fw.close();
            
            return false;
        }
    }
}
