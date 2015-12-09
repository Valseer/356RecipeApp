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
    protected Integer stepNumber;
    protected HashMap<String,String> stepIngredients;
    protected StepTimer timer;
    protected boolean timerActive ;

    public Step(){}

    /**
     * Constructor for a step without a timer.
     */
    public Step(String step, String stepText, HashMap<String, String> ingredients, int stepNumber){
        this.stepName=step;
        this.stepText=stepText;
        this.stepIngredients=ingredients;
        this.stepNumber = stepNumber ;
        this.timer = null ;
    }

    /**
     * Constructor for a step with a timer.
     */
    public Step(String step, String stepText, HashMap<String, String> ingredients, int stepNumber, Long length){
        this.stepName=step;
        this.stepText=stepText;
        this.stepIngredients=ingredients;
        this.stepNumber = stepNumber ;
        this.timer = new StepTimer(length, new Long(1000), this) ;
        this.timerActive = false ;
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

    public void setStepNumber(Integer stepNumber){
        this.stepNumber=stepNumber;
    }

    public int getStepNumber(){ return stepNumber; }

    protected Step(Parcel in) {
        stepName = in.readString();
        stepText = in.readString();
        stepIngredients = (HashMap) in.readValue(HashMap.class.getClassLoader());
        stepNumber= in.readInt();
        Long time = in.readLong() ;
        if(time > 0){
            timer = new StepTimer(time, new Long(1000), this) ;
        }
        else timer = null ;
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
        if(timer != null){
            dest.writeLong(timer.getRemaining());
        }
        else dest.writeLong(0) ;
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

    public boolean hasTimer(){
        return this.timer instanceof StepTimer ;
    }

    public void startTimer() {
        //If the timer is null, you can't do any timer operations
        if(this.hasTimer()){
            System.out.println("Starting timer!") ;
            this.timer.start() ;
            this.timerActive = true ;
        }
    }

    public void stopTimer() {
        //If the timer is null, you can't do any timer operations
        if(this.hasTimer()) {
            // get the remaining time
            Long remaining = timer.getRemaining();
            // cancel the old timer
            this.timer.cancel();
            // create a new timer using the remaining time from the previous timer
            this.timer = new StepTimer(remaining, new Long(1000), this);
            //Timer no longer active.
            this.timerActive = false ;
        }
    }

    /**
     * For abstraction purposes, the Step_Activity is being passed in as a generic
     * object and then re-casted to a Step_Activity type object inside of the timer.
     * Reason: The Step has no need to "know" about the Step_Activity, nor should it.
     */
    public void setTimerView(Object step_activity){
        this.timer.setActivity(step_activity) ;
    }

    public boolean isTimerActive(){
        return this.timerActive ;
    }

}