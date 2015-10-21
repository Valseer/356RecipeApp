package com.example.tyler_000.recipeapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.example.tyler_000.recipeapp.gestures.StepGesture;
import com.vuzix.hardware.GestureSensor;

public class Step_Activity extends Activity
{
    public final static String EXTRA_RECIPE = "com.example.tyler_000.recipeapp.Recipe";
    Recipe currentRecipe;
    Step currentStep;
    GestureSensor gs ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle data = getIntent().getExtras();
        currentRecipe = data.getParcelable(EXTRA_RECIPE);
        currentStep = currentRecipe.curStep;
        TextSwitcher step = (TextSwitcher) findViewById(R.id.stepDescription);
        changeView();

        //Create the gesture sensor
        gs = new StepGesture(this) ;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if ( keyCode == KeyEvent.KEYCODE_VOLUME_DOWN ) {
            nextStep() ;
        }else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            previousStep() ;
        }
        // let the system handle all other key events
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Increment the current step and update the view.
     */
    public void nextStep(){
        System.out.println("Called next step!") ;
        currentStep = currentRecipe.nextStep();
        changeView();
    }

    /**
     * Decrement the current step and update the view.
     */
    public void previousStep(){
        System.out.println("Called prev step!") ;
        currentStep = currentRecipe.prevStep();
        changeView();
    }

    private void changeView(){

        TextView stepName = (TextView) findViewById(R.id.stepName);
        stepName.setText((CharSequence) currentStep.getStepName());

        TextSwitcher step = (TextSwitcher) findViewById(R.id.stepDescription);
        TextView stepDescription = new TextView(this);
        step.addView(stepDescription,-1);
        step.setText((CharSequence) currentStep.stepText);

        return;

    }

    @Override
    /**
     * This will re-register the gesture sensor once it returns to this activity.
     */
    protected void onResume(){
        super.onResume();
        gs.register() ;
    }

    @Override
    /**
     * This will un-register the gesture sensor when the activity is changed.
     */
    protected void onPause(){
        super.onPause();
        gs.unregister();
    }

}
