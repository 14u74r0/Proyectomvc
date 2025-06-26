package com.mycompany.proyectomvc.controller;

import com.mycompany.proyectomvc.dao.ProductoDAO;
import com.mycompany.proyectomvc.model.Producto;
import com.mycompany.proyectomvc.view.ProductoView;

import java.util.List;

public class ProductoController {
    private ProductoDAO productoDAO;
    private ProductoView productoView;
    
    public ProductoController(ProductoDAO productoDAO, ProductoView productoView) {
        this.productoDAO = productoDAO;
        this.productoView = productoView;
    }
    
    public void agregarProducto(int id, String nombre, String descripcion, double precio) {
        Producto producto = new Producto(id, nombre, descripcion, precio);
        productoDAO.addProducto(producto);
        productoView.mostrarMensaje("Producto agregado exitosamente.");
    }

    public void verProducto(int id) {
        Producto producto = productoDAO.getProductoById(id);
        productoView.mostrarDetallesProducto(producto);
    }

    public void actualizarProducto(int id, String nombre, String descripcion, double precio) {
        Producto productoExistente = productoDAO.getProductoById(id);
        if (productoExistente != null) {
            productoExistente.setNombre(nombre);
            productoExistente.setDescripcion(descripcion);
            productoExistente.setPrecio(precio);
            productoDAO.updateProducto(productoExistente);
            productoView.mostrarMensaje("Producto actualizado exitosamente.");
        } else {
            productoView.mostrarMensaje("No se pudo actualizar: Producto con ID " + id + " no encontrado.");
        }
    }

    public void eliminarProducto(int id) {
        productoDAO.deleteProducto(id);
        productoView.mostrarMensaje("Intento de eliminar producto con ID: " + id);
    }

    public void verTodosLosProductos() {
        List<Producto> productos = productoDAO.getAllProductos();
        productoView.mostrarListaProductos(productos);
    }
}
