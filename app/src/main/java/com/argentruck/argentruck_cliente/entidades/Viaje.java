package com.argentruck.argentruck_cliente.entidades;


import org.json.JSONArray;

public class Viaje {

    private String id;
    private Integer idCamionero;
    private String fecha;
    private String origen;
    private String destino;
    private Integer espacioTotal;
    private Integer espacioLibre;
    private Integer espacioPedido;
    private String conductor;

    public Viaje(String fecha, String origen, String destino, Integer espacioTotal, Integer espacioLibre) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.espacioTotal = espacioTotal;
        this.espacioLibre = espacioLibre;
    }

    public String getId() {
        return id;
    }

    public Integer getIdCamionero() {
        return idCamionero;
    }

    public void setIdCamionero(Integer idCamionero) {
        this.idCamionero = idCamionero;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getEspacioTotal() {
        return espacioTotal;
    }

    public void setEspacioTotal(Integer espacioTotal) {
        this.espacioTotal = espacioTotal;
    }

    public Integer getEspacioLibre() {
        return espacioLibre;
    }

    public void setEspacioLibre(Integer espacioLibre) {
        this.espacioLibre = espacioLibre;
    }

    public Integer getEspacioPedido() {
        return espacioPedido;
    }

    public void setEspacioPedido(Integer espacioPedido) {
        this.espacioPedido = espacioPedido;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }
}
