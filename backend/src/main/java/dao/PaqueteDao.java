/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entidades.paquete;
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
public class PaqueteDao {
    public List<paquete> getPaquetes(){
        List<paquete> paquetes = new ArrayList<>();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Paquete;");
            while (rs.next()) {
                paquete paquete = new paquete();
                paquete.setId(rs.getInt("id_paquete"));
                paquete.setIdRuta(rs.getInt("id_ruta"));
                paquete.setIdCliente(rs.getInt("nit_cliente"));
                paquete.setEnDestino(rs.getBoolean("enDestino"));
                paquete.setRecolectado(rs.getBoolean("recolectado"));
                paquete.setCuotaDestino(rs.getDouble("cuotaDestino"));
                paquete.setTarifaOperacion(rs.getDouble("tarifaDeOperacion"));
                paquete.setLibras(rs.getDouble("libras"));
                paquete.setHoras(rs.getDouble("horas"));
                
                paquetes.add(paquete);
            }
            return paquetes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public paquete getById(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Paquete WHERE id_paquete = ?;");
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                paquete paquete = new paquete();
                paquete.setId(rs.getInt("id_paquete"));
                paquete.setIdRuta(rs.getInt("id_ruta"));
                paquete.setIdCliente(rs.getInt("nit_cliente"));
                paquete.setEnDestino(rs.getBoolean("enDestino"));
                paquete.setRecolectado(rs.getBoolean("recolectado"));
                paquete.setCuotaDestino(rs.getDouble("cuotaDestino"));
                paquete.setTarifaOperacion(rs.getDouble("tarifaDeOperacion"));
                paquete.setLibras(rs.getDouble("libras"));
                paquete.setHoras(rs.getDouble("horas"));
                return paquete;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public paquete insert(paquete paquete){
        try {
            
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO Paquete (id_ruta, nit_cliente, tarifaDeOperacion, enDestino, recolectado, libras, subtotal, horas, cuotaDestino) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmt.setInt(1, paquete.getIdRuta());
            stmt.setInt(2, paquete.getIdCliente());
            stmt.setDouble(3, paquete.getTarifaOperacion());
            stmt.setBoolean(4, paquete.isEnDestino());
            stmt.setBoolean(5, paquete.isRecolectado());
            stmt.setDouble(6, paquete.getLibras());
            stmt.setDouble(7, paquete.getSubtotal());
            stmt.setDouble(8, paquete.getHoras());
            stmt.setDouble(9, paquete.getCuotaDestino());
            
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                paquete.setId(generatedKeys.getInt(1));
                return paquete;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateEnDestino(int id, boolean enDestino) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Paquete SET enDestino = ? WHERE id_paquete = ?;"
            );
            stmt.setBoolean(1, enDestino);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado del paquete", e);
        }
    }
    public void updateRecolectado(int id, boolean recolectado) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Paquete SET recolectado = ? WHERE id_paquete = ?;"
            );
            stmt.setBoolean(1, recolectado);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado del paquete", e);
        }
    }
    public void updateHoras(int id, double horas) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Paquete SET horas = ? WHERE id_paquete = ?;"
            );
            stmt.setDouble(1, horas);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado del paquete", e);
        }
    }
    public void setCosto(int id, double costo) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Paquete SET costo = ? WHERE id_paquete = ?;"
            );
            stmt.setDouble(1, costo);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado del paquete", e);
        }
    }
    public void setSubTotal(int id, double subtotal) {
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(
                    "UPDATE Paquete SET subtotal = ? WHERE id_paquete = ?;"
            );
            stmt.setDouble(1, subtotal);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado del paquete", e);
        }
    }
    public void delete(int id){
        try {
            PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Paquete WHERE id_paquete = ?;");
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
