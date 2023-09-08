package com.example.apnakitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnakitchen.Listener.RecipeClickListener;
import com.example.apnakitchen.Models.SimilarRecipeResponse;
import com.example.apnakitchen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeViewHolder> {
    Context context;
    List<SimilarRecipeResponse>list;
    RecipeClickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.similar_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.similarRecipe_Name.setText(list.get(position).title);
        holder.similarRecipe_Name.setSelected(true);
        holder.similarRecipe_Serving.setText("Servings: "+list.get(position).servings+" Persons");
        holder.similarRecipe_Serving.setSelected(true);
        Picasso.get().load("https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.similarRecipe_Image);

        holder.similarRecipe_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class SimilarRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView similarRecipe_holder;
    TextView similarRecipe_Name,similarRecipe_Serving;
    ImageView similarRecipe_Image;
    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        similarRecipe_holder=itemView.findViewById(R.id.similar_recipe_holder);
        similarRecipe_Name=itemView.findViewById(R.id.similar_title);
        similarRecipe_Serving=itemView.findViewById(R.id.similar_serving);
        similarRecipe_Image=itemView.findViewById(R.id.similar_recipe_image);

    }
}
