package com.example.haruswisuda.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haruswisuda.R;

import java.util.List;

public class AdapterSpinnerPenyakit extends RecyclerView.Adapter<AdapterSpinnerPenyakit.ViewHolder> {
        List<ModelSPinner> gejalas;
        Context context;

        public AdapterSpinnerPenyakit(List<ModelSPinner> modelGejalas, Context context) {
            this.gejalas = modelGejalas;
            this.context = context;
        }
        @NonNull
        @Override
        public AdapterSpinnerPenyakit.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View gejaka= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gejala,parent,false);
            return new AdapterSpinnerPenyakit.ViewHolder(gejaka);
        }

        @Override
        public void onBindViewHolder(@NonNull final AdapterSpinnerPenyakit.ViewHolder holder, final int position) {

        }
    @Override
    public int getItemCount() {
        if (gejalas != null){
            return gejalas.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nmGejala, idsoal, cfu;
        RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
