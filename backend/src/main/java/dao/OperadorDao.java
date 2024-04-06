/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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
public class OperadorDao {
    
    public List<operador> getOperadores(){
        List<operador> operadores = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Operador;");
            while (rs.next()) {
                operador operador = new operador();
                operador.setActivo(rs.getBoolean("activo"));
                operador.setNombre(rs.getString("nombre"));
                operador.setContraseña(rs.getString("contrasena"));
                operadores.add(operador);
            }
            return operadores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public operador getById(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Operador     WHERE id_operador = ?;");
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                operador operador = new operador();
                operador.setActivo(rs.getBoolean("activo"));
                operador.setNombre(rs.getString("nombre"));
                operador.setContraseña(rs.getString("contrasena"));
                return operador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public operador insert(operador operador){
        try {
            
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO Operador (activo, nombre, contrasena) VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setBoolean(1, operador.isActivo());
            stmt.setString(2, operador.getNombre());
            stmt.setString(3, operador.getContraseña());
            
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                operador.setId(generatedKeys.getInt(1));
                return operador;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateActivo(int id, boolean activo) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Operador SET activo = ? WHERE id_operador = ?;"
            );
            stmt.setBoolean(1, activo);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado del operador", e);
        }
    }
    public void updateNombre(int id, String nombre) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Operador SET nombre = ? WHERE id_operador = ?;"
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
                    "UPDATE Operador SET contrasena = ? WHERE id_operador = ?;"
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
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Operador WHERE id_operador = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
