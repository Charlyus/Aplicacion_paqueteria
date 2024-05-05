/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import dao.RutaDao;
import dao.reporteClienteDao;
import entidades.reporteCliente;
import entidades.ruta;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.ApiException;

/**
 *
 * @author carlos
 */
public class reporteClienteService {
    private reporteClienteDao rutaDao = new reporteClienteDao();

    public List<reporteCliente> getRutas() {
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

     
   
    
    
    
}
