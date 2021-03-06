package com.example.tyler_000.recipeapp;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;

import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.tyler_000.recipeapp.gestures.StepGesture;
import com.vuzix.hardware.GestureSensor;

public class Step_Activity extends Activity
{
    public final static String EXTRA_RECIPE = "com.example.tyler_000.recipeapp.Recipe";
    Recipe currentRecipe;
    Step currentStep;
    private TextSwitcher stepSwitcher;
    private TextSwitcher stepNameSwitcher;
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


        //Assign the current step activity to the current step timer, if necessary.
        if(currentStep.hasTimer()){
            currentStep.setTimerView(this) ;
        }

        stepSwitcher =  (TextSwitcher) findViewById(R.id.stepDescription);
        stepSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            @Override
            public View makeView()
            {
                TextView stepText = new TextView(Step_Activity.this);
                stepText.setTextSize(16);
                return stepText;
            }
        });

        stepNameSwitcher= (TextSwitcher) findViewById(R.id.stepName);
        stepNameSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView stepName= new TextView(Step_Activity.this);
                stepName.setTextSize(20);

                return stepName;
            }
        });

        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        // set the animation type of textSwitcher
        stepSwitcher.setInAnimation(in);
        stepSwitcher.setOutAnimation(out);
        stepNameSwitcher.setInAnimation(in);
        stepNameSwitcher.setOutAnimation(out);
        updateView();

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
        return super.onKeyUp(keyCode, event);
    }

    /**
     * Increment the current step and update the view.
     */
    public void nextStep(){
        //Start the timer automatically when moving to the next step if it has not been started yet.
        if(currentStep.hasTimer() && !currentStep.timerActive){
            currentStep.startTimer() ;
        }
        //Cycle to the next step
        currentStep = currentRecipe.nextStep();
        //If the current view has a timer, pass in the current step_activity
        if(currentStep.hasTimer()) {
            currentStep.setTimerView(this);
        }
        updateView();
    }

    /**
     * Decrement the current step and update the view.
     */
    public void previousStep(){
        currentStep = currentRecipe.prevStep();
        updateView();
    }

    public void updateView(){
        stepSwitcher.setText(currentStep.getStepText());
        stepNameSwitcher.setText(currentStep.getStepName());

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

    // This method is called by the timer when it finishes.
    protected void onFinish(String stepName){
        //This works, I promise.
        Context context = getApplicationContext() ;
        CharSequence text = "The timer for " + stepName + " has finished." ;
        int duration = Toast.LENGTH_LONG ;

        Toast toast = Toast.makeText(context, text, duration) ;
        toast.show() ;
    }


    // Getter for the current step
    protected Step getCurrentStep(){
        return this.currentStep ;
    }

}
