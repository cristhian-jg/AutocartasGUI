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

        private TextView tvEstadistica;

        public EstadisticasViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEstadistica = itemView.findViewById(R.id.tvEstadistica);
        }

        public void bindEstadistica(Estadistica estadistica) {
            tvEstadistica.setText(estadistica.getJugador());
        }
    }
}
