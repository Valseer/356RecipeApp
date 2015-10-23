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

public class Step_Activity extends Activity
{
    public final static String EXTRA_RECIPE = "com.example.tyler_000.recipeapp.Recipe";
    Recipe currentRecipe;
    Step currentStep;
    private TextSwitcher stepSwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle data = getIntent().getExtras();
       /*
        currentRecipe =  data.getParcelable(EXTRA_RECIPE);
        currentStep = currentRecipe.getCurStep();
        */
        currentRecipe = new Recipe();
        currentStep = currentRecipe.getCurStep();
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

        Animation in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        // set the animation type of textSwitcher
        stepSwitcher.setInAnimation(in);
        stepSwitcher.setOutAnimation(out);
        changeView();
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


        stepSwitcher.setText(currentStep.getStepText());

        return;

    }
}
