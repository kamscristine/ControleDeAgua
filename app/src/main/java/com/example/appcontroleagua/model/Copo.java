package com.example.appcontroleagua.model;

public class Copo {

    private float volume;
    private boolean cheio = true;

    // construtor
    public Copo(float volume) {
        this.volume = volume;
    }

    public float getVolume() {
        return volume;
    }

    public boolean isCheio() {
        return cheio;
    }

    public void beber(){
        cheio = false;
    }

    public void desbeber(){
        cheio = true;
    }

}
