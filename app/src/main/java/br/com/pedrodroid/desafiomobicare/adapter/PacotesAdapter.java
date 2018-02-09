package br.com.pedrodroid.desafiomobicare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.pedrodroid.desafiomobicare.R;
import br.com.pedrodroid.desafiomobicare.modelo.Pacote;


/**
 * Created by pedrodroid on 08/07/17.
 */

public class PacotesAdapter extends BaseAdapter {
    private List<Pacote> pacotes;
    private Context context;

    public PacotesAdapter(List<Pacote> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Object getItem(int i) {
        return pacotes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pacote pacote = pacotes.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.item_pacote, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.item_pacote_imagem);
        Picasso.with(context).load(pacote.getCover()).placeholder(R.mipmap.ic_launcher).into(imageView);

        TextView titulo = view.findViewById(R.id.item_pacote_local);
        titulo.setText("Pacote : " + pacote.getName());

        TextView dias = view.findViewById(R.id.item_pacote_dias);
        dias.setText(pacote.getDias() + " Dias");

        TextView price = view. findViewById(R.id.item_pacote_preco);
        price.setText(pacote.getPrice());


        return view;
    }
}
