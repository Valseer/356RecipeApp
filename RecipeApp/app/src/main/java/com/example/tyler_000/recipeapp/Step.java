package com.example.tyler_000.recipeapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * Created by john on 10/12/2015.
 */
public class Step implements Parcelable {
    protected String stepName;
    protected String stepText;
    protected int stepNumber;
    protected HashMap<String,String> stepIngredients;
    public Step(){}

    public Step(String step, String stepText, HashMap<String, String> ingredients){
        this.stepName=step;
        this.stepText=stepText;
        this.stepIngredients=ingredients;
    }

    public HashMap<String, String> getStepIngredients(){
        return stepIngredients;
    }

    public String getStepName() {
        return stepName;
    }

    public String getStepText() {
        return stepText;
    }

    public void setStepIngredients(HashMap<String, String> stepIngredients) {
        this.stepIngredients = stepIngredients;
    }


    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setStepText(String stepText) {
        this.stepText = stepText;
    }

    public void setStepNumber(int stepNumber){
        this.stepNumber=stepNumber;
    }
    public int getStepNumber(){
        return stepNumber;
    }
    protected Step(Parcel in) {
        stepName = in.readString();
        stepText = in.readString();
        stepIngredients = (HashMap) in.readValue(HashMap.class.getClassLoader());
        stepNumber= in .readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stepName);
        dest.writeString(stepText);
        dest.writeValue(stepIngredients);
        dest.writeInt(stepNumber);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}