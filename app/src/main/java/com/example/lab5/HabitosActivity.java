package com.example.lab5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab5.databinding.ActivityHabitosBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HabitosActivity extends AppCompatActivity {

    private ActivityHabitosBinding binding;
    private List<Habito> listaHabitos;
    private HabitosAdapter adapter;
    private SharedPreferences preferences;
    private static final String PREF_NAME = "app_preferences";
    private static final String KEY_HABITOS = "lista_habitos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHabitosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Leer hábitos guardados
        String jsonHabitos = preferences.getString(KEY_HABITOS, null);
        if (jsonHabitos != null) {
            Type tipoLista = new TypeToken<List<Habito>>() {}.getType();
            listaHabitos = new Gson().fromJson(jsonHabitos, tipoLista);
        } else {
            listaHabitos = new ArrayList<>();
        }

        // Configurar RecyclerView
        adapter = new HabitosAdapter(listaHabitos);
        binding.recyclerHabitos.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerHabitos.setAdapter(adapter);

        // Botón para registrar nuevo hábito
        binding.btnNuevoHabito.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewHabitoActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        cargarHabitos();
    }
    private void cargarHabitos() {
        String jsonHabitos = preferences.getString(KEY_HABITOS, null);
        if (jsonHabitos != null) {
            Type tipoLista = new TypeToken<List<Habito>>() {}.getType();
            listaHabitos = new Gson().fromJson(jsonHabitos, tipoLista);
        } else {
            listaHabitos = new ArrayList<>();
        }

        adapter = new HabitosAdapter(listaHabitos);
        binding.recyclerHabitos.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerHabitos.setAdapter(adapter);
    }

}