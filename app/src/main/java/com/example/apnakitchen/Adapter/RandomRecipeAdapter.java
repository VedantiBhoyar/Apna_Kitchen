package com.example.apnakitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnakitchen.Listener.RecipeClickListener;
import com.example.apnakitchen.Models.Recipe;
import com.example.apnakitchen.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe>list;
    RecipeClickListener listener;

     public RandomRecipeAdapter(Context context,List<Recipe>list,RecipeClickListener listener){
         this.context=context;
         this.list=list;
         this.listener=listener;
     }
    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
         holder.dishName.setText(list.get(position).title);
         holder.dishName.setSelected(true);
         holder.serving.setText(list.get(position).servings+" Servings");
         holder.like.setText(list.get(position).aggregateLikes+" Likes");
         holder.time.setText(list.get(position).readyInMinutes+" Minutes");
        Picasso.get().load(list.get(position).image).into(holder.foodImage);

        if(list.get(position).vegetarian) {
            holder.veg.setVisibility(View.VISIBLE);
            holder.non_veg.setVisibility(View.GONE);
        }
        else{
            holder.veg.setVisibility(View.GONE);
            holder.non_veg.setVisibility(View.VISIBLE);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
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
class RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView container;
    ImageView foodImage;
    TextView dishName ,serving,like,time,info;
    LinearLayout veg,non_veg;
    public RandomRecipeViewHolder(@NonNull View itemView){
        super(itemView);
        container=itemView.findViewById(R.id.container);
        foodImage=itemView.findViewById(R.id.foodImage);
        dishName=itemView.findViewById(R.id.dishName);
        serving=itemView.findViewById(R.id.serving);
        like=itemView.findViewById(R.id.like);
        time=itemView.findViewById(R.id.time);
        veg=itemView.findViewById(R.id.veg);
        non_veg=itemView.findViewById(R.id.non_veg);


    }
}
