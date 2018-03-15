package br.com.pedrodroid.desafiomobicare.dto;

import java.io.Serializable;
import java.util.ArrayList;


import br.com.pedrodroid.desafiomobicare.modelo.Pacote;


public class PacoteSync implements Serializable{

    private ArrayList<Pacote> destination;

    public ArrayList<Pacote> getDestination() {
        return destination;
    }
}
