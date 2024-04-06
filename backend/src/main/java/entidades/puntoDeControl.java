package entidades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carlos
 */
public class puntoDeControl {
    private int id;
    private int idRecepcionista;
    private int idOperador;
    private int cantidadCola;
    private double coutaDestino;
    paquete[] cola;

    public puntoDeControl(int id, int idRecepcionista, int idOperador, int cantidadCola, double coutaDestino) {
        this.id = id;
        this.idRecepcionista = idRecepcionista;
        this.idOperador = idOperador;
        this.cantidadCola = cantidadCola;
        this.coutaDestino = coutaDestino;
        this.cola= new paquete[cantidadCola];
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(int idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public int getCantidadCola() {
        return cantidadCola;
    }

    public void setCantidadCola(int cantidadCola) {
        this.cantidadCola = cantidadCola;
    }

    public double getCoutaDestino() {
        return coutaDestino;
    }

    public void setCoutaDestino(double coutaDestino) {
        this.coutaDestino = coutaDestino;
    }

    public paquete[] getCola() {
        return cola;
    }

    public void setCola(paquete[] cola) {
        this.cola = cola;
    }
    
}
