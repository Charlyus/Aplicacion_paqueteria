/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.recepcionista;
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
public class RecepcionistaDao {
        
    public List<recepcionista> getRecepcionistas(){
        List<recepcionista> recepcionistas = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Recepcionista;");
            while (rs.next()) {
                recepcionista recepcionista = new recepcionista();
                recepcionista.setId(rs.getInt("id_recepcionista"));
                recepcionista.setActivo(rs.getBoolean("activo"));
                recepcionista.setNombre(rs.getString("nombre"));
                recepcionista.setContraseña(rs.getString("contrasena"));
                recepcionistas.add(recepcionista);
            }
            return recepcionistas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public recepcionista getById(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Recepcionista WHERE id_recepcionista = ?;");
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                recepcionista recepcionista = new recepcionista();
                recepcionista.setId(rs.getInt("id_recepcionista"));
                recepcionista.setActivo(rs.getBoolean("activo"));
                recepcionista.setNombre(rs.getString("nombre"));
                recepcionista.setContraseña(rs.getString("contrasena"));
                return recepcionista;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public recepcionista insert(recepcionista recepcionista){
        try {
            
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO Recepcionista (activo, nombre, contrasena) VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setBoolean(1, recepcionista.isActivo());
            stmt.setString(2, recepcionista.getNombre());
            stmt.setString(3, recepcionista.getContraseña());
            
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                recepcionista.setId(generatedKeys.getInt(1));
                return recepcionista;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateActivo(int id, boolean activo) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Recepcionista SET activo = ? WHERE id_recepcionista = ?;"
            );
            stmt.setBoolean(1, activo);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado del recepcionista", e);
        }
    }
    public void updateNombre(int id, String nombre) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Recepcionista SET nombre = ? WHERE id_recepcionista = ?;"
            );
            stmt.setString(1, nombre);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el nombre del Recepcionista", e);
        }
    }
    
    public void updateContraseña(int id, String contraseña) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Recepcionista SET contrasena = ? WHERE id_recepcionista = ?;"
            );
            stmt.setString(1, contraseña);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la contraseña del Recepcionista", e);
        }
    }
    
    public void delete(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Recepcionista WHERE id_recepcionista = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
