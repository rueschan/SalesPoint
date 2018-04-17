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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rubén Escalante
 */
public class FileManager {
    
    private static final String ENCODING = "UTF-8";
    private static final String READING_ENCODING = "UTF-8";//"windows-1250";
    
    private static String getFileName(FileTypes type) {
        String path;
        
        switch (type) {
            case TICKET:
                path = "Ventas";
                break;
            case GASTOS:
                path = "Ventas";
                break;
            case MESEROS:
                path = "Personal";
                break;
            default:
                path = "Productos";
                break;
        }
        path += File.separator + type.toString().toLowerCase() + ".txt";
        
        return path;
    }
    
    // Regresa un arreglo con los nombres de los elementos en el archivo txt correspondiente
    public static ArrayList<String> readStringsInFile(FileTypes fileType) {
        
        String fileName = getFileName(fileType);
        File selected = new File(fileName);
        
        ArrayList<String> salida = new ArrayList<>();
        
        try {
            if (selected.createNewFile()) {
                LogFileMannager.writeLog("Archivo " + fileName + " creado con exito.");
            } else {
                LogFileMannager.writeLog("Acceso a " + fileName + ".");
                
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), READING_ENCODING));
//                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
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
        
        String fileName = getFileName(fileType);
        File selected = new File(fileName);
        
        ArrayList<Inventario> salida = new ArrayList<>();
        
