package br.com.pedrodroid.desafiomobicare.dto;

import java.io.Serializable;
import java.util.ArrayList;


import br.com.pedrodroid.desafiomobicare.modelo.Pacote;

/**
 * Created by pedrodroid on 08/07/17.
 */

public class PacoteSync implements Serializable{

    private ArrayList<Pacote> destination;

    public ArrayList<Pacote> getDestination() {
        return destination;
    }
}
