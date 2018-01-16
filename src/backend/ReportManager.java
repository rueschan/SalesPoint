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

public class ReportManager {
    
    // Crear formato al gusto del cliente
    public static void createReport(String excelName) {
        ExcelFile report = new ExcelFile(excelName);
        
        if (report.getCellValue(0, 0) == "") {
            formatReport(report);
        }
        
    }

    private static void formatReport(ExcelFile report) {
        String headers = "Test-c2-c3/f2c1-f2c2";
        report.translateFromText(headers);
    }
}
