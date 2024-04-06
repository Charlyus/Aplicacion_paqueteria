package entidades;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlos
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class paquete {
   private int id;
   private double libras;
   private double cuotaDestino;
   private int idRuta;
   private int idCliente;
   private double tarifaOperacion;
   private boolean enDestino;
   private boolean recolectado;
   private double subtotal;
   private double horas;
   private double costo;

}
