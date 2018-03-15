package br.com.pedrodroid.desafiomobicare.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.pedrodroid.desafiomobicare.R;
import br.com.pedrodroid.desafiomobicare.adapter.PacotesAdapter;
import br.com.pedrodroid.desafiomobicare.dto.PacoteSync;
import br.com.pedrodroid.desafiomobicare.modelo.Pacote;
import br.com.pedrodroid.desafiomobicare.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private PacotesAdapter mAdapter;
    private Pacote pacotes;
    private Intent tratarConexao;
    private PacoteSync pacoteSync;
    static final String STATE_ACTIVITY = "salvando";
    private SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tratarConexao = new Intent(this, TratarConexao.class);

        refresh = findViewById(R.id.refresh);
        refresh.setOnRefreshListener(OnRefreshListener());


        listView = findViewById(R.id.lv_pacotes);
        listView.setAdapter(mAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pacote, View view, int i, long l) {
                pacotes = (Pacote) listView.getItemAtPosition(i);
                Intent vaiProDetalhes = new Intent(MainActivity.this, Detalhes.class);
                vaiProDetalhes.putExtra("pacotes", pacotes);
                startActivity(vaiProDetalhes);

            }
        });

    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Retrofit();
                Log.e("onRefresh", "Chamado");
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOnline()) {
            Retrofit();
        } else {
            startActivity(tratarConexao);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(STATE_ACTIVITY, pacoteSync);
        Log.e("OnsavedChamado", String.valueOf(outState));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        pacoteSync = (PacoteSync) savedInstanceState.getSerializable(STATE_ACTIVITY);
        Log.e("OnRestore", String.valueOf(pacoteSync));
    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public void Retrofit() {
        Call<PacoteSync> call = new RetrofitInicializador().getPacoteService().buscaPacote();

        call.enqueue(new Callback<PacoteSync>() {
            @Override
            public void onResponse(Call<PacoteSync> call, Response<PacoteSync> response) {

                if (response.isSuccessful()) {
                    pacoteSync = response.body();
                    mAdapter = null;

                    if (pacoteSync != null) {
                        mAdapter = new PacotesAdapter(pacoteSync.getDestination(), MainActivity.this);
                    }

                    listView.setAdapter(mAdapter);
                    refresh.setRefreshing(false);
                    Log.e("OnReponse chamado", response.message());
                }
            }

            @Override
            public void onFailure(Call<PacoteSync> call, Throwable t) {
                Log.e("onfailure", t.getMessage());
            }
        });
    }
}
