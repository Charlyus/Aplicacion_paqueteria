/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import dao.PuntoControlDao;
import entidades.puntoDeControl;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.ApiException;

/**
 *
 * @author carlos
 */
public class PuntoControlService {
    private PuntoControlDao puntoControlDao = new PuntoControlDao();

    public List<puntoDeControl> getPuntosDeControl() {
        return puntoControlDao.getPuntosDeControl();
    }

    public puntoDeControl getPuntoDeControlById(Integer id) throws ApiException{
        if( id == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .message("Id is required")
                    .build();
        }

        puntoDeControl puntoDeControl = puntoControlDao.getById(id);
        if( puntoDeControl == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_NOT_FOUND)
                    .message("punto de control not found")
                    .build();
        }
        return puntoDeControl;
    }

    public puntoDeControl insertPuntoControl(puntoDeControl puntoDeControl) throws ApiException {
        
        return puntoControlDao.insert(puntoDeControl);
    }

    
    
    public puntoDeControl updateCantidadCola(puntoDeControl puntoDeControl) {
        int id = puntoDeControl.getId();
        int cantidadCola=puntoDeControl.getCantidadCola();
         puntoControlDao.updateCantidadCola(id, cantidadCola);
         return puntoDeControl;
    }
    public void updateCuotaDestino(int id, int cuotaDestino) {
        
         puntoControlDao.updateCuotaDestino(id, cuotaDestino);
    }
    public void updateDescripcion(int id, String Descripcion) {
        
         puntoControlDao.updateDescripcion(id, Descripcion);
    }
    public void deletePuntoControl(int id) {
        puntoControlDao.delete(id);
    }
}
