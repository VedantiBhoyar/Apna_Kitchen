package com.example.apnakitchen.Listener;

import com.example.apnakitchen.Models.RecipeDetailResponse;

public interface RecipeDetailListener {
    void didFetch(RecipeDetailResponse response,String message);
    void didError(String message);
}
