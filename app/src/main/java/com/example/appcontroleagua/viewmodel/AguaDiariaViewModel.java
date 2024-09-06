package com.example.appcontroleagua.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.appcontroleagua.model.AguaDiaria;
import com.example.appcontroleagua.model.Copo;

import java.util.ArrayList;
import java.util.List;

public class AguaDiariaViewModel extends ViewModel  {
    private AguaDiaria aguaDiaria;
    private List<CopoViewModel> copos;

    public AguaDiariaViewModel(int peso, int volumeCopo) {
        this.aguaDiaria = new AguaDiaria(peso, volumeCopo);
        this.copos = new ArrayList<>();
        for (Copo copo : aguaDiaria.getCopos()) {
            this.copos.add(new CopoViewModel(copo));
        }
    }

    public List<CopoViewModel> getCopos() {
        return copos;
    }

    public float litrosBebidosAteAgora() {
        float quantBebida = 0;
        for (CopoViewModel copo : copos) {
            if (copo.isCheio()) {
                quantBebida += copo.getVolume();
            }
        }
        return quantBebida / 1000.0f; // Convert to liters
    }

    public float quantLitrosFaltando() {
        float quantFaltando = aguaDiaria.getVolumeTotal() - litrosBebidosAteAgora() * 1000;
        return Math.max(quantFaltando, 0) / 1000.0f; // Garante que o valor não seja negativo
    }

    public int getPeso() {
        return aguaDiaria.getPeso();
    }

    public void reset() {
        aguaDiaria.reset();
        for (CopoViewModel copoViewModel : copos) {
            copoViewModel.desbeber();
        }
    }
}
