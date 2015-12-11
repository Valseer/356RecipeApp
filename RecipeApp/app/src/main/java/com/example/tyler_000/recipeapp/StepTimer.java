package com.example.tyler_000.recipeapp;

import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Austin on 11/6/2015.
 * This is the timer for a recipe step.
 */
public class StepTimer extends CountDownTimer{

    protected Long remaining, interval ;
    protected Step_Activity step_activity;
    protected boolean active ;
    protected Step step ;

    // This constructor is not used, IDE was mad.
    public StepTimer(){
        super(1000,1000) ;
    }

    public StepTimer(Long length, Long interval, Step step){
        super(length, interval) ;
        this.remaining = length ;
        this.interval = interval ;
        this.step = step ;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // Update the remaining time
        // NOTE: there is a one second degree of inaccuracy because
        // onTick is only being called every 1000 milliseconds.

        //There is a certain degree of error with the millisUntilFinished, it will not be in exactly 1000 intervals.
        if(millisUntilFinished % 1000 != 0){
            this.remaining = (((Long)(millisUntilFinished/1000)) + 1) ;
        } else{ this.remaining = millisUntilFinished ; }
        this.remaining = millisUntilFinished ;

        //Update the view, check for null-pointer
        if(this.step_activity != null && this.step_activity.getCurrentStep() == this.step){
            System.out.println("Timer is on current step.") ;
            //this.step_activity.updateView();
        }
    }

    @Override
    public void onFinish() {
        //Create notification to user that timer has finished.
        if(this.step_activity != null){
            String stepName = this.step.getStepName() ;
            this.step_activity.onFinish(stepName) ;
        }
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

    public void setActive(boolean active){
        this.active = active ;
    }

}