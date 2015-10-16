package com.example.tyler_000.recipeapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;

public class Step_Activity extends Activity
{
    public final static String EXTRA_RECIPE = "com.example.tyler_000.recipeapp.Recipe";
    Recipe currentRecipe;
    Step currentStep;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle data = getIntent().getExtras();
        currentRecipe = data.getParcelable(EXTRA_RECIPE);
        currentStep = currentRecipe.curStep;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if ( keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ) {
            currentStep = currentRecipe.nextStep();
            changeView();
        }else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            currentStep = currentRecipe.prevStep();
            changeView();
        }
        // let the system handle all other key events
        return super.onKeyDown(keyCode, event);
    }

    private void changeView(){

    }
}
