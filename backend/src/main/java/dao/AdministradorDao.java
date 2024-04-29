/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.administrador;
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
public class AdministradorDao {
    
    public List<administrador> getOperadores(){
        List<administrador> administradores = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Administrador;");
            while (rs.next()) {
                administrador administrador = new administrador();
                administrador.setId(rs.getInt("Id_administrador"));
                administrador.setNombre(rs.getString("nombre"));
                administrador.setContraseña(rs.getString("contrasena"));
                administradores.add(administrador);
            }
            return administradores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public administrador getById(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Administrador     WHERE Id_administrador = ?;");
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                administrador administrador = new administrador();
                administrador.setId(rs.getInt("Id_administrador"));
                administrador.setNombre(rs.getString("nombre"));
                administrador.setContraseña(rs.getString("contrasena"));
                return administrador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public administrador insert(administrador administrador){
        try {
            
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO Administrador (nombre, contrasena) VALUES (?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, administrador.getNombre());
            stmt.setString(2, administrador.getContraseña());
            
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                administrador.setId(generatedKeys.getInt(1));
                return administrador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void updateNombre(int id, String nombre) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Administrador SET nombre = ? WHERE Id_administrador = ?;"
            );
            stmt.setString(1, nombre);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el nombre del operador", e);
        }
    }
    
    public void updateContraseña(int id, String contraseña) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Administrador SET contrasena = ? WHERE Id_administrador = ?;"
            );
            stmt.setString(1, contraseña);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la contraseña del operador", e);
        }
    }
    
    public void delete(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Administrador WHERE Id_administrador = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
