/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.AdministradorDao;
import dao.OperadorDao;
import entidades.administrador;
import entidades.operador;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.ApiException;

/**
 *
 * @author carlos
 */
public class AdministradorService {
    private AdministradorDao administradorDao = new AdministradorDao();

    public List<administrador> getOperadores() {
        return administradorDao.getOperadores();
    }

    public administrador getOperadorById(Integer id) throws ApiException{
        if( id == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .message("Id is required")
                    .build();
        }

        administrador administrador = administradorDao.getById(id);
        if( administrador == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_NOT_FOUND)
                    .message("operador not found")
                    .build();
        }
        return administrador;
    }

    public administrador insertOperador(administrador administrador) throws ApiException {
        
        return administradorDao.insert(administrador);
    }

    
    
   
    public void updateNombre(int id, String nombre) {
        
         administradorDao.updateNombre(id, nombre);
    }
    public void updateContraseña(int id, String contraseña) {
        
         administradorDao.updateContraseña(id, contraseña);
    }
    public void deleteOperador(int id) {
        administradorDao.delete(id);
    }
}
