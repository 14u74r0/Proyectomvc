package com.mycompany.proyectomvc.factory;

import com.mycompany.proyectomvc.dao.ProductoDAO;
import com.mycompany.proyectomvc.dao.ProductoFileDAOimpl; 

public class DAOFactory {
    public static ProductoDAO getProductoDAO() {
        return new ProductoFileDAOimpl();
    }
}