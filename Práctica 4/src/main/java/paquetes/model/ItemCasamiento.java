package paquetes.model;

import javax.persistence.*;

@Entity
@Table(name = "items_casamiento")
public class ItemCasamiento {

    @Id
    private String item;
    private double precio;

    public String getNombre() { return item; }
    public void setNombre(String nombre) { this.item = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
}
