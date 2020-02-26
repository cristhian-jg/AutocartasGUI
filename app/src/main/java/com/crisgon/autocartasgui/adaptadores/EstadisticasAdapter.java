package com.crisgon.autocartasgui.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crisgon.autocartasgui.R;
import com.crisgon.autocartasgui.modelo.Estadistica;

import java.util.ArrayList;

/**
 * Adaptador de las RecyclerView de Estadisticas que define que se muestra en este.
 *
 * Created by @cristhian-jg on 20/02/2020.
 */
public class EstadisticasAdapter extends RecyclerView.Adapter<EstadisticasAdapter.EstadisticasViewHolder> {

    private ArrayList<Estadistica> estadisticas;

    public EstadisticasAdapter(ArrayList<Estadistica> estadisticas) {
        this.estadisticas = estadisticas;
    }

    @NonNull
    @Override
    public EstadisticasAdapter.EstadisticasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_estadistica, parent, false);
        return new EstadisticasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EstadisticasAdapter.EstadisticasViewHolder holder, int position) {
        Estadistica estadistica = estadisticas.get(position);
        holder.bindEstadistica(estadistica);
    }

    @Override
    public int getItemCount() {
        return estadisticas.size();
    }

    public class EstadisticasViewHolder extends RecyclerView.ViewHolder {

        private TextView tvIdEstadistica;
        private TextView tvJugador;
        private TextView tvPartida;
        private TextView tvGanadas;
        private TextView tvPerdidas;
        private TextView tvEmpatadas;

        public EstadisticasViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdEstadistica = itemView.findViewById(R.id.tvIdEstadistica);
            tvJugador = itemView.findViewById(R.id.tvJugadorEstadistica);
            tvPartida = itemView.findViewById(R.id.tvPartidaEstadistica);
            tvGanadas = itemView.findViewById(R.id.tvGanadasEstadistica);
            tvPerdidas = itemView.findViewById(R.id.tvPerdidasEstadistica);
            tvEmpatadas  = itemView.findViewById(R.id.tvEmpatadasEstadistica);
        }

        public void bindEstadistica(Estadistica estadistica) {
            tvIdEstadistica.setText(String.valueOf(estadistica.getId()));
            tvJugador.setText(estadistica.getJugador());
            tvPartida.setText(String.valueOf(estadistica.getPartida()));
            tvGanadas.setText(String.valueOf(estadistica.getGanadas()));
            tvPerdidas.setText(String.valueOf(estadistica.getPerdidas()));
            tvEmpatadas.setText(String.valueOf(estadistica.getEmpatadas()));
        }
    }
}
