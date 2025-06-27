package com.mycompany.proyectomvc.dao; 


import com.mycompany.proyectomvc.model.Producto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProductoFileDAOimpl implements ProductoDAO {
    
    private final String filePath = "productos.txt";
    private List<Producto> productos = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger();
    
     public ProductoFileDAOimpl() {
        productos = new ArrayList<>();
        idCounter = new AtomicInteger(0);
        loadProductsFromFile(); 
    }
    
     private void loadProductsFromFile() {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.out.println("Archivo de productos no encontrado (" + filePath + "). Se creará uno nuevo al guardar.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int maxId = 0;
            while ((line = reader.readLine()) != null) {
                Producto producto = Producto.fromCsvString(line); 
                if (producto != null) {
                    productos.add(producto);
                    if (producto.getId() > maxId) {
                        maxId = producto.getId();
                    }
                }
            }
            idCounter.set(maxId);
            System.out.println("Productos cargados desde " + filePath);
        } catch (IOException e) {
            System.err.println("Error al cargar productos desde el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveProductsToFile() {
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Producto producto : productos) {
                writer.write(producto.toCsvString()); 
                writer.newLine();
            }
            System.out.println("Productos guardados en " + filePath);
        } catch (IOException e) {
            System.err.println("Error al guardar productos en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @Override
    public void addProducto(Producto producto) {
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
        return new ArrayList<>(productos); 
    }
}

