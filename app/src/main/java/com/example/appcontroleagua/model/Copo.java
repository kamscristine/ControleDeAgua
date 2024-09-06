package com.example.appcontroleagua.model;

public class Copo {

    private boolean cheio;
    private int volume;

    public Copo(int volume) {
        this.volume = volume;
        this.cheio = false;
    }

    public boolean isCheio() {
        return cheio;
    }

    public void beber() {
        cheio = true;
    }

    public void desbeber() {
        cheio = false;
    }

    public int getVolume() {
        return volume;
    }
}