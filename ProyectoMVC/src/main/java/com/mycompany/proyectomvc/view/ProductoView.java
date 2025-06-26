package com.mycompany.proyectomvc.view;

import com.mycompany.proyectomvc.model.Producto;
import java.util.List;

public class ProductoView {
    public void mostrarDetallesProducto(Producto producto) {
        if (producto != null) {
            System.out.println("\n--- Detalles del Producto ---");
            System.out.println("ID: " + producto.getId());
            System.out.println("Nombre: " + producto.getNombre());
            System.out.println("Descripción: " + producto.getDescripcion());
            System.out.println("Precio: $" + String.format("%.2f", producto.getPrecio()));
            System.out.println("---------------------------\n");
        } else {
            System.out.println("Producto no encontrado");
        }
    }
    
    public void mostrarListaProductos(List<Producto> productos) {
        if (productos != null && !productos.isEmpty()) {
            System.out.println("\"\\n--- Lista de Productos ---\"");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
            System.out.println("---------------------------\\n");
        } else {
            System.out.println("No hay productos para mostrar.");
        }
    }
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
