package com.example.tyler_000.recipeapp;

import android.content.Context;

import com.vuzix.hardware.GestureSensor;

/**
 * Created by Austin on 10/16/2015.
 * This class handles the gesture input.
 */
public class Gestures extends GestureSensor {

    public Gestures(Context c){
        super(c) ;
    }

    protected void doSomething(String str){
        System.out.println(str + " sensed!") ;
    }

    @Override
    protected void onBackSwipe(int i) {
        doSomething("Back swipe") ;
    }

    @Override
    protected void onForwardSwipe(int i) {
        doSomething("Forward swipe") ;
    }

    @Override
    protected void onUp(int i) {
        doSomething("Up") ;
    }

    @Override
    protected void onDown(int i) {
        doSomething("Down") ;
    }

    @Override
    protected void onNear() {
        doSomething("Near") ;
    }

    @Override
    protected void onFar() {
        doSomething("Far") ;
    }

}