/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Rubén Escalante
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExcelFile {
    
    private File excelFile;
    
    public ExcelFile() {
        String nombreArchivo = "ExcelFile.csv";
        excelFile = new File(nombreArchivo);
        
        try {
            if (excelFile.createNewFile()) {
                System.out.println("Archivo " + nombreArchivo + " creado con exito.");
                LogFileMannager.writeLog("Archivo " + nombreArchivo + " creado con exito.");
            } else {
                System.out.println("El archivo " + nombreArchivo + " ya existe.");
                LogFileMannager.writeLog("El archivo " + nombreArchivo + " ya existe.");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
    public ExcelFile(String nombre) {
        String nombreArchivo = nombre + ".csv";
        excelFile = new File(nombreArchivo);
        
        try {
            if (excelFile.createNewFile()) {
                System.out.println("Archivo " + nombreArchivo + " creado con exito.");
                LogFileMannager.writeLog("Archivo " + nombreArchivo + " creado con exito.");
            } else {
                System.out.println("El archivo " + nombreArchivo + " ya existe.");
                LogFileMannager.writeLog("El archivo " + nombreArchivo + " ya existe.");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete() throws Throwable {
        if (excelFile.delete()) {
            System.out.println("Archivo borrado con exito.");
        }
        this.finalize();
    }
    
    public ExcelFile cloneFile() throws FileNotFoundException, IOException {
        ExcelFile nuevoExcel = new ExcelFile();
        File nuevo = nuevoExcel.getFile();
        BufferedReader br;
        FileWriter fw;
        
        br = new BufferedReader(new FileReader(excelFile));
        fw = new FileWriter(nuevo);
        
        String line = br.readLine();
        while (line != null) {
            fw.write(line + "\n");
            line = br.readLine();
        }
        fw.close();
        br.close();
        return nuevoExcel;
    }
    
    public ExcelFile cloneFile(String newFile) throws FileNotFoundException, IOException {
        ExcelFile nuevoExcel = new ExcelFile(newFile);
        File nuevo = nuevoExcel.getFile();
        BufferedReader br;
        FileWriter fw;
        
        br = new BufferedReader(new FileReader(excelFile));
        fw = new FileWriter(nuevo);
        
        String line = br.readLine();
        while (line != null) {
            fw.write(line + "\n");
            line = br.readLine();
        }
        fw.close();
        br.close();
        return nuevoExcel;
    }
    
    public File getFile() {
        return excelFile;
    }

    public void setFile(File nuevo) {
        excelFile = nuevo;
    }
    
    public String getCellValue(int x, int y) {
        BufferedReader br;
        String line;
        int contY = 0;
        
        try {
            br = new BufferedReader(new FileReader(excelFile));
            
            line = br.readLine();
            while (line != null) {
                if (contY == y) {
                    String[] arrValores = line.split(",");
                    br.close();
                    return arrValores[x];
                }
                
                line = br.readLine();
                contY++;
            }
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo " + excelFile.getName() + " no fue encontrado.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }
    
    public void setCellValue(int x, int y, String dato) {
        BufferedReader br;
        FileWriter fw;
        String line;
        int contY = 0;
        boolean insertado = false;
        
        try {
            File temp = cloneFile("nuevo").getFile();
            br = new BufferedReader(new FileReader(temp));
            fw = new FileWriter(excelFile);
            
            while (true) {
                line = br.readLine();
                
                if (contY == y) {
                    insertado = true;
                    if (line != null) {
                        String[] arrValores = line.split(",");
                        for (int i = 0; i < arrValores.length; i++) {
                            if (i == x) {
                                fw.write(dato + ",");
                            } else {
                                fw.write(arrValores[i] + ",");
                            }
                        }
                        fw.write("\n");
                    } else {
                        for (int i = 0; i < x; i++) {
                            fw.write(",");
                        }
                        fw.write(dato);
                        break;
                    }
                } else {
                    if (line != null) {
                        fw.write(line);
                    } else if (insertado) {
                        break;
                    }
                    fw.write("\n");
                }
                contY++;
                
            }
            
            fw.close();
            br.close();
            if (temp.delete()) {
                System.out.println("Copia borrada.");
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo " + excelFile.getName() + " no fue encontrado.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String[] getRowValue(int y) {
        BufferedReader br;
        String line;
        String[] arrValores = {};
        int contY = 0;
        
        try {
            br = new BufferedReader(new FileReader(excelFile));
            
            line = br.readLine();
            while (line != null) {
                if (contY == y) {
                    arrValores = line.split(",");
                    br.close();
                    return arrValores;
                }
                
                line = br.readLine();
                contY++;
            }
            br.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo " + excelFile.getName() + " no fue encontrado.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return arrValores;
    }
    
    public void setRowValue(int y, String[] datos) {
        BufferedReader br;
        FileWriter fw;
        String line;
        int contY = 0;
        boolean insertado = false;
        
        try {
            File temp = cloneFile("copia").getFile();
            br = new BufferedReader(new FileReader(temp));
            fw = new FileWriter(excelFile);
            
            while (true) {
                line = br.readLine();
                
                if (contY == y) {
                    insertado = true;
                    for (String dato : datos) {
                        fw.write(dato + ",");
                    }
                    fw.write("\n");
                } else {
                    if (line != null) {
                        fw.write(line);
                    } else if (insertado) {
                        break;
                    }
                    fw.write("\n");
                }
                contY++;
                
            }
            
            fw.close();
            br.close();
            if (temp.delete()) {
                System.out.println("Copia borrada.");
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo " + excelFile.getName() + " no fue encontrado.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addRow(String[] datos) {
        FileWriter fw;
        
        try {
            fw = new FileWriter(excelFile, true);
            
            for (String dato : datos) {
                fw.write(dato + ",");
            }
            fw.write("\n");
            fw.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public String[] popRow() {
        BufferedReader br;
        String line;
        String wrLine;
        String[] salida = {};
        
        try {
            File temp = cloneFile("copia").getFile();
            br = new BufferedReader(new FileReader(temp));
            FileWriter fw = new FileWriter(excelFile);
            
            // Lee la 1ra linea. Si esta vacia se regresa un Array vacio y el archivo queda igual (pues está vacio).
            line = br.readLine();
            if (line == null) {
                
                fw.close();
                br.close();
                if (temp.delete()) {
                System.out.println("Copia borrada.");
                }
                return salida;
            }
            
            // Lee las siguientes lineas en caso de que si haya
            while (true) {
                
                wrLine = line;
                line = br.readLine();
                if (line != null) {
                    fw.write(wrLine + "\n");
                } else {
                    fw.close();
                    br.close();
                    if (temp.delete()) {
                        System.out.println("Copia borrada.");
                    }
                    salida = wrLine.split(",");
                    return salida;
                }
                
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo " + excelFile.getName() + " no fue encontrado.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return salida;
    }
    
    public void translateFromText(String text) {
        String[] rows = text.split("/");
        String[] columns;
        
        for (String row : rows) {
            columns = row.split("-");
            addRow(columns);
        }
    }
    
    public static int letterToNumber(String letters) {
        final int BASE_ASCII_VALUE = 64;
        int rise = 26;
        int value = 0;
        char letter;
        int nonAsciiValue;
        
        letters = letters.toUpperCase();
        
        for (int i = letters.length() - 1; i >= 0; i--) {
            letter = letters.charAt(i);
            
            nonAsciiValue = (int)letter - BASE_ASCII_VALUE;
            System.out.println("-ASCII " + nonAsciiValue);
            if (i == letters.length() - 1) {
                value += nonAsciiValue;
            } else {
                value += (rise * nonAsciiValue);
                rise += rise;
            }
            
        }
        
        value -= 1;
        return value;
    }
}
