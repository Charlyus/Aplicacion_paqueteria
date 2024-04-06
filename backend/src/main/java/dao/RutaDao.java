/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


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
public class RutaDao {
    public List<ruta> getRutas(){
        List<ruta> rutas = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Ruta;");
            while (rs.next()) {
                ruta ruta = new ruta();
                ruta.setId(rs.getInt("id_ruta"));
                ruta.setNombre(rs.getString("nombre"));
                ruta.setCantPuntosControl(rs.getInt("cantPuntosControl"));
                ruta.setActivo(rs.getBoolean("activo"));
                
                
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

    public ruta insert(ruta ruta){
        try {
            
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO Ruta (nombre, cantPuntosControl, activo) VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, ruta.getNombre());
            stmt.setInt(2, ruta.getCantPuntosControl());
            stmt.setBoolean(3, ruta.isActivo());
            
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                ruta.setId(generatedKeys.getInt(1));
                return ruta;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateActivo(int id, boolean activo) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Ruta SET activo = ? WHERE id_ruta = ?;"
            );
            stmt.setBoolean(1, activo);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado de la ruta", e);
        }
    }
    
    
    
    public void delete(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Ruta WHERE id_ruta = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
