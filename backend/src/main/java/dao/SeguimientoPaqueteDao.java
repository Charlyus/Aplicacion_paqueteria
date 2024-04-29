/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.SeguimientoPaquete;
import entidades.operador;
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
public class SeguimientoPaqueteDao {
    
    public List<SeguimientoPaquete> getPaquetes(){
        List<SeguimientoPaquete> seguimientoPaquetes = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT id_paquete, MAX(id_punto_control) FROM SeguimientoPaquete GROUP BY id_paquete;");
            while (rs.next()) {
                SeguimientoPaquete seguimientoPaquete = new SeguimientoPaquete();
                seguimientoPaquete.setIdPaquete(rs.getInt("id_paquete"));
                seguimientoPaquete.setIdPuntoControl(rs.getInt("id_punto_control"));
                seguimientoPaquetes.add(seguimientoPaquete);
            }
            return seguimientoPaquetes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SeguimientoPaquete getById(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM SeguimientoPaquete WHERE id_paquete = ? AND id_punto_control = (SELECT MAX(id_punto_control) FROM SeguimientoPaquete WHERE id_paquete = ?);");
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SeguimientoPaquete seguimientoPaquete = new SeguimientoPaquete();
                seguimientoPaquete.setIdPaquete(rs.getInt("id_paquete"));
                seguimientoPaquete.setIdPuntoControl(rs.getInt("id_punto_control"));
                seguimientoPaquete.setIdRuta(rs.getInt("id_ruta"));
                return seguimientoPaquete;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SeguimientoPaquete insert(SeguimientoPaquete seguimientoPaquete){
        try {
            
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO SeguimientoPaquete (id_paquete, id_ruta, id_punto_control) VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, seguimientoPaquete.getIdPaquete());
            stmt.setInt(2, seguimientoPaquete.getIdRuta());
            stmt.setInt(3, seguimientoPaquete.getIdPuntoControl());
            stmt.execute();
            
            return seguimientoPaquete;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM SeguimientoPaquete WHERE id_paquete = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
