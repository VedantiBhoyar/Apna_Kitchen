package com.example.apnakitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.apnakitchen.Adapter.NutriAdapter;
import com.example.apnakitchen.Adapter.PropertyAdapter;
import com.example.apnakitchen.Listener.NutritionListener;
import com.example.apnakitchen.Listener.RecipeDetailListener;
import com.example.apnakitchen.Models.NutritrionResponse;
import com.example.apnakitchen.Models.RecipeDetailResponse;
import com.squareup.picasso.Picasso;


public class NutritionActivity extends AppCompatActivity {
    RequestManager manager;
    ProgressDialog dialog;
    TextView RecipeName;
    ImageView recipeImage;
    int id;
    NutriAdapter adapter;
    PropertyAdapter propertyAdapter;
    RecyclerView Nutrient_recyclerView,property_recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        id=getIntent().getIntExtra("ID",0);
        RecipeName = findViewById(R.id.recipe_name);
        recipeImage = findViewById(R.id.Recipe_Image);
        Nutrient_recyclerView=findViewById(R.id.recycleView_nutri);
        property_recycleView=findViewById(R.id.recycleView_prop);



        dialog = new ProgressDialog(this);
        manager = new RequestManager(NutritionActivity.this);
        manager.getRecipeDetails(recipeDetailListener,id);
        dialog.setTitle("Loading Nutritional Contains....");
        dialog.show();

    }

    private final RecipeDetailListener recipeDetailListener = new RecipeDetailListener() {
        @Override
        public void didFetch(RecipeDetailResponse response, String message) {
            dialog.dismiss();
            RecipeName.setText(response.title);
            Picasso.get().load(response.image).into(recipeImage);

            manager.getNutritionContains(nutritionListener,id);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(NutritionActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private final  NutritionListener nutritionListener=new NutritionListener() {
        @Override
        public void didFetch(NutritrionResponse response, String message) {
            dialog.dismiss();
            Nutrient_recyclerView.setHasFixedSize(true);
            Nutrient_recyclerView.setLayoutManager(new LinearLayoutManager(NutritionActivity.this,LinearLayoutManager.HORIZONTAL,false));
            adapter=new NutriAdapter(NutritionActivity.this,response.nutrients);
            Nutrient_recyclerView.setAdapter(adapter);

            property_recycleView.setHasFixedSize(true);
            property_recycleView.setLayoutManager(new LinearLayoutManager(NutritionActivity.this,LinearLayoutManager.HORIZONTAL,false));
            propertyAdapter=new PropertyAdapter(NutritionActivity.this,response.properties);
            property_recycleView.setAdapter(propertyAdapter);






        }

        @Override
        public void didError(String message) {
            Toast.makeText(NutritionActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };


}