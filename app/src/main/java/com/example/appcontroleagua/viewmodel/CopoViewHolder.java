package com.example.appcontroleagua.viewmodel;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcontroleagua.OnCopoClickListener;
import com.example.appcontroleagua.R;

public class CopoViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    private final TextView txtML;
    private final OnCopoClickListener onCopoClickListener;

    public CopoViewHolder(@NonNull View itemView, OnCopoClickListener onCopoClickListener) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        txtML = itemView.findViewById(R.id.txtML);
        this.onCopoClickListener = onCopoClickListener;
    }

    public void bind(final CopoViewModel copoViewModel) {
        imageView.setImageResource(copoViewModel.isCheio() ? R.drawable.copinhocheio : R.drawable.copinhovazio);
        txtML.setText(copoViewModel.getVolume() + " ml");

        itemView.setOnClickListener(v -> {
            if (!copoViewModel.isCheio()) {
                copoViewModel.beber();
                imageView.setImageResource(R.drawable.copinhocheio);
            } else {
                copoViewModel.desbeber();
                imageView.setImageResource(R.drawable.copinhovazio);
            }
            onCopoClickListener.copoClicado(copoViewModel);
        });
    }
}

