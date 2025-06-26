package com.mycompany.proyectomvc.dao;

import com.mycompany.proyectomvc.model.Producto; 
import java.util.List;

public interface ProductoDAO {
    void addProducto(Producto producto);
    Producto getProductoById(int id);
    void updateProducto(Producto producto);
    void deleteProducto(int id);
    List<Producto> getAllProductos();
    }
