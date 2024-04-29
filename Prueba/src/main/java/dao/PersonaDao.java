/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidad.Persona;
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
public class PersonaDao {
        
    public List<Persona> getPersonas(){
        List<Persona> Personas = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Persona;");
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setNombre(rs.getString("nombre"));
                persona.setDpi(rs.getInt("Dpi"));
                persona.setEdad(rs.getInt("edad"));
                Personas.add(persona);
            }
            return Personas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Persona getById(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Persona WHERE id_recepcionista = ?;");
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Persona persona = new Persona();
                persona.setNombre(rs.getString("nombre"));
                persona.setDpi(rs.getInt("Dpi"));
                persona.setEdad(rs.getInt("edad"));
                return persona;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Persona insert(Persona persona){
        try {
            
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO Persona (nombre, Dpi, edad) VALUES (?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setString(1, persona.getNombre());
            stmt.setInt(2, persona.getDpi());
            stmt.setInt(3, persona.getEdad());
            
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            
            return persona;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNombre(int Dpi, String nombre) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Persona SET nombre = ? WHERE Dpi = ?;"
            );
            stmt.setString(1, nombre);
            stmt.setInt(2, Dpi);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el nombre de la persona", e);
        }
    }
    public void updateEdad(int Dpi, int edad) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Persona SET edad = ? WHERE Dpi = ?;"
            );
            stmt.setInt(1, edad);
            stmt.setInt(2, Dpi);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la edad de la persona", e);
        }
    }
    
    
    
    public void delete(int Dpi){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Persona WHERE Dpi = ?;");
            stmt.setInt(1, Dpi);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
