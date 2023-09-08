package com.example.apnakitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apnakitchen.Adapter.IngredientsAdapter;
import com.example.apnakitchen.Adapter.InstructionAdapter;
import com.example.apnakitchen.Adapter.RandomRecipeAdapter;
import com.example.apnakitchen.Adapter.SimilarRecipeAdapter;
import com.example.apnakitchen.Listener.RecipeClickListener;
import com.example.apnakitchen.Listener.RecipeDetailListener;
import com.example.apnakitchen.Listener.RecipeInstrutionListener;
import com.example.apnakitchen.Listener.SimilarRecipeListener;
import com.example.apnakitchen.Models.RecipeDetailResponse;
import com.example.apnakitchen.Models.RecipeInstructionResponse;
import com.example.apnakitchen.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReceipeDetailActivity extends AppCompatActivity {
    int id;
    TextView recipeName,mealSource,summary;
    ImageView recipeImage;
    RecyclerView recyclerView,recyclerView_similar,recycle_instruction;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    Button nutri_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe_detail);

        findViews();
        id=Integer.parseInt(getIntent().getStringExtra("id"));
        manager=new RequestManager(this);
        manager.getRecipeDetails(recipeDetailListener,id);
        manager.getSimilarRecipe(similarRecipesListener,id);
        manager.getRecipeInstruction(recipeInstrutionListener,id);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading Details....");
        dialog.show();

        nutri_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReceipeDetailActivity.this,NutritionActivity.class).putExtra("ID",id));

            }
        });
    }

    private void findViews() {
        recipeName=findViewById(R.id.ReceipeName);
        mealSource=findViewById(R.id.Meal_Source);
        recipeImage=findViewById(R.id.recipe_image);
        summary=findViewById(R.id.summary);
        recyclerView=findViewById(R.id.recycle_view);
        recyclerView_similar=findViewById(R.id.recycle_similar_recipe);
        recycle_instruction=findViewById(R.id.recycle_instruction);
        nutri_Button=findViewById(R.id.Button);
    }
    private  final RecipeDetailListener recipeDetailListener=new RecipeDetailListener() {
        @Override
        public void didFetch(RecipeDetailResponse response, String message) {
            dialog.dismiss();
            recipeName.setText(response.title);
            mealSource.setText(response.sourceName);
            summary.setText(response.summary);
            Picasso.get().load(response.image).into(recipeImage);


            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(ReceipeDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter=new IngredientsAdapter(ReceipeDetailActivity.this,response.extendedIngredients);
            recyclerView.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(ReceipeDetailActivity.this,message,Toast.LENGTH_SHORT).show();

        }
    };
    private final SimilarRecipeListener similarRecipesListener=new SimilarRecipeListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
            recyclerView_similar.setHasFixedSize(true);
            recyclerView_similar.setLayoutManager(new LinearLayoutManager(ReceipeDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
            similarRecipeAdapter =new SimilarRecipeAdapter(ReceipeDetailActivity.this,response,recipeClickListener);
            recyclerView_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
              Toast.makeText(ReceipeDetailActivity.this,message,Toast.LENGTH_SHORT).show();

        }
    };
    private  final RecipeClickListener recipeClickListener=new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(ReceipeDetailActivity.this,ReceipeDetailActivity.class).putExtra("id",id));
        }
    };
    private final RecipeInstrutionListener recipeInstrutionListener=new RecipeInstrutionListener() {
        @Override
        public void didFetch(List<RecipeInstructionResponse> response, String message) {
            recycle_instruction.setHasFixedSize(true);
            recycle_instruction.setLayoutManager(new LinearLayoutManager(ReceipeDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
            InstructionAdapter instructionAdapter=new InstructionAdapter(ReceipeDetailActivity.this,response);
            recycle_instruction.setAdapter(instructionAdapter);
        }

        @Override
        public void didError(String message) {
             Toast.makeText(ReceipeDetailActivity.this,message,Toast.LENGTH_SHORT);
        }
    };
}