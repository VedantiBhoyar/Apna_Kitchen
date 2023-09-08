package com.example.apnakitchen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apnakitchen.Models.RecipeInstructionResponse;
import com.example.apnakitchen.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapterViewHolder> {

   Context context;
   List<RecipeInstructionResponse> list;

    public InstructionAdapter(Context context, List<RecipeInstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionAdapterViewHolder holder, int position) {
       holder.textView_instruction.setText(list.get(position).name);
       holder.recycle_instruction_steps.setHasFixedSize(true);
       holder.recycle_instruction_steps.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
       InstructionStepsAdapter instructionStepsAdapter=new InstructionStepsAdapter(context,list.get(position).steps);
       holder.recycle_instruction_steps.setAdapter(instructionStepsAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionAdapterViewHolder extends RecyclerView.ViewHolder{
   TextView textView_instruction;
    RecyclerView recycle_instruction_steps;
    public InstructionAdapterViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_instruction=itemView.findViewById(R.id.textView_instruction);
        recycle_instruction_steps=itemView.findViewById(R.id.recycle_instruction_steps);

    }
}
