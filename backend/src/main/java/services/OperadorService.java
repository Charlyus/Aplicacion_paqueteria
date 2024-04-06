/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.OperadorDao;
import entidades.operador;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.ApiException;

/**
 *
 * @author carlos
 */
public class OperadorService {
    private OperadorDao operadorDao = new OperadorDao();

    public List<operador> getOperadores() {
        return operadorDao.getOperadores();
    }

    public operador getOperadorById(Integer id) throws ApiException{
        if( id == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .message("Id is required")
                    .build();
        }

        operador operador = operadorDao.getById(id);
        if( operador == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_NOT_FOUND)
                    .message("operador not found")
                    .build();
        }
        return operador;
    }

    public operador insertOperador(operador operador) throws ApiException {
        
        return operadorDao.insert(operador);
    }

    
    
    public void updateActivo(int id, boolean activo) {
        
         operadorDao.updateActivo(id, activo);
    }
    public void updateNombre(int id, String nombre) {
        
         operadorDao.updateNombre(id, nombre);
    }
    public void updateContraseña(int id, String contraseña) {
        
         operadorDao.updateContraseña(id, contraseña);
    }
    public void deleteOperador(int id) {
        operadorDao.delete(id);
    }
}
