package com.example.apnakitchen;

import android.content.Context;

import com.example.apnakitchen.Listener.NutritionListener;
import com.example.apnakitchen.Listener.RandomRecipeResponseListener;
import com.example.apnakitchen.Listener.RecipeDetailListener;
import com.example.apnakitchen.Listener.RecipeInstrutionListener;
import com.example.apnakitchen.Listener.SimilarRecipeListener;
import com.example.apnakitchen.Models.NutritrionResponse;
import com.example.apnakitchen.Models.RandomRecipeApiResponse;
import com.example.apnakitchen.Models.RecipeDetailResponse;
import com.example.apnakitchen.Models.RecipeInstructionResponse;
import com.example.apnakitchen.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.spoonacular.com/")
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

    public RequestManager(Context context) {
        this.context = context;
    }
    public void getRandomRecipe(RandomRecipeResponseListener listener,List<String>tags){
        CallRandomRecipes callRandomRecipes=retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse>call=callRandomRecipes.CallRandomRecipe(context.getString(R.string.api_key),"100",tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });


    }
    public void getRecipeDetails(RecipeDetailListener listener,int id){
        CallRecipeDetails callRecipeDetails=retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailResponse>call=callRecipeDetails.callRecipeDetails(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });

    }
    public  void getSimilarRecipe(SimilarRecipeListener listener,int id){
        CallSimilarRecipe callSimilarRecipe=retrofit.create(CallSimilarRecipe.class);
        Call<List<SimilarRecipeResponse>>call=callSimilarRecipe.callSimilarRecipe(id,"10",context.getString(R.string.api_key));
        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });
    }
    public void getNutritionContains(NutritionListener listener, int id){
        CallNutritionContains callNutritionContains=retrofit.create(CallNutritionContains.class);
        Call<NutritrionResponse>call=callNutritionContains.callNutritrionContains(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<NutritrionResponse>() {
            @Override
            public void onResponse(Call<NutritrionResponse> call, Response<NutritrionResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<NutritrionResponse> call, Throwable t) {
                listener.didError(t.getMessage());

            }
        });

    }
    public  void getRecipeInstruction(RecipeInstrutionListener listener,int id){
        CallInstruction  callInstruction=retrofit.create(CallInstruction.class);
        Call<List<RecipeInstructionResponse>>call=callInstruction.callInstruction(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<List<RecipeInstructionResponse>>() {
            @Override
            public void onResponse(Call<List<RecipeInstructionResponse>> call, Response<List<RecipeInstructionResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<RecipeInstructionResponse>> call, Throwable t) {
               listener.didError(t.getMessage());
            }
        });
    }
    private interface CallRandomRecipes{
        @GET("recipes/random")
     Call<RandomRecipeApiResponse> CallRandomRecipe(
         @Query("apiKey") String api_key,
         @Query("number") String number,
         @Query("tags") List<String> tags);

    }
    private interface  CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailResponse>callRecipeDetails(
                @Path("id")int id,
                @Query("apiKey") String apiKey
        );
    }
    private  interface  CallSimilarRecipe{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>>callSimilarRecipe(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey

        );
    }
    private  interface  CallNutritionContains{
        @GET("recipes/{id}/nutritionWidget.json")
        Call<NutritrionResponse>callNutritrionContains(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );

    }
    private interface  CallInstruction{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<RecipeInstructionResponse>> callInstruction(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}

