package com.example.lab5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HabitosAdapter extends RecyclerView.Adapter<HabitosAdapter.HabitoViewHolder> {

    private List<Habito> listaHabitos;

    public HabitosAdapter(List<Habito> listaHabitos) {
        this.listaHabitos = listaHabitos;
    }

    @NonNull
    @Override
    public HabitoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habito, parent, false);
        return new HabitoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitoViewHolder holder, int position) {
        Habito habito = listaHabitos.get(position);
        holder.textNombre.setText(habito.getNombre());
        holder.textCategoria.setText("Categoría: " + habito.getCategoria());
        holder.textFrecuencia.setText("Cada " + habito.getFrecuenciaHoras() + " horas");
        holder.textFechaInicio.setText("Inicio: " + habito.getFechaHoraInicio());
    }

    @Override
    public int getItemCount() {
        return listaHabitos.size();
    }

    public static class HabitoViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre, textCategoria, textFrecuencia, textFechaInicio;

        public HabitoViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombreHabito);
            textCategoria = itemView.findViewById(R.id.textCategoria);
            textFrecuencia = itemView.findViewById(R.id.textFrecuencia);
            textFechaInicio = itemView.findViewById(R.id.textFechaInicio);
        }
    }
}
