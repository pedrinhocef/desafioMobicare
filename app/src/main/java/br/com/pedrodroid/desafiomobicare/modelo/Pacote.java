package br.com.pedrodroid.desafiomobicare.modelo;

import java.io.Serializable;

public class Pacote implements Serializable {

    private String name;
    private String description;
    private String price;
    private int dias;
    private String cover;
    private String local;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getDias() {
        return dias;
    }

    public String getCover() {
        return cover;
    }

    public String getLocal() {
        return local;
    }
}
