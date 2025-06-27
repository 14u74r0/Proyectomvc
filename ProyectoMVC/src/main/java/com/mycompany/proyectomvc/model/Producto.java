
package com.mycompany.proyectomvc.model;

 public class Producto {
     private int id;
     private String nombre;
     private String descripcion;
     private double precio;
    
     public Producto() {
         
     }
     
     public Producto(int id, String nombre, String descripcion, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
     }
     
     

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
    public String toCsvString() {
        return String.valueOf(id) + "," +
               (nombre != null ? nombre : "") + "," +
               (descripcion != null ? descripcion : "") + "," +
               String.valueOf(precio);
    }
  
    public static Producto fromCsvString(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length == 4) {
            try {
                int id = Integer.parseInt(parts[0].trim());
                String nombre = parts[1].trim();
                String descripcion = parts[2].trim();
                double precio = Double.parseDouble(parts[3].trim());
                return new Producto(id, nombre, descripcion, precio);
            } catch (NumberFormatException e) {
                System.err.println("Error al parsear línea CSV a Producto: " + csvLine + " - " + e.getMessage());
                return null;
            }
        }
        return null;
    }
 }
