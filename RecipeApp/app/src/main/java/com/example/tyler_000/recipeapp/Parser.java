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
    static String[] allMeasurements = {"tsp","tbs","cup","oz","ml","L","gal","lbs","lb","fl_oz","qt"};
    static ArrayList<String> actualAllMeasurements = new ArrayList<String>();
    public static Recipe parseRecipe(String fileLocation){
        for(String s: allMeasurements){
            if(!actualAllMeasurements.contains(s)) {
                actualAllMeasurements.add(s);
            }
        }
        String line;
        Recipe recipe= new Recipe();
        JSONObject rec= new JSONObject();
        HashMap<String, String> ingredientsMap= new HashMap<String, String>();
        JSONObject ingredients;
        try{
            BufferedReader read= new BufferedReader(new FileReader(new File(fileLocation)));
            line = read.readLine();
            rec= new JSONObject(line);
            recipe.setRecipeTitle(rec.getJSONObject("recipe").getString("name"));
            boolean check= true;
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
                    j++;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    check = false;
                    System.out.println("Steps stopped");
                }
            }
            recipe.setRecipeSteps(recipeSteps);
            ingredients= rec.getJSONObject("recipe").getJSONObject("Ingredients");
            String ingr="";
            j=1;
            check=true;
            while(check){
                try {
                    ingr = ingredients.getString(Integer.toString(j));
                    String[] ingred=ingr.split(" ");
                    int i=0;
                    String amount="";
                    String food="";
                    for(String s : ingred){
                        //value, measurement, ingredient (1 cup flour/ 1 tablespoon cream or tartar)
                        //value, ingredient (ie 1 egg)
                        if(actualAllMeasurements.contains(s)){
                            for (int k=0;k<=i; k++){
                                amount+=ingred[k]+" ";
                            }
                            break;
                        }
                        i++;
                    }
                    for (int k=i;k<ingred.length; k++){
                        food+=ingred[k]+" ";
                    }
                    ingredientsMap.put(food,amount);
                    j++;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    check = false;
                    System.out.println("Ingredients stopped");
                }
            }
            recipe.setIngredients(ingredientsMap);
            read.close();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Recipe creation failed");
        }

        return recipe;
    }
}
