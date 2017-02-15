package com.argentruck.argentruck_cliente;


import java.util.UUID;

public class Client {
    private String mId;
    private String mName;
    private String mTitle;
    private String mCompany;
    private String mCapacidad;
    private String mFecha;
    private String origen;
    private String destino;
    private int mImage;

    public Client(String name, String title, String company, int image) {
        mId = UUID.randomUUID().toString();
        mName = name;
        mTitle = title;
        mCompany = company;
        mImage = image;
        origen = name;
        destino = title;
        mCapacidad = company;
        mFecha = "10/25/2016";
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }

    public void setCapacidad(String capacidad) {
        mCapacidad = capacidad;
    }

    public String getCapacidad() {
        return mCapacidad;
    }

    public void setFecha(String fecha) {
        mFecha = fecha;
    }

    public String getFecha() {
        return mFecha;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "ID='" + mId + '\'' +
                ", Compañía='" + mCompany + '\'' +
                ", Nombre='" + mName + '\'' +
                ", Cargo='" + mTitle + '\'' +
                ", Origen='" + origen + '\'' +
                ", Destino='" + destino + '\'' +
                ", Capacidad='" + mCapacidad+ '\'' +
                ", Fecha='" + mFecha+ '\'' +
                '}';
    }
}

