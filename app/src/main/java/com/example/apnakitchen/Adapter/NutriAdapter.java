package com.example.apnakitchen.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apnakitchen.Models.Nutrient;
import com.example.apnakitchen.R;

import java.util.List;

public class NutriAdapter  extends  RecyclerView.Adapter<NutriViewHolder>{
    Context context;
    List<Nutrient> list;


    public NutriAdapter(Context context, List<Nutrient> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public NutriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NutriViewHolder(LayoutInflater.from(context).inflate(R.layout.nutri_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NutriViewHolder holder, int position) {
        holder.nutri_name.setText(list.get(position).name);
        holder.nutri_unit.setText("Unit : "+list.get(position).unit);
        holder.nutri_amount.setText("Amount : "+ (int) list.get(position).amount);
        holder.daily_need.setText("Percent Of Daily Needs : "+ (int) list.get(position).percentOfDailyNeeds);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class NutriViewHolder extends RecyclerView.ViewHolder{
   TextView nutri_name,nutri_amount,nutri_unit,daily_need;
    public NutriViewHolder(@NonNull View itemView) {
        super(itemView);
        nutri_name=itemView.findViewById(R.id.nutri_name);
        nutri_amount=itemView.findViewById(R.id.nutri_amount);
        nutri_unit=itemView.findViewById(R.id.nutri_unit);
        daily_need=itemView.findViewById(R.id.nutri_daily_need);
    }
}
