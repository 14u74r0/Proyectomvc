package com.mycompany.proyectomvc; // 

import com.mycompany.proyectomvc.controller.ProductoController; 
import com.mycompany.proyectomvc.dao.ProductoDAO; 
import com.mycompany.proyectomvc.factory.DAOFactory; 
import com.mycompany.proyectomvc.view.ProductoView; 

public class ProyectoMVC { 

    public static void main(String[] args) {
        ProductoDAO productoDAO = DAOFactory.getProductoDAO();

        ProductoView productoView = new ProductoView();

        ProductoController controller = new ProductoController(productoDAO, productoView);

        System.out.println("--- DEMOSTRACIÓN DE GESTIÓN DE PRODUCTOS ---");

        controller.verTodosLosProductos();

        controller.agregarProducto(0, "Teclado Mecánico", "Teclado retroiluminado para gaming", 80.00); // ID 0 para que DAO asigne uno
        controller.verTodosLosProductos();
        
        System.out.println("\nBuscando producto con ID 1:");
        controller.verProducto(1);

        controller.actualizarProducto(2, "Mouse Pro Gaming", "Mouse con alta precisión DPI", 35.50);
        controller.verProducto(2); 

        controller.actualizarProducto(99, "Producto Inexistente", "Descripción", 10.00);

        controller.eliminarProducto(3);
        controller.verTodosLosProductos();

        controller.eliminarProducto(99);
    }
}