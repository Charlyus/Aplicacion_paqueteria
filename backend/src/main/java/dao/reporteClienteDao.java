/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import entidades.reporteCliente;
import entidades.ruta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class reporteClienteDao {
    public List<reporteCliente> getRutas(){
        List<reporteCliente> rutas = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT Cliente.nit, Cliente.nombre, COUNT(Paquete.nit_cliente) AS cantidad_paquetes,  SUM(Paquete.costo) AS COSTOS, SUM(Paquete.subtotal) AS INGRESOS, SUM(Paquete.subtotal - Paquete.costo) AS GANANCIAS FROM Paquete LEFT JOIN Cliente ON Cliente.nit = Paquete.nit_cliente WHERE Paquete.recolectado = TRUE GROUP BY Cliente.nit, Cliente.nombre;");
            while (rs.next()) {
                reporteCliente ruta = new reporteCliente();
                ruta.setNit(rs.getInt("nit"));
                ruta.setNombre(rs.getString("nombre"));
                ruta.setCantidad(rs.getInt("cantidad_paquetes"));
                ruta.setCosto(rs.getInt("COSTOS"));
                ruta.setIntreso(rs.getInt("INGRESOS"));
                ruta.setGanancia(rs.getInt("GANANCIAS"));
                
                rutas.add(ruta);
            }
            return rutas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ruta getById(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Ruta WHERE id_ruta = ?;");
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ruta ruta = new ruta();
                ruta.setId(rs.getInt("id_ruta"));
                ruta.setNombre(rs.getString("nombre"));
                ruta.setCantPuntosControl(rs.getInt("cantPuntosControl"));
                ruta.setActivo(rs.getBoolean("activo"));
                return ruta;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
}
