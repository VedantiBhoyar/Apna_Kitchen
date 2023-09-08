package com.example.apnakitchen.Models;

import java.util.ArrayList;

public class Ingredient {
    public int id;
    public String name;
    public String localizedName;
    public String image;

    public double amount;
    public String unit;
    public ArrayList<Nutrient> nutrients;
}
