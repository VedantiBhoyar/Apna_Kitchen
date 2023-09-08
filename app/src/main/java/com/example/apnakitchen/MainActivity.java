package com.example.apnakitchen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apnakitchen.Adapter.RandomRecipeAdapter;
import com.example.apnakitchen.Listener.RandomRecipeResponseListener;
import com.example.apnakitchen.Listener.RecipeClickListener;
import com.example.apnakitchen.Models.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter adapter;
    RecyclerView recyclerView;
    Spinner spinner;
    SearchView search;
    List<String> tags=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading....");
        search=findViewById(R.id.searchView);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipe(randomRecipeResponseListener,tags);
                dialog.show();
                return  true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        spinner=findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(this,R.array.tags,R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager=new RequestManager(this);
//        manager.getRandomRecipe(randomRecipeResponseListener,tags);
//        dialog.show();
    }
    private final RandomRecipeResponseListener randomRecipeResponseListener=new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView =findViewById(R.id.recycle_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
            adapter=new RandomRecipeAdapter(MainActivity.this,response.recipes,recipeClickListener);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            manager.getRandomRecipe(randomRecipeResponseListener,tags);
            dialog.show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final RecipeClickListener recipeClickListener=new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(MainActivity.this,ReceipeDetailActivity.class).putExtra("id",id));
        }
    };

}