        try {
            if (selected.createNewFile()) {
                LogFileMannager.writeLog("Archivo " + fileName + " creado con exito.");
            } else {
                LogFileMannager.writeLog("Acceso a " + fileName + ".");
                
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), READING_ENCODING));
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
    
    public static void editFileByInventory(FileTypes fileType, 
            String destiny, Inventario data) {
        
        String fileName = getFileName(fileType);
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
        BufferedWriter bw;
        
        try {
            
//            br = new BufferedReader(new FileReader(tempFile));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(tempName), READING_ENCODING));
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
            File file = new File(fileName);
	    bw = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file.getAbsoluteFile(), true), ENCODING));
            
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
                
                bw.write(line + "\r\n");
                line = br.readLine();
            }
            bw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static void editFileByName(FileTypes fileType, DataTypes dataType, 
            String destiny, String data) {
        
        String fileName = getFileName(fileType);
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
        BufferedWriter bw;
        
        try {
            System.out.println(fileName);
//            br = new BufferedReader(new FileReader(tempFile));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(tempName), READING_ENCODING));
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
            File file = new File(fileName);
	    bw = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file.getAbsoluteFile(), true), ENCODING));
            
            String[] components;
            String line = br.readLine();
            while (line != null) {
                components = line.split(":");
                
                if (components[0].equals(destiny)) {
                    
                    switch (dataType){
                        case NAME:
                            components[0] = data;
                            break;
                        case PRICE:
                            components[1] = data;
                        case QUANTITY:
                            try {
                                components[2] = data;
                            } catch (IndexOutOfBoundsException exception) {
                                LogFileMannager.writeLog("Failed to insert " + data
                                        + " of type " + dataType.toString()
                                        + "into " + components[0]);
                            }
                            break;
                    }
                
                    try {
                        line = components[0] + ":" + components[1] + ":" + components[2];
                    } catch (IndexOutOfBoundsException exception) {
                        line = components[0] + ":" + components[1];
                    }
                }
                
                bw.write(line + "\r\n");
                line = br.readLine();
            }
            bw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static void addToFile(FileTypes fileType, String[] data) {    
        
        BufferedWriter bw = null;
        FileWriter fw = null;
        String fileName = getFileName(fileType);
        
        try {
            
            String cadena;
            if (fileType == FileTypes.BEBIDAS) {
                cadena = "\r\n" + data[0] + ":" + data[1] + ":" + 0;
            } else {
                cadena = "\r\n" + data[0] + ":" + data[1];
            }
            File file = new File(fileName);
	    bw = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file.getAbsoluteFile(), true), ENCODING));
            
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
        String fileName = getFileName(fileType);
        
        
        try {
            
            File file = new File(fileName);
	    bw = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file.getAbsoluteFile(), true), ENCODING));
            
            bw.write("\r\n" + data);
        
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
        
        String fileName = getFileName(fileType);
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
        BufferedWriter bw;
        
        try {
            
//            br = new BufferedReader(new FileReader(tempFile));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(tempName), READING_ENCODING));
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
            File file = new File(fileName);
	    bw = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file.getAbsoluteFile(), true), ENCODING));
            
            String[] components;
            String line = br.readLine();
            while (line != null) {
                components = line.split(":");
                
                if (!components[0].equals(data.getName())) {
                    bw.write(line + "\r\n");
                }
                
                line = br.readLine();
            }
            bw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static void deleteFromFileByName(FileTypes fileType, String data) {
        
        String fileName = getFileName(fileType);
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
        BufferedWriter bw;
        
        try {
            
//            br = new BufferedReader(new FileReader(tempFile));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(tempName), READING_ENCODING));
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
            File file = new File(fileName);
	    bw = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file.getAbsoluteFile(), true), ENCODING));
            
            String[] components;
            String line = br.readLine();
            while (line != null) {
                components = line.split(":");
                
                if (!components[0].equals(data)) {
                    bw.write(line + "\r\n");
                }
                
                line = br.readLine();
            }
            bw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static void deleteStringFromFile(FileTypes fileType, String data) {
        
        String fileName = getFileName(fileType);
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
        BufferedWriter bw;
        
        try {
            
//            br = new BufferedReader(new FileReader(tempFile));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(tempName), READING_ENCODING));
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
            File file = new File(fileName);
	    bw = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file.getAbsoluteFile(), true), ENCODING));
            
            String line = br.readLine();
            while (line != null) {
                
                if (!line.equals(data)) {
                    bw.write(line + "\r\n");
                }
                
                line = br.readLine();
            }
            bw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static void updateFile(FileTypes fileType, ArrayList<Inventario> data) {
        
        String fileName = getFileName(fileType);
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
        BufferedWriter bw;
        
        try {
            
//            br = new BufferedReader(new FileReader(tempFile));
            br = new BufferedReader(new InputStreamReader(new FileInputStream(tempName), READING_ENCODING));
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
            File file = new File(fileName);
	    bw = new BufferedWriter(new OutputStreamWriter(
		new FileOutputStream(file.getAbsoluteFile(), true), ENCODING));
            
            String[] components;
            String line = br.readLine();
            while (line != null) {
                components = line.split(":");
                
                for (Inventario element : data) {
                    
                    if (components[0].equals(element.getName())) {
                        components[1] = String.valueOf(element.getPrice());

                        try {
                            components[2] = String.valueOf(element.getQuantity());
                            line = components[0] + ":" + components[1] + ":" + components[2];
                        } catch (IndexOutOfBoundsException exception) {
                            line = components[0] + ":" + components[1];
                        }
                        data.remove(element);
                        break;
                    }
                    
                }
                
                bw.write(line + "\r\n");
                line = br.readLine();
            }
            bw.close();
            br.close();
            
        } catch (Exception ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tempFile.delete();
    }
    
    public static String searchInFile(FileTypes fileType, String searchable) {
        String fileName = getFileName(fileType);
        File selected = new File(fileName);
        
        String salida = "";
        
        try {
            if (selected.createNewFile()) {
                LogFileMannager.writeLog("Archivo " + fileName + " creado con exito.");
            } else {
                LogFileMannager.writeLog("Acceso a " + fileName + ".");
                
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), READING_ENCODING));
//                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), Charset.defaultCharset()));
                String line = br.readLine();
                while (line != null) {
                    
                    if (line.contains(searchable)) {
                        salida = line;
                        LogFileMannager.writeLog("Linea leída de " + fileName + 
                        ": " + salida.toString());
                        br.close();
                        return salida;
                    }
                    
                    line = br.readLine();
                }
                br.close();
            }
        } catch (Exception e) {
            LogFileMannager.writeLog(e.getMessage());
        }
        return salida;
    }
    
}
