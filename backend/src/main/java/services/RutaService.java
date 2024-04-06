/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import dao.RutaDao;
import entidades.ruta;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.ApiException;

/**
 *
 * @author carlos
 */
public class RutaService {
    private 
            RutaDao rutaDao = new RutaDao();

    public List<ruta> getRutas() {
        return rutaDao.getRutas();
    }

    public ruta getRutaById(Integer id) throws ApiException{
        if( id == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .message("Id is required")
                    .build();
        }

        ruta ruta = rutaDao.getById(id);
        if( ruta == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_NOT_FOUND)
                    .message("ruta not found")
                    .build();
        }
        return ruta;
    }

    public ruta insertRuta(ruta ruta) throws ApiException {
        
        return rutaDao.insert(ruta);
    }

    
    public void updateActivo(int id, boolean activo) {
        
         rutaDao.updateActivo(id, activo);
    }
   
    
    
    public void deletePaquete(int id) {
        rutaDao.delete(id);
    }
}
