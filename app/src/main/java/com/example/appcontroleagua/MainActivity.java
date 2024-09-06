package com.example.appcontroleagua;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcontroleagua.adapter.AguaDiariaAdapter;
import com.example.appcontroleagua.viewmodel.AguaDiariaViewModel;
import com.example.appcontroleagua.viewmodel.CopoViewModel;

public class MainActivity extends AppCompatActivity implements OnCopoClickListener{

    private AguaDiariaViewModel aguaDiariaViewModel;
    private AguaDiariaAdapter adapter;
    private RecyclerView recyclerView;
    private TextView txtBebeu;
    private TextView txtFaltando;
    private TextView txtCoposFaltando;
    private Button btnCalcular;
    private ImageButton btnLimpar;
    private EditText edtPeso;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        txtBebeu = findViewById(R.id.avisoQtdBebida);
        txtFaltando = findViewById(R.id.avisoQtdFaltando);
        txtCoposFaltando = findViewById(R.id.qtdCoposFaltando);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);
        edtPeso = findViewById(R.id.inserirPeso);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        btnCalcular.setOnClickListener(v -> {
            String pesoStr = edtPeso.getText().toString();
            if (!pesoStr.isEmpty()) {
                int peso = Integer.parseInt(pesoStr);
                aguaDiariaViewModel = new AguaDiariaViewModel(peso, 150);
                adapter = new AguaDiariaAdapter(aguaDiariaViewModel.getCopos(), this);
                recyclerView.setAdapter(adapter);
                updateUI();
            }
        });

        btnLimpar.setOnClickListener(v -> {
            edtPeso.setText("");
            txtBebeu.setText("0 L");
            txtFaltando.setText("0 L");
            recyclerView.setAdapter(null);
            aguaDiariaViewModel = null;
        });

        if (savedInstanceState != null) {
            int peso = savedInstanceState.getInt("peso", 0); // Default value if not found
            if (peso > 0) {
                aguaDiariaViewModel = new AguaDiariaViewModel(peso, 150);
                edtPeso.setText(String.valueOf(peso));
                adapter = new AguaDiariaAdapter(aguaDiariaViewModel.getCopos(), this);
                recyclerView.setAdapter(adapter);
                updateUI();
            }
        }
    }

    @Override
    public void copoClicado(CopoViewModel copoViewModel) {
        // Atualize a UI quando um copo for clicado
        updateUI();
    }

    private void updateUI() {

        if (aguaDiariaViewModel != null) {
            float litrosBebidos = aguaDiariaViewModel.litrosBebidosAteAgora();
            float litrosFaltando = aguaDiariaViewModel.quantLitrosFaltando();

            txtFaltando.setText(String.format("%.1f L", litrosBebidos));
            // Atualize o texto "faltando" para não mostrar valores negativos
            txtBebeu.setText(String.format("%.1f L", Math.max(litrosFaltando, 0)));


            int coposFaltando = (int) Math.ceil(litrosFaltando / 0.15);
            txtCoposFaltando.setText(coposFaltando + " cp ou");

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (aguaDiariaViewModel != null) {
            outState.putInt("peso", aguaDiariaViewModel.getPeso());
        }
        outState.putString("peso_edit_text", edtPeso.getText().toString());
    }
}