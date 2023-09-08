package com.example.apnakitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnakitchen.Models.Ingredient;
import com.example.apnakitchen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionIngredientsAdapter extends RecyclerView.Adapter<InstructionIngredientAdapterViewHolder> {
    Context context;
    List<Ingredient> list;

    public InstructionIngredientsAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngredientAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_steps_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientAdapterViewHolder holder, int position) {
               holder.textView_instruction_steps_item.setText(list.get(position).name);
               holder.textView_instruction_steps_item.setSelected(true);
               Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageView_instruction_step);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionIngredientAdapterViewHolder extends RecyclerView.ViewHolder{
    TextView textView_instruction_steps_item;
    ImageView imageView_instruction_step;

    public InstructionIngredientAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_instruction_steps_item=itemView.findViewById(R.id.textView_instruction_steps_item);
        imageView_instruction_step=itemView.findViewById(R.id.imageView_instruction_step);
    }
}
