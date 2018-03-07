/*
Esta clase genera objetos tipo Venta donde se hayan todos los articulos seleccionados
para una venta. Otra manera de llamarlo podría ser "recibo".

- venta: arreglo donde se guardan objetos tipo Inventario.
 */
package backend;

import java.util.ArrayList;

/**
 * @author Rubén Escalante
 * Contact: rescalante21@hotmail.com
 */
public class Venta {
    
    private ArrayList<Inventario> venta;
    
    public Venta() {
        venta = new ArrayList<>();
    }
    
    public void addItem(Inventario item) {
        venta.add(item);
    }
    
    public void removeItemByPosition(int position){
        venta.remove(position);
    }
    
    public void removeItemByItem(String item) {
        
        for (Inventario element : venta) {
            if (element.getName().equals(item)) {
                venta.remove(element);
            }
        }  
    }
    
    public Double getTotal() {
        Double total = 0.0;
        
        for (Inventario element : venta) {
            total += element.getPrice();
        }
        return total;
    }
    
    public void cleanVenta() {
        venta.clear();
    }
    
}
