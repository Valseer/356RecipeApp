package com.example.tyler_000.recipeapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by john on 10/5/2015.
 */



public class Recipe implements Parcelable {
    protected String recipeTitle;
    protected Step firstStep;
    protected HashMap ingredients;
    protected ArrayList<Step> recipeSteps;

    public Recipe(){

    }

    public Recipe(String fileLocation){
        Recipe rec= Parser.parseRecipe(fileLocation);
    }

    public void displayRecipe(){

    }

    public Step startRecipe(){
        return recipeSteps.get(0);
    }


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


    protected Recipe(Parcel in) {
        recipeTitle = in.readString();
        firstStep = (Step) in.readValue(Step.class.getClassLoader());
        ingredients = (HashMap) in.readValue(HashMap.class.getClassLoader());
        if (in.readByte() == 0x01) {
            recipeSteps = new ArrayList<Step>();
            in.readList(recipeSteps, Step.class.getClassLoader());
        } else {
            recipeSteps = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recipeTitle);
        dest.writeValue(firstStep);
        dest.writeValue(ingredients);
        if (recipeSteps == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(recipeSteps);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}