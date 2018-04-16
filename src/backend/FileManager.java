/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubén Escalante
 */
public class FileManager {
    
    // Regresa un arreglo con los nombres de los elementos en el archivo txt correspondiente
    public static ArrayList<String> readStringsInFile(FileTypes fileType) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        File selected = new File(fileName);
        
        ArrayList<String> salida = new ArrayList<>();
        
        try {
            if (selected.createNewFile()) {
                LogFileMannager.writeLog("Archivo " + fileName + " creado con exito.");
            } else {
                LogFileMannager.writeLog("Acceso a " + fileName + ".");
                
//                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "windows-1250"));
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
                String line = br.readLine();
                while (line != null) {
                    System.out.println("line >> " + line);
                    salida.add(line);
                    
                    line = br.readLine();
                }
                br.close();
                LogFileMannager.writeLog("Nombres leidos de " + fileName + 
                        ":" + salida.toString());
            }
        } catch (Exception e) {
            LogFileMannager.writeLog(e.getMessage());
        }
        return salida;
    }
    
    // Regresa un arreglo con objetos tipo Inventario con los elementos en el archivo txt correspondiente
    public static ArrayList<Inventario> readItemsInFile(FileTypes fileType) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        File selected = new File(fileName);
        
        ArrayList<Inventario> salida = new ArrayList<>();
        
        try {
            if (selected.createNewFile()) {
                LogFileMannager.writeLog("Archivo " + fileName + " creado con exito.");
            } else {
                LogFileMannager.writeLog("Acceso a " + fileName + ".");
                
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "windows-1250"));
//                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
                    
                    String line = br.readLine();
                    String[] lineItems;
                    String name;
                    Double price;
                    Integer quantity;
                    while (line != null) {
                        Inventario nuevo;
                        lineItems = line.split(":");
                        name = lineItems[0];
                        price = Double.parseDouble(lineItems[1]);

                        try {
                            quantity = Integer.parseInt(lineItems[2]);
                            nuevo = new Inventario(name, price, quantity);

                        } catch (IndexOutOfBoundsException e) {
                            nuevo = new Inventario(name, price);

                        }

                        salida.add(nuevo);

                        line = br.readLine();
                    }
                    br.close();
                    LogFileMannager.writeLog("Datos leidos de " + fileName + 
                            ":" + salida.toString());
                } catch (UnsupportedEncodingException ex) {
                    LogFileMannager.writeLog(ex.getLocalizedMessage());
                }
                
            }
        } catch (IOException exception) {
            LogFileMannager.writeLog(exception.getLocalizedMessage());
        }
        return salida;
    }
    
    public static void editFileByName(FileTypes fileType, DataTypes dataType, 
            String destiny, Inventario data) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        String tempName = "temp.txt";

        File selected = new File(fileName);
        selected.renameTo(new File(tempName));
        File tempFile = new File(tempName);

        File nuevo = new File(fileName);
        try {
            nuevo.createNewFile();
        } catch (IOException ex) {
            LogFileMannager.writeLog(ex.getLocalizedMessage());
        }
        
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
                    components[0] = data.getName();
                    components[1] = String.valueOf(data.getPrice());
                
                    try {
                        components[2] = String.valueOf(data.getQuantity());
                        line = components[0] + ":" + components[1] + ":" + components[2];
                    } catch (IndexOutOfBoundsException exception) {
                        line = components[0] + ":" + components[1];
                    }
                }
                
                fw.write(line + "\n");
                line = br.readLine();
            }
            fw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static void addToFile(FileTypes fileType, String[] data) {    
        
        BufferedWriter bw = null;
        FileWriter fw = null;
        String fileName = fileType.toString().toLowerCase() + ".txt";
        
        
        try {
            
            String cadena;
            if (fileType == FileTypes.BEBIDAS) {
                cadena = "\r\n" + data[0] + ":" + data[1] + ":" + data[2];
            } else {
                cadena = "\r\n" + data[0] + ":" + data[1];
            }
            File file = new File(fileName);
          
	    fw = new FileWriter(file.getAbsoluteFile(), true);
	    bw = new BufferedWriter(fw);
            
            bw.write(cadena);
        
            LogFileMannager.writeLog("Se añadió " + data[0] + " a " + fileType.toString());
	    System.out.println("Se añadió " + data[0] + " a " + fileType.toString());

	} catch (IOException e) {

		e.printStackTrace();

	} finally {

		try {

                    if (bw != null)
			bw.close();

                    if (fw != null)
			fw.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}
	}
    }
    
    public static void addToPlainFile(FileTypes fileType, String data) {    
        
        BufferedWriter bw = null;
        FileWriter fw = null;
        String fileName = fileType.toString().toLowerCase() + ".txt";
        
        
        try {
            
            String cadena;
            File file = new File(fileName);
          
	    fw = new FileWriter(file.getAbsoluteFile(), true);
	    bw = new BufferedWriter(fw);
            
            bw.write(data);
        
            LogFileMannager.writeLog("Se añadió texto a " + fileType.toString());

	} catch (IOException e) {

		e.printStackTrace();

	} finally {

		try {

                    if (bw != null)
			bw.close();

                    if (fw != null)
			fw.close();

		} catch (IOException ex) {

			ex.printStackTrace();

		}
	}
    }
    
    public static void deleteFromFileByInventario(FileTypes fileType, Inventario data) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        String tempName = "temp.txt";

        File selected = new File(fileName);
        selected.renameTo(new File(tempName));
        File tempFile = new File(tempName);

        File nuevo = new File(fileName);
        try {
            nuevo.createNewFile();
        } catch (IOException ex) {
            LogFileMannager.writeLog(ex.getLocalizedMessage());
        }
        
        BufferedReader br;
        FileWriter fw;
        
        try {
            
            br = new BufferedReader(new FileReader(tempFile));
            fw = new FileWriter(nuevo);
            
            String[] components;
            String line = br.readLine();
            while (line != null) {
                components = line.split(":");
                
                if (!components[0].equals(data.getName())) {
                    fw.write(line + "\r\n");
                }
                
                line = br.readLine();
            }
            fw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static void deleteFromFileByName(FileTypes fileType, String data) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        String tempName = "temp.txt";

        File selected = new File(fileName);
        selected.renameTo(new File(tempName));
        File tempFile = new File(tempName);

        File nuevo = new File(fileName);
        try {
            nuevo.createNewFile();
        } catch (IOException ex) {
            LogFileMannager.writeLog(ex.getLocalizedMessage());
        }
        
        BufferedReader br;
        FileWriter fw;
        
        try {
            
            br = new BufferedReader(new FileReader(tempFile));
            fw = new FileWriter(nuevo);
            
            String[] components;
            String line = br.readLine();
            while (line != null) {
                components = line.split(":");
                
                if (!components[0].equals(data)) {
                    fw.write(line + "\r\n");
                }
                
                line = br.readLine();
            }
            fw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static void deleteStringFromFile(FileTypes fileType, String data) {
        
        String fileName = fileType.toString().toLowerCase() + ".txt";
        String tempName = "temp.txt";

        File selected = new File(fileName);
        selected.renameTo(new File(tempName));
        File tempFile = new File(tempName);

        File nuevo = new File(fileName);
        try {
            nuevo.createNewFile();
        } catch (IOException ex) {
            LogFileMannager.writeLog(ex.getLocalizedMessage());
        }
        
        BufferedReader br;
        FileWriter fw;
        
        try {
            
            br = new BufferedReader(new FileReader(tempFile));
            fw = new FileWriter(nuevo);
            
            String line = br.readLine();
            while (line != null) {
                
                if (!line.equals(data)) {
                    fw.write(line + "\r\n");
                }
                
                line = br.readLine();
            }
            fw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
}
