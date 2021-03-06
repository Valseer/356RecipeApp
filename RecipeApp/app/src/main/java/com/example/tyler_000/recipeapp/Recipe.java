package com.example.tyler_000.recipeapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by john on 10/5/2015.
 */



public class Recipe implements Parcelable {
    protected String recipeTitle;
    protected Step curStep;
    protected HashMap ingredients;
    protected ArrayList<Step> recipeSteps = new ArrayList<Step>();

    public Recipe(){

        this.recipeTitle = "default title";
        Step newStep = new Step("Step 1","This is a step with a 5-second timer.", ingredients = new HashMap<String,String>(), 0, new Long(5000));
        this.curStep = newStep;
        this.recipeSteps.add(newStep);
        Step secondStep = new Step("Step 2", "This is the second step, no timer.", ingredients = new HashMap<String,String>(), 1) ;
        this.recipeSteps.add(secondStep) ;
        Step thirdStep = new Step("Step 3","This is the third step with a 5-second timer.", ingredients = new HashMap<String,String>(), 2, new Long(5000));
        this.recipeSteps.add(thirdStep) ;

    }

    public Recipe(String fileLocation){
        Recipe recipe= Parser.parseRecipe(fileLocation);
        this.setCurStep(recipe.getCurStep());
        this.setIngredients(recipe.getIngredients());
        this.setRecipeSteps(recipe.getRecipeSteps());
        this.setRecipeTitle(recipe.getRecipeTitle());
    }

    public void displayRecipe(){

    }

    public Step startRecipe(){
        return recipeSteps.get(0);
    }
    public void setCurStep(Step curStep){this.curStep=curStep;}
    public Step nextStep(){
        if(curStep.getStepNumber()<(recipeSteps.size())){
            curStep=recipeSteps.get(curStep.getStepNumber());
        }
        return curStep;
    }

    public Step prevStep(){
        if(curStep.stepNumber!=1){
            curStep=recipeSteps.get(curStep.getStepNumber()-2);
        }
        return curStep;
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

    public Step getCurStep(){
        if(curStep == null){
            Step newStep = new Step("cur was null bitch","was null",ingredients, 0);
            return newStep;
        }
        return curStep;
    }

    public ArrayList<Step> getRecipeSteps() {
        return recipeSteps;
    }
    public void setRecipeSteps(ArrayList<Step> recipeSteps){
        this.recipeSteps=recipeSteps;
    }
    
    protected Recipe(Parcel in) {
        recipeTitle = in.readString();
        curStep = (Step) in.readValue(Step.class.getClassLoader());
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
        dest.writeValue(curStep);
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
