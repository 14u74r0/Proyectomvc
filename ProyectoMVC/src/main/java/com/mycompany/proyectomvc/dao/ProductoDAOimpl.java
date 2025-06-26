package com.mycompany.proyectomvc.dao; 

import com.mycompany.proyectomvc.model.Producto; 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductoDAOimpl implements ProductoDAO {
    private List<Producto> productos = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger();
    
    public ProductoDAOimpl() {
        addProducto(new Producto(idCounter.incrementAndGet(), "Laptop", "Potente laptop para gaming", 600000.00));
        addProducto(new Producto(idCounter.incrementAndGet(), "Mouse", "Mouse ergonomico wireless", 50000.00));
    }
    @Override
    public void addProducto(Producto producto) {
        // Asignar un nuevo ID si el producto no tiene uno (para datos iniciales o nuevas creaciones)
        if (producto.getId() == 0) {
            producto.setId(idCounter.incrementAndGet());
        }
        productos.add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    @Override
    public Producto getProductoById(int id) {
        Optional<Producto> foundProducto = productos.stream()
                                                    .filter(p -> p.getId() == id)
                                                    .findFirst();
        return foundProducto.orElse(null);
    }

    @Override
    public void updateProducto(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                productos.set(i, producto);
                System.out.println("Producto actualizado: " + producto.getNombre());
                return;
            }
        }
        System.out.println("Producto con ID " + producto.getId() + " no encontrado para actualizar.");
    }

    @Override
    public void deleteProducto(int id) {
        boolean removed = productos.removeIf(p -> p.getId() == id);
        if (removed) {
            System.out.println("Producto con ID " + id + " eliminado.");
        } else {
            System.out.println("Producto con ID " + id + " no encontrado para eliminar.");
        }
    }

    @Override
    public List<Producto> getAllProductos() {
        return new ArrayList<>(productos); // Devolver una copia para evitar modificaciones externas
    }
}

