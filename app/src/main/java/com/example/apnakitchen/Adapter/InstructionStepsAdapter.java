package com.example.apnakitchen.Adapter;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnakitchen.Models.Step;
import com.example.apnakitchen.R;

import java.util.List;

public class InstructionStepsAdapter extends RecyclerView.Adapter<InstructionStepsAdapterViewHolder> {
   Context context;
   List<Step>list;

    public InstructionStepsAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepsAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.list_steps,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepsAdapterViewHolder holder, int position) {
       holder.step_count.setText(String.valueOf(list.get(position).number));
       holder.steps.setText(list.get(position).step);

       holder.recycle_instruction_ingredients.setHasFixedSize(true);
       holder.recycle_instruction_ingredients.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
       InstructionIngredientsAdapter instructionIngredientsAdapter=new InstructionIngredientsAdapter(context,list.get(position).ingredients);
       holder.recycle_instruction_ingredients.setAdapter(instructionIngredientsAdapter);

       holder.recycle_instruction_equipment.setHasFixedSize(true);
       holder.recycle_instruction_equipment.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
       InstructionEquipmentAdapter instructionEquipmentAdapter=new InstructionEquipmentAdapter(context,list.get(position).equipment);
       holder.recycle_instruction_equipment.setAdapter(instructionEquipmentAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionStepsAdapterViewHolder extends RecyclerView.ViewHolder{
   TextView steps,step_count;
   RecyclerView recycle_instruction_equipment,recycle_instruction_ingredients;
    public InstructionStepsAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        steps=itemView.findViewById(R.id.steps);
        step_count=itemView.findViewById(R.id.step_count);
        recycle_instruction_equipment=itemView.findViewById(R.id.recycle_instruction_equipment);
        recycle_instruction_ingredients=itemView.findViewById(R.id.recycle_instruction_ingredients);
    }
}
