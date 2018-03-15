package br.com.pedrodroid.desafiomobicare.services;

import br.com.pedrodroid.desafiomobicare.dto.PacoteSync;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PacoteService {

    @GET("pacotes")
    Call <PacoteSync> buscaPacote();
}
