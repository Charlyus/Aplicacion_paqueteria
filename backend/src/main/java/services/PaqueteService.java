/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entidades.paquete;
import jakarta.servlet.http.HttpServletResponse;
import util.ApiException;
import java.util.List;
import dao.PaqueteDao;

/**
 *
 * @author carlos
 */
public class PaqueteService {
    private PaqueteDao paqueteDao = new PaqueteDao();
    
    public List<paquete> getPaquetesDestino() {
        return paqueteDao.getPaquetesEnDestino();
    }
    public List<paquete> getPaquetes() {
        return paqueteDao.getPaquetes();
    }
    public List<paquete> getPaquetesEnDestino() {
        return paqueteDao.getPaquetesEnDestino();
    }
    public paquete getPaqueteById(Integer id) throws ApiException{
        if( id == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_BAD_REQUEST)
                    .message("Id is required")
                    .build();
        }

        paquete paquete = paqueteDao.getById(id);
        if( paquete == null ) {
            throw ApiException.builder()
                    .code(HttpServletResponse.SC_NOT_FOUND)
                    .message("paquete not found")
                    .build();
        }
        return paquete;
    }

    public paquete insertPaquete(paquete paquete) throws ApiException {
        
        return paqueteDao.insert(paquete);
    }

    public void updateHorasPaquete(int id, int horas) {
        
         paqueteDao.updateHoras(id, horas);
    }
    public void updateRecolectadoPaquete(int id, boolean recolectado) {
        
         paqueteDao.updateRecolectado(id, recolectado);
    }
    public void updateEnDestinoPaquete(int id, boolean enDestino) {
        
         paqueteDao.updateEnDestino(id, enDestino);
    }
    public void setCosto(int id) {
        paquete paquete = paqueteDao.getById(id);
        double costo= paquete.getHoras()*paquete.getTarifaOperacion();
         paqueteDao.setCosto(id, costo);
    }
    public void setSubTotal(int id) {
        paquete paquete = paqueteDao.getById(id);
        double subtotal= paquete.getLibras()+paquete.getCuotaDestino();
         paqueteDao.setCosto(id, subtotal);
    }
    public void deletePaquete(int id) {
        paqueteDao.delete(id);
    }
}
