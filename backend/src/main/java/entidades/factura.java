package entidades;


import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlos
 */
public class factura {
    private int id;
    private int nitCliente;
    private double total;
    private LocalDate fecha;
    private boolean costo;

    public factura(int id, int nitCliente, double total, LocalDate fecha, boolean costo) {
        this.id = id;
        this.nitCliente = nitCliente;
        this.total = total;
        this.fecha = fecha;
        this.costo = costo;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(int nitCliente) {
        this.nitCliente = nitCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isCosto() {
        return costo;
    }

    public void setCosto(boolean costo) {
        this.costo = costo;
    }
    
}
