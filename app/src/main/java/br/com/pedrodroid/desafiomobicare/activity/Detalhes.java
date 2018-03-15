package br.com.pedrodroid.desafiomobicare.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.com.pedrodroid.desafiomobicare.R;
import br.com.pedrodroid.desafiomobicare.modelo.Pacote;


public class Detalhes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button btnBuy = findViewById(R.id.detalhes_btn_buy);
        ImageView local = findViewById(R.id.detalhes_image_local);
        TextView name = findViewById(R.id.detalhes_nome_pacote);
        TextView price = findViewById(R.id.detalhes_price_pacote);
        TextView desc = findViewById(R.id.detalhes_desc_pacote);

        final Intent intent = getIntent();
        final Pacote pacote = (Pacote) intent.getSerializableExtra("pacotes");

        if (pacote != null) {
            name.setText(pacote.getName());
            price.setText(pacote.getPrice());
            desc.setText(pacote.getDescription());
            Picasso.with(this).load(pacote.getLocal()).placeholder(R.mipmap.ic_launcher).into(local);
        }

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Detalhes.this, "Comprando Passagem Selecionada " + pacote.getName() , Toast.LENGTH_SHORT).show();
                infoDevice();
            }
        });

    }

    public void infoDevice(){
        int sdkInt = Build.VERSION.SDK_INT;
        String marcaDevice = Build.MANUFACTURER;
        String model = Build.MODEL;

        Toast.makeText(this, "SDK: " + sdkInt + " Marca do Aparelho: " + marcaDevice
                + " Modelo: " + model ,Toast.LENGTH_LONG).show();
    }
}
