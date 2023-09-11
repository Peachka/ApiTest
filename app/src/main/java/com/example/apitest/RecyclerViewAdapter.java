package com.example.apitest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<BeerDescriptionModel> beerDescriptionModels;

    private final RecyclerViewInterface recyclerViewInterface;

    public RecyclerViewAdapter(Context context, ArrayList<BeerDescriptionModel> beerDescriptionModels, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.beerDescriptionModels = beerDescriptionModels;
        this.recyclerViewInterface = recyclerViewInterface;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.beer_element, parent, false);
        return new RecyclerViewAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.brand.setText("Brand: "+beerDescriptionModels.get(position).getBrand());
        holder.name.setText("Name: "+ beerDescriptionModels.get(position).getName());
        holder.style.setText("Style: "+beerDescriptionModels.get(position).getStyle());
        holder.alcohol.setText("Alcohol: "+beerDescriptionModels.get(position).getAlcohol());
    }

    @Override
    public int getItemCount() {
        return beerDescriptionModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView brand, name, style, alcohol;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            brand = itemView.findViewById(R.id.brand);
            name = itemView.findViewById(R.id.name);
            style = itemView.findViewById(R.id.style);
            alcohol = itemView.findViewById(R.id.alcohol);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });
        }
    }
}
