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

public class AdapterInfo extends RecyclerView.Adapter<AdapterInfo.ViewHolder>  {
    List<ModelInfo> listpenyakit;
    Context context;
    int[] Gambarpenyakit = {R.drawable.berakkapur,R.drawable.gumboro,R.drawable.ilt,R.drawable.ngorokkk,R.drawable.snot,R.drawable.tetelo};
    String [] imgs ={"snot","ngorokkk","ilt","berakkapur","gumboro","tetelo"};

    public AdapterInfo(List<ModelInfo> listpenyakit, Context context) {
        this.listpenyakit = listpenyakit;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View penyakit= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data,parent,false);
        return new ViewHolder(penyakit);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInfo.ViewHolder holder, final int position) {
        holder.namapenyakit.setText(listpenyakit.get(position).getNama_penyakit());
        holder.gambar.setImageResource(Gambarpenyakit[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,InfoPenyakit.class);
                intent.putExtra("nama",listpenyakit.get(position).getNama_penyakit());
                intent.putExtra("image",imgs[position]);
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
