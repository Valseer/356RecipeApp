package com.example.tyler_000.recipeapp;

import android.os.CountDownTimer;

/**
 * Created by Austin on 11/6/2015.
 * This is the timer for a recipe step.
 */
public class StepTimer extends CountDownTimer{

    protected long remaining, step ;
    protected Step_Activity step_activity;

    // This constructor is not used, IDE was mad.
    public StepTimer(){
        super(1000,1000) ;
    }

    public StepTimer(long length, long step){
        super(length, step) ;
        this.remaining = length ;
        this.step = step ;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // Update the remaining time
        // NOTE: there is a one second degree of inaccuracy because
        // onTick is only being called every 1000 milliseconds.
        this.remaining = millisUntilFinished ;

        //Update the view, check for null-pointer
        if(this.step_activity != null){
            this.step_activity.updateView();
        }
    }

    @Override
    public void onFinish() {
        //Create notification to user that timer has finished.
    }

    public long getRemaining(){
        return this.remaining ;
    }

    //Used to set the step activity for the timer to callback to the update view method.
    public void setActivity(Object obj){
        if(obj instanceof Step_Activity){
            this.step_activity = (Step_Activity)obj ;
        }
    }

}