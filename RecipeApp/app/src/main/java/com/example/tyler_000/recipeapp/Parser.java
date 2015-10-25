package com.example.tyler_000.recipeapp;
import org.json.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by john on 10/7/2015.
 */
public class Parser {
    public static Recipe parseRecipe(String fileLocation){
        String line;
        Recipe recipe= new Recipe();
        JSONObject rec= new JSONObject();
        try{
            BufferedReader read= new BufferedReader(new FileReader(fileLocation));
            while((line = read.readLine())!=null){
                rec= new JSONObject();
            }
            read.close();
            recipe.setRecipeTitle(rec.getString("name"));

            JSONObject steps= rec.getJSONObject("Steps");
            boolean check= true;
            int i=1;
            ArrayList<Step> recipeSteps= new ArrayList<Step>();
            while(check) {
                JSONObject step;
                try {
                    step = rec.getJSONObject(Integer.toString(i));
                    Step thisStep = new Step();
                    thisStep.setStepText(step.getJSONObject("StepText").toString());
                    if (step.getJSONObject("timer").equals("true")) {
                        //thisStep.setTimer(Integer.parseInt(step.getJSONObject("timerVal").toString()));
                    }
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                    check = false;
                }
            }
            recipe.setRecipeSteps(recipeSteps);
            JSONArray ingredients= rec.getJSONArray("Ingredients");
            String ingr="";
            HashMap<String, String> ingredientsMap= new HashMap<String, String>();
            i=0;
            while(true){
                try {
                    ingr=ingredients.getString(i);
                    ingredientsMap.put(ingr.split(" ")[0], ingr.split(" ")[1]);
                }
                catch(Exception e){
                    break;
                }

            }
            recipe.setIngredients(ingredientsMap);
        }
        catch (Exception e){

        }

        return recipe;
    }
}
