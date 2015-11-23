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
        Step newStep = new Step("potato","potato potato", ingredients = new HashMap<String,String>(),1);
        this.curStep = newStep;
        this.recipeSteps.add(newStep);
    }

    public Recipe(String fileLocation){
        Recipe rec= Parser.parseRecipe(fileLocation);
        this.recipeTitle=rec.getRecipeTitle();
        this.recipeSteps=rec.getRecipeSteps();
        this.ingredients=rec.getIngredients();
        this.curStep=rec.getCurStep();
        System.out.println("Created Recipe: "+ rec.getRecipeTitle());
    }

    public void displayRecipe(){

    }

    public Step startRecipe(){
        return recipeSteps.get(0);
    }

    public Step nextStep(){
        if(curStep.stepNumber!=(recipeSteps.size())){
            curStep=recipeSteps.get(curStep.getStepNumber()+1);

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
            Step newStep = new Step("cur was null bitch","was null",ingredients,1);
            return newStep;
        }
        return curStep;
    }

    public void setCurStep(Step step){
        curStep=step;
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
