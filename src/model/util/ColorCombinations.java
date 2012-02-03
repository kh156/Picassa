package model.util;



import java.util.*;

import model.RGBColor;


/**
 * Combine two colors by combining their components.
 * 
 * This is a separate class from color since it is just one set of
 * ways to combine colors, many may exist and we do not want to keep
 * modifying the RGBColor class.
 * 
 * @author Robert C. Duvall
 */
public class ColorCombinations
{
    /**
     * Combine two colors by adding their components.
     */
    public static RGBColor add (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() + right.getRed(), 
                left.getGreen() + right.getGreen(),
                left.getBlue() + right.getBlue());
    }

    /**
     * Combine two colors by subtracting their components.
     */
    public static RGBColor subtract (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() - right.getRed(), 
                left.getGreen() - right.getGreen(),
                left.getBlue() - right.getBlue());
    }

    /**
     * Combine two colors by multiplying their components.
     */
    public static RGBColor multiply (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() * right.getRed(), 
                left.getGreen() * right.getGreen(),
                left.getBlue() * right.getBlue());
    }

    /**
     * Combine two colors by dividing their components.
     */
    public static RGBColor divide (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() / right.getRed(), 
                left.getGreen() / right.getGreen(),
                left.getBlue() / right.getBlue());
    }

    /**
     * Combine two colors by taking modulus of their components.
     */
    public static RGBColor modulus (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() % right.getRed(), 
                left.getGreen() % right.getGreen(),
                left.getBlue() % right.getBlue());
    }

    /**
     * Combine two colors by taking powers of their components.
     */
    public static RGBColor exponent (RGBColor left, RGBColor right)
    {
        return new RGBColor(Math.pow(left.getRed(), right.getRed()), 
                Math.pow(left.getGreen(), right.getGreen()),
                Math.pow(left.getBlue(), right.getBlue()));
    }

    /**
     * Invert a color by negating its components.
     */
    public static RGBColor negate (RGBColor color)
    {
        return new RGBColor(color.getRed() * -1,
                color.getGreen() * -1, 
                color.getBlue() * -1);
    }

    /**
     * User-define the three RGB parameters.
     */
    public static RGBColor color (RGBColor red, RGBColor green, RGBColor blue)
    {
        return new RGBColor(red.getRed(), green.getGreen(), blue.getBlue());
    }

    public static RGBColor random () {
        return new RGBColor(Math.random() * 2.0 - 1.0, Math.random() * 2.0 - 1.0, Math.random() * 2.0 - 1.0);
    }

    public static RGBColor floor (RGBColor color) {
        return new RGBColor(Math.floor(color.getRed()), Math.floor(color.getGreen()), Math.floor(color.getBlue()));
    }

    public static RGBColor ceil (RGBColor color) {
        return new RGBColor(Math.ceil(color.getRed()), Math.ceil(color.getGreen()), Math.ceil(color.getBlue()));
    }

    public static RGBColor abs (RGBColor color) {
        return new RGBColor(Math.abs(color.getRed()), Math.abs(color.getGreen()), Math.abs(color.getBlue()));
    }

    public static RGBColor clamp (RGBColor color) {
        RGBColor newColor = new RGBColor(color);
        newColor.clamp();
        return newColor;
    }

    public static RGBColor wrap (RGBColor color) {
        RGBColor newColor = new RGBColor(color);
        newColor.wrap();
        return newColor;
    }
    
    public static RGBColor sin (RGBColor color) {
        return new RGBColor(Math.sin(color.getRed()), Math.sin(color.getGreen()), Math.sin(color.getBlue()));
    }
    
    public static RGBColor cos (RGBColor color) {
        return new RGBColor(Math.cos(color.getRed()), Math.cos(color.getGreen()), Math.cos(color.getBlue()));
    }
    
    public static RGBColor tan (RGBColor color) {
        return new RGBColor(Math.tan(color.getRed()), Math.tan(color.getGreen()), Math.tan(color.getBlue()));
    }

    public static RGBColor atan (RGBColor color) {
        return new RGBColor(Math.atan(color.getRed()), Math.atan(color.getGreen()), Math.atan(color.getBlue()));
    }
    
    public static RGBColor log (RGBColor color) {
        return new RGBColor(Math.log(color.getRed()), Math.log(color.getGreen()), Math.log(color.getBlue()));
    }
    
    public static RGBColor rgbToYCrCb (RGBColor color) {
        return ColorModel.rgb2ycrcb(color);
    }
    
    public static RGBColor yCrCbtoRGB (RGBColor color) {
        return ColorModel.ycrcb2rgb(color);
    }
    
    public static RGBColor perlinColor (RGBColor left, RGBColor right) {
        return PerlinNoise.colorNoise(left, right);
    }
    
    public static RGBColor perlinBW (RGBColor left, RGBColor right) {
        return PerlinNoise.greyNoise(left, right);
    }
    
    public static RGBColor sum (List<RGBColor> list) {
        double red = 0; 
        double green = 0;
        double blue = 0;
        for (RGBColor entry:list) {
            red += entry.getRed();
            green += entry.getGreen();
            blue += entry.getBlue();
        }
        return new RGBColor(red, green, blue);
    }
    
    public static RGBColor product(List<RGBColor> list) {
        double red = 1; 
        double green = 1;
        double blue = 1;
        for (RGBColor entry:list) {
            red *= entry.getRed();
            green *= entry.getGreen();
            blue *= entry.getBlue();
        }
        return new RGBColor(red, green, blue);
    }
    
    public static RGBColor average(List<RGBColor> list) {
        RGBColor sum = sum(list); 
        return new RGBColor(sum.getRed()/list.size(), sum.getGreen()/list.size(), sum.getBlue()/list.size());
    }
    
    public static RGBColor min(List<RGBColor> list) {
        double red = Double.MAX_VALUE; 
        double green = Double.MAX_VALUE;
        double blue = Double.MAX_VALUE;
        for (RGBColor entry:list) {
            red = (entry.getRed() < red) ? entry.getRed() : red;
            green = (entry.getGreen() < green) ? entry.getGreen() : green;
            blue = (entry.getBlue() < blue) ? entry.getBlue() : blue;
        }
        return new RGBColor(red, green, blue);
    }

    public static RGBColor max(List<RGBColor> list) {
        double red = -Double.MAX_VALUE; 
        double green = -Double.MAX_VALUE;
        double blue = -Double.MAX_VALUE;
        for (RGBColor entry:list) {
            red = (entry.getRed() > red) ? entry.getRed() : red;
            green = (entry.getGreen() > green) ? entry.getGreen() : green;
            blue = (entry.getBlue() > blue) ? entry.getBlue() : blue;
        }
        return new RGBColor(red, green, blue);
    }
}
