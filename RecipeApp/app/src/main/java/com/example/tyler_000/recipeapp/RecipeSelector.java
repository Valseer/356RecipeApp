package com.example.tyler_000.recipeapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class RecipeSelector extends ListActivity {
    public final static String EXTRA_RECIPE = "com.example.tyler_000.recipeapp.Recipe";
    ArrayList<Recipe> recipeList;
    ArrayList<String> recipeNames = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selector);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,recipeNames);
        recipeList = new ArrayList<Recipe>();
        recipeList.add(new Recipe());
        recipeList.add(new Recipe());
        recipeList.add(new Recipe());
        setListAdapter(adapter);
        for(int i=0; i < recipeList.size(); i++) {
            recipeList.get(i).setRecipeTitle("Potatoes" + i);
            recipeNames.add(recipeList.get(i).getRecipeTitle());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)  {
        if ( keyCode == KeyEvent.KEYCODE_BUTTON_SELECT ) {
            Intent intent = new Intent(this, Step_Activity.class);
            Recipe selectedRecipe = new Recipe();
            intent.putExtra(EXTRA_RECIPE, selectedRecipe);
            startActivity(intent);
            return true;

        }
        // let the system handle all other key events
        return super.onKeyDown(keyCode, event);
    }
}
