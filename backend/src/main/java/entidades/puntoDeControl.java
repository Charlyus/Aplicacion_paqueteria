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
public class puntoDeControl {
    private int id;
    private int idRecepcionista;
    private int idOperador;
    private int cantidadCola;
    private double cuotaDestino;
    private String descripcion;
    paquete[] cola;

    
}
