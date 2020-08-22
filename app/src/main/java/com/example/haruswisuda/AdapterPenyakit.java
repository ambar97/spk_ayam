package com.example.haruswisuda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPenyakit extends RecyclerView.Adapter<AdapterPenyakit.ViewHolder>  {
    List<ModelPenyakit> listpenyakit;
    Context context;
    int[] Gambarpenyakit = {R.drawable.berakkapur,R.drawable.gumboro,R.drawable.ilt,R.drawable.ngorokkk,R.drawable.snot,R.drawable.tetelo};

    public AdapterPenyakit(List<ModelPenyakit> listpenyakit, Context context) {
        this.listpenyakit = listpenyakit;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPenyakit.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View penyakit= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data,parent,false);
        return new ViewHolder(penyakit);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPenyakit.ViewHolder holder, final int position) {
        holder.namapenyakit.setText(listpenyakit.get(position).getNama_penyakit());
        holder.gambar.setImageResource(Gambarpenyakit[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ListdataActivity.class);
                intent.putExtra("nama",listpenyakit.get(position).getNama_penyakit());
                intent.putExtra("image",Gambarpenyakit[position]);
                intent.putExtra("des",listpenyakit.get(position).getDeskripsi());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listpenyakit != null){
            return listpenyakit.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namapenyakit;
        ImageView gambar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namapenyakit = itemView.findViewById(R.id.namapenyakit);
            gambar = itemView.findViewById(R.id.images);
        }
    }

}
