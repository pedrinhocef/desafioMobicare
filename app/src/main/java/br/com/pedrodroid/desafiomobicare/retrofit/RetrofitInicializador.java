package br.com.pedrodroid.desafiomobicare.retrofit;

import br.com.pedrodroid.desafiomobicare.services.PacoteService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;
    private final String URL = "https://private-0a5bc-mobicare5.apiary-mock.com";

    public RetrofitInicializador() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

                retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(JacksonConverterFactory.create())
                        .client(client.build())
                        .build();

    }

    public PacoteService getPacoteService() {
        return retrofit.create(PacoteService.class);
    }
}
