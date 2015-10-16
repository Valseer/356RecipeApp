package com.example.tyler_000.recipeapp;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class RecipeSelector extends ListActivity {

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
}
