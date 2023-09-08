package com.example.apnakitchen.Listener;

import com.example.apnakitchen.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipeListener {
    void didFetch(List<SimilarRecipeResponse>response,String message);
    void didError(String message);
}
