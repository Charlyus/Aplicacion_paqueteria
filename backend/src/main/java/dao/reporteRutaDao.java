/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import entidades.reporteCliente;
import entidades.reporteRuta;
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
public class reporteRutaDao {
    public List<reporteRuta> getRutas(){
        List<reporteRuta> rutas = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT Ruta.id_ruta,Ruta.nombre, COUNT(Paquete.id_paquete) AS CANTIDAD FROM Paquete INNER JOIN Ruta ON Ruta.id_ruta=Paquete.id_ruta GROUP BY Ruta.id_ruta ORDER BY CANTIDAD DESC LIMIT 3;");
            while (rs.next()) {
                reporteRuta ruta = new reporteRuta();
                ruta.setId(rs.getInt("id_ruta"));
                ruta.setNombre(rs.getString("nombre"));
                ruta.setCantidad(rs.getInt("CANTIDAD"));
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
