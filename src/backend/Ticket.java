package backend;

public class Ticket {
    
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
        salida += DateManager.getCompleteDate() + "\r\n";
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
