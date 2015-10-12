package com.example.tyler_000.recipeapp;

import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Recipe_Selector extends Activity {
ListView myLayout;
ArrayList<recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe__selector);
        myLayout = (ListView) findViewById(R.id.listView);
        recipeList.add(new recipe());
        recipeList.add(new recipe());
        recipeList.add(new recipe());
        for(int i=0; i < recipeList.size(); i++) {
            Button b = new Button(this);
            b.setHeight(20);
            b.setWidth(myLayout.getWidth());
            b.setText(recipeList.get(i).toString());
            myLayout.addView(b);
        }
    }

}
