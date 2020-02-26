package com.crisgon.autocartasgui.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crisgon.autocartasgui.ICartaListener;
import com.crisgon.autocartasgui.R;
import com.crisgon.autocartasgui.modelo.Carta;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Adaptador del RecyclerView de Cartas que define que se muestra en este.
 *
 * Created by @cristhian-jg on 20/02/2020.
 */
public class CartaAdapter extends RecyclerView.Adapter<CartaAdapter.CartaViewHolder> {

    private ArrayList<Carta> cartas;
    private ICartaListener listener;

    public CartaAdapter(ArrayList<Carta> cartas, ICartaListener listener) {
        this.cartas = cartas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_cartas, parent, false);
        return new CartaViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartaViewHolder holder, int position) {
        Carta carta = cartas.get(position);
        holder.bindCarta(carta);
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }

    public class CartaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ICartaListener listener;

        private TextView tvId;
        private TextView tvMotor;
        private TextView tvPotencia;
        private TextView tvVelocidad;
        private TextView tvCilindros;
        private TextView tvRevoluciones;
        private TextView tvConsumo;

        public CartaViewHolder(@NonNull View itemView, ICartaListener listener) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvMotor = itemView.findViewById(R.id.tvMotor);
            tvPotencia = itemView.findViewById(R.id.tvPotencia);
            tvVelocidad = itemView.findViewById(R.id.tvVelocidad);
            tvCilindros = itemView.findViewById(R.id.tvCilindros);
            tvRevoluciones = itemView.findViewById(R.id.tvRevoluciones);
            tvConsumo = itemView.findViewById(R.id.tvConsumo);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        public void bindCarta(Carta carta) {
            tvId.setText(carta.getIdentificador());
            tvMotor.setText("" + carta.getMotor());
            tvPotencia.setText(String.valueOf(carta.getPotencia()));
            tvVelocidad.setText(String.valueOf(carta.getVelocidad()));
            tvCilindros.setText(String.valueOf(carta.getCilindros()));
            tvRevoluciones.setText(String.valueOf(carta.getRevoluciones()));
            tvConsumo.setText(String.valueOf(carta.getConsumo()));
        }

        @Override
        public void onClick(View v) {
            listener.onSelectedCarta(getAdapterPosition());
        }
    }
}
