package com.example.tyler_000.recipeapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

import java.util.ArrayList;

public class RecipeSelector extends ListActivity {
    public final static String EXTRA_RECIPE = "com.example.tyler_000.recipeapp.Recipe";
    ArrayList<Recipe> recipeList;
    ArrayList<Recipe> recipeReferenceArray = new ArrayList<Recipe>();
    ArrayList<String> recipeNames = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_selector);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipeNames);
        recipeList = new ArrayList<Recipe>();
        recipeList.add(new Recipe());
        recipeList.add(new Recipe());
        recipeList.add(new Recipe());
        recipeReferenceArray.addAll(recipeList);
        setListAdapter(adapter);
        ListView recipeView = getListView();
        recipeView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(view.getContext(), Step_Activity.class);

                Recipe selectedRecipe = (Recipe) recipeReferenceArray.get(parent.getSelectedItemPosition());
                intent.putExtra(EXTRA_RECIPE, selectedRecipe);
                startActivity(intent);
            }
        });

        for (int i = 0; i < recipeList.size(); i++) {
            recipeList.get(i).setRecipeTitle("Potatoes" + i);
            recipeNames.add(recipeList.get(i).getRecipeTitle());
            adapter.notifyDataSetChanged();

        }


    }
    }
