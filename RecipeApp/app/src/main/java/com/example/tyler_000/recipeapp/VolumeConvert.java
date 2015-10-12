package com.example.tyler_000.recipeapp;

/**
 * Created by Jean on 10/12/2015.
 */
public class VolumeConvert {
    //cup    1 cup = 16 tbsp
    //tablespoon (tbsp)   1 tbsp = 3 tsp
    //teaspoon (tsp)

/**
 * CUP
 **/

    /**
     * cup to tablespoon
     **/
    public double cup_to_tbsp(double cup )
    {
        return (cup * 16);
    }

    /**
     * cup to teaspoon
     **/

    public double cup_to_tsp(double cup )
    {
        return (cup * 48);
    }


/**
 * TABLESPOON
 */

    /**
     * tablespoon to cup
     **/
    public double tbsp_to_cup(double tbsp )
    {
        return (tbsp * 0.0625);
    }

    /**
     * tablespoon to teaspoon
     **/
    public double tbsp_to_tsp(double tbsp ) {
        return (tbsp * 3);
    }

/**
 * TEASPOON
 */
    /**
     * teaspoon to cup
     **/
    public double tsp_to_cup(double tsp ) {
        return (tsp * 0.0208333);
    }

    /**
     * teaspoon to tablespoon
     **/
    public double tsp_to_tbsp(double tsp ) {
        return (tsp * 0.333333);
    }
}
