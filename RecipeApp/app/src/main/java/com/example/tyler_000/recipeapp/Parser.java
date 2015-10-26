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
        HashMap<String, String> ingredientsMap= new HashMap<String, String>();
        JSONArray ingredients;
        try{
            BufferedReader read= new BufferedReader(new FileReader(new File(fileLocation)));
            line = read.readLine();
            rec= new JSONObject(line);
            recipe.setRecipeTitle(rec.getJSONObject("recipe").getString("name"));
            boolean check= true;
            int i=1;
            ArrayList<Step> recipeSteps= new ArrayList<Step>();
            JSONObject steps= rec.getJSONObject("recipe").getJSONObject("Steps");
            JSONObject step;
            int j=1;
            while(check){
                try {
                    step = steps.getJSONObject(Integer.toString(j));
                    Step thisStep = new Step();
                    thisStep.setStepText(step.getString("StepText"));
                    thisStep.setStepNumber(j);
                    if (step.getString("timer").equals("true")) {
                        //thisStep.setTimer(Integer.parseInt(step.getJSONObject("timerVal").toString()));
                    }
                    recipeSteps.add(thisStep);
                    i++;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    check = false;
                    System.out.println("Steps stopped");
                }
            }
            recipe.setRecipeSteps(recipeSteps);
            ingredients= rec.getJSONArray("Ingredients");
            String ingr="";
            for(i=0; i<ingredients.length(); i++){
                ingr=ingredients.getString(i);
                ingredientsMap.put(ingr.split(" ")[0], ingr.split(" ")[1]);
            }
            recipe.setIngredients(ingredientsMap);
            System.out.println("Ingredients stopped");
            read.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return recipe;
    }
}
