package com.example.tyler_000.recipeapp;

/**
 * Created by Jean on 10/12/2015.
 */
public class LiquidConvert {
    //gallon (g)  1g = 128 fl oz
    //quart (qt)
    //pint (pt)  1pt = 16 fl oz
    //fluid ounce (fl oz)

/**
 * GALLON
 **/

    /**
     * Gallons to fluid ounce (fl_oz)
     **/
    public double gallons_to_fluidOz(double gallon)
    {
        return gallon * 128;
    }

    /**
     * Gallons to pint (fl_oz)
     **/
    public double gallons_to_pint(double gallon)
    {
        return gallon * 8;
    }

    /**
     * Gallons to quart (fl_oz)
     **/

    public double gallons_to_quart(double gallon)
    {
        return gallon * 4;
    }

/**
 * QUART
 **/

    /**
     * quart (qt) to Gallons
     **/
    public double quart_to_gallon(double quart)
    {
        return (quart * 0.25);
    }

    /**
     * quart (qt) to pint
     **/
    public double quart_to_pint(double quart)
    {
        return (quart * 2);
    }

    /**
     * quart (qt) to fluid Ounces
     **/
    public double quart_to_fluidOz(double quart)
    {
        return (quart * 32);
    }


/**
 * PINT
 **/

    /**
     * pint to Gallons
     **/
    public double pint_to_gallon(double pint )
    {
        return (pint * .125);
    }

    /**
     * pint to quart
     **/
    public double pint_to_quart(double pint )
    {
        return (pint * .5);
    }

    /**
     * pint to fluid Ounces
     **/
    public double pint_to_fluidOz(double pint )
    {
        return (pint * 16);
    }


/**
 * FlUID OUNCE
 **/

    /**
     * fluid ounce (fl_oz) to Gallons
     **/
    public double fluidOunce_to_gallon(double fluidOunce )
    {
        return (fluidOunce * 0.0078125);
    }

    /**
     * fluid ounce (fl_oz) to quart
     **/
    public double fluidOunce_to_quart(double fluidOunce )
    {
        return (fluidOunce * 0.03125);
    }

    /**
     * fluid ounce (fl_oz) to pint
     **/
    public double fluidOunce_to_pint(double fluidOunce )
    {
        return (fluidOunce * 0.0625);
    }

}
