package com.example.tyler_000.recipeapp;

import android.os.Bundle;
import android.app.Activity;

public class Step_Activity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
