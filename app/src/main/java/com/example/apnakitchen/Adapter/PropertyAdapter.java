package com.example.apnakitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnakitchen.Models.Property;
import com.example.apnakitchen.R;

import java.util.List;

public class PropertyAdapter  extends RecyclerView.Adapter<PropertyAdapterViewHolder>{
    Context context;
    List<Property> list;

    public PropertyAdapter(Context context, List<Property> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PropertyAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PropertyAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.property,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyAdapterViewHolder holder, int position) {
        holder.property_name.setText("Name : "+list.get(position).name);
        holder.property_amount.setText("Amount : "+String.valueOf(list.get(position).amount));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class PropertyAdapterViewHolder extends RecyclerView.ViewHolder{
    TextView property_name,property_amount;
    public PropertyAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        property_name=itemView.findViewById(R.id.propertry_name);
        property_amount=itemView.findViewById(R.id.property_amount);


    }
}
