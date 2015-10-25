package com.example.tyler_000.recipeapp.gestures;

import android.content.Context;

import com.vuzix.hardware.GestureSensor;

/**
 * Created by Austin on 10/16/2015.
 * This class handles the gesture input, nullifies all gesture inputs.
 */
public class RecipeSelectorGesture extends GestureSensor {

    public RecipeSelectorGesture(Context c) {
        super(c);
    }

    @Override
    protected void onBackSwipe(int i) {
        //do nothing
    }

    @Override
    protected void onForwardSwipe(int i) {
        //do nothing
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
        //do nothing
    }

    @Override
    protected void onFar() {
        //do nothing
    }

}