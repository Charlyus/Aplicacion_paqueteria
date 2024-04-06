/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import entidades.puntoDeControl;
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
public class PuntoControlDao {
    public List<puntoDeControl> getPuntosDeControl(){
        List<puntoDeControl> puntosDeControl = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PuntoDeControl;");
            while (rs.next()) {
                puntoDeControl puntoDeControl = new puntoDeControl();
                puntoDeControl.setId(rs.getInt("id_punto_control"));
                puntoDeControl.setIdRecepcionista(rs.getInt("id_recepcionista"));
                puntoDeControl.setIdOperador(rs.getInt("id_operador"));
                puntoDeControl.setCantidadCola(rs.getInt("cantidadCola"));
                puntoDeControl.setCuotaDestino(rs.getDouble("cuotaDestino"));
                puntoDeControl.setDescripcion("Descripcion");
                puntosDeControl.add(puntoDeControl);
            }
            return puntosDeControl;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public puntoDeControl getById(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM PuntoDeControl WHERE id_punto_control = ?;");
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                puntoDeControl puntoDeControl = new puntoDeControl();
                puntoDeControl.setId(rs.getInt("id_punto_control"));
                puntoDeControl.setIdRecepcionista(rs.getInt("id_recepcionista"));
                puntoDeControl.setIdOperador(rs.getInt("id_operador"));
                puntoDeControl.setCantidadCola(rs.getInt("cantidadCola"));
                puntoDeControl.setCuotaDestino(rs.getDouble("cuotaDestino"));
                puntoDeControl.setDescripcion("Descripcion");
                return puntoDeControl;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public puntoDeControl insert(puntoDeControl puntoDeControl){
        try {
            
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO PuntoDeControl (id_recepcionista, id_operador, cantidadCola, cuotaDestino, Descripcion) VALUES (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, puntoDeControl.getIdRecepcionista());
            stmt.setInt(2, puntoDeControl.getIdOperador());
            stmt.setInt(3, puntoDeControl.getCantidadCola());
            stmt.setDouble(4, puntoDeControl.getCuotaDestino());
            stmt.setString(5, puntoDeControl.getDescripcion());
            
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                puntoDeControl.setId(generatedKeys.getInt(1));
                return puntoDeControl;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCantidadCola(int id, int cantidadCola) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE PuntoDeControl SET cantidadCola = ? WHERE id_punto_control = ?;"
            );
            stmt.setInt(1, cantidadCola);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la cantidad en cola del punto  de control", e);
        }
    }
    public void updateCuotaDestino(int id, double cuotaDestino) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE PuntoDeControl SET cuotaDestino = ? WHERE id_punto_control = ?;"
            );
            stmt.setDouble(1, cuotaDestino);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la cuota de destino en cola del punto  de control", e);
        }
    }
    
    public void updateDescripcion(int id, String Descripcion) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE PuntoDeControl SET Descripcion = ? WHERE id_punto_control = ?;"
            );
            stmt.setString(1, Descripcion);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la descripcion en cola del punto  de control", e);
        }
    }
    
    public void delete(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM PuntoDeControl WHERE id_punto_control = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
