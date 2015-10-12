package com.example.tyler_000.recipeapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Recipe_Selector extends ListActivity {
ListView myLayout;
ArrayList<Recipe> recipeList;
    ArrayList<String> recipeNames = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe__selector);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,recipeNames);
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
