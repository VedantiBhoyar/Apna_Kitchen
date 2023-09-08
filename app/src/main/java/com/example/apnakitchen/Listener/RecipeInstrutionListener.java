package com.example.apnakitchen.Listener;

import com.example.apnakitchen.Models.RecipeInstructionResponse;

import java.util.List;

public interface RecipeInstrutionListener {
    void didFetch(List<RecipeInstructionResponse> response, String message);
    void didError(String message);
}
