/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import frontend.MainScreen;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rubén Escalante
 */
public class Ticket {
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date = new Date();
    private Venta venta;
    private boolean tarjeta;
    private String mesero;
    
    public Ticket(Venta venta, boolean tarjeta, String mesero) {
        this.venta = venta;
        this.tarjeta = tarjeta;
        this.mesero = mesero;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public boolean isTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(boolean tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getMesero() {
        return mesero;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    @Override
    public String toString() {
        String salida = "";
        salida += dateFormat.format(date) + "\r\n";
        salida += mesero + "\r\n\r\n";
        salida += venta.toTicketString() + "\r\n";
        String credit;
        if (tarjeta) {
            credit = "Yes";
        } else {
            credit = "No";
        }
        salida += "Pago con tarjeta: " + credit + "\r\n\r\n";
        salida += "TOTAL: " + venta.getTotal() + "\r\n";
        salida += "+++++++++++++++++++++++++++++++++++++++++++++++++++\r\n";
        return salida;
    }
    
    public void confirmarVenta() {
        FileManager.addToPlainFile(FileTypes.TICKET, toString());
    }
    
}
