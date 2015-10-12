package com.example.tyler_000.recipeapp;

import java.util.HashMap;

/**
 * Created by john on 10/5/2015.
 */



public class Recipe {
    protected String recipeTitle;
    //protected Step firstStep;
    protected HashMap ingredients;

    public Recipe(){

    }

    public Recipe(String fileLocation){
        Recipe rec= Parser.parseRecipe(fileLocation);
    }

    public void displayRecipe(){

    }

    /*public Step startRecipe(){
        return null;
    }*/

    public String getRecipeTitle(){
        return recipeTitle;
    }
    public void setRecipeTitle(String recipeTitle){
        this.recipeTitle=recipeTitle;
    }

    public HashMap getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap ingredients) {
        this.ingredients = ingredients;
    }

}
