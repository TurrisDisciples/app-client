package com.argentruck.argentruck_cliente;


import com.argentruck.argentruck_cliente.entidades.Viaje;

public class Singletonazo {
    private String email;
    private String ip = "http://192.168.1.16";
    private Viaje currentShowViaje;

    private static Singletonazo ourInstance = new Singletonazo();

    public static Singletonazo getInstance() {
        return ourInstance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Singletonazo() {
    }

    public String getIp() {
        return ip;
    }

    public Viaje getCurrentShowViaje() {
        return currentShowViaje;
    }

    public void setCurrentShowViaje(Viaje currentShowViaje) {
        this.currentShowViaje = currentShowViaje;
    }
}
