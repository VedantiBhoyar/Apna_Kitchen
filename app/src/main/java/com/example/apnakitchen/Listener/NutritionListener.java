package com.example.apnakitchen.Listener;

import com.example.apnakitchen.Models.NutritrionResponse;
import com.example.apnakitchen.Models.RecipeDetailResponse;

public interface NutritionListener {
    void didFetch(NutritrionResponse response, String message);
    void didError(String message);
}
