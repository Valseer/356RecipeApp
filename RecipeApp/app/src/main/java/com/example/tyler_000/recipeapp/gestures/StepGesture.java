package com.example.tyler_000.recipeapp.gestures;

import android.content.Context;

import com.example.tyler_000.recipeapp.Step_Activity;
import com.vuzix.hardware.GestureSensor;

/**
 * Created by Austin on 10/16/2015.
 * This class handles the gesture input.
 */
public class StepGesture extends GestureSensor {
    Context step_activity ;

    public StepGesture(Context c) {
        super(c);
        step_activity = c ;
    }

    @Override
    protected void onBackSwipe(int i) {
        //do nothing
    }

    @Override
    protected void onForwardSwipe(int i) {
        ((Step_Activity)step_activity).nextStep() ;
    }

    @Override
    protected void onUp(int i) {
        //do nothing
    }

    @Override
    protected void onDown(int i) {
        //do nothing
    }

    @Override
    protected void onNear() {
        ((Step_Activity)step_activity).previousStep();
    }

    @Override
    protected void onFar() {
        //do nothing (for now)
    }

}