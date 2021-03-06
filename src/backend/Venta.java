/*
Esta clase genera objetos tipo Venta donde se hayan todos los articulos seleccionados
para una venta. Otra manera de llamarlo podría ser "recibo".

- venta: arreglo donde se guardan objetos tipo Inventario.
 */
package backend;

import java.util.ArrayList;
import java.util.Iterator;

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
        
        String cleaned = "";
        for (int i = 0; i < item.length(); i++) {
            if(item.charAt(i)=='.'){
                break;
            }else{
                cleaned += item.charAt(i);
            }

        }
        System.out.println("Item a remover: '" + cleaned + "'");
        
        ArrayList<Inventario> arr = new ArrayList<>();    
        Iterator<Inventario> iter = venta.iterator();
        
        while(iter.hasNext()){
            
            Inventario element  = iter.next();
            
            if(!cleaned.equalsIgnoreCase(element.getName())){
                arr.add(element);
            }
        }
        
        venta.clear();
        venta.addAll(arr);
    }
    
    public String getElements(){
        
        StringBuilder sb = new StringBuilder();
        venta.forEach((Inventario element) -> {
            sb.append(element.getName()).append(" ").append(element.getPrice()).append("\n");
        });
        
        return sb.toString();
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
    
    public int size(){
        return venta.size();
        
    }
    
    public void updateInventory() {
        Iterator<Inventario> iter = venta.iterator();
        
        while (iter.hasNext()) {
            Inventario next = iter.next();
            
            if (next.getQuantity() != null) {
                next.setQuantity(next.getQuantity() - 1);
            }
            
        }
        
        FileManager.updateFile(FileTypes.BEBIDAS, venta);
    }

    @Override
    public String toString() {
        String salida = "";
        
        for (Inventario inventario : venta) {
            salida += inventario.toString() + "\r\n";
        }
        return salida;
    }
    
    public String toTicketString() {
        String salida = "";
        
        for (Inventario inventario : venta) {
            salida += inventario.toTicketString() + "\r\n";
        }
        return salida;
    }
}
