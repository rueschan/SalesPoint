/*
Esta clase sirve para almacenar los elementos del inventario de cualquier tipo.

- name: Nombre del producto.
- cost: Costo del producto.
- quantity: Cantidad de productos que quedan en el inventario. Este valor puede
            ser igual a "null" puesto que no todos los productos son contabilizables.

*/
package backend;

/**
 * @author Rubén Escalante
 * Contact: rescalante21@hotmail.com
 */
public class Inventario implements Comparable<Inventario>{
    
    private String name;
    private Double price;
    private Integer quantity;
    
    public static Inventario convertFromString(String raw) {
        String[] parts = raw.split(":");
        
        Inventario salida;
        if (parts.length < 3) {
            salida = new Inventario(parts[0], Double.valueOf(parts[1]));
        } else {
            salida = new Inventario(parts[0], Double.valueOf(parts[1]), Integer.valueOf(parts[2]));
        }
        return salida;
    }
    
    public Inventario(String name, Double price) {
        this.name = name;
        this.price = price;
        this.quantity = null;
    }
    
    public Inventario(String name, Double price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public void addQuantity() {
        this.quantity ++;
    }
    
    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
    }
    
    public void substractQuantity() {
        this.quantity --;
    }
    
    public void substractQuantity(Integer quantity) {
        this.quantity -= quantity;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double cost) {
        this.price = cost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int compareTo(Inventario other) {
        return name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "[" + name + ", $" + price + ", " + quantity + " ]";
    } 
    
    public String toTicketString() {
        return name + ", $" + price;
    }
}
