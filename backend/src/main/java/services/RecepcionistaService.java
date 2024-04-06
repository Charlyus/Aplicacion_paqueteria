/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.RecepcionistaDao;
import entidades.recepcionista;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.ApiException;

/**
 *
 * @author carlos
 */
public class RecepcionistaService {
    private RecepcionistaDao recepcionistaDao = new RecepcionistaDao();

    public List<recepcionista> getRecepcionistas() {
        return recepcionistaDao.getRecepcionistas();
    }

    public recepcionista getRecepcionistaById(Integer id) throws ApiException{
        if( id == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .message("Id is required")
                    .build();
        }

        recepcionista recepcionista = recepcionistaDao.getById(id);
        if( recepcionista == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_NOT_FOUND)
                    .message("recepcionista not found")
                    .build();
        }
        return recepcionista;
    }

    public recepcionista insertRecepcionista(recepcionista recepcionista) throws ApiException {
        
        return recepcionistaDao.insert(recepcionista);
    }

    
    
    public void updateActivo(int id, boolean activo) {
        
         recepcionistaDao.updateActivo(id, activo);
    }
    public void updateNombre(int id, String nombre) {
        
         recepcionistaDao.updateNombre(id, nombre);
    }
    public void updateContraseña(int id, String contraseña) {
        
         recepcionistaDao.updateContraseña(id, contraseña);
    }
    public void deleteRecepcionista(int id) {
        recepcionistaDao.delete(id);
    }
}
