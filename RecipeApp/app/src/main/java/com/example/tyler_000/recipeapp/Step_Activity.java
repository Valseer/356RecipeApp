package com.example.tyler_000.recipeapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.tyler_000.recipeapp.gestures.StepGesture;
import com.vuzix.hardware.GestureSensor;

public class Step_Activity extends Activity
{
    public final static String EXTRA_RECIPE = "com.example.tyler_000.recipeapp.Recipe";
    Recipe currentRecipe;
    Step currentStep;
    private TextSwitcher stepSwitcher;
    GestureSensor gs ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle data = getIntent().getExtras();

        currentRecipe =  data.getParcelable(EXTRA_RECIPE);
        currentStep = currentRecipe.getCurStep();
        /*
        currentRecipe = new Recipe();
        currentStep = currentRecipe.getCurStep();
        */
        TextView recipeName = (TextView) findViewById(R.id.RecipeName);
        recipeName.setText(currentRecipe.getRecipeTitle());

        TextView stepName = (TextView) findViewById(R.id.stepName);
        stepName.setText(currentStep.getStepName());

        stepSwitcher =  (TextSwitcher) findViewById(R.id.stepDescription);
        stepSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            @Override
            public View makeView()
            {
                TextView stepText = new TextView(Step_Activity.this);
                return stepText;
            }
        });

        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        // set the animation type of textSwitcher
        stepSwitcher.setInAnimation(in);
        stepSwitcher.setOutAnimation(out);
        changeView();

        //Create the gesture sensor
        gs = new StepGesture(this) ;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event)  {

        if ( keyCode == KeyEvent.KEYCODE_DPAD_RIGHT ) {
            nextStep() ;
            return false;
        }else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            previousStep() ;
            return false;
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


        stepSwitcher.setText(currentStep.getStepText());

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
