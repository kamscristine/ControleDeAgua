package com.example.appcontroleagua.model;

import java.util.ArrayList;
import java.util.List;

public class AguaDiaria {
    private float peso;
    private float volumeCopo;
    private List<Copo> copos = new ArrayList<Copo>();


    public AguaDiaria(float peso, float volumeCopo) {
        this.peso = peso;
        this.volumeCopo = volumeCopo;
    }

    public List<Copo> getCopos() {
        return copos;
    }

    public float litrosBebidosAteAgora(){
        int cont = 0;
        for(Copo c: copos){
            if (c.isCheio()){
                cont+=1;
            }
        }
        return (cont * volumeCopo)/1000;
    }









}
