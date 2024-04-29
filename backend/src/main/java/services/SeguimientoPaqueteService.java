/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.OperadorDao;
import dao.SeguimientoPaqueteDao;
import entidades.SeguimientoPaquete;
import entidades.operador;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import util.ApiException;

/**
 *
 * @author carlos
 */
public class SeguimientoPaqueteService {
    private SeguimientoPaqueteDao seguimientoPaqueteDao = new SeguimientoPaqueteDao();

    public List<SeguimientoPaquete> getPaquetes() {
        return seguimientoPaqueteDao.getPaquetes();
    }

    public SeguimientoPaquete getPaqueteById(Integer id) throws ApiException{
        if( id == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .message("Id is required")
                    .build();
        }

        SeguimientoPaquete seguimientoPaquete = seguimientoPaqueteDao.getById(id);
        if( seguimientoPaquete == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_NOT_FOUND)
                    .message("operador not found")
                    .build();
        }
        return seguimientoPaquete;
    }

    public SeguimientoPaquete insertRegistro(SeguimientoPaquete seguimientoPaquete) throws ApiException {
        
        return seguimientoPaqueteDao.insert(seguimientoPaquete);
    }

    public void delete(int id) {
        seguimientoPaqueteDao.delete(id);
    }
}
