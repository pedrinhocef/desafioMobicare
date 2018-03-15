package br.com.pedrodroid.desafiomobicare.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.pedrodroid.desafiomobicare.R;

public class TratarConexao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sem_conexao_activity);

        Button btn = (Button) findViewById(R.id.btn_carregar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TratarConexao.this, MainActivity.class);
                if (isOnline()) {
                    startActivity(intent);
                } else {
                    Toast.makeText(TratarConexao.this, "Ainda sem conexão!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
