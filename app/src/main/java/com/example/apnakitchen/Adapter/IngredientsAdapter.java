package com.example.apnakitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnakitchen.Models.ExtendedIngredient;
import com.example.apnakitchen.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class IngredientsAdapter extends  RecyclerView.Adapter<IngredientsViewHolder>{
    Context context;
    List<ExtendedIngredient>list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient>list) {
        this.context = context;
        this.list=list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredients,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.name.setText(list.get(position).name);
        holder.name.setSelected(true);
        holder.quantity.setText(list.get(position).original);
        holder.quantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class IngredientsViewHolder extends RecyclerView.ViewHolder{
    TextView quantity,name;
    ImageView image;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        quantity=itemView.findViewById(R.id.ingrident_quantity);
        name=itemView.findViewById(R.id.ingrident_name);
        image=itemView.findViewById(R.id.ingrident_image);
    }
}
