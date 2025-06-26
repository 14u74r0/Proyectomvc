package com.mycompany.proyectomvc; // 

import com.mycompany.proyectomvc.controller.ProductoController; 
import com.mycompany.proyectomvc.dao.ProductoDAO; 
import com.mycompany.proyectomvc.factory.DAOFactory; 
import com.mycompany.proyectomvc.view.ProductoView; 

public class ProyectoMVC { 

    public static void main(String[] args) {
        // 1. Obtener la implementación del DAO usando la Fábrica (Factory Pattern)
        ProductoDAO productoDAO = DAOFactory.getProductoDAO();

        // 2. Instanciar la Vista
        ProductoView productoView = new ProductoView();

        // 3. Instanciar el Controlador e Inyectar las dependencias (Dependency Injection)
        ProductoController controller = new ProductoController(productoDAO, productoView);

        // --- Simulación de Interacciones del Usuario (a través del Controlador) ---

        System.out.println("--- DEMOSTRACIÓN DE GESTIÓN DE PRODUCTOS ---");

        // Ver todos los productos inicialmente
        controller.verTodosLosProductos();

        // Agregar un nuevo producto
        controller.agregarProducto(0, "Teclado Mecánico", "Teclado retroiluminado para gaming", 80.00); // ID 0 para que DAO asigne uno
        controller.verTodosLosProductos();

        // Ver un producto específico
        System.out.println("\nBuscando producto con ID 1:");
        controller.verProducto(1);

        // Actualizar un producto existente
        controller.actualizarProducto(2, "Mouse Pro Gaming", "Mouse con alta precisión DPI", 35.50);
        controller.verProducto(2); // Verificar la actualización

        // Intentar actualizar un producto que no existe
        controller.actualizarProducto(99, "Producto Inexistente", "Descripción", 10.00);

        // Eliminar un producto
        controller.eliminarProducto(3);
        controller.verTodosLosProductos();

        // Intentar eliminar un producto que no existe
        controller.eliminarProducto(99);
    }
}